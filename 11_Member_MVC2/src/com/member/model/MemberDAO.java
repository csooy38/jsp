package com.member.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {

	Connection con = null; // DB 연결하는 객체.
	PreparedStatement pstmt = null; // DB에 SQL문을 전송하는 객체.
	ResultSet rs = null; // SQL문을 실행 후 결과값을 가지고 있는 객체.

	String sql = null; // 쿼리문을 저장할 객체.

	// public은 객체를 불러올 때마다 heap영역에 객체를 생성하게 되어 메모리 차지가 많음 => 메모리 낭비
	// private 접근지정자 => 싱글턴 방식으로 ProductDAO 객체를 만드는 방식
	// 1단계 : 싱글턴 방식으로 객체를 만들기 위해서는 우선적으로 기본생성자의 접근 제어자를
	// private로 선언을 해줘야 함. (외부접근 x, 내부에서 객체를 만들어줘야함)

	// 2단계 : 정적 멤버로 선언을 해야 함. => static으로 선언을 해야한다는 의미.
	private static MemberDAO instance = null;

	// 3단계 : 외부에서 객체 생성을 하지 못하게 접근을 제어 - private 기본 생성자를 만듦.
	private MemberDAO() {
	}

	// 4단계 : 기본 생성자 대신에 싱글턴 객체를 return을 해주는 getInstance() 메서드를
	// 만들어서 여기에 접근하게 하는 방법. => 정적 인스턴스를 만들어서 가져다 씀
	public static MemberDAO getInstance() { // 반환 타입이 MemberDAO
		if (instance == null) {
			instance = new MemberDAO(); // 객체생성(주소값 가지고있음) => method 영역에 생성
		}
		return instance;
	} // getInstance() 메서드 end

	// DB 연동하는 작업을 진행하는 메서드 - DBCP(Connection Pool) 방식으로 연결 진행
	public void openConn() {

		try {
			// 1단계 : JNDI 서버 객체 생성
			Context ctx = new InitialContext(); // Context(javax.lang)

			// 2단계 : lookup() 메서드를 이용하여 매칭되는 커넥션을 찾는다.
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/myoracle"); // DataSource(javax.sql) =>
																					// object타입이라 형변환 필요

			// 3단계 : DataBase 객체를 이용하여 커넥션 객체를 하나 가져온다.
			con = ds.getConnection();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	} // openConn() 메서드 end

	public List<MemberDTO> getMemberList() {
		List<MemberDTO> list = new ArrayList<MemberDTO>();
		openConn();
		sql = "select * from member10 order by num desc";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				MemberDTO dto = new MemberDTO();

				dto.setNum(rs.getInt("num"));
				dto.setMemid(rs.getString("memid"));
				dto.setMemname(rs.getString("memname"));
				dto.setPwd(rs.getString("pwd"));
				dto.setAge(rs.getInt("age"));
				dto.setMileage(rs.getInt("mileage"));
				dto.setJob(rs.getString("job"));
				dto.setAddr(rs.getString("addr"));
				dto.setRegdate(rs.getString("regdate"));

				list.add(dto);
			}

			// open 객체 닫기
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;

	} // getMemberList() end

	// member10 테이블에 회원의 정보를 저장하는 메서드
	public int insertMember(MemberDTO dto) {
		int result = 0;

		try {
			openConn();
			sql = "insert into member10 values(member10_seq.nextval,?,?,?,?,?,?,?,sysdate)";
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, dto.getMemid());
			pstmt.setString(2, dto.getMemname());
			pstmt.setString(3, dto.getPwd());
			pstmt.setInt(4, dto.getAge());
			pstmt.setInt(5, dto.getMileage());
			pstmt.setString(6, dto.getJob());
			pstmt.setString(7, dto.getAddr());

			result = pstmt.executeUpdate();

			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;

	} // insertMember() end

	public MemberDTO contentMember(int member_no) {
		MemberDTO dto = new MemberDTO();

		try {
			openConn();
			sql = "select * from member10 where num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, member_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				dto.setNum(rs.getInt("num"));
				dto.setMemid(rs.getString("memid"));
				dto.setMemname(rs.getString("memname"));
				dto.setPwd(rs.getString("pwd"));
				dto.setAge(rs.getInt("age"));
				dto.setMileage(rs.getInt("mileage"));
				dto.setJob(rs.getString("job"));
				dto.setAddr(rs.getString("addr"));
				dto.setRegdate(rs.getString("regdate"));
			}

			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dto;

	} // contentMember() end

	// member10 테이블에서 회원의 정보를 수정하는 메서드
	public int updateMember(MemberDTO dto) {
		int result = 0;

		try {
			openConn();
			sql = "select * from member10 where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getNum());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				if (dto.getPwd().equals(rs.getString("pwd"))) {
					sql = "update member10 set age=?, mileage=?, job=?, addr=? where num=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, dto.getAge());
					pstmt.setInt(2, dto.getMileage());
					pstmt.setString(3, dto.getJob());
					pstmt.setString(4, dto.getAddr());
					pstmt.setInt(5, dto.getNum());

					result = pstmt.executeUpdate();
				} else { // 비밀번호가 틀린 경우
					result = -1;
				}
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	} // updateMember() end
	
	public int deleteMember(int member_num, String member_pwd) {
		int result = 0;

		try {
			openConn();
			sql = "select * from member10 where num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, member_num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(member_pwd.equals(rs.getString("pwd"))) {
					sql = "delete from member10 where num = ?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, member_num);
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
	} // deleteMember() end
}
