package com.practice.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.practice.model.PracticeDAO;
import com.practice.model.PracticeDTO;


@WebServlet("/content.do")
public class ContentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ContentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int practice_no = Integer.parseInt(request.getParameter("no"));
		
		PracticeDAO dao = PracticeDAO.getInstance();
		
		dao.hitContent(practice_no);
		PracticeDTO dto = dao.contentPractice(practice_no);
		
		request.setAttribute("cont", dto);
		
		RequestDispatcher rd = request.getRequestDispatcher("view/practice_content.jsp");
		rd.forward(request, response);
	}

}
