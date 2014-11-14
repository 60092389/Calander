package DAO;

import java.sql.SQLException;

import javax.naming.NamingException;

import DTO.Schedule;
import Util.Util;

public class ScheduleDAO extends JDBC{
	
	public ScheduleDAO(){
		super();
	}

	public static Object getSchedule(String id, String year, String month) {
		Schedule schedule;
		return null;
	}

	public boolean addSchedule(Schedule schedule) throws SQLException {
		boolean addOk = false;
		
		try {
			System.out.println("add schedule start");
			conn = getconnection(conn);
			
			stmt = conn.prepareStatement(Util.ADDSCHEDULE);
			stmt.setString(1, schedule.getTitle());
			stmt.setString(2, schedule.getYear());
			stmt.setString(3, schedule.getMonth());
			stmt.setString(4, schedule.getDay());
			stmt.setString(5, schedule.getContents());
			stmt.setString(6, schedule.getUserid());
			
			int count = stmt.executeUpdate();
			if(count>0){
				System.out.println("add ok");
				addOk = true;
			}
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block			
			e.printStackTrace();
		} finally{
			closeDB(conn, stmt, rs);
		}		
		return addOk;		
	}

}
