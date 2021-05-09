package com.sist;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public LoginServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1단계 : Ex04.jsp 페이지에서 넘어온 데이터들을 처리해 주자.
		String userId = request.getParameter("id");
		String userPwd = request.getParameter("pwd");
		
		// 한글 처리
		response.setContentType("text/html; charset=UTF-8");
		
		// 2단계 : 처리한 데이터를 웹 브라우저에 출력해 보자.
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<body>");
		out.println("<h2>");
		out.println("아이디 : " + userId + "<br>");
		out.println("비밀번호 : " + userPwd + "<br>");
		out.println("</h2>");
		out.println("</body>");
		out.println("</html>");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
