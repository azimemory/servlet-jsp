package com.kh.servlet.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


// 1) * : 모든 
//		ex) /request/* : localhost:9090/servlet/request 로 시작하는 모든 요청
// 2) *.확장자 : 확장자 매칭을 나타낸다.
//		ex) *.com : .com으로 uri가 끝나는 모든 요청을 해당 서블릿으로 받겠다.
// 3) / : default 서블릿 , servlet에 지정되지 않은 모든 요청을 처리

@WebServlet("/request/*")
public class B_Request extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public B_Request() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uri = request.getRequestURI();
		String[] uriArr = uri.split("/");
		
		switch(uriArr[uriArr.length - 1]) {
			case "get" : testGet(request,response);
				break;
			case "post": testPost(request,response);
				break;
			case "multi": testMulti(request,response);
				break;
			default : sendError(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}
	
	private void testGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//사용자로부터 이름과 나이를 전달받아
		//사용자가 10년 뒤에 몇살인지 구해주자.
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
			
		
		//사용자 화면에
		//name 님은 10년 뒤 n살입니다. 라고 출력해주세요.
		PrintWriter pw = response.getWriter();
		
		//////////////////Session Test///////////////////
		HttpSession session = request.getSession();
		pw.println("<h1>" + session.getAttribute("nick") + "님 ");
		pw.println("<a href='/servlet/session/logout'>logout</a></h1>");
		//////////////////Session Test///////////////////
		
		pw.write("<h1>"+name +" 님은 10년 뒤 "+ (age + 10) +" 살입니다.</h1>");
	}
	
	private void testPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//사용자로부터 이름과 나이를 전달받아
		//사용자가 10년 뒤에 몇살인지 구해주자.
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		
		//사용자 화면에
		//name 님은 10년 뒤 n살입니다. 라고 출력해주세요.
		PrintWriter pw = response.getWriter();
		pw.write("<h1>Post방식 요청입니다.</h2>");
		pw.write("<h1>"+name +" 님은 10년 뒤 "+ (age + 10) +"살입니다.</h1>");
	}

	private void testMulti(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//content-type이 multipart인 경우 request.getParameter() 메서드 사용불가
		//String name = request.getParameter("name");
		//String file = request.getParameter("file");
		
		//직접 request body에 담겨있는 값을 읽어오자!
		InputStream is = null;
		OutputStream os = null;
		String root = getServletContext().getRealPath("/");
		
		try {
			is = request.getInputStream();
			os = new FileOutputStream(new File(root + "multipart.txt"));
			int check = 0;
			while((check=is.read()) != -1) {
				os.write(check);
			}
		}finally {
			os.close();
			is.close();
		}
	}
	
	private void sendError(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response 응답코드 지정
		//404에러 코드를 담아서 보낸다.
		response.setStatus(404);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
