package com.upload.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.upload.model.UploadDAO;
import com.upload.model.UploadDTO;

public class UploadDeleteOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 삭제 폼 페이지에서 넘어 온 글 번호와 비밀번호로 DB에서 해당 글을 삭제하는 클래스
		
		int upload_no = Integer.parseInt(request.getParameter("upload_no").trim());
		String upload_pwd = request.getParameter("upload_pwd").trim();
		
		UploadDAO dao = UploadDAO.getInstance();
		UploadDTO dto = dao.uploadContent(upload_no);
		
		// upload된 파일까지 삭제
		String up = "C:\\NCS\\transfer\\15_Board_FileUpload\\WebContent\\upload";
		
		// 업로드된 파일명 : /년-월-일/파일명
		String fileName = dto.getUpload_file();
		
		PrintWriter out = response.getWriter();
		ActionForward forward = new ActionForward();		
		
		int res = 0;
		
		if(!upload_pwd.equals(dto.getUpload_pwd())) {	// 비밀번호가 틀린 경우
			out.println("<script>");
			out.println("alert('삭제 실패! 비밀번호를 확인하세요.')");
			out.println("history.back()");
			out.println("</script>");
		}else {	// 비밀번호가 같은 경우
			res = dao.deleteUpload(upload_no);
			
			if(fileName != null) {	// 첨부파일이 있는 경우
				File file = new File(up+fileName);
				file.delete();	// 기존 이진 파일을 삭제하는 메서드
			}
			
			
			
			if(res > 0) {
				forward.setRedirect(true);
				forward.setPath("upload_list.do");
			}else {
				out.println("<script>");
				out.println("alert('삭제 실패!')");
				out.println("history.back()");
				out.println("</script>");
			}
		}
		
		return forward;
	}

}
