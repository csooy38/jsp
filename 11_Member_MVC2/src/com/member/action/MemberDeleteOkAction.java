package com.member.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberDAO;

public class MemberDeleteOkAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int member_num = Integer.parseInt(request.getParameter("mem_num"));
		String member_pwd = request.getParameter("mem_pwd");
		
		MemberDAO dao = MemberDAO.getInstance();
		int res = dao.deleteMember(member_num, member_pwd);
		
		PrintWriter out = response.getWriter();
		
		if(res > 0) {
			out.println("<script>");
			out.println("alert('회원 삭제 성공!')");
			out.println("location.href='select.do'");
			out.println("</script>");
		}else if(res == -1) {
			out.println("<script>");
			out.println("alert('회원 삭제 실패! 비밀번호를 확인하세요.')");
			out.println("history.back()");
			out.println("</script>");
		}else {
			out.println("<script>");
			out.println("alert('회원 삭제 실패!')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		return null;
	}


}
