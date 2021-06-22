package com.member.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberDAO;
import com.member.model.MemberDTO;

public class MemberListAction implements Action {
 
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		// member 테이블에서 회원 전체 리스트를 조회하여 view page로 이동시키는 작업
		MemberDAO dao = MemberDAO.getInstance();
		List<MemberDTO> memberList = dao.getMemberList();
		
		// DB에서 가져온 전체 회원 리스트를 view page로 이동시키자.
		request.setAttribute("List", memberList);
		
		return "view/member_list.jsp";
	}
}
