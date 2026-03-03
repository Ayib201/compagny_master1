package com.groupeisi.com.company.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebServlet(name = "welcome", value = "/welcome")
public class WelcomeServlet extends HttpServlet {

	private static final Logger log = LoggerFactory.getLogger(WelcomeServlet.class);


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			req.getRequestDispatcher("WEB-INF/jsp/welcome.jsp").forward(req, resp);
		} catch (Exception ex) {
			log.error("Error : ", ex);
		}

	}
}
