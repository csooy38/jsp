package com.board.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;


@WebServlet("/deleteOk.do")
public class DeleteOkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public DeleteOkServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 글 번호에 해당하는 게시글을 DB에서 삭제하는 작업
		
		// 1단계 : 삭제 폼 페이지에서 넘어 온 데이터들을 처리해 주자.
		int board_no = Integer.parseInt(request.getParameter("no"));
		String board_pwd = request.getParameter("pwd");
		
		BoardDAO dao = BoardDAO.getInstance();
		int res = dao.deleteBoard(board_no, board_pwd);
		
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		if(res > 0) {
			out.println("<script>");
			out.println("alert('게시글 삭제 성공!')");
			out.println("location.href='select.do'");
			out.println("</script>");
		}else if(res == -1) { 
			out.println("<script>");
			out.println("alert('게시글 삭제 실패! 비밀번호가 틀립니다!')");
			out.println("history.back()");
			out.println("</script>");
		}else {
			out.println("<script>");
			out.println("alert('게시글 삭제 실패!')");
			out.println("history.back()");
			out.println("</script>");
		}
		
	}

}
