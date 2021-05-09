package com.sist.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.model.EmpDAO;


@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// [삭 제]라는 글자 클릭 시 사원번호를 가져와 DB에서 
		// 사원번호에 해당하는 사원정보를 삭제하는 작업.
		
		// select.jsp에서 get방식으로 넘겨받은 no변수를 empno 변수에 저장한다.
		int empno = Integer.parseInt(request.getParameter("no"));
		
		EmpDAO dao = new EmpDAO();
		int res = dao.deleteEmp(empno);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		if(res > 0) { // 사원 삭제가 성공한 경우
			response.sendRedirect("select");
		}else { // 사원 삭제가 실패한 경우
			out.println("<script>");
			out.println("alert('사원 삭제 실패!')");
			out.println("history.back()");
			out.println("</script>");
		} // if문 end
	} // service문 end
}
