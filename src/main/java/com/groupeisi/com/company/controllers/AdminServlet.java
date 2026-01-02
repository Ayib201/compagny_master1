package com.groupeisi.com.company.controllers;

import java.io.IOException;

import com.groupeisi.com.company.dto.UserDto;
import com.groupeisi.com.company.services.IUserService;
import com.groupeisi.com.company.services.UserService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(name = "admin", value = "/admin")
public class AdminServlet extends HttpServlet {

	private IUserService userService;
	private final Logger logger = LoggerFactory.getLogger(AdminServlet.class);

	@Override
	public void init(ServletConfig config) throws ServletException {
		userService = new UserService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			loadPage(req, resp);
		} catch (Exception exception) {
			logger.error("Error : ", exception);
		}
	}

	private void loadPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("usersList", userService.getAll());

		req.getRequestDispatcher("WEB-INF/jsp/users/list.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		UserDto userDto = new UserDto(0, firstName, lastName, email, password);
		
		userService.save(userDto);

		try {
			loadPage(req, resp);
		} catch (Exception exception) {
			logger.error("Error : ", exception);
		}
	}
}
