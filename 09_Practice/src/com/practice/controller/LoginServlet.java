package com.practice.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.practice.model.Pr_memberDAO;
import com.practice.model.PracticeDAO;


@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String pr_id = request.getParameter("pr_id").trim();
		String pr_pwd = request.getParameter("pr_pwd").trim();
		
		Pr_memberDAO dao = Pr_memberDAO.getInstance();
		
		int res = dao.loginPractice(pr_id, pr_pwd);
		
		PrintWriter out = response.getWriter();
		
		if(res == 1) {
			out.println("<script>");
			out.println("alert('로그인 성공!')");
			out.println("location.href='select.do'");
			out.println("</script>");
		}else if(res == -1) {
			out.println("<script>");
			out.println("alert('로그인 실패! 비밀번호를 확인하세요.')");
			out.println("history.back()");
			out.println("</script>");
		}else {
			out.println("<script>");
			out.println("alert('로그인 실패! 아이디를 확인하세요.')");
			out.println("history.back()");
			out.println("</script>");
		}
				
	}

}
