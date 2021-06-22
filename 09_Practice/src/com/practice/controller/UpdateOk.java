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

@WebServlet("/updateOk.do")
public class UpdateOk extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UpdateOk() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		int practice_no = Integer.parseInt(request.getParameter("no").trim());
		String practice_title = request.getParameter("title").trim();
		String practice_writer = request.getParameter("writer").trim();
		String practice_cont = request.getParameter("content");
		String practice_pwd = request.getParameter("pwd").trim();
		
		PracticeDTO dto = new PracticeDTO();
		dto.setPractice_no(practice_no);
		dto.setPractice_title(practice_title);
		dto.setPractice_writer(practice_writer);
		dto.setPractice_cont(practice_cont);
		dto.setPractice_pwd(practice_pwd);
		
		PracticeDAO dao = PracticeDAO.getInstance();
		int res = dao.updateContent(dto);
		
		PrintWriter out = response.getWriter();
		
		if(res > 0) {
			out.println("<script>");
			out.println("alert('수정 성공!')");
			out.println("location.href='select.do?no="+practice_no+"'");
			out.println("</script>");
		}else if(res == -1) {
			out.println("<script>");
			out.println("alert('수정 실패! 비밀번호를 확인하세요.')");
			out.println("history.back()");
			out.println("</script>");
		}else {
			out.println("<script>");
			out.println("alert('수정 실패!')");
			out.println("history.back()");
			out.println("</script>");
		}
	}

}
