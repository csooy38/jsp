package com.board.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;

public class BoardWriteOkAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String board_title = request.getParameter("title");
		String board_writer = request.getParameter("writer").trim();
		String board_cont = request.getParameter("context");
		String board_pwd = request.getParameter("pwd");
		
		BoardDTO dto = new BoardDTO();
		
		dto.setBoard_title(board_title);
		dto.setBoard_writer(board_writer);
		dto.setBoard_cont(board_cont);
		dto.setBoard_pwd(board_pwd);
		
		BoardDAO dao = BoardDAO.getInstance();
		int res = dao.writeBoard(dto);
		
		PrintWriter out = response.getWriter();		
		
		if(res > 0) {
			out.println("<script>");
			out.println("alert('글쓰기 성공!')");
			out.println("location.href='board_list.do'");
			out.println("</script>");
		}else {
			out.println("<script>");
			out.println("alert('글쓰기 실패!')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		return null;
	}

}
