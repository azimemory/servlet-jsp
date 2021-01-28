package com.kh.toy.member.model.service;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kh.toy.common.code.Code;
import com.kh.toy.common.exception.DataAccessException;
import com.kh.toy.common.exception.ToAlertException;
import com.kh.toy.common.mail.MailSender;
import com.kh.toy.common.template.JDBCTemplate;
import com.kh.toy.common.util.http.HttpUtil;
import com.kh.toy.member.model.dao.MemberDao;
import com.kh.toy.member.model.vo.Member;

//Service
//웹어플리케이션의 비지니스 로직 작성
//사용자가 전송한 데이터를 Controller에게 전달 받고
//비지니스 로직을 위해 추가적으로 필요한 데이터를 Dao에게 요청해
//핵심로직인 비지니스로직을 작성한다.
//비지니스 로직을 Service가 담당하기 때문에 transaction관리도 Service가 담당.
public class MemberService {
	
	MemberDao memberDao = new MemberDao();
	JDBCTemplate jdt = JDBCTemplate.getInstance();
	
	public Member memberAuthenticate(String userId, String password){	
		Connection conn = jdt.getConnection();
		//Dao에게 userId와 password로 식별할 수 있는 회원정보를 DB에서 조회할 것을 요청
		Member member = memberDao.memberAuthenticate(conn, userId, password);
		jdt.close(conn);
		return member;
	}
	
	public Member selectMemberById(String userId){	
		Connection conn = jdt.getConnection();
		Member member = memberDao.selectMemberById(conn, userId);
		jdt.close(conn);
		return member;
	}
	
	public List<Member> selectMemberByRegdate(Date begin, Date end){
		Connection conn = jdt.getConnection();
		List<Member> memberList = memberDao.selectMemberByRegdate(conn, begin, end);
		jdt.close(conn);
		return memberList;
	}
 		
	public ArrayList<Member> selectMemberList(){
		Connection conn = jdt.getConnection();
		ArrayList<Member> memberList = memberDao.selectMemberList(conn);
		jdt.close(conn);
		return memberList;
	}
	
	public void authenticateEmail(Member member) {
		//POST방식으로 통신해보기
		String subject = "회원가입을 완료해주세요!";
		String htmlText = "";
		
		HttpUtil http = new HttpUtil();
		String url = Code.DOMAIN+"/mail";
		
		//header 저장
		Map<String,String> headers = new HashMap<>();
		headers.put("content-type", "application/x-www-form-urlencoded; charset=utf-8");
		
		//parameter 저장
		Map<String,String> params = new HashMap<>();
		params.put("mailTemplate", "temp_join");
		params.put("userId", member.getUserId());
		
		htmlText = http.post(url, headers, http.urlEncodedForm(params));
	    new MailSender().sendEmail(member.getEmail(),subject,htmlText);
	}
	
	public int insertMember(Member member){
		//Transaction관리를 Service단에서 처리하기 위해 Connection을 
		//Service의 메서드에서 생성
		Connection conn = jdt.getConnection();
		int res = 0;
		
		try {
			//Dao의 메서드로 생성한 Connection을 주입
			 res = memberDao.insertMember(conn, member);
			//Dao에서 아무 문제없이 실행이 끝난다면 commit;
			jdt.commit(conn);
		}catch(DataAccessException e) {
			//Dao에서 SQLException이 발생할 경우 rollback처리
			jdt.rollback(conn);
			throw new ToAlertException(e.error);
		}finally {
			jdt.close(conn);
		}
		
		return res;
	}
	
	public int updateMember(Member member){
		Connection conn = jdt.getConnection();
		int res = 0;
		
		try {
			res = memberDao.updateMember(conn, member);
			jdt.commit(conn);
		}catch(DataAccessException e) {
			jdt.rollback(conn);
			throw new ToAlertException(e.error);
		}finally {
			jdt.close(conn);
		}
		
		return res;
	}
	
	public int deleteMember(String userId){
		Connection conn = jdt.getConnection();
		
		int res = 0;
		try {
			res = memberDao.deleteMember(conn, userId);
			jdt.commit(conn);
		} catch (DataAccessException e) {
			jdt.rollback(conn);
			throw new ToAlertException(e.error);
		}finally {
			jdt.close(conn);
		}
		return res;
	}
}
