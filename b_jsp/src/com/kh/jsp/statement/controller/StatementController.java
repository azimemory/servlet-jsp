package com.kh.jsp.statement.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class StatementController
 */
@WebServlet("/statement/*")
public class StatementController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StatementController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String[] uriArr = request.getRequestURI().split("/");
		switch (uriArr[uriArr.length - 1]) {
			case "statement": 
				statement(request,response);
				break;
			
			case "calc": 
				statementImpl(request,response);
				break;
			default:
				response.setStatus(404);
			}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void statement(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/view/statement/statement_inp.jsp")
		.forward(request, response);
	}
	
	private void statementImpl(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int num = Integer.parseInt(request.getParameter("num"));
		request.setAttribute("num", num);
		
		request.getRequestDispatcher("/WEB-INF/view/statement/statement.jsp")
		.forward(request, response);
	}

	
	
	
	
	
	
	
	
	
	
	
}
