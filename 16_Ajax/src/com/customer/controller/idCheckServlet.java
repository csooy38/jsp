package com.customer.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.CustomerDAO;

@WebServlet("/idCheck.do")
public class idCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public idCheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// customer 테이블에서 중복된 아이디가 있는지 여부를 확인하는 메서드.
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String user_id = request.getParameter("id").trim();
		
		CustomerDAO dao = CustomerDAO.getInstance();
		String res = dao.idCheck(user_id);
		
		// 아이디 중복 체크 결과를 클라이언트 쪽으로 전송한다.
		out.println(res);
	}

}
