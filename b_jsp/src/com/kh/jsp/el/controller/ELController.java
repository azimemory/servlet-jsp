package com.kh.jsp.el.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.jsp.el.model.vo.Student;

/**
 * Servlet implementation class ELController
 */
@WebServlet("/el/*")
public class ELController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ELController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String[] uriArr = request.getRequestURI().split("/");
			switch (uriArr[uriArr.length -1]) {
			case "el": 
				el(request,response);
				break;
			case "elimpl": 
				elImpl(request, response);
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
	
	private void el(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/el_jstl/el_inp.jsp")
		.forward(request, response);
	}
	
	private void elImpl(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파라미터로 넘어온 국영수코 과목의 평균점수와 총합계를 구해
		//request 객체에 추가하고 el.jsp로 요청을 재지정
		
		//1. 파라미터로 넘어온 데이터 파싱
		String name = request.getParameter("name");
		int kor = Integer.parseInt(request.getParameter("kor"));
		int math = Integer.parseInt(request.getParameter("math"));
		int eng = Integer.parseInt(request.getParameter("eng"));
		int coding = Integer.parseInt(request.getParameter("coding"));
	
		//합계와 평균 구하기
		int sum = kor + math + eng + coding;
		int avg = sum/4;
		
		//request 객체에 합계와 평균 추가
		request.setAttribute("sum", sum);
		request.setAttribute("avg", avg);
		
		
		//////////////////////VO에 데이터를 담아서 전송////////////////////////
		
		Student student = new Student();
		student.setName(name);
		student.setKor(kor);
		student.setMath(math);
		student.setEng(eng);
		student.setCoding(coding);
		
		request.setAttribute("student", student);
		
		/////////////////////MAP에 데이터를 담아서 전송///////////////////////
		Map commandMap = new HashMap<String,Object>();
		commandMap.put("name", "map에 담긴 이름 : " + name);
		commandMap.put("kor", kor);
		commandMap.put("math", math);
		commandMap.put("eng", eng);
		commandMap.put("coding", coding);
		commandMap.put("sum", sum);
		commandMap.put("avg", avg);
		
		request.setAttribute("studentMap", commandMap);
		
		/////////////////////////// array/list 에서 데이터 꺼내쓰기 //////////////////////////////
		List studentList = new ArrayList<>();
		studentList.add(student);
		studentList.add(commandMap);
		
		request.setAttribute("studentList", studentList);
		
		//파라미터로 넘어온 학생이름을 session 저장
		HttpSession session = request.getSession();
		session.setAttribute("name", name+"(logout)");
		
		//el.jsp
		request.getRequestDispatcher("/WEB-INF/view/el_jstl/el.jsp")
		.forward(request, response);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
