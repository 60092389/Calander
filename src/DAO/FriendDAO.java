package DAO;

import java.sql.*;

import javax.naming.*;
import javax.sql.*;

import DTO.Friends;
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
			ClassNotFoundException, SQLException {

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
}
