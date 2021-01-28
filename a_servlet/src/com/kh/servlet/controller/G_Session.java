package com.kh.servlet.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class H_Session
 */
@WebServlet("/session/*")
public class G_Session extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public G_Session() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String[] uriArr = request.getRequestURI().split("/");
		switch (uriArr[uriArr.length-1]) {
			case "login":
					login(request,response);
				break;
			case "logout":
					logout(request,response);
				break;
			default: 
					sendError(request,response);
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
	
	
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//HttpSession : Session Scope를 가지는 객체
		//				Session Scope : 요청이 온 시점 부터 브라우저가 종료되는 시점까지 존재하는 수명주기
		//			데이터를 저장하기 위해 사용한다.
		
		//HttpSession은 JVM의 Heap영역에 저장되는 일반적인 객체이다.
		//원하는 데이터를 저장하고 꺼내쓰기 위한 메서드가 제공된다.
		//HttpSession을 생성하기 위해 사용하는 request.getSession() 메서드는 해당 요청에 의해 생성된 Session객체를 반환
		//생성된 session객체는 해당 session 객체를 접근할 수 있는 고유의 id를 가지고 있다.
		//session이 생성되면 해당 응답 때 응답헤더에 session 객체를 식별할 수 있는 아이디(JSESSIONID)를 쿠키에 담아 보낸다.
		//서버는 응답을 하고 나면 모든 걸 잊기 때문에, session 객체를 식별하기 위한 아이디도 잊는다.
		//하지만 해당 JSESSION 아이디가 쿠키에 담겨저 보내졌기 때문에, 클라이언트가 다시 요청할 때 JSESSION ID도 함께 
		//서버에 전달된다.
		//그럼 서버는 요청 header에 담겨온 JSESSION ID를 사용해 다시 session 객체를 식별할 수 있게 된다.
		//쿠키의 기본 수명주기가 session이기 때문에, 별 다른 쿠키정책이 없다면
		//JSESSION ID를 가지고 있는 쿠키는 브라우저가 종료될 때 브라우저에서 삭제된다.
		//Session 객체를 식별하기 위한 JSESSION ID가 더이상 요청헤더에 담겨오지 않음으로 해당 Session객체는 더이상
		//사용이 불가능 해진다.
		//Session객체는 마지막으로 호출된지 30분이 지나면 JVM에서 삭제된다.
		//Session.setMaxInActiveInterval() 메서드를 사용해 조절할 수 있다.
		String nick = request.getParameter("nick");
		//Session 객체 생성
		//getSession(boolean)
		HttpSession session = request.getSession();

		//session에 정보를 저장		
		//setAttribute : 속성에 정보를 저장 및 수정
		//getAttribute : 해당 속성을 가져온다.
		//removeAttribute : 해당 속성을 삭제한다.
		session.setAttribute("nick", nick);
		
		//브라우저를 종료해도 session을 유지하기
		//JSESSIONID 쿠키의 max-age를 session이 아니라 시간으로 잡아
		//브라우저가 종료되더라도 쿠키가 삭제되지 않게 처리하기
		//JSESSIONID 쿠키에 담기게 되는 Session ID를
		//HttpSession.getID() 메서드로 반환받을 수 있다.
		response.setHeader("set-cookie", "JSESSIONID=" + session.getId() + "; max-age=3600; path=/");
		
		PrintWriter pw = response.getWriter();
		pw.println("<h1>" + session.getAttribute("nick") + "님 ");
		pw.println("<a href='/servlet/session/logout'>logout</a></h1>");
		pw.println("<a href='/servlet/request/get?age=10'>request/get에서 session정보 확인하기</a><br>");
		pw.println("<a href='/servlet/cookie'>cookie에서 session정보 확인하기</a><br>");
		pw.println("<a href='/servlet/usercount'>로그인 유저 수 확인하기</a><br>");
	}
	
	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Session 객체 삭제
		HttpSession session = request.getSession();
		//session.invalidate();
		
		//nick 이라는 이름으로 저장한 속성만 삭제
		session.removeAttribute("nick");
		response.sendRedirect("/servlet/cookie");
	}
	
	private void sendError(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setStatus(404);
	}
	

}
