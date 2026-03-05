package com.groupeisi.com.company.controllers;

import com.groupeisi.com.company.dto.ProductDto;
import com.groupeisi.com.company.requests.ProduitRequest;
import com.groupeisi.com.company.services.produit.IProductService;
import com.groupeisi.com.company.services.produit.ProductService;
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

import java.io.IOException;

@WebServlet(name = "produit", value = "/produit")
public class ProductServlet extends HttpServlet {

	private static final String REDIRECT_PRODUIT = "produit";
	private static final String KEY_MESSAGE      = "errorMessage";

	private transient IProductService productService;
	private transient IUserService    userService;

	private static final Logger logger = LoggerFactory.getLogger(ProductServlet.class);

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.userService    = new UserService();
		this.productService = new ProductService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			ProduitRequest request = ProduitRequest.from(req);
			if (request.isDelete()) {
				productService.delete(request.getRef());
				resp.sendRedirect(REDIRECT_PRODUIT);
				return;
			}

			if (request.isEdit()) {
				productService.get(request.getRef())
						.ifPresent(p -> req.setAttribute("editProduct", p));
			}

			loadPage(req, resp);

		} catch (Exception e) {
			logger.error("Erreur chargement liste produits", e);
			req.setAttribute(KEY_MESSAGE, e.getMessage());
			loadPage(req, resp);
		}
	}

	// ── POST ──────────────────────────────────────────────────────────

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			ProduitRequest request = ProduitRequest.from(req);

			if (request.isDelete()) {
				productService.delete(request.getRef());
				resp.sendRedirect(REDIRECT_PRODUIT);
				return;
			}

			ProductDto dto = request.toDto();
			if (dto.getRef() == null || dto.getRef().isBlank()) {
				req.setAttribute(KEY_MESSAGE, "Référence obligatoire pour créer le produit");
				loadPage(req, resp);
				return;
			}
			if (request.isEdit()) {
				productService.update(dto);
			} else if (request.isCreate()) {
				productService.save(dto);
			}

			resp.sendRedirect(REDIRECT_PRODUIT);

		} catch (Exception e) {
			req.setAttribute(KEY_MESSAGE, e.getMessage());
			loadPage(req, resp);
		}
	}

	private void loadPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("productsList", productService.getAll());
		req.setAttribute("usersList",    userService.getAll());
		req.getRequestDispatcher("WEB-INF/jsp/products/list.jsp").forward(req, resp);
	}
}