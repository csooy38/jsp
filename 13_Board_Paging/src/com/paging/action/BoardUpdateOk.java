package com.paging.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.paging.model.BoardDAO;
import com.paging.model.BoardDTO;

public class BoardUpdateOk implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		int page = Integer.parseInt(request.getParameter("page"));
		int board_no = Integer.parseInt(request.getParameter("no"));
		String db_pwd = request.getParameter("db_pwd");
		
		String board_title = request.getParameter("title").trim();
		String board_cont = request.getParameter("content").trim();
		String board_pwd = request.getParameter("pwd").trim();
		
		PrintWriter out = response.getWriter();
		
		if(board_pwd.equals(db_pwd)) {
			BoardDTO dto = new BoardDTO();
			
			dto.setBoard_no(board_no);
			dto.setBoard_title(board_title);
			dto.setBoard_cont(board_cont);
			
			BoardDAO dao = BoardDAO.getInstance();	
			
			int res = dao.updateBoard(dto);
			
			if(res > 0) {
				out.println("<script>");
				out.println("alert('수정 완료!')");
				out.println("location.href='board_cont.do?no=" + board_no + "&page=" + page +"'");
				out.println("</script>");
			}else {
				out.println("<script>");
				out.println("alert('수정 실패!')");
				out.println("history.back()");
				out.println("</script>");
			}
		}else {
			out.println("<script>");
			out.println("alert('수정 실패! 비밀번호를 확인하세요.')");
			out.println("history.back()");
			out.println("</script>");
		}

	}

}
