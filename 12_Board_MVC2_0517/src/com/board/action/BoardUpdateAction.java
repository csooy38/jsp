package com.board.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;

public class BoardUpdateAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 우선은 DB에서 글 번호에 해당하는 게시글을 가져와서 수정 폼 페이지에 넘겨주는 클래스
		int board_no = Integer.parseInt(request.getParameter("no"));
		
		BoardDAO dao = BoardDAO.getInstance();
		BoardDTO dto = dao.boardCont(board_no);
		
		request.setAttribute("edit", dto);
		
		return "view/board_update.jsp";
	}

}
