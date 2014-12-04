package Util;

public class Util {
	//
	public static final String ENCORDING = "UTF-8";	
	public static final String USER = "root";	
	public static final String ADMIN = "Admin";
	public static final String LOGIN = "Login";
	public static final String JOIN = "Join";
	public static final String LOGOUT = "Logout";
	public static final String USERUPDATE = "UserUpdate";	
	public static final String SESSION = "session";
	public static final String FRIENDS = "Friends";
	public static final String ADDFRIENDS = "AddFriends";
	public static final String SCHEDULEDELETE = "scheduleDelete";
	public static final String USERDELETEBTN = "User Delete";//11.18오후 3:49분에 추가 유저삭제버튼에 필요
	public static final String REQUEST = "friend request"; // 11-20 01:02 친구 요청에 필요
	public static final String ACCEPTREQ = "accept request";
	public static final String REJECTREQ = "reject request";
	public static final String DELFRIEND = "delete friend";
	public static final String FRIENDID = "friend id";
	public static final String SHOWFS = "show friend schedule";
	//SQL
	public static final String LOGINQUERY = "SELECT * FROM User WHERE u_id =? AND password=?";
	public static final String CREATEUSERQUERY = "INSERT INTO User (u_id, password, name) VALUES(?,?,?)";
	public static final String UPDATEQUERY = "UPDATE User SET password=?,name=? WHERE n_id =?";
	public static final String HASUSERID = "SELECT * FROM User WHERE u_id = ?";
	public static final String ADDSCHEDULE = "INSERT INTO Schedule (title, year, month, day, content, userid) VALUES (?,?,?,?,?,?)";
	public static final String DELETEUSER = "DELETE FROM User WHERE n_id = ?";//11.18 16:02/ 22 15 다시수정
	public static final String DELETESCHEDULE = "DELETE FROM Schedule WHERE s_id=?";//11.18 17:22 스케쥴삭제
	public static final String SCHEDUALLIST = "SELECT content, day,month, title, year, s_id FROM Schedule "
			+ "WHERE year=? AND month=? AND userid=?";
	public static final String GETSCHEDULE = "SELECT * FROM Schedule WHERE s_id=?";
	public static final String ALLUSERS = "SELECT * FROM User ORDER BY n_id ASC";//11.18 오전 12:41 추가 어드민뷰에 유저들불러올때 사용
	public static final String HASFRIENDID = "SELECT * FROM Friends where u_id=?";
	public static final String FINDFRIENDQUERY = "SELECT * FROM Friends where f_id=?";
	public static final String SCHEDULEUPDATE = "UPDATE Schedule SET title=?, year=?, month=?, day=?, content=? WHERE s_id=?";
	public static final String INSERTREQ = "INSERT INTO Request (r_id, u_id) values (?, ?)";
	public static final String HASREQUEST = "SELECT * FROM Request WHERE u_id=?";
	public static final String HASUSERIDBYNUM = "SELECT * FROM User WHERE n_id = ?";
	public static final String ADDFRIEND = "INSERT INTO Friends (u_id, f_id) VALUES (?, ?)";
	public static final String DELETEREQ = "DELETE FROM Request WHERE r_id =? AND u_id=?";
	public static final String GETFRIEND = "SELECT * FROM Friends WHERE u_id = ?";
	public static final String DELFRIENDSQL = "DELETE FROM Friends WHERE u_id=? AND f_id=?";
	public static final String DELFRIENDSELF = "DELETE FROM Friends WHERE f_id=?";
	//PATH
	public static final String MAINPATH = "index.jsp";
	public static final String CALENDARPATH = "CalendarView.jsp";
	public static final String ADMINPATH = "AdminView.jsp";
	public static final String DETAILVIEWPATH = "ViewSchedule.jsp";
	public static final String SEARCHVIEW = "SearchView.jsp";
	public static final String REQEUSTVIEW = "RequestView.jsp";
	public static final String FRIENDLIST = "FriendList.jsp";
	
	public static final String USERTYPE = "type";

	public static final String USERID = "id";
	public static final String FID = "f_id";
	
	

}
