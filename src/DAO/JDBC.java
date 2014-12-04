package DAO;


import java.sql.*;

import javax.naming.*;
import javax.sql.*;





public class JDBC {	
	
	protected Connection conn = null;
	protected PreparedStatement stmt;
	protected ResultSet rs;
	
	public JDBC(){
		init();		
	}

	private void init() {
		// TODO Auto-generated method stub		
		stmt = null;
		rs = null;		
		if(conn == null){
			try {
				
				conn = getconnection(conn);
				System.out.println("init connection ok");
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				System.out.println("init connection fail");
				e.printStackTrace();
			}
		}
	}

	public static Connection getconnection(Connection conn) throws NamingException{
		System.out.println("get connection");
			   
	    try {
	    	Context initContext = new InitialContext();
		   Context envContext  = (Context) initContext.lookup("java:/comp/env");
		    DataSource dataSource = (DataSource) envContext.lookup("jdbc/SCalendar");
			conn = dataSource.getConnection();
			System.out.println("connection ok");
		} catch (SQLException e) {
			System.out.println("jdbc sql 오류");
			e.printStackTrace();
		} 
		
		return conn;		
	}
	
	 public static void closeDB(Connection conn, PreparedStatement stmt, ResultSet rs) {
	        if (rs != null) { try { rs.close(); } catch (Exception e) { } }
	        if (stmt != null) { try { stmt.close(); } catch (Exception e) { } }
	        if (conn != null) { try { conn.close(); } catch (Exception e) { } }
	    }
	 
	 public static void closeDB(Connection conn, PreparedStatement stmt) {
	        if (stmt != null) { try { stmt.close(); } catch (Exception e) { } }
	        if (conn != null) { try { conn.close(); } catch (Exception e) { } }
	    }
	
	
}
