package com.reply.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reply.model.BbsDAO;
import com.reply.model.BbsDTO;

public class BbsDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// get 방식으로 넘어 온 글 번호에 해당하는 게시글을 DB에서 삭제하는 클래
		
		int nowPage = Integer.parseInt(request.getParameter("page").trim());
		int bbs_no = Integer.parseInt(request.getParameter("no").trim());
		
		BbsDAO dao = BbsDAO.getInstance();
		BbsDTO dto = dao.getBbsCont(bbs_no);
		
		request.setAttribute("del", dto);
		request.setAttribute("page", nowPage);
		request.setAttribute("no", bbs_no);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("view/bbs_delete.jsp");
		
		return forward;
	}

}
