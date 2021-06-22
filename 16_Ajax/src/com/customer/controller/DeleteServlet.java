package com.customer.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.CustomerDAO;

@WebServlet("/delete.do")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// customer 테이블에서 회원을 삭제하는 메서드

		response.setContentType("text/html; charset=UTF-8");

		String user_no = request.getParameter("no");

		CustomerDAO dao = CustomerDAO.getInstance();
		int res = dao.deleteCustomer(user_no);

		if (res > 0) {
			dao.numberCheck(user_no);
		}
		
		PrintWriter out = response.getWriter();

		out.print(res);
	}

}
