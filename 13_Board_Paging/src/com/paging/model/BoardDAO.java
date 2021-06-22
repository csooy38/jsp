package com.paging.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {

	Connection con = null; // DB 연결하는 객체.
	PreparedStatement pstmt = null; // DB에 SQL문을 전송하는 객체.
	ResultSet rs = null; // SQL문을 실행 후 결과 값을 가지고 있는 객체.

	String sql = null; // 쿼리문을 저장할 객체.

	// 싱글톤 방식으로 BoardDAO 객체를 만들자.
	// 1단계 : 싱글톤 방식으로 객체를 만들기 위해서는 우선적으로
	// 기본생성자의 접근제어자를 private 으로 선언을 해야 함.
	// 2단계 : 정적 멤버로 선언을 해야 함 - static 으로 선언을 한다는 의미.
	private static BoardDAO instance = null;

	// 3단계 : 외부에서 객체 생성을 하지 못하게 접근을 제어 - private 기본 생성자를 만듬.
	private BoardDAO() {
	}

	// 4단계 : 기본 생성자 대신에 싱긑턴 객체를 return을 해 주는 getInstance()
	// 메서드를 만들어서 여기에 접근하게 하는 방법
	public static BoardDAO getInstance() {
		if (instance == null) {
			instance = new BoardDAO();
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

	// board 테이블의 전체 게시물의 수를 조회하는 메서드
	public int getListCount() {
		int count = 0;

		try {
			openConn();
			sql = "select count(*) from board";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				count = rs.getInt(1); // sql문 질의결과 첫번째(1) 인덱스 값을 count 변수에 저장
			}

			// open 객체 닫기
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return count;
	} // getListCount() end

	// board 테이블에서 게시물을 가져오는 메서드
	public List<BoardDTO> getBoardList(int page, int rowsize) {

		List<BoardDTO> list = new ArrayList<BoardDTO>();

		// 해당 페이지에서 시작 번호
		int startNo = (page * rowsize) - (rowsize - 1);

		// 해당 페이지에서 마지막 번호
		int endNo = (page * rowsize);

		try {
			openConn();
			sql = "select * from " + "(select row_number() over(order by board_no desc) rnum, b.* from board b) "
					+ "where rnum >= ? and rnum <= ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startNo);
			pstmt.setInt(2, endNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setBoard_no(rs.getInt("board_no"));
				dto.setBoard_writer(rs.getString("board_writer"));
				dto.setBoard_title(rs.getString("board_title"));
				dto.setBoard_cont(rs.getString("board_cont"));
				dto.setBoard_pwd(rs.getString("board_pwd"));
				dto.setBoard_hit(rs.getInt("board_hit"));
				dto.setBoard_regdate(rs.getString("board_regdate"));
				dto.setBoard_c_count(rs.getInt("board_c_count"));

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
	} // getBoardList() end

	// board 테이블에 게시글을 추가하는 메서드
	public int insertBoard(BoardDTO dto) {
		int result = 0, count = 0;

		try {
			openConn();
			sql = "select max(board_no) from board";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				count = rs.getInt(1) + 1;
			}

			sql = "insert into board values(?, ?, ?, ?, ?, default, sysdate, default)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, count);
			pstmt.setString(2, dto.getBoard_writer());
			pstmt.setString(3, dto.getBoard_title());
			pstmt.setString(4, dto.getBoard_cont());
			pstmt.setString(5, dto.getBoard_pwd());

			result = pstmt.executeUpdate();

			rs.close();
			pstmt.close();
			con.close();
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

			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	} // boardHit() end

	public BoardDTO getBoardCont(int board_no) {
		BoardDTO dto = new BoardDTO();

		try {
			openConn();
			sql = "select * from board where board_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_no);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto.setBoard_no(rs.getInt("board_no"));
				dto.setBoard_writer(rs.getString("board_writer"));
				dto.setBoard_title(rs.getString("board_title"));
				dto.setBoard_cont(rs.getString("board_cont"));
				dto.setBoard_pwd(rs.getString("board_pwd"));
				dto.setBoard_hit(rs.getInt("board_hit"));
				dto.setBoard_regdate(rs.getString("board_regdate"));
				dto.setBoard_c_count(rs.getInt("board_c_count"));
			}

			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dto;
	}

	public int updateBoard(BoardDTO dto) {
		int result = 0;

		try {
			openConn();
			sql = "update board set board_title = ?, board_cont = ? where board_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getBoard_title());
			pstmt.setString(2, dto.getBoard_cont());
			pstmt.setInt(3, dto.getBoard_no());

			result = pstmt.executeUpdate();

			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	} // updateBoard() end

	// board 테이블에서 글번호에 해당하는 게시글을 삭제하는 메서드
	public int deleteBoard(int board_no) {
		int result = 0;

		try {
			openConn();
			sql = "delete from board where board_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_no);
			result = pstmt.executeUpdate();

			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	} // deleteBoard() end
	
	// board 테이블에서 중간에 삭제된 게시글이 있는 경우 글 번호를 다시 적용하는 메서드
	public void updateNo(int board_no) {
		
		try {
			openConn();
			sql = "update board set board_no = board_no - 1 where board_no > ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_no);
			pstmt.executeUpdate();
			
			pstmt.close(); con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} // updateNo() end

	/*
	public List<BoardDTO> searchBoard(String find_field, String find_text) {
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		openConn();

		if (find_field.equals("all")) {
			sql = "select * from board where board_title like ? or board_cont like ? or board_write like ?";
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%" + find_text + "%");
				pstmt.setString(2, "%" + find_text + "%");
				pstmt.setString(3, "%" + find_text + "%");

				rs = pstmt.executeQuery();

				while (rs.next()) {
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
				rs.close();
				pstmt.close();
				con.close();

				return list;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (find_field.equals("title_content")) {
			sql = "select * from board where board_title like ? or board_cont like ?";
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%" + find_text + "%");
				pstmt.setString(2, "%" + find_text + "%");

				rs = pstmt.executeQuery();

				while (rs.next()) {
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
				rs.close();
				pstmt.close();
				con.close();

				return list;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (find_field.equals("title")) {
			sql = "select * from board where board_title like ?";
		} else if (find_field.equals("content")) {
			sql = "select * from board where board_cont like ?";
		} else if (find_field.equals("writer")) {
			sql = "select * from board where board_writer like ?";
		}

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + find_text + "%");

			rs = pstmt.executeQuery();

			while (rs.next()) {
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
			rs.close();
			pstmt.close();
			con.close();

			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	} // searchBoard() end
*/
	
	public List<BoardDTO> getSearchBoardList(String find_field, String find_text, int page, int rowsize) {
		List<BoardDTO> list = new ArrayList<BoardDTO>();

		// 해당 페이지에서 시작 번호
		int startNo = (page * rowsize) - (rowsize - 1);

		// 해당 페이지에서 마지막 번호
		int endNo = (page * rowsize);

		openConn();

		if (find_field.equals("all")) {
			sql = "select * from (select row_number() over(order by board_no desc) rnum, b.* from board b where board_title like ? or board_cont like ? or board_writer like ?) where rnum >= ? and rnum <= ?";
			// sql = "select * from (select row_number() over(order by board_no desc) rnum, b.* from board b) where rnum >= ? and rnum <= ? and (board_title like ? or board_cont like ? or board_writer like ?)";
			// sql = "select * from board where board_title like ? or board_cont like ? or
			// board_writer like ?";
			try {
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, "%" + find_text + "%");
				pstmt.setString(2, "%" + find_text + "%");
				pstmt.setString(3, "%" + find_text + "%");
				pstmt.setInt(4, startNo);
				pstmt.setInt(5, endNo);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					BoardDTO dto = new BoardDTO();
					dto.setBoard_no(rs.getInt("board_no"));
					dto.setBoard_writer(rs.getString("board_writer"));
					dto.setBoard_title(rs.getString("board_title"));
					dto.setBoard_cont(rs.getString("board_cont"));
					dto.setBoard_pwd(rs.getString("board_pwd"));
					dto.setBoard_hit(rs.getInt("board_hit"));
					dto.setBoard_regdate(rs.getString("board_regdate"));
					dto.setBoard_c_count(rs.getInt("board_c_count"));

					list.add(dto);
				}
				rs.close();
				pstmt.close();
				con.close();

				return list;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (find_field.equals("title_content")) {

			sql = "select * from (select row_number() over(order by board_no desc) rnum, b.* from board b where board_title like ? or board_cont like ?) where rnum >= ? and rnum <= ?";
			// sql = "select * from (select row_number() over(order by board_no desc) rnum, b.* from board b) where rnum >= ? and rnum <= ? and (board_title like ? or board_cont like ?)";
			// sql = "select * from board where board_title like ? or board_cont like ?";
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%" + find_text + "%");
				pstmt.setString(2, "%" + find_text + "%");
				pstmt.setInt(3, startNo);
				pstmt.setInt(4, endNo);	

				rs = pstmt.executeQuery();

				while (rs.next()) {
					BoardDTO dto = new BoardDTO();
					dto.setBoard_no(rs.getInt("board_no"));
					dto.setBoard_writer(rs.getString("board_writer"));
					dto.setBoard_title(rs.getString("board_title"));
					dto.setBoard_cont(rs.getString("board_cont"));
					dto.setBoard_pwd(rs.getString("board_pwd"));
					dto.setBoard_hit(rs.getInt("board_hit"));
					dto.setBoard_regdate(rs.getString("board_regdate"));
					dto.setBoard_c_count(rs.getInt("board_c_count"));

					list.add(dto);
				}
				rs.close();
				pstmt.close();
				con.close();

				return list;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (find_field.equals("title")) {
			sql = "select * from (select row_number() over(order by board_no desc) rnum, b.* from board b where board_title like ?) where rnum >= ? and rnum <= ?";
			// sql = "select * from (select row_number() over(order by board_no desc) rnum, b.* from board b) where rnum >= ? and rnum <= ? and board_title like ?";
			// sql = "select * from board where board_title like ?";
		} else if (find_field.equals("content")) {
			sql = "select * from (select row_number() over(order by board_no desc) rnum, b.* from board b where board_cont like ?) where rnum >= ? and rnum <= ?";
			// sql = "select * from (select row_number() over(order by board_no desc) rnum, b.* from board b) where rnum >= ? and rnum <= ? and board_cont like ?";
			// sql = "select * from board where board_cont like ?";
		} else if (find_field.equals("writer")) {
			sql = "select * from (select row_number() over(order by board_no desc) rnum, b.* from board b where board_writer like ?) where rnum >= ? and rnum <= ?";
			//sql = "select * from (select row_number() over(order by board_no desc) rnum, b.* from board b) where rnum >= ? and rnum <= ? and board_writer like ?";
			// sql = "select * from board where board_writer like ?";
		}

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + find_text + "%");
			pstmt.setInt(2, startNo);
			pstmt.setInt(3, endNo);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setBoard_no(rs.getInt("board_no"));
				dto.setBoard_writer(rs.getString("board_writer"));
				dto.setBoard_title(rs.getString("board_title"));
				dto.setBoard_cont(rs.getString("board_cont"));
				dto.setBoard_pwd(rs.getString("board_pwd"));
				dto.setBoard_hit(rs.getInt("board_hit"));
				dto.setBoard_regdate(rs.getString("board_regdate"));
				dto.setBoard_c_count(rs.getInt("board_c_count"));

				list.add(dto);
			}
			rs.close();
			pstmt.close();
			con.close();

			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	} // getSearchBoardList() end

	// board 테이블에서 검색어에 해당하는 게시물의 수를 조회하는 메서드
	public int getSearchListCount(String find_field, String find_text) {
		int count = 0;
		openConn();
		if (find_field.equals("all")) {
			sql = "select count(*) from board where board_title like ? or board_cont like ? or board_writer like ?";

			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%" + find_text + "%");
				pstmt.setString(2, "%" + find_text + "%");
				pstmt.setString(3, "%" + find_text + "%");

				rs = pstmt.executeQuery();

				if (rs.next()) {
					count = rs.getInt(1);
				}

				rs.close();
				pstmt.close();
				con.close();

				return count;

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (find_field.equals("title_content")) {
			sql = "select count(*) from board where board_title like ? or board_cont like ?";

			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%" + find_text + "%");
				pstmt.setString(2, "%" + find_text + "%");

				rs = pstmt.executeQuery();

				if (rs.next()) {
					count = rs.getInt(1);
				}

				rs.close();
				pstmt.close();
				con.close();

				return count;

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (find_field.equals("title")) {
			sql = "select count(*) from board where board_title like ?";
		} else if (find_field.equals("content")) {
			sql = "select count(*) from board where board_cont like ?";
		} else if (find_field.equals("writer")) {
			sql = "select count(*) from board where board_writer like ?";
		}
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + find_text + "%");

			rs = pstmt.executeQuery();

			if (rs.next()) {
				count = rs.getInt(1);
			}

			rs.close();
			pstmt.close();
			con.close();

			return count;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return count;
	} // getSearchListCount() end
	
	
	
	
	public int insertComment(CommentDTO dto, int board_no) {
		int result = 0, count = 0;
		
		try {
			openConn();
			sql = "select count(*) from board_comment";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1) + 1;
			}
			
			sql = "insert into board_comment values(?, ?, ?, ?, ?, sysdate)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, count);
			pstmt.setInt(2, board_no);
			pstmt.setString(3, dto.getComment_writer());
			pstmt.setString(4, dto.getComment_cont());
			pstmt.setString(5, dto.getComment_pwd());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	} // insertComment() end
	
	public List<CommentDTO> getCommentList(int board_no) {
		List<CommentDTO> list = new ArrayList<CommentDTO>();
		
		try {
			openConn();
			sql = "select * from board_comment where comment_board_no = ? order by comment_no desc";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_no);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CommentDTO dto = new CommentDTO();
				
				dto.setComment_no(rs.getInt("comment_no"));
				dto.setComment_board_no(rs.getInt("comment_board_no"));
				dto.setComment_writer(rs.getString("comment_writer"));
				dto.setComment_cont(rs.getString("comment_cont"));
				dto.setComment_pwd(rs.getString("comment_pwd"));
				dto.setComment_sysdate(rs.getString("comment_sysdate"));
				
				list.add(dto);
			}
			
			rs.close(); pstmt.close(); con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return list;
	} // getCommentList() end
	
	
	public void boardCountHit(int board_no) {
		openConn();
		
		sql = "update board set board_c_count = board_c_count+1 where board_no = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_no);
			pstmt.executeUpdate();
			
			pstmt.close(); con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	} // boardCountHit() end
	
	
	public int deleteComment(int comment_no) {
		int result = 0;

		try {
			openConn();
			sql = "delete from board_comment where comment_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, comment_no);
			
			result = pstmt.executeUpdate();
			
			pstmt.close(); con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	} // deleteComment() end
	
	
	public void boardCountDown(int board_no) {
		openConn();
		sql = "update board set board_c_count = board_c_count-1 where board_no = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_no);
			pstmt.executeUpdate();
			
			pstmt.close(); con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} // boardCountDown() end
	
	
	
	
}
