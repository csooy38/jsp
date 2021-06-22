package com.product.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.product.model.ProductDAO;
import com.product.model.ProductDTO;


@WebServlet("/updateOk")
public class UpdateOkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public UpdateOkServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int product_pnum = Integer.parseInt(request.getParameter("num"));
		int product_input = Integer.parseInt(request.getParameter("input_price").trim());
		int product_output = Integer.parseInt(request.getParameter("output_price").trim());
		int product_trans = Integer.parseInt(request.getParameter("trans_cost").trim());
		int product_mileage = Integer.parseInt(request.getParameter("mileage").trim());
		String product_company = request.getParameter("company").trim();
		
		ProductDTO dto = new ProductDTO();
		
		dto.setPnum(product_pnum);
		dto.setInput_price(product_input);
		dto.setOutput_price(product_output);
		dto.setTrans_cost(product_trans);
		dto.setMileage(product_mileage);
		dto.setCompany(product_company);
		
		ProductDAO dao = ProductDAO.getInstance();
		
		int res = dao.updateProducts(dto);
		request.setAttribute("cont", dto);
		
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		if(res != 0) {
			out.println("<script>");
			out.println("alert('제품 정보 수정 성공!')");
			out.println("location.href='content.do?num=" + dto.getPnum()+"'");
			out.println("</script>");
		}else {
			out.println("<script>");
			out.println("alert('제품 정보 수정 실패!')");
			out.println("history.back()");
			out.println("</script>");
		}
		
	}

}
