package com.practice.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.practice.model.PracticeDAO;
import com.practice.model.PracticeDTO;

@WebServlet("/insertOk.do")
public class InsertOkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public InsertOkServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String practice_head = request.getParameter("head");
		String practice_title = request.getParameter("title");
		String practice_writer = request.getParameter("writer");
		String practice_cont = request.getParameter("content");
		String practice_pwd = request.getParameter("pwd");
		
		PracticeDTO dto = new PracticeDTO();
		
		dto.setPractice_head(practice_head);
		dto.setPractice_title(practice_title);
		dto.setPractice_writer(practice_writer);
		dto.setPractice_cont(practice_cont);
		dto.setPractice_pwd(practice_pwd);
		
		PracticeDAO dao = PracticeDAO.getInstance();
		int res = dao.insertContent(dto);
		
		PrintWriter out = response.getWriter();
		
		if(res > 0) {
			out.println("<script>");
			out.println("alert('등록 성공!')");
			out.println("location.href='select.do'");
			out.println("</script>");
		}else {
			out.println("<script>");
			out.println("alert('등록 실패!')");
			out.println("history.back()");
			out.println("</script>");
		}
		
	}

}
