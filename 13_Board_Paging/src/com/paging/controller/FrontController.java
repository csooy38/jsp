package com.paging.controller;

import java.io.IOException;

import java.io.UnsupportedEncodingException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.paging.action.*;


public class FrontController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		// 한글 인코딩 작업
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// getRequestURI() : "/프로젝트명/파일명(*.do)" 라는 문자열을 String 타입으로 반환해 주는 메서드.
		String uri = request.getRequestURI();
		//System.out.println("uri >>> " + uri);
		
		// getContextPath() : 현재 프로젝트명을 String 타입으로 반화해 주는 메서드.
		String path = request.getContextPath();
		//System.out.println("path >>> " + path);
		
		String command = uri.substring(path.length() + 1);
		System.out.println("command >>> " + command);
		
		Action action = null;
		String viewPage = null;		
		
		if(command.equals("board_list.do")) {
			action = new BoardListAction();
			action.execute(request, response);
			viewPage = "view/board_list.jsp";
		}else if(command.equals("board_write.do")){
			viewPage = "view/board_write.jsp";	// 페이지 이동만 필요하므로 바로 지정. 액션 필요X
		}else if(command.equals("board_writeOk.do")) {
			action = new BoardWriteOkAction();
			action.execute(request, response);
		}else if(command.equals("board_cont.do")) {
			action = new BoardContAction();
			action.execute(request, response);
			viewPage = "view/board_cont.jsp";
		}else if(command.equals("board_update.do")) {
			action = new BoardUpdateAction();
			action.execute(request, response);
			viewPage = "view/board_update.jsp";
		}else if(command.equals("board_updateOk.do")) {
			action = new BoardUpdateOk();
			action.execute(request, response);
		}else if(command.equals("board_delete.do")) {
			action = new BoardDeleteAction();
			action.execute(request, response);
			viewPage = "view/board_delete.jsp";
		}else if(command.equals("board_deleteOk.do")) {
			action = new BoardDeleteOkAction();
			action.execute(request, response);
		}else if(command.equals("board_search.do")) {
			action = new BoardSearchAction();
			action.execute(request, response);
			viewPage = "view/board_search.jsp";
		}else if(command.equals("board_comment.do")) {
			action = new BoardCommentAction();
			action.execute(request, response);
		}else if(command.equals("comment_delete.do")) {
			action = new CommentDeleteAction();
			action.execute(request, response);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(viewPage);
		
		rd.forward(request, response);
		
	}
}
