package servlet.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import Util.Util;

public class ServletFilter implements Filter {

	public ServletFilter() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain nextFilter) throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println("encording " + Util.ENCORDING);
		request.setCharacterEncoding(Util.ENCORDING);
		
		nextFilter.doFilter(request, response);

	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		// TODO Auto-generated method stub

	}

}
