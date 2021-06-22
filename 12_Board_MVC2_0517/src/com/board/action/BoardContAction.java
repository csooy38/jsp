package com.board.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;

public class BoardContAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 글 제목 클릭시 해당 글번호를 가지고 상세 내역을 조회하는 클래스.
		
		int board_no = Integer.parseInt(request.getParameter("no"));
		
		BoardDAO dao = BoardDAO.getInstance();
		
		// 조회 수 증가하는 메서드 호출
		dao.boardHit(board_no);
		
		BoardDTO dto = dao.boardCont(board_no);
		
		request.setAttribute("Cont", dto);
		
		return "view/board_content.jsp";
	}

}
