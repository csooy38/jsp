package com.practice.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.practice.model.PracticeDAO;
import com.practice.model.PracticeDTO;


@WebServlet("/search.do")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String find_field = request.getParameter("find_field");
		String find_enter = request.getParameter("find_enter");
		
		PracticeDAO dao = PracticeDAO.getInstance();
		List<PracticeDTO> list = dao.searchList(find_field, find_enter);
		
		request.setAttribute("List", list);
		
		RequestDispatcher rd = request.getRequestDispatcher("/view/practice_search.jsp");
		rd.forward(request, response);
		
		
		
	}

}
