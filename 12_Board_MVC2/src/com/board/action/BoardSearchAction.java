package com.board.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;

public class BoardSearchAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String board_search = request.getParameter("search");
		String board_data = request.getParameter("searchData");
		
		BoardDAO dao = BoardDAO.getInstance();
		List<BoardDTO> searchList = dao.searchBoard(board_search, board_data);
		
		request.setAttribute("List", searchList);
		request.setAttribute("data", board_data);
		
		return "view/board_search.jsp";
	}

}
