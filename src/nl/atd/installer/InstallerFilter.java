package nl.atd.installer;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.atd.helper.InstallHelper;

public class InstallerFilter implements Filter {

	@Override
	public void destroy() {}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		InstallHelper ih = new InstallHelper(request.getServletContext());
		if(!ih.isInstalled()) {
			chain.doFilter(request, response);
		}else{
			((HttpServletResponse)response).sendRedirect(((HttpServletRequest)request).getContextPath() + "/login.jsp");
			return;
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}

}
