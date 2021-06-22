package com.board.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;

public class BoardUpdateOkAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 글 번호에 해당하는 내용을 수정 폼 창에서 수정하여 수정된 내용의 게시글을 DB에서 수정하는 클래스
		
		String board_writer = request.getParameter("writer");
		String board_title = request.getParameter("title");
		String board_cont = request.getParameter("content");
		String board_pwd = request.getParameter("pwd").trim();
		
		// 히든으로 넘어온 데이터들도 받아 주어야 한다.
		int board_no = Integer.parseInt(request.getParameter("no").trim());
		String db_pwd = request.getParameter("db_pwd").trim();
		
		PrintWriter out = response.getWriter();
		
		if(board_pwd.equals(db_pwd)) {
			BoardDTO dto = new BoardDTO();
			
			dto.setBoard_writer(board_writer);
			dto.setBoard_title(board_title);
			dto.setBoard_cont(board_cont);
			dto.setBoard_no(board_no);
			
			BoardDAO dao = BoardDAO.getInstance();
			int res = dao.updateBoard(dto);
			
			if(res > 0) {
				out.println("<script>");
				out.println("alert('글 수정 성공!')");
				out.println("location.href='board_cont.do?no="+dto.getBoard_no()+"'");
				out.println("</script>");
			}else {
				out.println("<script>");
				out.println("alert('글 수정 실패!')");
				out.println("history.back()");
				out.println("</script>");
			}
			
		}else {
			// 비밀번호가 틀린 경우	
			out.println("<script>");
			out.println("alert('비밀번호가 틀립니다. 확인해주세요.')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		
		
		return null;
	}

}
