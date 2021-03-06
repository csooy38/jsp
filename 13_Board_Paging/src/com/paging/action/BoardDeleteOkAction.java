package com.paging.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.paging.model.BoardDAO;

public class BoardDeleteOkAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 삭제 폼 페이지에서 넘어온 데이터로 DB에서 해당 게시글을 삭제하는 클래스.		
		
		int board_no = Integer.parseInt(request.getParameter("no"));
		int page = Integer.parseInt(request.getParameter("page"));
		String db_pwd = request.getParameter("db_pwd");
		String board_pwd = request.getParameter("pwd").trim();
		
		PrintWriter out = response.getWriter();
		
		if(board_pwd.equals(db_pwd)) {
			BoardDAO dao = BoardDAO.getInstance();
			int res = dao.deleteBoard(board_no);
			
			// board 테이블의 글 번호를 수정하는 메서드 호출(시퀀스에는 사용X)
			dao.updateNo(board_no);
			
			if(res > 0) {
				out.println("<script>");
				out.println("alert('삭제 완료!')");
				out.println("location.href='board_list.do?page=" + page + "'");
				out.println("</script>");
			}else {
				out.println("<script>");
				out.println("alert('삭제 실패!')");
				out.println("history.back()");
				out.println("</script>");
			}
		}else {
			out.println("<script>");
			out.println("alert('삭제 실패! 비밀번호를 확인하세요.')");
			out.println("history.back()");
			out.println("</script>");
		}
		
	}

}
