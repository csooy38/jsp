package com.admin.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.shop.controller.Action;
import com.shop.controller.ActionForward;
import com.shop.model.ProductDAO;
import com.shop.model.ProductDTO;

public class AdminProdInputOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 상품등록 폼 페이지에서 넘어온 데이터들을 DB에 저장하는 컨트롤러 클래스.
		
		// 첨부파일이 저장될 위치(경로)를 설정
		String saveFolder = "C:\\NCS\\transfer\\17_ShoppingMall\\WebContent\\upload";
		
		// 첨부파일 용량(크기) 제한 - 파일 업로드 최대 크기
		int fileSize = 5*1024*1024; // 5MB
		
		// 이진 파일 업로드를 위한 객체 생성
		MultipartRequest multi = new MultipartRequest(request, saveFolder, fileSize, "UTF-8", new DefaultFileRenamePolicy());
		
		String p_category = multi.getParameter("p_category").trim();
		String p_name = multi.getParameter("p_name").trim();
		String p_company = multi.getParameter("p_company").trim();
		int p_qty = Integer.parseInt(multi.getParameter("p_qty").trim());
		int p_price = Integer.parseInt(multi.getParameter("p_price").trim());
		String p_spec = multi.getParameter("p_spec").trim();
		String p_content = multi.getParameter("p_content").trim();
		int p_point = Integer.parseInt(multi.getParameter("p_point").trim());
		
		// getFilesystemName() : 서버 상에 실제로 업로드 될 파일 이름을 문자열로 반환하는 메서드.
		String p_image = multi.getFilesystemName("p_image");
		
		ProductDTO dto = new ProductDTO();
		
		dto.setPcategory_fk(p_category);
		dto.setPname(p_name);
		dto.setPcompany(p_company);
		dto.setPqty(p_qty);
		dto.setPrice(p_price);
		dto.setPspec(p_spec);
		dto.setPcontents(p_content);
		dto.setPoint(p_point);		
		dto.setPimage(p_image);
		
		ProductDAO dao = ProductDAO.getInstance();
 		int check = dao.insertProduct(dto);
		
 		PrintWriter out = response.getWriter();
 		ActionForward forward = new ActionForward();
 		
 		if(check > 0) {
 			forward.setRedirect(true);
 			forward.setPath("admin_prod_list.do");
 		}else {
 			out.println("<script>");
			out.println("alert('상품등록 실패!')");
			out.println("history.back()");
			out.println("</script>");
 		}
		
		return forward;
	}

}
