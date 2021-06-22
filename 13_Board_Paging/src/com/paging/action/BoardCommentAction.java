package com.paging.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.paging.model.BoardDAO;
import com.paging.model.CommentDTO;

public class BoardCommentAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String comment_writer = request.getParameter("c_writer").trim();
		String comment_cont = request.getParameter("c_content").trim();
		String comment_pwd = request.getParameter("c_pwd").trim();
		int board_no = Integer.parseInt(request.getParameter("no"));
		int page = Integer.parseInt(request.getParameter("page"));
		
		CommentDTO dto = new CommentDTO();
		dto.setComment_writer(comment_writer);
		dto.setComment_cont(comment_cont);
		dto.setComment_pwd(comment_pwd);
		
		BoardDAO dao = BoardDAO.getInstance();
		int res = dao.insertComment(dto, board_no);
		
		PrintWriter out = response.getWriter();
	
		/*
		if(res > 0) {
			out.println("<script>");
			out.println("alert(댓글 작성 완료!)");
			out.println("location.href='board_cont.do?no=" + board_no + "&page=" + page +"'");
			out.println("</script>");
		}else {
			out.println("<script>");
			out.println("alert(댓글 작성 실패!)");
			out.println("history.back()");
			out.println("</script>");
		}
		*/
		
		if(res > 0) {
			out.println("<script>");
			out.println("alert('댓글 작성 완료!')");
			out.println("location.href='board_cont.do?no=" + board_no + "&page=" + page +"'");
			out.println("</script>");
			
			dao.boardCountHit(board_no);
			
		}else {
			out.println("<script>");
			out.println("alert('댓글 작성 실패!')");
			out.println("history.back()");
			out.println("</script>");
		}

	}

}
