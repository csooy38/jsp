package com.board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;



@WebServlet("/content.do")
public class ContentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ContentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get 방식으로 넘어 온 글번호로 DB에서 조회하여 글번호에 해당하는 게시글 상세 정보를 view page로 넘겨주는 작업
		// 1단계 : 글번호에 해당하는 게시글 상세 정보를 view page로 넘겨주는 작업
		int board_no = Integer.parseInt(request.getParameter("no"));
		
		// 2단계 : 글번호로 BoardDAO 클래스의 상세 내역을 조회하는 메서드 인자로 지정
		BoardDAO dao = BoardDAO.getInstance();
		
		dao.boardHit(board_no);		// 조회수를 증가시키는 메서드 호출
		BoardDTO boardCont = dao.boardCont(board_no);	// 상세 내역을 조회하는 메서드 호출
		
		request.setAttribute("cont", boardCont);
		
		RequestDispatcher rd = request.getRequestDispatcher("view/board_content.jsp");
		rd.forward(request, response);
		
	}

}
