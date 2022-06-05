package test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import test.dto.Board;
import test.util.DBPool;

public class BoardDao {

	private static BoardDao boardDao = null;
	
	private BoardDao() {}
	
	public static BoardDao getInstance() {
		if(boardDao == null) {
			boardDao = new BoardDao();
		}
		return boardDao;
	}
	
	public List<Board> selectList(){
		String sql = "SELECT * FROM board";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Board> list = new ArrayList<>();
		
		try {
			con = DBPool.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int bnum = rs.getInt("bnum");
				String writer = rs.getString("writer");
				String title = rs.getString("title");
				String content = rs.getString("content");
				
				Board board = new Board(bnum, writer, title, content);
				list.add(board);
			}
			
			return list;
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			DBPool.close(con, pstmt, rs);
		}
	}
	
	public Board selectOne(int reqNum){
		String sql = "SELECT * FROM board WHERE bnum = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Board board = null;
		
		try {
			con = DBPool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, reqNum);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int bnum = rs.getInt("bnum");
				String writer = rs.getString("writer");
				String title = rs.getString("title");
				String content = rs.getString("content");
				
				board = new Board(bnum, writer, title, content);
			}
			
			return board;
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			DBPool.close(con, pstmt, rs);
		}
	}
}
