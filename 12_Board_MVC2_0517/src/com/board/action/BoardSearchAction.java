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
		String board_find = request.getParameter("find");
		
		BoardDAO dao = BoardDAO.getInstance();
		List<BoardDTO> list = dao.searchBoard(board_search, board_find);
		
		request.setAttribute("SearchList", list);
		request.setAttribute("Search", board_search);
		request.setAttribute("Find", board_find);
		
		return "view/board_search.jsp";
	}

}
