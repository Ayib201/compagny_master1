package com.groupeisi.com.company.controllers;

import java.io.IOException;

import com.groupeisi.com.company.dto.UserDto;
import com.groupeisi.com.company.requests.UserRequest;
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
	private static final String KEY_MESSAGE    = "errorMessage";

	private transient IUserService userService;
	private static final Logger logger = LoggerFactory.getLogger(AdminServlet.class);

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.userService = new UserService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			UserRequest request = UserRequest.from(req);

			if (request.isDelete()) {
				userService.delete(request.getEmail());
				resp.sendRedirect(REDIRECT_ADMIN);
				return;
			}

			if (request.isEdit()) {
				userService.get(request.getEmail())
						.ifPresent(user -> req.setAttribute("editUser", user));
			}

			loadPage(req, resp);

		} catch (Exception e) {
			logger.error("Erreur chargement liste utilisateurs", e);
			req.setAttribute(KEY_MESSAGE, e.getMessage());
			loadPage(req, resp);
		}
	}

	// ── POST ──────────────────────────────────────────────────────────

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			UserRequest request = UserRequest.from(req);

			if (request.isDelete()) {
				userService.delete(request.getEmail());
				resp.sendRedirect(REDIRECT_ADMIN);
				return;
			}

			UserDto dto = request.toDto();

			if (request.isEdit()) {
				userService.update(dto);
			} else if (request.isCreate()) {
				userService.save(dto);
			}

			resp.sendRedirect(REDIRECT_ADMIN);

		} catch (Exception e) {
			req.setAttribute(KEY_MESSAGE, e.getMessage());
			loadPage(req, resp);
		}
	}

	private void loadPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("usersList", userService.getAll());
		req.getRequestDispatcher("WEB-INF/jsp/users/list.jsp").forward(req, resp);
	}
}