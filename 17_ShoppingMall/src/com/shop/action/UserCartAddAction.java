package com.shop.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.controller.Action;
import com.shop.controller.ActionForward;
import com.shop.model.CartDAO;
import com.shop.model.CartDTO;

public class UserCartAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 상품 상세내역에서 장바구니 버튼을 클릭하면 해당 데이터들을 장바구니 테이블에 저장하는 컨트롤러 클래스
		
		String cart_name = request.getParameter("p_name").trim();
		int cart_price = Integer.parseInt(request.getParameter("p_price"));
		int cart_qpty = Integer.parseInt(request.getParameter("p_qty"));
		
		// type="hidden" 으로 넘어온 데이터도 받아 주어야 한다.
		int cart_pnum = Integer.parseInt(request.getParameter("p_num"));
		String cart_pspec = request.getParameter("p_spec").trim();
		String cart_pimage = request.getParameter("p_image").trim();
		
		CartDTO dto = new CartDTO();
		
		dto.setCart_pnum(cart_pnum);
		dto.setCart_pname(cart_name);
		dto.setCart_pspec(cart_pspec);
		dto.setCart_pimage(cart_pimage);
		dto.setCart_price(cart_price);
		dto.setCart_pqty(cart_qpty);
		
		CartDAO dao = CartDAO.getInstance();
		int check = dao.insertCart(dto);
		
		PrintWriter out = response.getWriter();
		ActionForward forward = new ActionForward();
		
		if(check > 0) {
			forward.setRedirect(true);
			forward.setPath("user_cart_list.do");
		}else {
			out.println("<script>");
			out.println("alert('장바구니 담기 실패!')");
			out.println("history.back()");
			out.println("</script>");
		}
		return forward;
	}

}
