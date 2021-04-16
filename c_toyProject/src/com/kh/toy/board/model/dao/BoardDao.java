package com.kh.toy.board.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.toy.board.model.vo.Board;
import com.kh.toy.common.code.ErrorCode;
import com.kh.toy.common.exception.DataAccessException;
import com.kh.toy.common.template.JDBCTemplate;
import com.kh.toy.common.util.file.FileVo;

public class BoardDao {

	JDBCTemplate jdt = JDBCTemplate.getInstance();
	//게시판 테이블에 게시글 저장
	public void insertBoard(Connection conn, Board board) {
		String sql = "insert into tb_board "
				+ "(bd_idx,user_id,title,content) "
				+ "values('b'||sc_board_idx.nextval,?,?,?)";
		PreparedStatement pstm = null;
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, board.getUserId());
			pstm.setString(2, board.getTitle());
			pstm.setString(3, board.getContent());
			pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.DATABASE_ACCESS_ERROR, e);
		}finally {
			jdt.close(pstm);
		}
	}
	
	//파일테이블에 파일메타정보 저장
	public void insertFile(Connection conn, FileVo fileVo) {
		String typeIdx = "";
		//1. 새로 등록되는 게시글의 파일정보를 저장
		// typeIdx의 값이 sequence의 currval
		if(fileVo.getTypeIdx() == null) {
			typeIdx = "'b'||sc_board_idx.currval";
		}else {
			typeIdx = "'" + fileVo.getTypeIdx() + "'"; 
		}
		
		String sql = "insert into tb_file "
				+ "(f_idx,type_idx,origin_file_name,rename_file_name,save_path) "
				+ "values(sc_file_idx.nextval,"+typeIdx+",?,?,?)";
		
		PreparedStatement pstm = null;
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1,fileVo.getOriginFileName());
			pstm.setString(2,fileVo.getRenameFileName());
			pstm.setString(3,fileVo.getSavePath());
			pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.DATABASE_ACCESS_ERROR, e);
		}finally {
			jdt.close(pstm);
		}
	}
	
	//게시글 상세
	public Board selectBoardDetail(Connection conn, String bdIdx) {
		Board board = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "select "
				+ "bd_idx,user_id,reg_date,title,content "
				+ "from tb_board "
				+ "where bd_idx = ? ";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, bdIdx);
			rs = pstm.executeQuery();
			
			if(rs.next()) {
				board = new Board();
				board.setBdIdx(rs.getString(1));
				board.setUserId(rs.getString(2));
				board.setRegDate(rs.getDate(3));
				board.setTitle(rs.getString(4));
				board.setContent(rs.getString(5));
			}
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.DATABASE_ACCESS_ERROR, e);
		}finally {
			jdt.close(rs, pstm);
		}
		
		return board;
	}
	
	//게시글 파일 정보
	public List<FileVo> selectFileWithBoard(Connection conn, String bdIdx){
		List<FileVo> res = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql ="select "
				+ "f_idx,type_idx,origin_file_name,rename_file_name,save_path,reg_date,is_del "
				+ "from tb_file "
				+ "where type_idx = ?";
		
		try {
			res = new ArrayList<FileVo>();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, bdIdx);
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				FileVo fileVo = new FileVo();
				fileVo.setfIdx(rs.getInt(1));
				fileVo.setTypeIdx(rs.getString(2));
				fileVo.setOriginFileName(rs.getString(3));
				fileVo.setRenameFileName(rs.getString(4));
				fileVo.setSavePath(rs.getString(5));
				fileVo.setRegDate(rs.getDate(6));
				fileVo.setIsDel(rs.getInt(7));
				res.add(fileVo);
			}
		} catch (SQLException e) {
			throw new DataAccessException(ErrorCode.DATABASE_ACCESS_ERROR,e);
		}finally {
			jdt.close(rs,pstm);
		}
		
		return res;
	}
	
	
	
	
	
	
	
	
	
	
	
}
