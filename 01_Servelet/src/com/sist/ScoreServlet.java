package com.sist;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/score")
public class ScoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ScoreServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// method="post"이므로 한글 인코딩 처리
		request.setCharacterEncoding("UTF-8");
		
		// 1단계 : Ex06.jsp 페이지에서 넘어온 데이터들을 처리해 주자.
		String name = request.getParameter("name");
		int kor = Integer.parseInt(request.getParameter("kor"));
		int eng = Integer.parseInt(request.getParameter("eng"));
		int mat = Integer.parseInt(request.getParameter("mat"));
		int java = Integer.parseInt(request.getParameter("java"));
		
		// 2단계 : 총점 및 평균을 구하자.
		int sum = kor+eng+mat+java;
		double avg = sum/4.0;
		
		// 3단계 : 학점을 구해 보자.
		String rank = "";
	
		if(avg>=90) {
			rank="A학점";
		}else if(avg>=80) {
			rank="B학점";
		}else if(avg>=70) {
			rank="C학점";
		}else if(avg>=60) {
			rank="D학점";
		}else {
			rank="F학점";
		}
		
		// 4단계 : 웹 브라우저에 성적 결과를 출력해 보자. 
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head>");
		out.println("<title>성적 조회</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<table border='1' cellspacing='0'>");
		out.println("<tr>");
		out.println("<th>이     름</th>");
		out.println("<td>" + name + "</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<th>국어점수</th>");
		out.println("<td>" + kor + "</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<th>영어점수</th>");
		out.println("<td>" + eng + "</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<th>수학점수</th>");
		out.println("<td>" + mat + "</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<th>자바점수</th>");
		out.println("<td>" + java + "</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<th>총     점</th>");
		out.println("<td>" + sum + "</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<th>평     균</th>");
		out.println("<td>" + String.format("%.2f점", avg)+ "</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<th>학     점</th>");
		out.println("<td>" + rank + "</td>");
		out.println("</tr>");
		out.println("</table>");
		out.println("</body>");
		out.println("</html>");
	}

}
