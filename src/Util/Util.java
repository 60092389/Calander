package Util;

public class Util {
   
   public static final String ENCORDING = "UTF-8";
   
   public static final String USER = "root";

   public static final String LOGINQUERY = "SELECT * FROM User WHERE u_id =? AND password=?";
   public static final String CREATEUSERQUERY = "INSERT INTO User (u_id, password, name) VALUES(?,?,?)";
   public static final String HASUSERID = "SELECT * FROM User WHERE u_id = ?";
   public static final String ADDSCHEDULE = "INSERT INTO Schedule (title, year, month, day, content, userid) VALUES (?,?,?,?,?,?)";
   
   
   public static final String ADMIN = "Admin";
   public static final String LOGIN = "Login";
   public static final String JOIN = "Join";
   public static final String LOGOUT = "Logout";
   public static final String USERUPDATE = "User Update";
   public static final String USERCU  = "User Create/Update";
   public static final String SESSION = "session";
   public static final String FRIENDS = "Friends";
   public static final String ADDFRIENDS = "AddFriends";
   public static final String SCHEDULEDELETE = "scheduleDelete";
   public static final String USERDELETEBTN = "User Delete";//11.18오후 3:49분에 추가 유저삭제버튼에 필요
   
   public static final String MAINPATH = "index.jsp";
   public static final String ERRORPATH = "error.jsp";
   public static final String CALENDARPATH = "CalendarView.jsp";
   public static final String ADMINPATH = "AdminView.jsp";
   public static final String DETAILVIEWPATH = "ViewSchedule.jsp";
   
   public static final String USERTYPE = "type";

   public static final String USERID = "id";

   public static final String SCHEDUALLIST = "SELECT content, day,month, title, year, s_id FROM Schedule "
                        + "WHERE year=? AND month=? AND userid=?";

   public static final String GETSCHEDULE = "SELECT * FROM schedule WHERE s_id=?";
   public static final String ALLUSERS = "SELECT * FROM user ORDER BY n_id ASC";//11.18 오전 12:41 추가 어드민뷰에 유저들불러올때 사용
}