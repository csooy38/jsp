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

@WebServlet("/delete.do")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		int practice_no = Integer.parseInt(request.getParameter("no"));
		String practice_pwd = request.getParameter("pwd");
		
		PrintWriter out = response.getWriter();
		
		PracticeDAO dao = PracticeDAO.getInstance();
		
		int res = dao.deleteContent(practice_no, practice_pwd);
		
		
		if(res > 0) {
			out.println("<script>");
			out.println("alert('글 삭제 성공!')");
			out.println("location.href='select.do'");
			out.println("</script>");
		}else if(res == -1){
			out.println("<script>");
			out.println("alert('글 삭제 실패! 비밀번호를 확인하세요.')");
			out.println("history.back()");
			out.println("</script>");
		}else {
			out.println("<script>");
			out.println("alert('글 삭제 실패!')");
			out.println("history.back()");
			out.println("</script>");
		}
		
}
	
}
