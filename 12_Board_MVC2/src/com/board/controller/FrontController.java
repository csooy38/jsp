package com.board.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.action.*;

public class FrontController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 한글 인코딩 작업
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String uri = request.getRequestURI();
		String projectPath = request.getContextPath();
		String command = uri.substring(projectPath.length()+1);
		System.out.println("command >>> " + command);
		
		Action action = null;
		
		if(command.equals("board_list.do")) {
			action = new BoardListAction();
		}else if(command.equals("board_write.do")) {
			action = new BoardWriteAction();
		}else if(command.equals("board_writeOk.do")) {
			action = new BoardWriteOkAction();
		}else if(command.equals("board_content.do")) {
			action = new BoardContextAction();
		}else if(command.equals("board_update.do")) {
			action = new BoardUpdateAction();
		}else if(command.equals("board_updateOk.do")) {
			action = new BoardUpdateOkAction();
		}else if(command.equals("board_delete.do")) {
			action = new BoardDeleteAction();
		}else if(command.equals("board_deleteOk.do")) {
			action = new BoardDeleteOkAction();
		}else if(command.equals("board_search.do")) {
			action = new BoardSearchAction();
		}
		
		String path = action.execute(request, response);
		
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}
}
