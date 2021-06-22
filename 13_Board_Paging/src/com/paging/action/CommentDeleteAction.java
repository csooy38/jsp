package com.paging.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.paging.model.BoardDAO;

public class CommentDeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		int board_no = Integer.parseInt(request.getParameter("no"));
		int page = Integer.parseInt(request.getParameter("page"));
		int comment_no = Integer.parseInt(request.getParameter("cnum"));

		BoardDAO dao = BoardDAO.getInstance();
		int res = dao.deleteComment(comment_no);
		
		PrintWriter out = response.getWriter();
		
		if(res > 0) {
			out.println("<script>");
			out.println("alert('댓글 삭제 완료!')");
			out.println("location.href='board_cont.do?no=" + board_no + "&page=" + page +"'");
			out.println("</script>");
			
			dao.boardCountDown(board_no);
			
		}else {
			out.println("<script>");
			out.println("alert('댓글 삭제 실패!')");
			out.println("history.back()");
			out.println("</script>");
		}
	}

}
