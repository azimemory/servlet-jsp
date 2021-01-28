package com.kh.servlet.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class A_First
 */
@WebServlet("/first")
public class A_First extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public A_First() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		//1. 사용자의 요청을 받아서 파라미터로 넘어온 값을 확인
		System.out.println("GET방식으로 넘어온 요청입니다.");
		//파라미터이름이 nick인 파라미터값을 반환
		String nick = request.getParameter("nick");
		System.out.println("넘어온 데이터 : " + nick);
		
		//2. 사용자에게 응답
		//response.getWriter() 메서드를 사용해 response body를 작성할 수 있는
		//PrintWriter 객체를 반환받는다.
		PrintWriter writer = response.getWriter();
		writer.println("<h1 style='color:red'>Welcome to My Server</h1>");
		writer.println("<h2>localhost:9090/servlet/first 요청에 대한 응답입니다.</h2>");
		writer.println("<h2>어서오세요 "+ nick +"님!</h2>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//인코딩 문제를 해결
		//request body의 인코딩을 UTF-8로 지정
		request.setCharacterEncoding("UTF-8");
		//response header에 content-type:"text/html; charset=utf-8";
		response.setHeader("content-type", "text/html; charset=utf-8");
		
		System.out.println("POST방식으로 넘어온 요청입니다.");
		//파라미터이름이 nick인 파라미터값을 반환
		String nick = request.getParameter("nick");
		System.out.println("넘어온 데이터 : " + nick);
		
		//2. 사용자에게 응답
		//response.getWriter() 메서드를 사용해 response body를 작성할 수 있는
		//PrintWriter 객체를 반환받는다.
		PrintWriter writer = response.getWriter();
		writer.println("<h1 style='color:red'>Welcome to My Server</h1>");
		writer.println("<h2>localhost:9090/servlet/first 요청에 대한 응답입니다.</h2>");
		writer.println("<h2>어서오세요 "+ nick +"님!</h2>");
	}

}
