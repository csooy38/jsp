package com.practice.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class Pr_memberDAO {

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = null;
	
	private static Pr_memberDAO instance = null;
	
	private Pr_memberDAO() { }
	
	public static Pr_memberDAO getInstance() {
		if(instance == null) {
			instance = new Pr_memberDAO();
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
	
	public int loginPractice(String id, String pwd) {
		int result = 0;

		try {			
			openConn();
			
			sql = "select * from pr_member where pr_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				if(pwd.equals(rs.getString("pr_pwd"))) {
					result = 1;
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
		
	} // loginPractice() end
}

















