package com.product.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
	
	Connection con = null;				// DB와 연결하는 객체
	PreparedStatement pstmt = null; 	// DB에 SQL문을 전송하는 객체
	ResultSet rs = null;				// SQL문을 실행 후 결과값을 가지고 있는 객체
	String sql = null;					// 쿼리문을 저장할 객체
	
	// 싱글톤 방식으로 ProductDAO 객체를 만들자.
	// 1단계 : 싱글톤 방식으로 객체를 만들기 위해서는 
	//	우선적으로 기본생성자의 접근제어자를 private으로 선언해야 한다. 
	// 2단계 : 정적 멤버로 선언을 해야 한다. - static으로 선언
	private static ProductDAO instance = null;
	
	// 3단계 : 외부에서 객체 생성을 하지 못하게 접근을 제어 - private 기본 생성자를 생성
	private ProductDAO() {	}
	
	// 4단계 : 기본 생성자 대신에 싱클톤 객체를 return 해주는 
	//	getInstance() 메서드를 만들어서 여기에 접근하게 하는 방법
	public static ProductDAO getInstance() {
		if(instance == null) {
			instance = new ProductDAO();
		}
		return instance;
	}
	
	// DB 연동하는 작업을 진행하는 메서드
	public void openConn() {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String user = "web";
		String password = "1234";
		
		try {
			// 1단계 : 오라클 드라이버 로딩
			Class.forName(driver);
			
			// 2단계 : DB(오라클)와 연결
			con = DriverManager.getConnection(url, user, password);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // openConn() 메서드 end
	
	// products 테이블에서 전체 리스트를 조회하는 메서드
	public List<ProductDTO> getProductList() {
		List<ProductDTO> list = new ArrayList<ProductDTO>();	
		
		try {
			// 오라클 데이터베이스 로딩 및 DB 연동 작업 메서드 호출
			openConn();
			sql = "select * from products order by pnum desc";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ProductDTO dto = new ProductDTO();
				
				dto.setPnum(rs.getInt("pnum"));
				dto.setCategory_fk(rs.getString("category_fk"));
				dto.setProducts_name(rs.getString("products_name"));
				dto.setEp_code_fk(rs.getString("ep_code_fk"));
				dto.setInput_price(rs.getInt("input_price"));
				dto.setOutput_price(rs.getInt("output_price"));
				dto.setTrans_cost(rs.getInt("trans_cost"));
				dto.setMileage(rs.getInt("mileage"));
				dto.setCompany(rs.getString("company"));
				
				list.add(dto);
			}
			
			rs.close(); pstmt.close(); con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
		
	} // getProductList() end
	
	// DB에 제품을 등록하는 메서드
	public int insertProduct(ProductDTO dto) {
		int result = 0;
		
		openConn();
		
		try {
			sql = "insert into products values(products_seq.nextval, ?,?,?,?,?,?,?,?,1)";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getCategory_fk());
			pstmt.setString(2, dto.getProducts_name());
			pstmt.setString(3, dto.getEp_code_fk());
			pstmt.setInt(4, dto.getInput_price());
			pstmt.setInt(5, dto.getOutput_price());
			pstmt.setInt(6, dto.getTrans_cost());
			pstmt.setInt(7, dto.getMileage());
			pstmt.setString(8, dto.getCompany());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
		
	} // insertProduct() end
	
	// 제품 번호에 해당하는 제품을 조회하는 메서드.
	public ProductDTO getContentProduct(int product_num) {
		ProductDTO dto = new ProductDTO();	
		
		try {
			openConn();
			sql = "select * from products where pnum = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, product_num);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) { // 데이터가 한 줄 뿐이므로 while 쓸 필요X
				dto.setPnum(rs.getInt("pnum"));
				dto.setCategory_fk(rs.getString("category_fk"));
				dto.setProducts_name(rs.getString("products_name"));
				dto.setEp_code_fk(rs.getString("ep_code_fk"));
				dto.setInput_price(rs.getInt("input_price"));
				dto.setOutput_price(rs.getInt("output_price"));
				dto.setTrans_cost(rs.getInt("trans_cost"));
				dto.setMileage(rs.getInt("mileage"));
				dto.setCompany(rs.getString("company"));
			}
			
			rs.close(); pstmt.close(); con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dto;
		
	} // getContentProduct() end
	
	public int updateProducts(ProductDTO dto) {
		int result = 0;
			
		try {
			openConn();
			sql = "update products set input_price=?, output_price=?, trans_cost=?, mileage=?, company=? where pnum =?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, dto.getInput_price());
			pstmt.setInt(2, dto.getOutput_price());
			pstmt.setInt(3, dto.getTrans_cost());
			pstmt.setInt(4, dto.getMileage());
			pstmt.setString(5, dto.getCompany());
			pstmt.setInt(6, dto.getPnum());
			
			result = pstmt.executeUpdate();
			
			pstmt.close(); con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
		
	} // updateProducts() end

	public int deleteProduct(int product_pnum) {
		int result = 0;		
		
		try {
			openConn();			
			sql = "delete from products where pnum = ?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, product_pnum);
			
			result = pstmt.executeUpdate();
			
			pstmt.close(); con.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
		
	} // deleteProduct() end
	
}
