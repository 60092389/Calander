package DAO;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import DTO.Schedule;
import Util.Util;

public class ScheduleDAO extends JDBC {

	public ScheduleDAO() {
		super();
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
			if (count > 0) {
				System.out.println("add ok");
				addOk = true;
			}

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB(conn, stmt, rs);
		}
		return addOk;
	}

	public ArrayList<ArrayList<Schedule>> getSchedule(String id, String year,String month)  {
		System.out.println("getSchedule start");
		Schedule schedule; // DB에서 가져온 일정정보를 담는 변수
		ArrayList<ArrayList<Schedule>> schedules = getNullSchedules(); 

		try {
			conn = getconnection(conn);
			stmt = conn.prepareStatement(Util.SCHEDUALLIST);

			stmt.setString(1, year);
			stmt.setString(2, month);
			stmt.setString(3, id);
			rs = stmt.executeQuery();

			while (rs.next()) {
				System.out.println("get schedule query ok");
				schedule = new Schedule();
				schedule.setContents(rs.getString(1));
				schedule.setDay(rs.getString(2));
				schedule.setMonth(rs.getString(3));
				schedule.setTitle(rs.getString(4));
				schedule.setYear(rs.getString(5));
				schedule.setS_id(Integer.parseInt(rs.getString(6)));

				schedules.get(Integer.parseInt(schedule.getDay()))
						.add(schedule);
			}

		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("get schedule query failed");
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeDB(conn, stmt, rs);
		}
		return schedules;

	}

	public static ArrayList<Schedule> getNullScheduleList() {
		System.out.println("get null scheduleList!!");
		ArrayList<Schedule> returnSchedules = new ArrayList<Schedule>();

		return returnSchedules;

	}

	public static Schedule getNullSchedule() {
		System.out.println("get null schedule!");
		Schedule returnSchedule = new Schedule();
		returnSchedule.setTitle(null);
		returnSchedule.setContents(null);

		return returnSchedule;

	}

	public static ArrayList<ArrayList<Schedule>> getNullSchedules() {
		System.out.println("get null schedules!");
		ArrayList<ArrayList<Schedule>> returnSchedules = new ArrayList<ArrayList<Schedule>>();

		for (int i = 1; i <= 32; i++) {
			returnSchedules.add(getNullScheduleList());
		}
		return returnSchedules;

	}

	public Schedule getSingleSchedule(int scheduleid)  {
		Schedule schedule = new Schedule();
		System.out.println("get single schedule start");
		try {
			conn = getconnection(conn);
			stmt = conn.prepareStatement(Util.GETSCHEDULE);
			stmt.setInt(1, scheduleid);
			rs = stmt.executeQuery();
			if(rs.next()){
				System.out.println("get single schedule ok");
				schedule.setS_id(rs.getInt("s_id"));
				schedule.setTitle(rs.getString("title"));
				schedule.setContents(rs.getString("content"));
				schedule.setDay(rs.getString("day"));
				schedule.setMonth(rs.getString("month"));
				schedule.setYear(rs.getString("year"));
				schedule.setUserid(rs.getString("userid"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("get single schedule failed");
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeDB(conn, stmt, rs);
		}
		return schedule;
	}

	public boolean deleteSchedule(int scheduleid) throws NamingException {
		// TODO Auto-generated method stub
		System.out.println("delete schedule start");
		boolean deleteOk = false;
		
		try {
			conn = getconnection(conn);
			stmt = conn.prepareStatement(Util.DELETESCHEDULE);
			stmt.setInt(1, scheduleid);
			
			int count = stmt.executeUpdate();
			if(count > 0){
				System.out.println("schedule delete ok");
				deleteOk = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("delete schedule query fail");
			e.printStackTrace();
		}		
		return deleteOk;
	}
	
	public boolean updateSchedule(Schedule schedule, int id) throws NamingException {
		// TODO Auto-generated method stub
		boolean updatScheduleOk = false;
		
		try {
			conn = getconnection(conn);
			stmt = conn.prepareStatement(Util.SCHEDULEUPDATE);
			stmt.setString(1, schedule.getTitle());
			stmt.setString(2, schedule.getYear());
			stmt.setString(3, schedule.getMonth());
			stmt.setString(4, schedule.getDay());
			stmt.setString(5, schedule.getContents());
			stmt.setInt(6, id);
			
			int count = stmt.executeUpdate();
			if(count > 0){
				System.out.println("update schedule query ok");
				updatScheduleOk = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("update schedule query fail");
			e.printStackTrace();
		}
		
		return updatScheduleOk;
	}

}
