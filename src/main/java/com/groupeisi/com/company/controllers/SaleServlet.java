package com.groupeisi.com.company.controllers;

import com.groupeisi.com.company.dto.SaleDto;
import com.groupeisi.com.company.requests.SaleRequest;
import com.groupeisi.com.company.services.produit.IProductService;
import com.groupeisi.com.company.services.produit.ProductService;
import com.groupeisi.com.company.services.sale.ISaleService;
import com.groupeisi.com.company.services.sale.SaleService;
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

@WebServlet(name = "sale", value = "/sale")
public class SaleServlet extends HttpServlet {

	private transient ISaleService saleService;
	private transient IProductService productService;
	private transient IUserService userService;
	private static final Logger logger = LoggerFactory.getLogger(SaleServlet.class);
	private static final String KEY_MESSAGE = "errorMessage";
	private static final String REDIRECT_SALE = "sale";

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.productService = new ProductService();
		this.saleService    = new SaleService();
		this.userService    = new UserService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			SaleRequest request = SaleRequest.from(req);

			if (request.isDelete()) {
				saleService.delete(request.validateId());
				resp.sendRedirect(REDIRECT_SALE);
				return;
			}

			if (request.isEdit()) {
				saleService.get(request.validateId())
						.ifPresent(s -> req.setAttribute("editSale", s));
			}

			loadPage(req, resp);

		} catch (Exception e) {
			logger.error("Erreur chargement liste ventes", e);
			req.setAttribute(KEY_MESSAGE, e.getMessage());
			loadPage(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			SaleRequest request = SaleRequest.from(req);

			if (request.isDelete()) {
				saleService.delete(request.validateId());
				resp.sendRedirect(REDIRECT_SALE);
				return;
			}

			SaleDto dto = request.toDto();

			if (request.isEdit()) {
				saleService.update(dto);
			} else if (request.isCreate()) {
				saleService.save(dto);
			}

			resp.sendRedirect(REDIRECT_SALE);

		} catch (Exception e) {
			req.setAttribute(KEY_MESSAGE, e.getMessage());
			loadPage(req, resp);
		}
	}

	private void loadPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("salesList",    saleService.getAll());
		req.setAttribute("productsList", productService.getAll());
		req.setAttribute("usersList",    userService.getAll());
		req.getRequestDispatcher("WEB-INF/jsp/sales/list.jsp").forward(req, resp);
	}
}