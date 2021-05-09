package com.sist.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.model.EmpDAO;
import com.sist.model.EmpDTO;

/**
 * Servlet implementation class InsertOKServlet
 */
@WebServlet("/insertOK")
public class InsertOKServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public InsertOKServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 사원등록 폼 페이지에서 넘어온 데이터를 DB에 저장하는 작업
		// 1. 한글 인코딩 작업
		request.setCharacterEncoding("UTF-8");
		
		// 2. 사원등록 폼에서 넘어온 데이터들을 받아야 한다.
		int empno = Integer.parseInt(request.getParameter("num").trim());
		String ename = request.getParameter("name").trim();
		String job = request.getParameter("job").trim();
		int mgr = Integer.parseInt(request.getParameter("mgr").trim());
		int sal = Integer.parseInt(request.getParameter("sal").trim());
		int comm = Integer.parseInt(request.getParameter("comm").trim());
		int deptno = Integer.parseInt(request.getParameter("deptno"));
		
		EmpDTO dto = new EmpDTO();
		dto.setEmpno(empno);
		dto.setEname(ename);
		dto.setJob(job);
		dto.setMgr(mgr);
		dto.setSal(sal);
		dto.setComm(comm);
		dto.setDeptno(deptno);
		
		EmpDAO dao = new EmpDAO();
		int res = dao.insertEmp(dto);
		
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		if(res > 0) { // 사원 추가가 성공한 경우
			// select 서블릿 호출
			response.sendRedirect("select");
		}else {	// 사원 추가가 실패한 경우
			out.println("<script>");
			out.println("alert('사원 추가 실패!')");
			out.println("history.back()");	// 이전 페이지로 이동하라는 명령어.
			out.println("</script>");
		} // if문 end	
	} // service문 end
}
