package com.shop.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class CartDAO {
	Connection con = null; // DB 연결하는 객체.
	PreparedStatement pstmt = null; // DB에 SQL문을 전송하는 객체.
	ResultSet rs = null; // SQL문을 실행 후 결과 값을 가지고 있는 객체.

	String sql = null; // 쿼리문을 저장할 객체.

	// 싱글톤 방식으로 CartDAO 객체를 만들자.
	// 1단계 : 싱글톤 방식으로 객체를 만들기 위해서는 우선적으로
	// 기본생성자의 접근제어자를 private 으로 선언을 해야 함.
	// 2단계 : 정적 멤버로 선언을 해야 함 - static 으로 선언을 한다는 의미.
	private static CartDAO instance = null;

	// 3단계 : 외부에서 객체 생성을 하지 못하게 접근을 제어 - private 기본 생성자를 만듬.
	private CartDAO() {
	}

	// 4단계 : 기본 생성자 대신에 싱긑턴 객체를 return을 해 주는 getInstance()
	// 메서드를 만들어서 여기에 접근하게 하는 방법
	public static CartDAO getInstance() {
		if (instance == null) {
			instance = new CartDAO();
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
	
	// shop_cart 테이블에 저장하는 메서드
	public int insertCart(CartDTO dto) {
		int result = 0, count = 0;
		
		try {
			openConn();
			sql = "select count(*) from shop_cart";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1) + 1;
			}
			
			sql = "insert into shop_cart values(?, ?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, count);
			pstmt.setInt(2, dto.getCart_pnum());
			pstmt.setString(3, dto.getCart_pname());
			pstmt.setInt(4, dto.getCart_pqty());
			pstmt.setInt(5, dto.getCart_price());
			pstmt.setString(6, dto.getCart_pspec());
			pstmt.setString(7, dto.getCart_pimage());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return result;
	} // insertCart() end
	
	
	// shop_cart 테이블의 전체 리스트를 조회하는 메서드
	public List<CartDTO> getCartList() {
		List<CartDTO> list = new ArrayList<CartDTO>();
		
		try {
			openConn();
			sql = "select * from shop_cart order by cart_num desc";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CartDTO dto = new CartDTO();
				
				dto.setCart_num(rs.getInt("cart_num"));
				dto.setCart_pnum(rs.getInt("cart_pnum"));
				dto.setCart_pname(rs.getString("cart_pname"));
				dto.setCart_pqty(rs.getInt("cart_pqty"));
				dto.setCart_price(rs.getInt("cart_price"));
				dto.setCart_pspec(rs.getString("cart_pspec"));
				dto.setCart_pimage(rs.getString("cart_pimage"));
				
				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return list;
	} // getCartList() end
	
	
	public int deleteCart(int cart_num) {
		int result = 0;
		
		try {
			openConn();
			sql = "delete from shop_cart where cart_num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cart_num);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		
		return result;
	} // deleteCart() end
	
	
	public void updateCartNO(int cart_num) {
		
		try {
			openConn();
			sql = "update shop_cart set cart_num = cart_num -1 where cart_num > ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cart_num);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
	} // updateCartNO() end
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
