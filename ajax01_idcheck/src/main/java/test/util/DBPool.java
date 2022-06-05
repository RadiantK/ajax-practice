package test.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBPool {

	private static DataSource ds;
	
	static { // 스태틱 초기화 블럭(클래스가 로딩될 때 호출되며 각 클래스당 최초 1회만 실행)
		try {
			Context initalContext = new InitialContext();
			ds = (DataSource) initalContext.lookup("java:comp/env/jdbc/temp");
//			Context envContext  = (Context)initalContext.lookup("java:/comp/env");
//			ds = (DataSource)envContext.lookup("jdbc/temp");
		}catch(NamingException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		try {
			Connection con = ds.getConnection();
			
			return con;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void close(Connection con) {
		try {
			if(con != null) con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Statement stmt) {
		try {
			if(stmt != null) stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Connection con, Statement stmt) {
		try {
			if(stmt != null) stmt.close();
			if(con != null) con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Statement stmt, ResultSet rs) {
		try {
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Connection con, Statement stmt, ResultSet rs) {
		try {
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();
			if(con != null) con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void commit(Connection con) {
		try {
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void rollback(Connection con) {
		try {
			con.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
