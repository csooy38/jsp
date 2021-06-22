package com.member.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberDAO;
import com.member.model.MemberDTO;

public class MemberContentAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// get방식으로 넘어온 회원번호에 해당하는 회원의 정보를 조회하는 클래스
		int member_num = Integer.parseInt(request.getParameter("num")); 
		
		MemberDAO dao = MemberDAO.getInstance();
		MemberDTO content = dao.contentMember(member_num);
		
		request.setAttribute("cont", content);
		
		return "view/member_content.jsp";
	}

}
