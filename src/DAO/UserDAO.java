package DAO;

import java.sql.*;

import javax.naming.*;
import javax.sql.*;

import DTO.User;
import Util.Util;

public class UserDAO extends JDBC{	
	public UserDAO(){
		super();
	}	

	public boolean loginUser(User user) throws NamingException, ClassNotFoundException{	
		
		boolean isLogin = false;
		
		try{			
			System.out.println("USERDAO");
			conn = getconnection(conn);		
			
			stmt = conn.prepareStatement(Util.LOGINQUERY);
			stmt.setString(1, user.getU_id().trim());
			stmt.setString(2, user.getPassword());
			
			rs = stmt.executeQuery();
			if(rs.next()){				
				isLogin = true;
			}
			
		} catch (SQLException e){
				System.out.println("login Query fail");
				System.out.println("error " + e);

		} finally{
			closeDB(conn, stmt, rs);
		}
		
		return isLogin;		
	}

	public User getUser(String id) throws NamingException {
		System.out.println("get user");	
		User user = null;
		
		try {
		
			conn = getconnection(conn);
			
			stmt = conn.prepareStatement(Util.HASUSERID);			
			stmt.setString(1, id);
			
			rs = stmt.executeQuery();
			if(rs.next()){
				System.out.println("user exist");
				user = new User();
				
				user.setN_id(rs.getString("n_id"));
				user.setU_id(rs.getString("u_id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));				
			}
			
		} catch (SQLException e){
			System.out.println("hasuserid Query fail");
			System.out.println("error " + e);

		} finally{
			closeDB(conn, stmt, rs);
		}
		return user;
	}

	public boolean createUser(User user) throws NamingException, ClassNotFoundException, SQLException {			
		
		
		
		boolean createOk = false;
		
		try {
			conn = getconnection(conn);
			stmt = conn.prepareStatement(Util.CREATEUSERQUERY);
			stmt.setString(1, user.getU_id().trim());
			stmt.setString(2, user.getPassword().trim());
			stmt.setString(3, user.getName().trim());
			
			int count = stmt.executeUpdate();
			if(count > 0){
				System.out.println("Create ok");
				createOk= true;
			}
			
		} catch (SQLException e){
			System.out.println("createuser Query fail");
			System.out.println("error " + e);

		} finally{
			closeDB(conn, stmt, rs);
		}
		return createOk;
	}
	
	public boolean hasUserId(User user) throws NamingException {
		System.out.println("has user id");
		boolean isOk = false;
		if(getUser(user.getU_id()) != null){
			isOk = true;
		}
		return isOk;
	}

}
