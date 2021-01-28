package com.kh.toy.member.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.toy.common.code.Code;
import com.kh.toy.common.code.ErrorCode;
import com.kh.toy.common.exception.ToAlertException;
import com.kh.toy.common.mail.MailSender;
import com.kh.toy.common.util.http.HttpUtil;
import com.kh.toy.member.model.service.MemberService;
import com.kh.toy.member.model.vo.Member;


/**
 * Servlet implementation class MemberController
 */
@WebServlet("/member/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MemberService memberService = new MemberService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String[] uriArr = request.getRequestURI().split("/");
		switch(uriArr[uriArr.length-1]) {
			case "join" : join(request,response);
				break;
			case "idcheck" : confirmId(request,response); 
				break;
			case "mailauth" : authenticateEmail(request,response); 
				break;
			case "joinimpl" : joinImpl(request,response);
				break;
			case "login" : login(request,response);
				break;
			case "loginimpl" : loginImpl(request,response); 
				break;
			case "logout" : logout(request,response); 
				break;
			case "mypage" : myPage(request,response); 
				break;
			default : response.setStatus(404);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void join(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/view/member/join.jsp")
		.forward(request, response);
	}
	
	private void confirmId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		Member member = memberService.selectMemberById(userId);
		
		//아이디가 있다.
		if(member != null) {
			//응답 body에 fail 작성
			response.getWriter().print("fail");
		//아이디가 없다.
		}else {
			//응답 body에 success 작성
			response.getWriter().print("success");
		}
	}
	
	private void authenticateEmail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("id");
		String password = request.getParameter("pw");
		String tell = request.getParameter("tell");
		String email = request.getParameter("email");
		
		Member member = new Member();
		member.setUserId(userId);
		member.setPassword(password);
		member.setTell(tell);
		member.setEmail(email);
		
		memberService.authenticateEmail(member);
		
		request.getSession().setAttribute("persistUser", member);
		request.setAttribute("msg", "회원가입 완료를 위한 이메일이 발송되었습니다.");
		request.setAttribute("url", "/index");
		request.getRequestDispatcher("/WEB-INF/view/common/result.jsp")
		.forward(request, response);
	}
	
	private void joinImpl(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Member member = (Member)request.getSession().getAttribute("persistUser");
		memberService.insertMember(member);
		
		//회원가입 정보를 삭제
		request.getSession().removeAttribute("persistUser");
		
		request.setAttribute("msg", "회원가입을 축하드립니다.");
		request.setAttribute("url", "/member/login");
		request.getRequestDispatcher("/WEB-INF/view/common/result.jsp")
		.forward(request, response);
	}
	
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/member/login.jsp")
		.forward(request, response);
	}
	
	private void loginImpl(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jsonData = request.getParameter("data");
		Gson gson = new Gson();
		
		Map<String,Object> jsonMap = gson.fromJson(jsonData, Map.class);
		String userId = (String)jsonMap.get("id");
		String password = (String)jsonMap.get("pw");
		
		Member member = memberService.memberAuthenticate(userId, password);
		
		if(member != null) {
			request.getSession().setAttribute("user", member);
			response.getWriter().print("success");
		}else {
			response.getWriter().print("fail");
		}
	}
	
	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getSession().removeAttribute("user");
		response.sendRedirect("/index");
	}
	
	private void myPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/view/member/mypage.jsp")
		.forward(request, response);
	}
	
	
	
}
