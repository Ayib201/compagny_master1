package com.groupeisi.com.company.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


@WebServlet(name = "logout", value = "/logout")
public class LogoutServlet extends HttpServlet {

	private final Logger log = LoggerFactory.getLogger(LogoutServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getSession().invalidate();
		req.getSession().setAttribute("username", null);
		try {
			resp.sendRedirect("login");
		} catch (Exception ex) {
			log.error("Error : ", ex);
		}

	}
}
