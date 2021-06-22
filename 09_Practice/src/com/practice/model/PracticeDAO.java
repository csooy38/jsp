package com.practice.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class PracticeDAO {

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = null;
	
	private static PracticeDAO instance = null;
	
	private PracticeDAO() { }
	
	public static PracticeDAO getInstance() {
		if(instance == null) {
			instance = new PracticeDAO();
		}
		return instance;
	}
	
	public void openConn() {
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/myoracle");
			con = ds.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	} // openConn() end
	
	public List<PracticeDTO> selectList() {
		List<PracticeDTO> list = new ArrayList<PracticeDTO>();
		
		try {
			openConn();
			sql = "select * from practice order by practice_no desc";
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				PracticeDTO dto = new PracticeDTO();
				
				dto.setPractice_no(rs.getInt("practice_no"));
				dto.setPractice_writer(rs.getString("practice_writer"));
				dto.setPractice_title(rs.getString("practice_title"));
				dto.setPractice_cont(rs.getString("practice_cont"));
				dto.setPractice_pwd(rs.getString("practice_pwd"));
				dto.setPractice_hit(rs.getInt("practice_hit"));
				dto.setPractice_regdate(rs.getString("practice_regdate").substring(0, 10));
				dto.setPractice_head(rs.getString("practice_head"));
				
				list.add(dto);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
		
	} // selectList() end
	
	public void hitContent(int no) {

		try {
			openConn();
			sql = "update practice set practice_hit = practice_hit + 1 where practice_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			rs.close(); pstmt.close(); con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	} // hitContent() end
	
	public List<PracticeDTO> selectHead() {
		List<PracticeDTO> list = new ArrayList<>();
		
		try {
			openConn();
			sql = "select distinct practice_head from practice order by practice_head";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				PracticeDTO dto = new PracticeDTO();
				
				dto.setPractice_head(rs.getString("practice_head"));
				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		return list;
	} // selectHead() end
	
	public PracticeDTO contentPractice(int no) {
		PracticeDTO dto = new PracticeDTO();

		try {
			openConn();
			sql = "select * from practice where practice_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				dto.setPractice_no(rs.getInt("practice_no"));
				dto.setPractice_writer(rs.getString("practice_writer"));
				dto.setPractice_title(rs.getString("practice_title"));
				dto.setPractice_cont(rs.getString("practice_cont"));
				dto.setPractice_pwd(rs.getString("practice_pwd"));
				dto.setPractice_hit(rs.getInt("practice_hit"));
				dto.setPractice_regdate(rs.getString("practice_regdate").substring(0, 10));
				dto.setPractice_head(rs.getString("practice_head"));
			}
			
			rs.close(); pstmt.close(); con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dto;
		
	} // contentPractice() end
	
	public List<PracticeDTO> searchList(String find_field, String find_enter) {
		List<PracticeDTO> list = new ArrayList<PracticeDTO>();
		openConn();
		
		if(find_field.equals("title")) {
			sql = "select * from practice where practice_title like ?";
			
		}else if(find_field.equals("content")) {
			sql = "select * from practice where practice_cont like ?";
		}else if(find_field.equals("writer")) {
			sql = "select * from practice where practice_writer like ?";
		}else if(find_field.equals("head")) {
			sql = "select * from practice where practice_head like ?";
		}
		
		else if(find_field.equals("title_content")) {
			sql = "select * from practice where practice_title like ? or practice_cont like ?";
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+find_enter+"%");
				pstmt.setString(2, "%"+find_enter+"%");
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					PracticeDTO dto = new PracticeDTO();
					dto.setPractice_no(rs.getInt("practice_no"));
					dto.setPractice_writer(rs.getString("practice_writer"));
					dto.setPractice_title(rs.getString("practice_title"));
					dto.setPractice_cont(rs.getString("practice_cont"));
					dto.setPractice_pwd(rs.getString("practice_pwd"));
					dto.setPractice_hit(rs.getInt("practice_hit"));
					dto.setPractice_regdate(rs.getString("practice_regdate").substring(0, 10));
					dto.setPractice_head(rs.getString("practice_head"));
					
					list.add(dto);
					
				}
				
				rs.close(); pstmt.close(); con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return list;
		}
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+find_enter+"%");
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				PracticeDTO dto = new PracticeDTO();
				dto.setPractice_no(rs.getInt("practice_no"));
				dto.setPractice_writer(rs.getString("practice_writer"));
				dto.setPractice_title(rs.getString("practice_title"));
				dto.setPractice_cont(rs.getString("practice_cont"));
				dto.setPractice_pwd(rs.getString("practice_pwd"));
				dto.setPractice_hit(rs.getInt("practice_hit"));
				dto.setPractice_regdate(rs.getString("practice_regdate").substring(0, 10));
				dto.setPractice_head(rs.getString("practice_head"));
				
				list.add(dto);
				
			}
			
			rs.close(); pstmt.close(); con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
		
	} // searchList() end
	
	public int insertContent(PracticeDTO dto) {
		int result = 0;

		try {
			openConn();
			sql = "insert into practice values(pr_seq.nextval, ?, ?, ?, ?, default, sysdate, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getPractice_writer());
			pstmt.setString(2, dto.getPractice_title());
			pstmt.setString(3, dto.getPractice_cont());
			pstmt.setString(4, dto.getPractice_pwd());
			pstmt.setString(5, dto.getPractice_head());
			
			result = pstmt.executeUpdate();
			
			pstmt.close(); con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
		
	} // insertContent() end
	
	public int updateContent(PracticeDTO dto) {
		int result = 0;

		try {
			openConn();			
			sql="select * from practice where practice_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getPractice_no());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				if(dto.getPractice_pwd().equals(rs.getString("practice_pwd"))) {
					sql="update practice set practice_title=?, practice_writer=?, practice_cont=? where practice_no=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, dto.getPractice_title());
					pstmt.setString(2, dto.getPractice_writer());
					pstmt.setString(3, dto.getPractice_cont());
					pstmt.setInt(4, dto.getPractice_no());
					
					result = pstmt.executeUpdate();
				}else {
					result = -1;
				}
			}
			rs.close(); pstmt.close(); con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		
		return result;
		
	} // updateContent() end
	
	public int deleteContent(int no, String pwd) {
		int result = 0;

		try {			
			openConn();
			sql = "select * from practice where practice_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				if(pwd.equals(rs.getString("practice_pwd"))) {
					sql = "delete from practice where practice_no = ?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, no);
					result = pstmt.executeUpdate();
				}else {
					result = -1;
				}
			}
			rs.close(); pstmt.close(); con.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
		
	} // deleteContent() end
}











