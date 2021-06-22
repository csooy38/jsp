package com.upload.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.upload.action.Action;
import com.upload.action.ActionForward;

public class FrontController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// 한글 인코딩 작업
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// getRequestURI() : 현재 프로젝트명과 파일명을 문자열로 반환하는 메서드.
		//  ==> 반환되는 문자열은 "/14_Board_reply/*.do"
		String uri = request.getRequestURI();
		
		// getContextPath() : 현재 프로젝트명을 문자열로 반환하는 메서드.
		// 	==> 반환되는 문자열은 /14_Board_reply""
		String project_path = request.getContextPath();
		
		// command 문자열 변수에는 *.do만 반환된다.
		String command = uri.substring(project_path.length()+1);
		System.out.println("command >>> " + command);
		
		Action action = null;
		ActionForward forward = null;
		
		Properties prop = new Properties();
		
		/*
		 * java.util.Properties 클래스
		 * - Properties 클래스는 HashTable의 하위 클래스.
		 * - 보통은 환경 변수 및 속성 값을 Properties 파일에 저장하여 쉽게 접근할 수 있는 장점이 있다.
		 * - Properties 파일은 일련의 키(key)-값(value)의 한 쌍으로 이루어져 있다. 
		 * - 보통은 파일에 저장하여 사용한다. 파일 이름을 *.properties 으로 끝나게 한다.
		 * - InputStream 에 Properties 파일을 인자로 넣어서 그 스트림으로부터 파일을 가져올 때 가장 많이 사용한다.
		 * 	인자로 들어온 Properties 파일을 읽게 된다.
		 * - load() 메서드를 이용하여 파일을 읽어들이게 된다. 
		 */
		
		FileInputStream fis = new FileInputStream("C:\\NCS\\transfer\\15_Board_FileUpload\\src\\com\\upload\\controller\\mapping.properties");
		prop.load(fis);
		
		// 키를 호출하면 value를, value를 호출하면 키 값을 반환한다.
		String value = prop.getProperty(command);
		System.out.println("value >>> " + value);
		
		if(value.substring(0, 7).equals("execute")) {
			
			StringTokenizer st = new StringTokenizer(value, "|");
			String url_1 = st.nextToken();	// "execute"
			String url_2 = st.nextToken();	// "패키지명.클래스명"
			
			/*
			 * 동적 객체 생성 : newInstance()
			 * - Class 객체를 이용하면 new 연산자의 사용 없이 동적으로 객체 생성이 가능하다.
			 * - 코드 작성 시에 클래스의 이름을 결정할 수 없고, 런타임(실행) 시에 클래스의 이름이 결정되는 경우에 유용하게 사용된다.
			 * 
			 * - newInstance() 메서드는 기본 생성자를 호출해서 객체를 생성하기 때문에 반드시 클래스에 기본 생성자가 존재해야 한다.
			 * 	예외가 발생할 수 있는데, 해당 클래스가 추상클래스이거나 인터페이스일 경우 발생하며 또한 클래스의 생성자가
			 * 	접근 제한자로 인해 접근할 수 없는 경우에도 발생한다. 따라서 예외처리를 해주어야 한다. 
			 * 
			 * - 반환타입은 Objects 타입이므로 맞게 형변환을 하면 되지만, 클래스의 타입을 모르는 상태이므로 형변환을 할 수가 없다.
			 * 	이러한 문제를 해결하기 위해서 인터페이스를 사용한다. 
			 * - Class.forName(class 이름)은 파라미터로 받은 class 이름에 해당하는 클래스를 로딩한 후에 
			 * 	그 클래스에 해당하는 인터페이스를 리턴한다. newInstance() 메서드는 로딩한 클래스의 객체를 생성하는 메서드이고
			 * 	이렇게 생성된 객체를 동적 객체 생성이라고 한다.   
			 */
			
			try {
				// url_2(패키지명.클래스명)를 로딩하겠다.
				Class url = Class.forName(url_2); 	
				
				// 동적으로 객체 생성
				// action = new BbsListAction(); 과 동일한 개념
				action = (Action)url.newInstance();
				
				forward = action.execute(request, response);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}else {	// value 값이 "execute" 문자열이 아닌 경우
			// view page로 이동
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath(value);
		}
		
		if(forward != null) {
			if(forward.isRedirect()) {	// true 인 경우 다시 *.do 실행
				response.sendRedirect(forward.getPath());
			}else {	// false 인 경우 view page로 이동
				RequestDispatcher rd = request.getRequestDispatcher(forward.getPath());
				rd.forward(request, response);
			}
		}
		
		
		
		
		
		
				
	}
}
