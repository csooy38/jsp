package com.board.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO {
	
	Connection con = null;				// DB와 연결하는 객체
	PreparedStatement pstmt = null; 	// DB에 SQL문을 전송하는 객체
	ResultSet rs = null;				// SQL문을 실행 후 결과값을 가지고 있는 객체
	String sql = null;					// 쿼리문을 저장할 객체
	
	// 싱글톤 방식으로 BoardDAO 객체를 만들자.
	// 1단계 : 싱글톤 방식으로 객체를 만들기 위해서는 
	//	우선적으로 기본생성자의 접근제어자를 private으로 선언해야 한다. 
	// 2단계 : 정적 멤버로 선언을 해야 한다. - static으로 선언
	private static BoardDAO instance = null;
	
	// 3단계 : 외부에서 객체 생성을 하지 못하게 접근을 제어 - private 기본 생성자를 생성
	private BoardDAO() {	}
	
	// 4단계 : 기본 생성자 대신에 싱클톤 객체를 return 해주는 
	//	getInstance() 메서드를 만들어서 여기에 접근하게 하는 방법
	public static BoardDAO getInstance() {
		if(instance == null) {
			instance = new BoardDAO();
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
	
	// board 테이블의 전체 레코드를 조회하는 메서드
	public List<BoardDTO> getBoardList(){
		
		List<BoardDTO> list = new ArrayList<BoardDTO>();
	
		try {
			openConn();
			
			sql = "select * from board order by board_no desc";
			
			// 오토커밋하는 명령어. 실무에선 오토커밋은 꺼두는 게 좋다.
			// con.setAutoCommit(false);
			
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				// board 테이블에서 하나의 레코드를 가져와서 각각의 컬럼을 
				// DTO 객체의 setter() 메서드의 인자로 저장.
				BoardDTO dto = new BoardDTO();
				
				dto.setBoard_no(rs.getInt("board_no"));
				dto.setBoard_writer(rs.getString("board_writer"));
				dto.setBoard_title(rs.getString("board_title"));
				dto.setBoard_cont(rs.getString("board_cont"));
				dto.setBoard_pwd(rs.getString("board_pwd"));
				dto.setBoard_hit(rs.getInt("board_hit"));
				dto.setBoard_regdate(rs.getString("board_regdate"));
				
				list.add(dto);
			}
			
			// open 객체 닫기
			rs.close(); pstmt.close(); con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
		
	} // getBoardList() end
	
	// board 테이블에 게시글을 추가해주는 메서드
	public int insertBoard(BoardDTO dto) {
		int result = 0;
				
		try {
			openConn();
			
			sql = "insert into board values(board_seq.nextval, ?, ?, ?, ?, default, sysdate)";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getBoard_writer());
			pstmt.setString(2, dto.getBoard_title());
			pstmt.setString(3, dto.getBoard_cont());
			pstmt.setString(4, dto.getBoard_pwd());
			
			result = pstmt.executeUpdate();
			
			pstmt.close(); con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
		
	} // insertBoard() end
	
	public void boardHit(int board_no) {
		
		try {
			openConn();
			
			sql = "update board set board_hit = board_hit + 1 where board_no = ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, board_no);
			
			pstmt.executeUpdate();
			
			// open 객체 닫기
			pstmt.close(); con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	} // boardHit() end
	
	public BoardDTO boardCont(int board_no) {
		BoardDTO dto = new BoardDTO();
		
		try {		
			openConn();
			
			sql = "select * from board where board_no = ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, board_no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto.setBoard_no(rs.getInt("board_no"));
				dto.setBoard_writer(rs.getString("board_writer"));
				dto.setBoard_title(rs.getString("board_title"));
				dto.setBoard_cont(rs.getString("board_cont"));
				dto.setBoard_pwd(rs.getString("board_pwd"));
				dto.setBoard_hit(rs.getInt("board_hit"));
				dto.setBoard_regdate(rs.getString("board_regdate"));
			}
			
			// open 객체 닫기
			rs.close(); pstmt.close(); con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dto;
		
	} // boardCont() end
	
	// board 테이블의 글번호에 해당하는 게시들을 수정하는 메서드
	public int updateBoard(BoardDTO dto) {
		int result = 0;
		
		try {
			openConn();
			sql = "select * from board where board_no = ?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, dto.getBoard_no());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(dto.getBoard_pwd().equals(rs.getString("board_pwd"))) {
					sql="update board set board_title=?, board_cont=? where board_no = ?";
					
					pstmt = con.prepareStatement(sql);
					
					pstmt.setString(1, dto.getBoard_title());
					pstmt.setString(2, dto.getBoard_cont());
					pstmt.setInt(3, dto.getBoard_no());
					
					// 수정 성공 시 1(숫자값) 반환
					result = pstmt.executeUpdate();
					
				}else { // 비밀번호가 틀린 경우
					result = -1;
				}
			}
			
			// open 객체 닫기
			rs.close(); pstmt.close(); con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
		
	} // updateBoard() end
	
	// board 테이블에서 글 번호에 해당하는 게시글을 삭제하는 메서드
	public int deleteBoard(int board_no, String board_pwd) {
		
		int result = 0;
		
		try {
			openConn();
			
			sql = "select * from board where board_no = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(board_pwd.equals(rs.getString("board_pwd"))) {
					
					sql = "delete from board where board_no = ?";
					pstmt = con.prepareStatement(sql);
					
					pstmt.setInt(1, board_no);
					
					result = pstmt.executeUpdate();
				}else {
					result = -1;
				}
			}
		
			pstmt.close(); con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
		
	} // deleteBoard() end
	
	// board 테이블에서 게시물을 검색하는 메서드
	public List<BoardDTO> searchBoard(String find_field, String find_name){
		List<BoardDTO> list = new ArrayList<BoardDTO>();

		try {
			openConn();
			
			if(find_field.equals("title")) {
				// 제목으로 검색한 경우
				sql = "select * from board where board_title like ? order by board_no desc";
				
				pstmt = con.prepareStatement(sql);	
				pstmt.setString(1, "%"+find_name+"%");
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					BoardDTO dto = new BoardDTO();
					
					dto.setBoard_no(rs.getInt("board_no"));
					dto.setBoard_writer(rs.getString("board_writer"));
					dto.setBoard_title(rs.getString("board_title"));
					dto.setBoard_cont(rs.getString("board_cont"));
					dto.setBoard_pwd(rs.getString("board_pwd"));
					dto.setBoard_hit(rs.getInt("board_hit"));
					dto.setBoard_regdate(rs.getString("board_regdate"));
					
					list.add(dto);
				}
				
				rs.close(); pstmt.close(); con.close();
				
			}else if(find_field.equals("content")) {
				// 내용으로 검색한 경우
				sql = "select * from board where board_cont like ? order by board_no desc";
				
				pstmt = con.prepareStatement(sql);	
				pstmt.setString(1, "%"+find_name+"%");
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					BoardDTO dto = new BoardDTO();
					
					dto.setBoard_no(rs.getInt("board_no"));
					dto.setBoard_writer(rs.getString("board_writer"));
					dto.setBoard_title(rs.getString("board_title"));
					dto.setBoard_cont(rs.getString("board_cont"));
					dto.setBoard_pwd(rs.getString("board_pwd"));
					dto.setBoard_hit(rs.getInt("board_hit"));
					dto.setBoard_regdate(rs.getString("board_regdate"));
					
					list.add(dto);
				}
				
				rs.close(); pstmt.close(); con.close();
				
			}else if(find_field.equals("title_content")) {
				// 제목+내용으로 검색한 경우
				sql = "select * from board where board_title like ? or board_cont like ? order by board_no desc";
				
				pstmt = con.prepareStatement(sql);	
				pstmt.setString(1, "%"+find_name+"%");
				pstmt.setString(2, "%"+find_name+"%");
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					BoardDTO dto = new BoardDTO();
					
					dto.setBoard_no(rs.getInt("board_no"));
					dto.setBoard_writer(rs.getString("board_writer"));
					dto.setBoard_title(rs.getString("board_title"));
					dto.setBoard_cont(rs.getString("board_cont"));
					dto.setBoard_pwd(rs.getString("board_pwd"));
					dto.setBoard_hit(rs.getInt("board_hit"));
					dto.setBoard_regdate(rs.getString("board_regdate"));
					
					list.add(dto);
				}
				
				rs.close(); pstmt.close(); con.close();
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
		
	} // searchBoard() end
}
