package com.kh.servlet.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class E_Cache
 */
@WebServlet("/cache")
public class E_Cache extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public E_Cache() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//ETag : 해당 컨텐츠가 수정된 이력이 있는 지 검사할 수 있는 식별자
		// 만약 컨텐츠가 1바이트라도 수정되게 되면 ETag값이 변경된다.
		// 또한 만약 컨텐츠의 내용이 수정되지 않더라도, 최종수정일을 변경하면 ETag값이 변경된다.
		
		//Cache-Control 헤더 : 캐시정책을 지정
		//no-store : 캐시를 저장하지 않음.
		//no-cache : 매번 서버에서 해당 자원의 Etag를 확인한다.
		//			만약 서버자원의 Etag와 캐시 저장소의 Etag가 다르면 자원을 요청하고
		//			같으면 캐시저장소의 자원을 사용
		//max-age : 캐시 수명을 지정. 지정한 초가 지나기 전에는 서버에 Etag도 확인하지 않는다.
		//			캐시 수명이 만료되면 no-cache 와 동일.
		
		//해당 응답은 캐시에 저장하지 않는다.
		response.setHeader("cache-control","no-store");
		response.setHeader("content-disposition", "attachment; filename=cat.jpg");
		request.getRequestDispatcher("/resources/image/image01.jpg")
		.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
