package com.board.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;


@WebServlet("/insert.do")
public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public InsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 게시글 입력폼 창에서 넘어온 데이터들을 DB에 저장하는 작업
		
		// 한글 인코딩 처리
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// 1단계 : 게시물 입력폼 창에서 넘어온 데이터들을 받아 주어야 한다.
		String board_writer = request.getParameter("writer").trim();
		String board_title = request.getParameter("title").trim();
		String board_content = request.getParameter("content");
		String board_pwd = request.getParameter("pwd").trim();
		
		// 2단계 : DB에 전송할 객체인 BoardDTO 객체에
		//	파라미터로 받은 데이터둘울 setter() 메서드의 인자로 넘겨주어야 한다.
		BoardDTO dto = new BoardDTO();
		
		dto.setBoard_writer(board_writer);
		dto.setBoard_title(board_title);
		dto.setBoard_cont(board_content);
		dto.setBoard_pwd(board_pwd);
		
		// 3단계 : BoardDTO 객체를 DB에 전송해주어야 한다.
		//	BoardDAO 객체 생성 후 해당 메서드의 인자로 dto 객체의 
		//	주소값을 넘겨준다.
		BoardDAO dao = BoardDAO.getInstance();
		
		int res = dao.insertBoard(dto);
		
		PrintWriter out = response.getWriter();
		
		if(res > 0) {	// 게시물 추가가 성공한 경우
			out.println("<script>");
			out.println("alert('글 작성 성공!')");
			out.println("location.href='select.do'");
			out.println("</script>");
		}else {
			out.println("<script>");
			out.println("alert('글 작성 실패!')");
			out.println("history.back()");
			out.println("</script>");
		}
	}

}
