package com.kh.servlet.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class D_PageChange
 */
@WebServlet("/page/*")
public class D_PageChange extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public D_PageChange() {
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
		
		switch(uriArr[uriArr.length - 2]) {
		case "forward" :
				if(uriArr[uriArr.length - 1].equals("name")) {
					forwardName(request, response);
				}else if(uriArr[uriArr.length - 1].equals("image")) {
					forwardImage(request, response);
				}else {
					sendError(request, response);
				}
			break;
		case "redirect" :
				if(uriArr[uriArr.length - 1].equals("name")) {
					redirectName(request, response);
				}else if(uriArr[uriArr.length - 1].equals("image")) {
					redirectImage(request, response);
				}else {
					sendError(request, response);
				}
			break;
		default:
			sendError(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void forwardName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//Request Scope : 요청이 발생한 시점에 객체가 생성되어 응답이 마무리되는 시점에 객체가 소멸하는 스코프
		// ex) HttpServletRequest, HttpServletResponse 객체가 Request Scope를 가지고 있음
		
		//RequestDispatcher.forward() : 요청을 재지정해주는 메서드
		//  /servlet/page/forward/name 넘어온 요청을 /servlet/page/impl 로 내부에서 재지정
		
		//요청을 재지정하겠다. /servlet/page/impl
		RequestDispatcher view = request.getRequestDispatcher("/page/impl");
		//받은 요청객체와 응답객체를 함께 넘겨준다.
		view.forward(request, response);
	}
	
	private void forwardImage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//해당 메서드로 들어온 요청을 
		// /resources/image/image01.jpg 로 재지정
		
		response.setHeader("content-disposition", "attachment; filename=cat.jpg");
		request.getRequestDispatcher("/resources/image/image01.jpg")
		.forward(request, response);
	}
	
	private void redirectName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Response.sendRedirect()  : 요청을 재요청할 수 있다.
		//sendRedirect(url) : 클라이언트에게 302 상태코드와 함께 매개변수로 넘겨준 url을 응답해준다.
		//302 상태코드를 받은 브라우저는 응답헤더에 함께 넘어온 url로 다시 요청하게 된다.
		
		//브라우저가 재요청할 url을 매개변수로 넘겨주기 때문에
		//context path가 반드시 필요, 브라우저는 우리의 웹어플리케이션으로 접근하기 위해 
		//context path가 필요하기 때문
		response.sendRedirect("/servlet/page/impl");
	}
	
	private void redirectImage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//응답코드 302는 브라우저에게 재요청할 것을 요청하는 응답코드이다.
		//response.sendRedirect() 메서드는 브라우저에게 302상태코드와 
		//location헤더로  재요청할 url을 응답한다.
		//따라서 응답이 발생하게 되고 응답이 발생하는 시점에
		//우리가 header를 설정한 응답객체와 요청객체는 응답이 끝나고 소멸한다.
		//(Request Scope를 가지고 있는 객체이기 때문!)
		
		//응답을 받은 브라우저는 302상태코드이기 때문에
		//우리가 location 헤더에 담아준 (== response.setRedirect() 메서드의 매개변수로 넣어준)
		//경로로 새로운 요청을 하게된다.
		//새로운 요청이기 때문에 새로운 request 객체와 response 객체가 생성된다.
		response.setHeader("content-disposition", "attachment; filename=cat.jpg");
		response.sendRedirect("/servlet/resources/image/image01.jpg");
	}
	
	private void sendError(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setStatus(404);
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
