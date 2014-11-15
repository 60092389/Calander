package Util;

public class Util {
	
	public static final String ENCORDING = "UTF-8";
	
	public static final String USER = "root";

	public static final String LOGINQUERY = "SELECT * FROM User WHERE u_id =? AND password=?";
	public static final String CREATEUSERQUERY = "INSERT INTO User (u_id, password, name) VALUES(?,?,?)";
	public static final String HASUSERID = "SELECT * FROM User WHERE u_id = ?";
	public static final String ADDSCHEDULE = "INSERT INTO Schedule (title, year, month, day, content, userid) VALUES (?,?,?,?,?,?)";
	
	
	public static final String ADMIN = "admin";
	public static final String LOGIN = "Login";
	public static final String JOIN = "Join";
	public static final String LOGOUT = "Logout";
	public static final String USERUPDATE = "User Update";
	public static final String USERCU  = "User Create/Update";
	public static final String SESSION = "session";
	public static final String FRIENDS = "Friends";
	public static final String ADDFRIENDS = "AddFriends";
	public static final String MAINPATH = "index.jsp";
	public static final String ERRORPATH = "error.jsp";
	public static final String CALENDARPATH = "CalendarView.jsp";
	public static final String ADMINPATH = "AdminView.jsp";
	
	public static final String USERTYPE = "type";

	public static final String USERID = "id";






	

	
	

}
