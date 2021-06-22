package com.reply.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reply.model.BbsDAO;
import com.reply.model.BbsDTO;

public class BbsDeleteOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 삭제 폼 페이지에서 넘어온 데이터들을 가지고 DB에서 해당 게시글을 삭제하는 클래스.
		
		int bbs_no = Integer.parseInt(request.getParameter("no").trim());
		int nowPage = Integer.parseInt(request.getParameter("page").trim());
		String db_pwd = request.getParameter("db_pwd").trim();
		int bbs_group = Integer.parseInt(request.getParameter("group").trim());
		int bbs_step = Integer.parseInt(request.getParameter("step").trim());
		
		String bbs_pwd = request.getParameter("pwd").trim();
		
		
		
		PrintWriter out = response.getWriter();
		ActionForward forward = new ActionForward();
		
		if(bbs_pwd.equals(db_pwd)) {			
			
			BbsDTO dto = new BbsDTO();
			
			dto.setBoard_no(bbs_no);
			dto.setBoard_pwd(bbs_pwd);
			dto.setBoard_group(bbs_group);
			dto.setBoard_step(bbs_step);				
			
			BbsDAO dao = BbsDAO.getInstance();
			int res = dao.deleteBbs(dto);
			//dao.updateNo(bbs_no);
					
			if(res > 0) {
				forward.setRedirect(true);
				forward.setPath("bbs_list.do?page="+nowPage);
			}else if(res == -2) {	// 답글이 있는 게시물의 경우
				out.println("<script>");
				out.println("alert('삭제 실패! 답글이 존재하는 글은 삭제할 수 없습니다.')");
				out.println("location.href='bbs_cont.do?no="+bbs_no+"&page="+nowPage+"'");
				out.println("</script>");
			}else {
				out.println("<script>");
				out.println("alert('삭제 실패!')");
				out.println("history.back()");
				out.println("</script>");
			}
		}else {	// 비밀번호가 틀린 경우
			out.println("<script>");
			out.println("alert('삭제 실패! 비밀번호를 확인하세요.')");
			out.println("history.back()");
			out.println("</script>");			
		}
		
		return forward;
	}

}
