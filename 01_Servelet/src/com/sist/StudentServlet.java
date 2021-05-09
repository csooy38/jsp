package com.sist;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet({ "/student", "/stu" })
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public StudentServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// method="post"인 경우에는 한글 깨짐 현상 발생.
		// 한글이 깨지지 않게 설정을 해야 한다.
		request.setCharacterEncoding("UTF-8");
		
		// 1단계 : Ex05.jsp 페이지에서 넘어온 데이터들을 처리해 주자.
		String hakbun = request.getParameter("hakbun");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		
		// 체크박스는 배열 형태로 저장되어야 하므로 
		// getParameterValues()를 활용한다. 반환형은 String[] 배열타입.
		String[] major = request.getParameterValues("major");
		
		// 2단계 : 웹 브라우저에 요청한 결과를 화면에 보여 주자.
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<body>");
		out.println("<h2>");
		out.println("학번 : " + hakbun + "<br>");
		out.println("이름 : " + name + "<br>");
		out.println("전공과목 : ");
		
		// 배열로 저장된 전공과목 출력
		for(int i=0; i<major.length; i++) {
			out.println(major[i] + "&nbsp;&nbsp;&nbsp;");
		}

		out.println("<br>연락처 : " + phone + "<br>");
		out.println("</h2>");
		out.println("</body>");
		out.println("</html>");
	}

}
