package com.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;


@WebServlet("/search.do")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public SearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 검색 폼 창에서 넘어 온 검색어로 DB에서 검색어에 해당하는 모든 데이터를 view page로 이동시키는 작업
		
		// 한글 인코딩 작업
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// 1단계 : 검색폼 창에서 넘어온 데이터들을 처리해 주자.
		String find_field = request.getParameter("find_field").trim();
		String find_name = request.getParameter("find_name").trim();
		
		// 2단계 : 파라미터로 넘어온 데이터들을 BoardDAO 클래스의 메서드에 인자로 넘겨 주자.
		BoardDAO dao = BoardDAO.getInstance();
		List<BoardDTO> search = dao.searchBoard(find_field, find_name);
		
		request.setAttribute("searchList", search);
		
		RequestDispatcher rd = request.getRequestDispatcher("view/board_search.jsp");
		rd.forward(request, response);
		
	}

}
