package com.admin.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.controller.Action;
import com.shop.controller.ActionForward;
import com.shop.model.ProductDAO;

public class AdminProdDelAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// get 방식으로 넘어온 상품번호로 DB에서 해당 상품을 삭제한느 컨트롤러 클래스. 
		
		int p_num = Integer.parseInt(request.getParameter("pnum").trim());
		
		ProductDAO dao = ProductDAO.getInstance();
		int check = dao.deleteProduct(p_num);
		
		PrintWriter out = response.getWriter();
		ActionForward forward = new ActionForward();
		
		if(check > 0) {
			forward.setRedirect(true);
			forward.setPath("admin_prod_list.do");
		}else {
			out.println("<script>");
			out.println("alert('상품삭제 실패!')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		return forward;
	}

}
