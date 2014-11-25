package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.*;
import javax.sql.*;

import DTO.Friends;
import DTO.Request;
import DTO.User;
import Util.Util;

public class FriendDAO extends JDBC {
	public FriendDAO() {
		super();
	}

	public Friends getFriend(String f_id) throws NamingException {
		System.out.println("get friend");
		Friends friend = null;

		try {

			conn = getconnection(conn);

			stmt = conn.prepareStatement(Util.HASFRIENDID);
			stmt.setString(2, f_id);

			rs = stmt.executeQuery();

		} catch (SQLException e) {
			System.out.println(" Query fail");
			System.out.println("error " + e);

		} finally {
			closeDB(conn, stmt, rs);
		}
		return friend;
	}
	//친구목록을 가져옴.
	
	public boolean findFriend(Friends friend) throws NamingException,
			ClassNotFoundException {

		boolean findOk = false;

		try {
			conn = getconnection(conn);
			stmt = conn.prepareStatement(Util.FINDFRIENDQUERY);
			stmt.setString(2, friend.getF_id());

			int count = stmt.executeUpdate();
			if (count > 0) {
				System.out.println("find ok");
				findOk = true;
			}

		} catch (SQLException e) {
			System.out.println("find Query fail");
			System.out.println("error " + e);

		} finally {
			closeDB(conn, stmt, rs);
		}
		return findOk;
	}
	//친구 추가할 시 친구find.
	
	public boolean hasfriendId(Friends friend) throws NamingException {
		System.out.println("has friend id");
		boolean isOk = false;
		if (getFriend(friend.getF_id()) != null) {
			isOk = true;
		}
		return isOk;
	}
	//친구추가할시 친구목록에 있을 시 에러.

	public boolean insertRequest(int r_id, int u_id) throws NamingException {
		// TODO Auto-generated method stub
		System.out.println("insert request start");
		boolean insertRequstOk = false;
		
		try {
			conn = getconnection(conn);
			stmt = conn.prepareStatement(Util.INSERTREQ);
			stmt.setInt(1, r_id);
			stmt.setInt(2, u_id);
			int count = stmt.executeUpdate();
			if(count >0){
				System.out.println("insert query ok");
				insertRequstOk = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("insert query fail");
			e.printStackTrace();
		}finally {
			closeDB(conn, stmt);
		}
		
		return insertRequstOk;
	}

	public boolean hasRequest(String n_id) throws NamingException {
		// TODO Auto-generated method stub
		boolean hasRequest = false;
		int id = Integer.parseInt(n_id);
		
		conn = getconnection(conn);
		try {
			stmt = conn.prepareStatement(Util.HASREQUEST);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if(rs.next()){
				System.out.println("hasRequest");
				hasRequest = true;
			}else{
				System.out.println("has no request");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("has request query fail");
			e.printStackTrace();
		}finally {
			closeDB(conn, stmt,rs);
		}		
		return hasRequest;
	}

	public List<User> getRequesterList(String n_id) throws NamingException {
		// TODO Auto-generated method stub
		System.out.println("get requester start");
		int id = Integer.parseInt(n_id);
		System.out.println(id);
		Request request = new Request();
		UserDAO uDAO = new UserDAO();
		User requester = new User();
		List<User> reqList = new ArrayList<User>();
		
		conn = getconnection(conn);
		try {
			stmt = conn.prepareStatement(Util.HASREQUEST);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			while(rs.next()){
				System.out.println("get reqeuster list query ok");
				request.setR_id(rs.getInt("r_id"));
				request.setU_id(rs.getInt("u_id"));
				System.out.println(request.getR_id());
				
				requester=uDAO.getUserByNum(request.getR_id());
				reqList.add(requester);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("get reqeuster list query fail");
			e.printStackTrace();
		}
		return reqList;
	}

	public ArrayList<User> getFriends(int id) throws NamingException {
		// TODO Auto-generated method stub
		System.out.println("get Friends start");
		ArrayList<User> friends = new ArrayList<User>();
		User friend = new User();
		UserDAO uDAO = new UserDAO();
		Friends getFri = new Friends();
		conn = getconnection(conn);
		try {
			stmt = conn.prepareStatement(Util.GETFRIEND);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			
			while(rs.next()){
				getFri.setF_id(rs.getString("f_id"));
				friend = uDAO.getUserByNum(Integer.parseInt(getFri.getF_id()));
				
				friends.add(friend);
			}
			System.out.println(friends.size());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("get friend query fail");
			e.printStackTrace();
		}finally {
			closeDB(conn, stmt, rs);
		}
		return friends;
	}
	public boolean addFriend(int userID, int friendID) throws NamingException {
		// TODO Auto-generated method stub
		System.out.println("add friend start");
		boolean addOk = false;
		
		conn = getconnection(conn);
		try {
			stmt = conn.prepareStatement(Util.ADDFRIEND);
			stmt.setInt(1, userID);
			stmt.setInt(2, friendID);
			int count = stmt.executeUpdate();
			if(count >0){
				System.out.println("add friend ok");
				addOk=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("add friend query fail");
			e.printStackTrace();
		} finally {
			closeDB(conn, stmt);
		}
		return addOk;
	}

	public boolean deleteFriend(int u_id, int f_id) throws NamingException {
		// TODO Auto-generated method stub
		System.out.println("delete friend start");
		boolean delfriendOk = false;
		
		conn = getconnection(conn);
		try {
			stmt = conn.prepareStatement(Util.DELFRIENDSQL);
			stmt.setInt(1, u_id);
			stmt.setInt(2, f_id);
			int count = stmt.executeUpdate();
			if(count > 0){
				System.out.println("delete friend ok");
				delfriendOk=true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("delete friend query fail");
			e.printStackTrace();
		}
		
		return delfriendOk;
	}	
}
