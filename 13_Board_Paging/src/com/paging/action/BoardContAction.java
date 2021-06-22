package com.paging.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.paging.model.BoardDAO;
import com.paging.model.BoardDTO;
import com.paging.model.CommentDTO;

public class BoardContAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 글 제목을 클릭했을 때 get 방식으로 넘어 온 글 번호에 해당하는 게시글을 조회하는 클래스
		
		int board_no = Integer.parseInt(request.getParameter("no"));
		int page = Integer.parseInt(request.getParameter("page"));
		
		BoardDAO dao = BoardDAO.getInstance();
		
		// 상세 내역 클릭시 조회수를 증가시키는 메서드 호출
		dao.boardHit(board_no);
		
		// 글 번호에 해당하는 상세 내역 메서드 호출
		BoardDTO dto = dao.getBoardCont(board_no);
		
		request.setAttribute("Cont", dto);
		request.setAttribute("page", page);	
		
		
		List<CommentDTO> c_list = dao.getCommentList(board_no);
		request.setAttribute("c_list", c_list);
	}

}
