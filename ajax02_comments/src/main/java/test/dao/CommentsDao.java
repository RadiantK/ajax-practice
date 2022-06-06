package test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import test.dto.Comments;
import test.util.DBPool;

public class CommentsDao {

	private static CommentsDao commentsDao = null;
	
	private CommentsDao() {}
	
	public static CommentsDao getInstance() {
		if(commentsDao == null) {
			commentsDao = new CommentsDao();
		}
		return commentsDao;
	}
	
	public List<Comments> selectList(int reqNum, int page) {
		String sql = "SELECT * FROM ( "
				+ "SELECT * FROM comments WHERE bnum = ? ORDER BY num DESC) TMP "
				+ "LIMIT ?, 5;";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Comments> list = new ArrayList<>();
		
		try {
			con = DBPool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, reqNum);
			pstmt.setInt(2, 0 + (page - 1) * 5);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int num = rs.getInt("num");
				int bnum = rs.getInt("bnum");
				String id = rs.getString("id");
				String comments = rs.getString("comments");
				
				Comments comm = new Comments(num, bnum, id, comments);
				list.add(comm);
			}
			
			return list;
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			DBPool.close(con, pstmt, rs);
		}
	}
	
	public int insert(Comments co) {
		String sql = "INSERT INTO comments(bnum, id, comments) "
				+ "VALUES(?,?,?)";
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBPool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, co.getBnum());
			pstmt.setString(2, co.getId());
			pstmt.setString(3, co.getComments());
			
			int result = pstmt.executeUpdate();
			
			return result;
		}catch(SQLException e) {
			e.printStackTrace();
			return -1;
		}finally {
			DBPool.close(con, pstmt);
		}
	}
	
	public int delete(int num) {
		String sql = "DELETE FROM comments WHERE num = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBPool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			int result = pstmt.executeUpdate();
			
			return result;
		}catch(SQLException e) {
			e.printStackTrace();
			return -1;
		}finally {
			DBPool.close(con, pstmt);
		}
	}
	
	public int getCount(int reqNum) {
		String sql = "SELECT IFNULL(COUNT(num), 0) cnt "
				+ "FROM comments WHERE bnum = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DBPool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, reqNum);
			rs = pstmt.executeQuery();
			
			int count = 0;
			if(rs.next()) {
				count = rs.getInt("cnt");
			}
			
			return count;
		}catch(SQLException e) {
			e.printStackTrace();
			return -1;
		}finally {
			DBPool.close(con, pstmt, rs);
		}
	}
}
