package com.kh.jsp.menu.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MenuController
 */
@WebServlet("/menu/*")
public class MenuController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MenuController() {
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
			case "menu" : menu(request,response);
				break;
			case "order" : order(request,response);
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
	
	private void menu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request
		.getRequestDispatcher("/WEB-INF/view/menu/menu.jsp")
		.forward(request, response);
	}
	
	private void order(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1. 클라이언트가 food 라는 이름의 파라미터로 보내는 데이터를 받아서 String 배열에 저장
		//	request.getParameter 사용 안됩니다. servlet api확인해보기
		String[] orderArr = request.getParameterValues("food");
		
		List orderList = new ArrayList<Map>();
		int payPrice = 0;
		
		//2. 사용자가 주문한 메뉴를 받아서 결제 가격을 계산
		// 피자 : 10000원 / 햄버거 : 5000원 / 회 : 70000원 / 치킨 : 18000원
		for(String order : orderArr) {
			int price = 0;
			Map commandMap = new HashMap<String,Object>();
			
			switch (order) {
				case "피자": 	price = 10000;  break;
				case "햄버거":	price = 5000;	break;
				case "회":		price = 70000;	break;
				case "치킨": 	price = 18000;  break;
			}
			payPrice += price;
			commandMap.put("name", order);
			commandMap.put("price", price);
			orderList.add(commandMap);
		}
		
		//3. receipt.jsp 에 결제금액정보, 주문한 음식별 가격을 담아서 요청을 재지정
		request.setAttribute("list", orderList);
		request.setAttribute("price", payPrice);
		request.getRequestDispatcher("/WEB-INF/view/menu/receipt.jsp")
		.forward(request, response);
	}
	
	
	
	
	
	
	
	
	
	
	

}
