package com.board.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;

public class BoardDeleteOkAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		int board_no = Integer.parseInt(request.getParameter("no"));
		String board_pwd = request.getParameter("pwd");
		String db_pwd = request.getParameter("db_pwd");
		
		PrintWriter out = response.getWriter();

		if(board_pwd.equals(db_pwd)) {
			
			BoardDAO dao = BoardDAO.getInstance();
			int res = dao.deleteBoard(board_no);

			if(res > 0) {
				out.println("<script>");
				out.println("alert('글 삭제 성공!')");
				out.println("location.href='board_list.do'");
				out.println("</script>");
			}else {
				out.println("<script>");
				out.println("alert('글 삭제 실패!')");
				out.println("history.back()");
				out.println("</script>");
			}
		}else {
			out.println("<script>");
			out.println("alert('글 삭제 실패! 비밀번호를 확인하세요.')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		return null;
	}

}
