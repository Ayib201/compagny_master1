package com.groupeisi.com.company.config;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


/**
 * Servlet Filter implementation class PrivateFilter
 */
@WebFilter("/*")
public class PrivateFilter implements Filter {

	private static final Logger logger = LoggerFactory.getLogger(PrivateFilter.class);

	/**
     */
    @Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		// on r´ecup`ere le nom de la session
		String username = (String)session.getAttribute("username");
		// on r´ecup`ere le chemin demand´e par l’utilisateur
		String chemin = req.getServletPath();
		
		// on r´ecup`ere la m´ethode HTTP utilis´ee (GET ou POST)
		String method = req.getMethod();
		if (username != null || chemin.equals("/") || chemin.equals("/login")|| chemin.equals("/sigup") || chemin.equals("/logout") || chemin.equals("/index.jsp") || chemin.equals("/login") && method.equalsIgnoreCase("POST") || chemin.equals("/singup") && method.equalsIgnoreCase("POST") || chemin.startsWith("/public/"))
			chain.doFilter(request, response);
		else
			res.sendRedirect(req.getContextPath());//retourne le chemin racine de l'application
	}


	/**
	 * detruit l'object de gestion des filtres
     */
	@Override
	public void destroy() throws UnsupportedOperationException {
		logger.info("Arret du filter");
	}


	/**
	 * @param arg0 : argument pour initialiser le filter
	 * @throws ServletException : exception envoyer en as d'erreur
	 */
	@Override
	public void init(FilterConfig arg0) throws UnsupportedOperationException, ServletException {
		logger.info("Initialisation du filter");
	}

}
