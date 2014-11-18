package servlet.controler;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Util.Util;
import DAO.ScheduleDAO;
import DAO.UserDAO;
import DTO.Schedule;
import DTO.User;

public class CalendarControler extends SharedControler{
	
	private static final long serialVersionUID = 1L;
	private ScheduleDAO scheduleDAO = new ScheduleDAO();

	public CalendarControler() {
		super();
		System.out.println("calendar controller");
	}
	
	public CalendarControler(HttpServletRequest request,
			HttpServletResponse response, User user) {
		System.out.println("calendar controller");
		initializeCalendar(request, response, user);
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("doGet Start");
		String resultPage = null;

		if (request.getParameter("type").equals(Util.SCHEDULEDELETE))
		{
			System.out.println("controler schedule delete in");
			int scheduleid = Integer.parseInt(request.getParameter("scheduleid"));
			System.out.println("!get"+scheduleid);
			User user = null;

			try {
				if(scheduleDAO.deleteSchedule(scheduleid)){
					UserDAO userDAO = new UserDAO();
					resultPage = Util.CALENDARPATH;
					user = isSession(request, userDAO);					
				}
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				resultPage =Util.ERRORPATH;
				e.printStackTrace();
			}
			goPage(request, response,resultPage, user);

		}		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("calendar post start");
		
		User user = null;
		UserDAO userDAO = new UserDAO();

		try {
			user = isSession(request, userDAO);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String type = request.getParameter("whatRequest");
		System.out.println(user.getU_id());
		System.out.println("!!"+type);
		
		if(type.equals("changeMonth")){
			
			System.out.println("change Month");
			String year = request.getParameter("year");
			String month = request.getParameter("month");
			System.out.println("!!!"+year);
			System.out.println("!!!"+month);

			System.out.println(year);
			System.out.println(month);
			
			request.setAttribute("year", year);
			request.setAttribute("month", month);
			request.setAttribute("schedules",
					scheduleDAO.getSchedule(user.getU_id(), year, month));
			
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(Util.CALENDARPATH);
			dispatcher.forward(request, response);
		}else if(type.equals("addSchedule")){
			if(user != null){
				Schedule schedule = new Schedule();
				
				String title = request.getParameter("inputTitle");
				String day = request.getParameter("inputDay");
				String contents = request.getParameter("inputContents");
				
				SimpleDateFormat transFormat = new SimpleDateFormat(
						"yyyy-MM-dd");
				Date date = null;				
				try {
					date = transFormat.parse(day);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				
				schedule.setTitle(title);
				System.out.println("!!"+cal.get(cal.YEAR)+cal.get(cal.MONTH)+
						cal.get(cal.DAY_OF_MONTH));
				
				schedule.setYear(String.valueOf(cal.get(cal.YEAR)));
				schedule.setMonth(String.valueOf(cal.get(cal.MONTH)+1));
				schedule.setDay(String.valueOf(cal.get(cal.DAY_OF_MONTH)));
				schedule.setContents(contents);
				schedule.setUserid(user.getU_id());
				
				try {
					if(scheduleDAO.addSchedule(schedule)){
						goPage(request, response, Util.MAINPATH);
					}else{
						goPage(request, response, Util.ERRORPATH);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("add schedule fail");
					e.printStackTrace();
				}
			}
		}else if(type.equals("viewdetail")){

			int scheduleid = Integer.parseInt(request.getParameter("scheduleid"));
			Schedule singleSchedule = scheduleDAO
					.getSingleSchedule(scheduleid);
			if (singleSchedule.getDay().length() < 2)
			{
				singleSchedule.setDay("0" + singleSchedule.getDay());
			}
			if (singleSchedule.getMonth().length() < 2)
			{
				singleSchedule.setMonth("0" + singleSchedule.getMonth());
			}
			request.setAttribute("singleSchedule", singleSchedule);
			goPage(request, response, Util.DETAILVIEWPATH);
		}
	}

	private void initializeCalendar(HttpServletRequest request,
			HttpServletResponse response, User user) {
		Calendar sendDayInfo = Calendar.getInstance();

		String year = String.valueOf(sendDayInfo.get(Calendar.YEAR));
		String month = String.valueOf(sendDayInfo.get(Calendar.MONTH) + 1);

		System.out.println("initializing calendar");
		System.out.println(user.getU_id());
		System.out.println(year);
		System.out.println(month);

		request.setAttribute("year", year);
		request.setAttribute("month", month);
		request.setAttribute("schedules",
				scheduleDAO.getSchedule(user.getU_id(), year, month));
	}	


	
}
