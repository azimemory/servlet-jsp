package com.kh.servlet.filter;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

//HttpServletRequest를 구현하고 있는
//HttpServletRequestWrapper를 상속
//따라서 ViewWrapper의 인스턴스는 HttpServletRequest 타입으로 다룰 수 있다.
public class ViewWrapper extends HttpServletRequestWrapper{
	
	HttpServletRequest request;

	public ViewWrapper(HttpServletRequest request) {
		super(request);
		this.request =request;
	}

	
	//1. method overriding
	public RequestDispatcher getRequestDispatcher(String path) {
		RequestDispatcher rd
			= request.getRequestDispatcher("/WEB-INF/view/" + path + ".html");
		return rd;
	}
	
	
	//2. 원하는 새로운 메서드를 추가
	public void testPcalssWrapper() {
		System.out.println("wrapper에서 추가한 메서드를 서블릿에서 호출");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
