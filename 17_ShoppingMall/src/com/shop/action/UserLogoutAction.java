package com.shop.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shop.controller.Action;
import com.shop.controller.ActionForward;

public class UserLogoutAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 세션으로 설정된 정보를 만료시키고 로그인 페이지로 이동시키는 컨트롤러 클래스
		
		HttpSession session = request.getSession();
		
		session.invalidate();  		// 세션 속성을 만료시키는 명령어.
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("member_login.do");
		
		return forward;
	}

}
