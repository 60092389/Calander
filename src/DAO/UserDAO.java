package DAO;

import java.sql.*;

import javax.naming.*;
import javax.sql.*;

import DTO.Friends;
import DTO.Schedule;
import DTO.User;
import Util.Util;

import java.util.ArrayList;
import java.util.List;

public class UserDAO extends JDBC {
	public UserDAO() {
		super();
	}

	public boolean loginUser(User user) throws NamingException,
			ClassNotFoundException {

		boolean isLogin = false;

		try {
			System.out.println("USERDAO");
			conn = getconnection(conn);

			stmt = conn.prepareStatement(Util.LOGINQUERY);
			stmt.setString(1, user.getU_id().trim());
			stmt.setString(2, user.getPassword());

			rs = stmt.executeQuery();
			if (rs.next()) {
				isLogin = true;
			}

		} catch (SQLException e) {
			System.out.println("login Query fail");
			System.out.println("error " + e);

		} finally {
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
			if (rs.next()) {
				System.out.println("user exist");
				user = new User();

				user.setN_id(rs.getString("n_id"));
				user.setU_id(rs.getString("u_id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
			}

		} catch (SQLException e) {
			System.out.println("hasuserid Query fail");
			System.out.println("error " + e);

		} finally {
			closeDB(conn, stmt, rs);
		}
		return user;
	}
	
	public List<User> getPage(int startPos, int numInPage) 
			throws SQLException, ClassNotFoundException{
		System.out.println("start getPage");
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		List<User> users = new ArrayList<User>();
		
		try {
			try {
				conn = getconnection(conn);
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("get page conn ok");
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery("SELECT * FROM User ORDER BY n_id DESC LIMIT " + startPos+", "+numInPage);
			
			while(rs.next()) {
				
				System.out.println("getpage query ok");
				User user = new User();

				user.setN_id(rs.getString("n_id"));
				user.setU_id(rs.getString("u_id"));
				user.setPassword(rs.getString("password"));
				user.setName(rs.getString("name"));

				users.add(user);
			
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("getpage query failed");
			e.printStackTrace();
		}finally {
			// 무슨 일이 있어도 리소스를 제대로 종료
			if (rs != null) try{rs.close();} catch(SQLException e) {}
			if (stmt != null) try{stmt.close();} catch(SQLException e) {}
			if (conn != null) try{conn.close();} catch(SQLException e) {}
		}
		
		return users;		
	}	

	public boolean createUser(User user) throws NamingException, ClassNotFoundException{

		boolean createOk = false;

		try {
			conn = getconnection(conn);
			stmt = conn.prepareStatement(Util.CREATEUSERQUERY);
			stmt.setString(1, user.getU_id().trim());
			stmt.setString(2, user.getPassword().trim());
			stmt.setString(3, user.getName().trim());

			int count = stmt.executeUpdate();
			if (count > 0) {
				System.out.println("Create ok");
				createOk = true;
			}

		} catch (SQLException e) {
			System.out.println("createuser Query fail");
			System.out.println("error " + e);

		} finally {
			closeDB(conn, stmt);
		}
		return createOk;
	}

	public boolean hasUserId(User user) throws NamingException {
		System.out.println("has user id");
		boolean isOk = false;
		if (getUser(user.getU_id()) != null) {
			isOk = true;
		}
		return isOk;
	}

	public ArrayList<User> getAllUsers() throws NamingException{
		System.out.println("get all users start");
		ArrayList<User> allUsers = null; // 모들 user 리스트릴 TreeMap 으로 반환하기 위한 변수

		try {
			conn = getconnection(conn);
			stmt = conn.prepareStatement(Util.ALLUSERS);
			rs = stmt.executeQuery();

			// user에 대한 정보가 있는 경우 (모든 user list 정보를 받아온다)
			while (rs.next()) {
				System.out.println("get all user query ok");
				// 모든 user list 정보를 받는 객체가 없는 경우
				if (allUsers == null) {
					allUsers = new ArrayList<User>();
				}

				User user = new User();

				user.setN_id(rs.getString("n_id"));
				user.setU_id(rs.getString("u_id"));
				user.setPassword(rs.getString("password"));
				user.setName(rs.getString("name"));

				allUsers.add(user);

			}
		} catch (SQLException e) {
			System.out.println("hasUser Query fail");
			System.out.println("error " + e);

		} finally {
			closeDB(conn, stmt, rs);
		}
		// System.out.println("size : " + allUsers.size());
		return allUsers;

	}

	public boolean updateUser(User user, int id) throws NamingException,
			ClassNotFoundException, SQLException {

		boolean updateOk = false;

		try {
			conn = getconnection(conn);
			stmt = conn.prepareStatement(Util.UPDATEQUERY);
			System.out.println("update id " + id);
			stmt.setString(1, user.getPassword().trim());
			stmt.setString(2, user.getName().trim());
			stmt.setInt(3, id);

			int count = stmt.executeUpdate();
			if (count > 0) {
				System.out.println("update query ok");
				updateOk = true;
			}

		} catch (SQLException e) {
			System.out.println("update Query fail");
			System.out.println("error " + e);

		} finally {
			closeDB(conn, stmt);
		}
		return updateOk;
	}

	public boolean deleteUser(int deleteId) throws NamingException {
		// TODO Auto-generated method stub
		boolean deleteUserOk = false;

		try {
			conn = getconnection(conn);
			stmt = conn.prepareStatement(Util.DELETEUSER);
			stmt.setInt(1, deleteId);
			int count = stmt.executeUpdate();
			if (count > 0) {
				System.out.println("user delete ok");
				deleteUserOk = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB(conn, stmt);
		}

		return deleteUserOk;
	}

	public User getUserByNum(int id) throws NamingException {
		System.out.println("get user by num id");
		User user = null;
		
		try {
			conn = getconnection(conn);
			stmt = conn.prepareStatement(Util.HASUSERIDBYNUM);
			stmt.setInt(1, id);

			rs = stmt.executeQuery();
			while (rs.next()) {
				System.out.println("user exist");
				user = new User();

				user.setN_id(rs.getString("n_id"));
				user.setU_id(rs.getString("u_id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
			}

		} catch (SQLException e) {
			System.out.println("hasuserid Query fail");
			System.out.println("error " + e);

		} finally {
			closeDB(conn, stmt, rs);
		}
		return user;
	}	

	public boolean deleteReq(int userID, int friendID) throws NamingException {
		// TODO Auto-generated method stub
		boolean deleteReqOk = false;
		conn = getconnection(conn);
		
		try {
			stmt = conn.prepareStatement(Util.DELETEREQ);
			stmt.setInt(1, friendID);
			stmt.setInt(2, userID);
			int count = stmt.executeUpdate();
			if(count > 0){
				System.out.println("request delete ok");
				deleteReqOk = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("delete reqeust query fail");
			e.printStackTrace();
		} finally {
			closeDB(conn, stmt);
		}
		return deleteReqOk;
	}	
}
