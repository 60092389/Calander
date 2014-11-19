package servlet.controler;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.UserDAO;
import DTO.User;
import Util.Util;

public class SharedControler extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public User isSession(HttpServletRequest request, UserDAO userDao) throws NamingException{
		User user = null;
		String sessionId = null;
		
		HttpSession session = request.getSession();
		sessionId = (String) session.getAttribute(Util.LOGIN);
		
		if (sessionId != null && !sessionId.equals(""))
		{
		
			user = userDao.getUser(sessionId);
		}
		return user;		
	}
	
	public void goPage(HttpServletRequest request,
			HttpServletResponse response, String path) throws ServletException, IOException
	{
		System.out.println("go page");
		RequestDispatcher rd;
		rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}
	
	public void goPage(HttpServletRequest request,
			HttpServletResponse response, 
			String path, 
			User user)
			throws ServletException, IOException
	{

		// user를 사용하는 calendar view에 대한 경로일 경우
		if (path.equals(Util.CALENDARPATH))
		{		
			new CalendarControler(request, response, user);
			if (user != null)
			{
				request.setAttribute(Util.USER, user);
			}
		}
		goPage(request, response, path);
	}

}
