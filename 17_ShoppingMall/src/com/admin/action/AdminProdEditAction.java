package com.admin.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.controller.Action;
import com.shop.controller.ActionForward;
import com.shop.model.CategoryDAO;
import com.shop.model.CategoryDTO;
import com.shop.model.ProductDAO;
import com.shop.model.ProductDTO;

public class AdminProdEditAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// get방식으로 넘어온 상품 번호에 해당하는 상품의 상세내역을 조회하여 수정 폼 페이지로 전달하는 컨트롤러 클래스
		
		int pnum = Integer.parseInt(request.getParameter("pnum").trim());
		
		ProductDAO dao = ProductDAO.getInstance();
		ProductDTO dto = dao.productCont(pnum);
		
		CategoryDAO cdao = CategoryDAO.getInstance();
		List<CategoryDTO> list = cdao.getCategoryList();
		
		request.setAttribute("productDTO", dto);
		request.setAttribute("categoryList", list);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("admin/admin_product_edit.jsp");
		
		return forward;
	}

}
