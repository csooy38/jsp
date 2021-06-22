package com.shop.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.controller.Action;
import com.shop.controller.ActionForward;
import com.shop.model.CartDAO;

public class UserCartDelAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 카트번호에 해당하는 주문목록을 DB에서 삭제하는 컨트롤러 클래스
		
		int cart_num = Integer.parseInt(request.getParameter("num"));
		
		CartDAO dao = CartDAO.getInstance();
		int check = dao.deleteCart(cart_num);
		
		PrintWriter out = response.getWriter();
		ActionForward forward = new ActionForward();
		
		if(check > 0) {
			dao.updateCartNO(cart_num);
			
			forward.setRedirect(true);
			forward.setPath("user_cart_list.do");
		}else {
			out.println("<script>");
			out.println("alert('삭제 실패!')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		return forward;
	}

}
