package com.board1.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board1.model.BoardDAO;
import com.board1.model.BoardDTO;

@WebServlet("/select.do")
public class SelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SelectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// DB에서 board 테이블의 게시글 전체 리스트 조회해서 view page로 전송하는 작업
		BoardDAO dao = BoardDAO.getInstance();
		List<BoardDTO> boardList = dao.getBoardList();
		
		request.setAttribute("List", boardList);
		
		RequestDispatcher rd = request.getRequestDispatcher("view/board_list.jsp");
		rd.forward(request, response);
	}

}
