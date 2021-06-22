package com.board.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;

public class BoardDeleteAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		int board_no = Integer.parseInt(request.getParameter("no"));
				
		BoardDAO dao = BoardDAO.getInstance();
		BoardDTO dto = dao.contextBoard(board_no);
		
		request.setAttribute("del", dto);
		
		return "view/board_delete.jsp";
	}

}
