package com.groupeisi.com.company.controllers;

import java.io.IOException;

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

	private static final String REDIRECT_ADMIN = "admin";
	private static final String ACTION_DELETE  = "delete";
	private static final String ACTION_EDIT    = "edit";
	private static final String ACTION_UPDATE  = "update";

	private transient IUserService userService;
	private static final Logger logger = LoggerFactory.getLogger(AdminServlet.class);

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		userService = new UserService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String action = req.getParameter("action");
			String email = req.getParameter("id");

			if (ACTION_DELETE.equals(action) && email != null) {
				userService.delete(email);
				resp.sendRedirect(REDIRECT_ADMIN);
				return;
			}

			if (ACTION_EDIT.equals(action) && email != null) {
				userService.get(email)
						.ifPresent(user -> req.setAttribute("editUser", user));
			}

			loadPage(req, resp);

		} catch (Exception e) {
			logger.error("Error : ", e);
		}
	}

	private void loadPage(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setAttribute("usersList", userService.getAll());
		req.getRequestDispatcher("WEB-INF/jsp/users/list.jsp")
				.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String action = req.getParameter("action");

			if (ACTION_DELETE.equals(action)) {
				String email = req.getParameter("id");
				userService.delete(email);
				resp.sendRedirect(REDIRECT_ADMIN);
				return;
			}

			String id        = req.getParameter("id");
			String firstName = req.getParameter("firstName");
			String lastName  = req.getParameter("lastName");
			String email     = req.getParameter("email");
			String password  = req.getParameter("password");

			UserDto userDto = new UserDto(email, firstName, lastName, password);

			if (ACTION_UPDATE.equals(action) && id != null && !id.isEmpty()) {
				userService.update(userDto);
			} else {
				userService.save(userDto);
			}

			resp.sendRedirect(REDIRECT_ADMIN);

		} catch (Exception e) {
			logger.error("Error : ", e);
		}
	}
}