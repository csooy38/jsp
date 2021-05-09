package com.sist;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/forward")
public class ForwardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ForwardServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userId = request.getParameter("id").trim();
		String userPwd = request.getParameter("pwd").trim();
		
		// 원래는 DB 회원 관련 테이블에서 입력한 id와 pwd가 맞는지
		// 확인하여 회원이면 메인 페이지로 이동
		String dbId = "hong";
		String dbPwd = "1234";
		
		if(userId.equals(dbId)) {	// 입력한 아이디와 DB 테이블 상의 아이디가 같은  경우
			if(userPwd.equals(dbPwd)) {	// 입력한 비밀번호와 DB 테이블 상의 비밀번호가 같은  경우
				// 아이디와 비밀번호가 모두 일치하여 회원인 경우
				// 메인 페이지로 이동 ==> 페이지 이동
				request.setAttribute("name", "홍길동");
				request.setAttribute("addr", "서울시 마포구");
				
				RequestDispatcher rd = request.getRequestDispatcher("Ex10.jsp");
				rd.forward(request, response);	// request 데이터와 함께 실제로 페이지 이동				
			}else {
				System.out.println("비밀번호가 잘못되었습니다.");
			}
		}else {
			System.out.println("아이디를 확인해주세요.");
		}
	
	}

}
