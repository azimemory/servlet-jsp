package com.kh.servlet.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class F_Cookie
 */
@WebServlet("/cookie")
public class F_Cookie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public F_Cookie() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//사용자가 검색한 프로그래밍 언어를 변수에 저장
		String search = request.getParameter("search");
		
		//이전 검색어를 미리 초기화
		String prevSearch = "최근 검색어가 없습니다.";
		
		//사용자가 검색한 프로그래밍 언어를 쿠키에 저장
		//set-cookie : 응답 헤더에 쿠키를 추가,  key=value 형태
		//같은 키에 값을 담으면 덮어써진다.
		
		//Max-Age : 쿠키의 수명을 초단위 지정
		//	max-age=20 : 응답 이후 20초 동안만 쿠키가 살아있다.
		//				기본값 : session (브라우저가 종료되면 쿠키도 같이 삭제)
		//Path : 어떤 URL로 클라이언트가 요청을 할 때 해당 쿠키를 헤더에 담아서 보내야 할 지 지정
		// path=/servlet/cache : 브라우저가 /servlet/cache로 요청할 때 헤더에 cookie를 담아서 보낸다.
		response.setHeader("set-cookie", "prevSearch="+search);
		
		//다시 클라이언트가 요청했을 때 요청헤더에 담겨온 prevSearch 쿠키를 확인
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie ck : cookies) {
				System.out.println(ck.getValue());
				
				//prevSearch 라는 이름의 쿠키가 있는 지 탐색
				if(ck.getName().equals("prevSearch")) {
					//만약 존재한다면 이전 검색어에 쿠키값을 담아준다.
					prevSearch =  ck.getValue();
				}
			}
		}
		
		PrintWriter pw = response.getWriter();
		
		//////////////////Session Test///////////////////
		HttpSession session = request.getSession();
		pw.println("<h1>" + session.getAttribute("nick") + "님 ");
		pw.println("<a href='/servlet/session/logout'>logout</a></h1>");
		//////////////////Session Test///////////////////
		
		pw.println("<h1>검색결과</h1>");
		pw.println("<h3 style='color:red'>"+
				"이전 검색어 : " + prevSearch + "</h1>");
		
		switch (search) {
		case "java":
			pw.println("<h2>자바는 객체지향 프로그래밍 언어 입니다.</h2>");
			break;
		case "oracle":
			pw.println("<h2>오라클은 DBMS입니다. SQL을 사용해 질의합니다.</h2>");		
			break;
		case "html":
			pw.println("<h2>HTML은 웹페이지 제작을 위한 마크업 언어입니다.</h2>");		
			break;
		case "css":
			pw.println("<h2>CSS는 웹페이지를 이쁘게 꾸며줍니다.</h2>");		
			break;
		case "javascript":
			pw.println("<h2>자바스크립트는 동적 웹페이지 제작을 위해 사용합니다.</h2>");	
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
