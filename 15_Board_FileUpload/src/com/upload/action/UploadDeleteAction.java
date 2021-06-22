package com.upload.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.upload.model.UploadDAO;
import com.upload.model.UploadDTO;

public class UploadDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// get 방식으로 넘어온 글 번호를 자료실 삭제 폼 페이지로 이동시키는 클래스 
		
		int upload_no = Integer.parseInt(request.getParameter("no"));
		
		request.setAttribute("number", upload_no);
		
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		forward.setPath("view/upload_delete.jsp");
		
		return forward;
	}

}
