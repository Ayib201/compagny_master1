package com.groupeisi.com.company.controllers;

import com.groupeisi.com.company.dto.PurchaseDto;
import com.groupeisi.com.company.requests.PurchaseRequest;
import com.groupeisi.com.company.services.produit.IProductService;
import com.groupeisi.com.company.services.produit.ProductService;
import com.groupeisi.com.company.services.purchase.IPurchaseService;
import com.groupeisi.com.company.services.purchase.PurchaseService;
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

@WebServlet(name = "purchase", value = "/purchase")
public class PurchaseServlet extends HttpServlet {

	private static final String REDIRECT_PURCHASE = "purchase";
	private transient IPurchaseService purchaseService;
	private transient IProductService  productService;
	private transient IUserService     userService;

	private static final Logger logger = LoggerFactory.getLogger(PurchaseServlet.class);

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.productService  = new ProductService();
		this.purchaseService = new PurchaseService();
		this.userService     = new UserService();
	}

	// ── GET ───────────────────────────────────────────────────────────

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		try {
			PurchaseRequest request = PurchaseRequest.from(req);

			if (request.isDelete()) {
				purchaseService.delete(request.validateId());
				resp.sendRedirect(REDIRECT_PURCHASE);
				return;
			}

			if (request.isEdit()) {
				purchaseService.get(request.validateId())
						.ifPresent(p -> req.setAttribute("editPurchase", p));
			}

			loadPage(req, resp);

		} catch (Exception e) {
			logger.error("Erreur chargement liste achats", e);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		try {
			PurchaseRequest request = PurchaseRequest.from(req);

			if (request.isDelete()) {
				purchaseService.delete(request.validateId());
				resp.sendRedirect(REDIRECT_PURCHASE);
				return;
			}

			PurchaseDto dto = request.toDto();

			if (request.isEdit()) {
				purchaseService.update(dto);
			} else if (request.isCreate()) {
				purchaseService.save(dto);
			}

			resp.sendRedirect(REDIRECT_PURCHASE);

		} catch (Exception e) {
			logger.error("Erreur inattendue dans doGet", e);
		}
	}

	private void loadPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("purchasesList", purchaseService.getAll());
		req.setAttribute("productsList",  productService.getAll());
		req.setAttribute("usersList",     userService.getAll());
		req.getRequestDispatcher("WEB-INF/jsp/purchases/list.jsp").forward(req, resp);
	}
}