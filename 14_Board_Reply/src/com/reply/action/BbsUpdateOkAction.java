package com.reply.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reply.model.BbsDAO;
import com.reply.model.BbsDTO;

public class BbsUpdateOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		// 수정 폼 페이지에서 넘어 온 데이터들을 가지고 DB에서 수정하는 클래스
		
		int bbs_no = Integer.parseInt(request.getParameter("no").trim());
		int nowPage = Integer.parseInt(request.getParameter("page").trim());
		String db_pwd = request.getParameter("db_pwd");
		
		System.out.println("nowPage >>> " + nowPage);
		System.out.println("bbs_no >>> " + bbs_no);
		
		String bbs_writer = request.getParameter("writer").trim();
		String bbs_title = request.getParameter("title").trim();
		String bbs_cont = request.getParameter("content").trim();
		String bbs_pwd = request.getParameter("pwd").trim();
				
		PrintWriter out = response.getWriter();		
		ActionForward forward = new ActionForward();
		
		if(bbs_pwd.equals(db_pwd)) {
			
			System.out.println("수정 비밀번호가 동일합니다.");
			
			BbsDAO dao = BbsDAO.getInstance();
			
			BbsDTO dto = new BbsDTO();
			dto.setBoard_no(bbs_no);
			dto.setBoard_writer(bbs_writer);
			dto.setBoard_title(bbs_title);
			dto.setBoard_cont(bbs_cont);
			dto.setBoard_pwd(bbs_pwd);
			
			int res = dao.updateBbs(dto);
			
			if(res > 0) {
				out.println("<script>");
				out.println("alert('수정완료!')");
				out.println("</script>");
				
				forward.setRedirect(true);
				forward.setPath("bbs_cont.do?no="+bbs_no+"&page="+nowPage);
			}else {
				out.println("<script>");
				out.println("alert('수정실패!')");
				out.println("history.back()");
				out.println("</script>");
			}	
			
		}else {
			System.out.println("수정 비밀번호가 다릅니다.");
			out.println("<script>");
			out.println("alert('수정실패! 비밀번호를 확인하세요!')");
			out.println("history.back()");
			out.println("</script>");
		}				
		
		return forward;
	}

}
