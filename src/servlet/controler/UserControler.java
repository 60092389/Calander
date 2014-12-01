package servlet.controler;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Util.Util;
import DAO.FriendDAO;
import DAO.UserDAO;
import DTO.User;

public class UserControler extends SharedControler {

	private static final long serialVersionUID = 1L;

	public UserControler() {
		// TODO Auto-generated constructor stub
		super();
		System.out.println("UserControler");
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Get start");
		String type = request.getParameter(Util.USERTYPE);

		if (type.equals(Util.SESSION)) {
			String userId = request.getParameter(Util.USERID);

			if (userId.equals(Util.ADMIN)) {
				goPage(request, response, Util.ADMINPATH);
			} else {
				User user = new User();
				user.setU_id(userId);
				user.setN_id(request.getParameter("nid"));
				FriendDAO fDAO = new FriendDAO();
				try {
					if (fDAO.hasRequest(user.getN_id())) {
						List<User> requester = fDAO.getRequesterList(user
								.getN_id());
						request.setAttribute("reqlist", requester);
						System.out.println("get requester ok");
						System.out.println(requester.get(0).getName());
						goPage(request, response, Util.REQEUSTVIEW);
					} else {
						goPage(request, response, Util.CALENDARPATH, user);
					}
				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else if (type.equals(Util.LOGOUT)) {
			removeSession(request);
			goPage(request, response, Util.MAINPATH);
		} else if (type.equals(Util.USERDELETEBTN)) {
			System.out.println("user delete called");
			try {
				deleteUser(request, response);
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (type.equals(Util.REQUEST)) {
			System.out.println("request called");
			try {
				sendRequest(request, response);
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (type.equals(Util.REJECTREQ)) {
			try {
				rejectRequest(request, response);
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (type.equals(Util.FRIENDS)) {
			doPost(request, response);
		} else if(type.equals(Util.DELFRIEND)){
			try {
				deleteFriend(request, response);
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void deleteFriend(HttpServletRequest request,
			HttpServletResponse response) throws NamingException, ServletException, IOException {
		// TODO Auto-generated method stub
		int f_id = Integer.parseInt(request.getParameter(Util.FRIENDID));
		int u_id = Integer.parseInt(request.getParameter(Util.USERID));
		FriendDAO fDAO = new FriendDAO();
		if(fDAO.deleteFriend(u_id,f_id) && fDAO.deleteFriend(f_id, u_id)){
			friendList(request, response);	
		}else{
			System.out.println("delete friend fail");
		}
		
	}

	private void rejectRequest(HttpServletRequest request,
			HttpServletResponse response) throws NamingException,
			ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("rejectRequest start");
		UserDAO uDAO = new UserDAO();
		int userID = Integer.parseInt(request.getParameter(Util.USERID));
		int friendID = Integer.parseInt(request.getParameter("friednid"));

		if (uDAO.deleteReq(userID, friendID)) {
			goPage(request, response, Util.MAINPATH);
		}

	}

	private void sendRequest(HttpServletRequest request,
			HttpServletResponse response) throws NamingException,
			ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("send request start");
		// 요청 받는 사람 아이디
		int u_id = Integer.parseInt(request.getParameter(Util.FID));
		// 요청 하는 사람 아이디
		int r_id = Integer.parseInt(request.getParameter(Util.USERID));
		FriendDAO fDAO = new FriendDAO();
		if (fDAO.insertRequest(r_id, u_id)) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('요청 완료');history.go(-1);</script>");
			out.flush();
		}else{
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('이미 요청하였습니다.');history.go(-1);</script>");
			out.flush();
		}
			
	}

	private void removeSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Post start");
		String type = request.getParameter(Util.USERTYPE);

		if (type.equals(Util.SESSION)) {
			String userId = request.getParameter(Util.USERID);

			if (userId.equals(Util.ADMIN)) {
				goPage(request, response, Util.ADMINPATH);
			} else {
				User user = new User();
				user.setU_id(userId);
				user.setN_id(request.getParameter("nid"));
				FriendDAO fDAO = new FriendDAO();
				try {
					if (fDAO.hasRequest(user.getN_id())) {
						List<User> requester = fDAO.getRequesterList(user
								.getN_id());
						request.setAttribute("reqlist", requester);
						System.out.println("get requester ok");
						System.out.println(requester.get(0).getName());
						goPage(request, response, Util.REQEUSTVIEW);
					} else {
						goPage(request, response, Util.CALENDARPATH, user);
					}
				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else if (type.equals(Util.JOIN)) {
			try {
				System.out.println("Join called");
				createUser(request, response);
			} catch (NamingException | ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (type.equals(Util.LOGIN)) {
			try {
				System.out.println("login called");
				loginUser(request, response);
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				System.out.println("오류");
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (type.equals(Util.USERUPDATE)) {
			System.out.println("user update called");
			updateUser(request, response);
		} else if (type.equals(Util.ADDFRIENDS)) {
			System.out.println("add friends called");
			try {
				searchFriend(request, response);
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (type.equals(Util.ACCEPTREQ)) {
			try {
				acceptReq(request, response);
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (type.equals(Util.FRIENDS)) {
			try {
				friendList(request, response);
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(type.equals(Util.SHOWFS)){
			try {
				showFriendSchedule(request,response);
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void showFriendSchedule(HttpServletRequest request,
			HttpServletResponse response) throws NamingException, ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("show friend schedule start");
		int f_id = Integer.parseInt(request.getParameter(Util.FRIENDID));
		UserDAO uDAO = new UserDAO();
		User friend = uDAO.getUserByNum(f_id);
		
		request.setAttribute("f_id", request.getParameter(Util.FRIENDID));
		goPage(request, response, Util.CALENDARPATH, friend);
	}

	private void friendList(HttpServletRequest request,
			HttpServletResponse response) throws NamingException,
			ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("friend list called");
		int id = Integer.parseInt(request.getParameter(Util.USERID));
		FriendDAO fDAO = new FriendDAO();
		System.out.println("!!" + id);
		ArrayList<User> friendList = fDAO.getFriends(id);

		request.setAttribute("friendList", friendList);
		goPage(request, response, Util.FRIENDLIST);
	}

	private void acceptReq(HttpServletRequest request,
			HttpServletResponse response) throws NamingException,
			ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("accept reqeust start");
		int userID = Integer.parseInt(request.getParameter(Util.USERID));
		int friendID = Integer.parseInt(request.getParameter("friednid"));
		FriendDAO fDAO = new FriendDAO();
		UserDAO uDAO = new UserDAO();
		// 양쪽다 친구를 추가 하기 위해 파라미터를 바꿔 두번 호출
		if(fDAO.addFriend(userID, friendID) && fDAO.addFriend(friendID, userID)){
			System.out.println("friend add ok");
			if (uDAO.deleteReq(userID, friendID)) {
				goPage(request, response, Util.MAINPATH);
			}
		} else {
			System.out.println("friend add fail");
		}

	}

	private void searchFriend(HttpServletRequest request,
			HttpServletResponse response) throws NamingException,
			ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("search friend start");
		String searchId = request.getParameter("inputFriendId");
		UserDAO userDao = new UserDAO();
		User friend = userDao.getUser(searchId);
		request.setAttribute("friend", friend);
		goPage(request, response, Util.SEARCHVIEW);
	}

	private void deleteUser(HttpServletRequest request,
			HttpServletResponse response) throws NamingException,
			ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("delete user start");
		UserDAO userDao = new UserDAO();
		int id = Integer.parseInt(request.getParameter(Util.USERID));

		if (userDao.deleteUser(id)) {
			System.out.println("delete user ok");
			goPage(request, response, Util.ADMINPATH);
		} else {
			System.out.println("delete user fail");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('유저 삭제 오류');history.go(-1);</script>");
			out.flush();
			// 에러팝업창
			// goPage(request, response, Util.ERRORPATH);
		}

	}

	private void loginUser(HttpServletRequest request,
			HttpServletResponse response) throws NamingException,
			ServletException, IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("login start");
		User user = new User();
		UserDAO userDao = new UserDAO();

		String inputId = request.getParameter("inputLoginId");
		String inputPassword = request.getParameter("inputLoginPassword");

		user.setU_id(inputId);
		user.setPassword(inputPassword);

		System.out.println("id : " + user.getU_id());
		System.out.println("pwd : " + user.getPassword());

		System.out.println("user set ok");
		if (checkUserId(userDao, user) && !user.getPassword().equals("")) {
			if (userDao.loginUser(user)) {
				System.out.println("longin ok!!");
				giveSession(request, user);
				goPage(request, response, Util.MAINPATH);
			} else {
				System.out.println("longin failed");
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('비밀번호 오류');history.go(-1);</script>");
				out.flush();
				// goPage(request, response, Util.MAINPATH);
			}
		} else {
			System.out.println("longin failed");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('유저 정보 조회 실패');history.go(-1);</script>");
			out.flush();
			// goPage(request, response, Util.MAINPATH);
		}
	}

	private void createUser(HttpServletRequest request,
			HttpServletResponse response) throws NamingException,
			ServletException, IOException, ClassNotFoundException, SQLException {
		System.out.println("Create user start");
		User user = new User();
		UserDAO userDao = new UserDAO();

		String inputId = request.getParameter("inputSignupId");
		String inputName = request.getParameter("inputSignupName");
		String inputPassword = request.getParameter("inputSignupPassword");
		String inputPasswordConfirm = request
				.getParameter("inputSignupPasswordConfirm");

		System.out.println("!" + inputId);
		System.out.println("!" + inputName);
		System.out.println("!" + inputPassword);
		System.out.println("!" + inputPasswordConfirm);

		user.setU_id(inputId);
		user.setName(inputName);

		if (!checkUserId(userDao, user)
				&& checkUserPwd(inputPassword, inputPasswordConfirm)) {
			user.setPassword(inputPassword);

			if (userDao.createUser(user)) {
				// giveSession(request, user);

				goPage(request, response, Util.MAINPATH);
			} else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('아이디 중복');history.go(-1);</script>");
				out.flush();
				// goPage(request, response, Util.ERRORPATH);
			}
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('ID 4자리 이상 / PW 8자리 이상 / 공란 확인');history.go(-1);</script>");
			out.flush();
		}
	}

	private boolean checkUserPwd(String inputPassword,
			String inputPasswordConfirm) {
		boolean isOk = false;
		if (inputPassword.equals(inputPasswordConfirm)
				&& !inputPassword.equals("") && inputPassword.length() >= 8) {
			isOk = true;
		}
		return isOk;
	}

	private boolean checkUserId(UserDAO userDao, User user)
			throws NamingException {
		boolean isOk = false;
		System.out.println("check user id");
		if (userDao.hasUserId(user) && !user.getU_id().equals("")
				&& user.getU_id().length() >= 4) {
			isOk = true;
		}
		System.out.println(isOk);
		return isOk;
	}

	private void giveSession(HttpServletRequest request, User user) {
		System.out.println("give session");
		HttpSession session = request.getSession();
		session.setAttribute(Util.LOGIN, user.getU_id());
	}

	private void updateUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("update User start");

		User user = new User(); // 수정되는 user의 정보를 담을 객체 변수
		UserDAO uDAO = new UserDAO(); // userDAO에 접근하기 위한 객체 변수
		int id = Integer.parseInt(request.getParameter("userid"));
		String inputName = request.getParameter("inputUpdateName");
		String inputPassword = request.getParameter("inputUpdatePassword");
		String inputPasswordConfirm = request
				.getParameter("inputUpdatePasswordConfirm");

		System.out.println("update pwd " + inputPassword);
		System.out.println("update pwdc " + inputPasswordConfirm);
		System.out.println("update name " + inputName);

		user.setName(inputName);

		// 정보 수정시 입력되는 비밀번호가 일치하는 경우
		if (checkUserPwd(inputPassword, inputPasswordConfirm)) {
			System.out.println("password ok");
			user.setPassword(inputPassword);

			// 입력된 정보로 user의 정보를 수정
			try {
				if (uDAO.updateUser(user, id)) {
					System.out.println("Update user");
					goPage(request, response, Util.MAINPATH);

					// 사용자의 정보 수정이 실패한 경우
				} else {
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script>alert('정보수정에 실패 했습니다.');history.go(-1);</script>");
					out.flush();
					// goPage(request, response, Util.MAINPATH);
				}
			} catch (ClassNotFoundException | NamingException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// 사용자가 입력한 비밀번호가 일치하지 않는 경우
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('비밀번호가 일치하지 않습니다.');history.go(-1);</script>");
			out.flush();
			// goPage(request, response, Util.MAINPATH);
		}
	}
	
	public boolean isFriend(int u_id, int f_id) throws NamingException{ 
		System.out.println("is friend start");
		boolean isfriend = false;
		FriendDAO fDAO = new FriendDAO();
		ArrayList<User> friends = fDAO.getFriends(u_id);
		
		for(int i = 0; i < friends.size(); i++){
			if(Integer.parseInt(friends.get(i).getN_id()) == f_id){
				System.out.println("이미 친구 입니다.");
				isfriend = true;
				break;
			}
		}		
		return isfriend;
	}
	
	//페이지 넘기기
	public void goPage(HttpServletRequest request,
			HttpServletResponse response, String path) throws ServletException, IOException
	{
		System.out.println("go page");
		RequestDispatcher rd;
		rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}
	
	public int getNumPages(int pageNo, int numInPage) throws ClassNotFoundException{
		System.out.println("get num pages");
		int numPages, numItems;				
		List<User> users = new ArrayList<User>();
		UserDAO uDAO = new UserDAO();
		try {
			users = uDAO.getAllUsers();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		numItems = users.size();
		numPages = (int)Math.ceil((double)numItems/(double)numInPage);
		return numPages;
	}
	
	public int getStartPos(int pageNo, int numInPage){		
		System.out.println("get start pos");
		System.out.println(pageNo);
		int startPos = (pageNo-1) * numInPage;
		return startPos;
	}
	public List<User> getPageUsers(int startPos, int numInPage) throws ClassNotFoundException, SQLException{
		System.out.println("get page users");
		List<User> users = new ArrayList<User>();
		UserDAO uDAO = new UserDAO();
		users = (List<User>)uDAO.getPage(startPos,numInPage);
		
		return users;		
	}	
	
}
