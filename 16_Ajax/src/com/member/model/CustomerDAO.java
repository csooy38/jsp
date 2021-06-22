package com.member.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class CustomerDAO {
	Connection con = null; // DB 연결하는 객체.
	PreparedStatement pstmt = null; // DB에 SQL문을 전송하는 객체.
	ResultSet rs = null; // SQL문을 실행 후 결과 값을 가지고 있는 객체.

	String sql = null; // 쿼리문을 저장할 객체.

	// 싱글톤 방식으로 UploadDAO 객체를 만들자.
	// 1단계 : 싱글톤 방식으로 객체를 만들기 위해서는 우선적으로
	// 기본생성자의 접근제어자를 private 으로 선언을 해야 함.
	// 2단계 : 정적 멤버로 선언을 해야 함 - static 으로 선언을 한다는 의미.
	private static CustomerDAO instance = null;

	// 3단계 : 외부에서 객체 생성을 하지 못하게 접근을 제어 - private 기본 생성자를 만듬.
	private CustomerDAO() {
	}

	// 4단계 : 기본 생성자 대신에 싱긑턴 객체를 return을 해 주는 getInstance()
	// 메서드를 만들어서 여기에 접근하게 하는 방법
	public static CustomerDAO getInstance() {
		if (instance == null) {
			instance = new CustomerDAO();
		}
		return instance;
	} // getInstance() 메서드 end

	// DB 연동하는 작업을 진행하는 메서드 - DBCP방식으로 연결 진행
	public void openConn() {

		try {
			// 1단계 : JNDI 서버 객체 생성.
			Context ctx = new InitialContext();

			// 2단계 : lookup() 메서드를 이용하여 매칭되는 커넥션을 찾는다.
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/myoracle");

			// 3단계 : DataSource 객체를 이용하여 커넥션 객체를 하나 가져온다.
			con = ds.getConnection();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("연결 실패");
		}

	} // openConn() 메서드 end

	// DB에 연결된 객체를 종료하는 메서드
	public void closeConn(ResultSet rs, PreparedStatement pstmt, Connection con) {

		try {

			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} // closeConn() end

	// customer 테이블의 전체 레코드를 조회하는 메서드
	public String getCustomerList() {
		String result = "";
		
		try {
			openConn();
			sql = "select * from customer order by no desc";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			result += "<customers>";
			
			while(rs.next()) {
				result += "<customer>";
				result += "<no>"+rs.getInt("no")+"</no>";
				result += "<id>"+rs.getString("id")+"</id>";
				result += "<name>"+rs.getString("name")+"</name>";
				result += "<age>"+rs.getInt("age")+"</age>";
				result += "<phone>"+rs.getString("phone")+"</phone>";
				result += "<addr>"+rs.getString("addr")+"</addr>";
				result += "</customer>";
				
			}
			
			result += "</customers>";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return result;
	} // getCustomerList() end
	
	// 입력폼 창에서 넘어온 아이디가 중복인지 여부를 확인하는 메서드
	public String idCheck(String user_id){
		String result = "사용 가능합니다.";
		
		try {
			openConn();
			sql = "select * from customer where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
					result = "중복입니다.";
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return result;
	} // idCheck() end
	
	// 입력폼에서 넘어온 데이터들을 DB에 저장하는 메서드
	public int insertCustomer(CustomerDTO dto) {
		int result = 0, count = 0;
		
		try {
			openConn();
			sql = "select count(*) from customer";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1) + 1;
			}
			
			sql = "insert into customer values(?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, count);
			pstmt.setString(2, dto.getId());
			pstmt.setString(3, dto.getName());
			pstmt.setInt(4, dto.getAge());
			pstmt.setString(5, dto.getPhone());
			pstmt.setString(6, dto.getAddr());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return result;
	} // insertCustomer() end
	
	// get 방식으로 받은 번호에 해당하는 회원을 삭제하는 메서드
	public int deleteCustomer(String user_no) {
		int result = 0;

		try {
			openConn();
			sql = "delete from customer where no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(user_no));
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return result;
	} // deleteCustomer() end
	
	
	// 삭제된 글 번호보다 큰 글번호들은 번호를 하나씩 감소시키는 메서드
	public void numberCheck(String user_no) {
		
		try {
			openConn();
			sql = "update customer set no = no - 1 where no > ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(user_no));
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
	} // numberCheck() end
}
