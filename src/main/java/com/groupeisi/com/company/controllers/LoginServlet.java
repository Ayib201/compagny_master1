package com.groupeisi.com.company.controllers;

import java.io.IOException;
import java.util.Optional;


import com.groupeisi.com.company.dto.UserDto;
import com.groupeisi.com.company.services.user.IUserService;
import com.groupeisi.com.company.services.user.UserService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(name = "login", value = "/login")
public class LoginServlet extends HttpServlet {

	private final Logger log = LoggerFactory.getLogger(LoginServlet.class);
	private IUserService userService;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		userService = new UserService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			req.getRequestDispatcher("index.jsp").forward(req, resp);
		} catch (Exception ex) {
			log.error("Error", ex);
		}

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String email = req.getParameter("email");
		String pwd = req.getParameter("password");
		
		log.info("L'email envoyé est {} ", email);
		try {
			Optional<UserDto> user = userService.login(email, pwd);
			if (user.isPresent()) {
				req.getSession().setAttribute("username", email);
				resp.sendRedirect("welcome");
			}
		} catch (Exception e) {
			log.error("Erreur", e);
		}
		try {
			resp.sendRedirect("login");
		} catch (Exception ex) {
			log.error("Error : ", ex);
		}

	}
}
