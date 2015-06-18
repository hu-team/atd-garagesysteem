package nl.atd.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.atd.helper.AuthHelper;

public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 3867500047504257874L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(AuthHelper.isLoggedIn(req.getSession())) {
			AuthHelper.logOut(req.getSession());
			
			resp.sendRedirect(req.getContextPath() + "/");
		}else{
			resp.sendRedirect(req.getContextPath() + "/");
		}
	}
}
