package com.kh.toy.common.util.file;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.kh.toy.common.code.Code;
import com.kh.toy.common.util.encoding.EncodingUtil;
import com.oreilly.servlet.multipart.FilePart;
import com.oreilly.servlet.multipart.MultipartParser;
import com.oreilly.servlet.multipart.ParamPart;
import com.oreilly.servlet.multipart.Part;

public class FileUtil {
	
	//1. 유니크한 파일이름을 생성해서 저장 (renameFileName 생성)
	//2. 저장경로를 웹어플리케이션 외부로 지정
	//		저장경로 + /연/월/일/ 폴더에 파일을 저장
	//3. 파라미터 데이터와 파일메타정보를 Map에 저장해 반환
	//4. 파일삭제 메서드
	private final int MAXSIZE = 1024 * 1024 * 10;
	Map<String,List> multiPartMap = new HashMap<String,List>();
	
	public Map<String,List> fileUpload(HttpServletRequest request) {
		List<FileVo> fileList = new ArrayList<FileVo>();
		try {
			MultipartParser mp = new MultipartParser(request, MAXSIZE);
			Part part = null;
			
			while((part = mp.readNextPart()) != null) {
				if(part.isFile()) {
					FilePart userFile = (FilePart)part;
					//사용자가 파일을 올리지 않으면 FilePart가 생성되지 않는 것이 아니라
					//빈 FilePart객체가 넘어온다. 사용자가 파일을 첨부하지 않으면 getFileName()이 null을 반환한다.
					if(userFile.getFileName() != null) {
						FileVo fileVo = getFileData(userFile);
						fileList.add(fileVo);
						//파일을 저장
						saveFile(userFile, fileVo);
					}
				}else if(part.isParam()){
					//parmPart에 저장되어있는 파라미터값을 받아서
					//key : value 형태로 저장, value는 list 타입
					ParamPart params = (ParamPart) part;
					List paramList = getParamValue(params);
					multiPartMap.put(params.getName(), paramList);
				}
			}
			
			multiPartMap.put("fileData", fileList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return multiPartMap;
	}
	
	public void deleteFile(String path) {
		File file = new File(Code.UPLOAD + path);
		file.delete();
	}
	
	private String getRenameFileName(String originFileName) {
		//확장자 추출
		String extention = originFileName.substring(originFileName.lastIndexOf("."));
		UUID renameFileId = UUID.randomUUID();
		String renameFileName = renameFileId + extention;	
		return renameFileName;
	}
	
	private String getSubPath() {
		//파일을 저장할 경로 생성
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH)+1;
		int date = cal.get(Calendar.DAY_OF_MONTH);
		return year + "/" + month + "/" + date + "/";
	}
	
	private void saveFile(FilePart userFile,FileVo fileVo) throws IOException {
		new File(Code.UPLOAD+fileVo.getSavePath()).mkdirs();
		//파일을 저장
		File file = new File(Code.UPLOAD+fileVo.getSavePath()+fileVo.getRenameFileName());
		userFile.writeTo(file);
	}
	
	private List<String> getParamValue(ParamPart params) throws UnsupportedEncodingException{
		String paramName = params.getName();
		String paramValue = params.getStringValue("UTF-8"); //파라미터값을 utf-8로 인코딩하여 반환
		List paramList = null;
		
		//이전에 같은 이름으로 저장된 파라미터가 없다면 
		if(multiPartMap.get(paramName) == null) {
			paramList = new ArrayList<>();
		}else {
			paramList = multiPartMap.get(paramName);
		}
		paramList.add(paramValue);
		return paramList;
	}
	
	private FileVo getFileData(FilePart userFile) throws UnsupportedEncodingException {
		//원본파일명
		String originFileName = new String(userFile.getFileName().getBytes("iso-8859-1"),"utf-8");
		//유니크한 파일이름
		String renameFileName = getRenameFileName(originFileName);				
		//저장할 경로를 생성
		String subPath = getSubPath();
		
		//fileVo에 파일 메타정보를 저장
		FileVo fileVo = new FileVo();
		fileVo.setOriginFileName(originFileName);
		fileVo.setRenameFileName(renameFileName);
		fileVo.setSavePath(subPath);
		return fileVo;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
