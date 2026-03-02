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
			String action = req.getParameter("action");
			String email = req.getParameter("id");

			if ("delete".equals(action) && email != null) {
				userService.delete(email);
				resp.sendRedirect("admin");
				return;
			}

			if ("edit".equals(action) && email != null) {
				userService.get(email).ifPresent(user ->
						req.setAttribute("editUser", user)
				);
			}
			loadPage(req, resp);
		} catch (Exception e) {
			logger.error("Error : ", e);
		}
	}

	private void loadPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("usersList", userService.getAll());
		req.getRequestDispatcher("WEB-INF/jsp/users/list.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String id = req.getParameter("id");
			String firstName = req.getParameter("firstName");
			String lastName  = req.getParameter("lastName");
			String email     = req.getParameter("email");
			String password  = req.getParameter("password");
			UserDto userDto = new UserDto(email,firstName, lastName , password);
			if (id != null && !id.isEmpty()) {
				userService.update(userDto);
			} else {
				userService.save(userDto);
			}
			resp.sendRedirect("admin");
		} catch (Exception e) {
			logger.error("Error : ", e);
		}
	}
}