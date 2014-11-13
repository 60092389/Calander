package servlet.controler;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Util.Util;
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Get start");
		String type = request.getParameter(Util.USERTYPE);
		
		if(type.equals(Util.SESSION)){
			String userId = request.getParameter(Util.USERID);
			
			if(userId.equals(Util.ADMIN)){
				goPage(request, response,Util.ADMINPATH);
			}else{
				User user = new User();
				user.setU_id(userId);
				goPage(request, response, Util.CALENDARPATH, user);
			}
		}else if(type.equals(Util.LOGOUT)){
			removeSession(request);
			goPage(request, response,Util.MAINPATH);
		}
	}

	private void removeSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Post start");
		String type = request.getParameter(Util.USERTYPE);
		
		if(type.equals(Util.SESSION)){
			String userId = request.getParameter(Util.USERID);
			
			if(userId.equals(Util.ADMIN)){
				goPage(request, response,Util.ADMINPATH);
			}else{
				User user = new User();
				user.setU_id(userId);
				goPage(request, response, Util.CALENDARPATH, user);
			}
		}else if(type.equals(Util.JOIN)){
			try {
				System.out.println("Join called");
				createUser(request,response);
			} catch (NamingException | ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(type.equals(Util.LOGIN)){
			try {
				System.out.println("login called");
				loginUser(request,response);
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				System.out.println("오류");
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void loginUser(HttpServletRequest request,
			HttpServletResponse response) throws NamingException, ServletException, IOException, ClassNotFoundException {
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
		if(checkUserId(userDao,user) && !user.getPassword().equals("")){			
			if(userDao.loginUser(user)){
				System.out.println("longin ok!!");
				giveSession(request, user);				
				goPage(request, response, Util.MAINPATH);
			}else{
				System.out.println("longin failed");
				goPage(request,response,Util.ERRORPATH);
			}
		}else{
			System.out.println("longin failed");
			goPage(request,response,Util.ERRORPATH);
		}
	}

	private void createUser(HttpServletRequest request,
			HttpServletResponse response) throws NamingException, ServletException, IOException, ClassNotFoundException, SQLException {
		System.out.println("Create user start");
		User user = new User();     			
		UserDAO userDao = new UserDAO();			
		
		
		String inputId = request.getParameter("inputSignupId");
		String inputName = request.getParameter("inputSignupName");
		String inputPassword = request.getParameter("inputSignupPassword");
		String inputPasswordConfirm = request.getParameter("inputSignupPasswordConfirm");
		
		System.out.println("!"+inputId);
		System.out.println("!"+inputName);
		System.out.println("!"+inputPassword);
		System.out.println("!"+inputPasswordConfirm);
		
		user.setU_id(inputId);
		user.setName(inputName);
		
		if(!checkUserId(userDao,user) && checkUserPwd(inputPassword,inputPasswordConfirm)){
			user.setPassword(inputPassword);
			
			if(userDao.createUser(user)){
				//giveSession(request, user);
				
				goPage(request, response, Util.MAINPATH);
			}else{
				goPage(request, response, Util.ERRORPATH);
			}			
		}
	}
	
	private boolean checkUserPwd(String inputPassword, String inputPasswordConfirm) {
		boolean isOk = false;
		if(inputPassword.equals(inputPasswordConfirm) && !inputPassword.equals("") && 
				inputPassword.length()>=8){
			isOk = true;
		}
		return isOk;
	}

	private boolean checkUserId(UserDAO userDao, User user) throws NamingException {
		boolean isOk =false;
		System.out.println("check user id");
		if(userDao.hasUserId(user) && !user.getU_id().equals("") && user.getU_id().length()>=4){
			isOk=true;
		}			
		System.out.println(isOk);
		return isOk;
	}

	
	private void giveSession(HttpServletRequest request, User user) {
		System.out.println("give session");
		HttpSession session = request.getSession();
		session.setAttribute(Util.LOGIN, user.getU_id());		
	}
}
