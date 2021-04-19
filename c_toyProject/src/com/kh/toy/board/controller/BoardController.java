package com.kh.toy.board.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.toy.board.model.service.BoardService;
import com.kh.toy.board.model.vo.Board;
import com.kh.toy.common.code.ErrorCode;
import com.kh.toy.common.exception.ToAlertException;
import com.kh.toy.member.model.vo.Member;

/**
 * Servlet implementation class BoardController
 */
@WebServlet("/board/*")
public class BoardController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardController() {
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
		case "detail": boardDetail(request,response);
			break;
		case "form": gotoForm(request,response);
			break;
		case "upload": uploadBoard(request,response);
			break;
		case "download": downloadFile(request,response);
			break;
		case "list": boardList(request,response);
			break;
		default: throw new ToAlertException(ErrorCode.CODE_404);

		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void gotoForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/views/board/boardForm.jsp")
		.forward(request, response);
	}
	
	private void uploadBoard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("user");
		
		boardService.insertBoard(member.getUserId(), request);
		
		request.setAttribute("alertMsg", "게시글 등록이 완료되었습니다.");
		request.setAttribute("url", "/index");
		request.getRequestDispatcher("/WEB-INF/views/common/result.jsp")
		.forward(request, response);
	}

	//boardDetail
	private void boardDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bdIdx = request.getParameter("bdIdx");
		Map<String,Object> commandMap = boardService.selectBoardDetail(bdIdx);
		request.setAttribute("data", commandMap);
		request.getRequestDispatcher("/WEB-INF/views/board/boardView.jsp")
		.forward(request, response);
	}
	
	private void downloadFile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  // TOMCAT의 context.xml 파일에 아래 Resources 태그를 추가해 외부 경로를 URL로 접근할 수 있도록 처리해주자.
		  // <Resources>
      	  //	<PreResources className="org.apache.catalina.webresources.DirResourceSet"
      	  //		webAppMount="/upload/" base="" />
		  // </Resources>
		 
		String originFileName = request.getParameter("originFileName");
		String renameFileName = request.getParameter("renameFileName");
		String subPath = request.getParameter("savePath");
		
		response.setHeader("content-disposition", "attachment; filename="+URLEncoder.encode(originFileName,"utf-8"));
		request.getRequestDispatcher("/upload/"+subPath+renameFileName)
		.forward(request, response);
	}
	
	private void boardList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int page = 1;
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		Map<String,Object> pageInfo = boardService.selectBoardList(page);
		request.setAttribute("pageInfo", pageInfo);
		request.getRequestDispatcher("/WEB-INF/views/board/boardList.jsp").forward(request, response);
	}

	
	
	
	
	
	

}
