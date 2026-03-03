package com.groupeisi.com.company.controllers;

import com.groupeisi.com.company.dao.product.ProductDao;
import com.groupeisi.com.company.dto.PurchaseDto;
import com.groupeisi.com.company.services.produit.IProductService;
import com.groupeisi.com.company.services.produit.ProductService;
import com.groupeisi.com.company.services.purchase.IPurchaseService;
import com.groupeisi.com.company.services.purchase.PurchaseService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@WebServlet(name = "purchase", value = "/purchase")
public class PurchaseServlet extends HttpServlet {

	private static final String REDIRECT_PURCHASE = "purchase";
	private static final String ACTION_DELETE = "delete";
	private static final String ACTION_EDIT   = "edit";
	private static final String ACTION_UPDATE = "update";
	private static final String DATE_PATTERN  = "yyyy-MM-dd";
	private static final String KEY_MESSAGE  = "errorMessage";

	private transient IPurchaseService purchaseService;
	private transient IProductService productService;

	private static final Logger logger = LoggerFactory.getLogger(PurchaseServlet.class);

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.productService  = new ProductService();
		this.purchaseService = new PurchaseService(new ProductDao());
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		try {
			String action  = req.getParameter("action");
			String idParam = req.getParameter("id");

			if (ACTION_DELETE.equals(action) && isValidLong(idParam)) {
				purchaseService.delete(Long.parseLong(idParam));
				resp.sendRedirect(REDIRECT_PURCHASE);
				return;
			}

			if (ACTION_EDIT.equals(action) && isValidLong(idParam)) {
				Optional<PurchaseDto> purchase =
						purchaseService.get(Long.parseLong(idParam));

				purchase.ifPresent(p ->
						req.setAttribute("editPurchase", p));
			}

			loadPage(req, resp);

		} catch (IOException | ServletException e) {
			logger.error("Error loading Purchase list", e);
			try {
				resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			} catch (IOException ioException) {
				logger.error("Failed to send error response", ioException);
			}
		}
	}

	private void loadPage(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		req.setAttribute("purchasesList", purchaseService.getAll());
		req.setAttribute("productsList", productService.getAll());
		req.getRequestDispatcher("WEB-INF/jsp/purchases/list.jsp")
				.forward(req, resp);
	}

	private Date parseDate(String dateStr) throws ParseException {
		if (dateStr == null || dateStr.isBlank()) return null;
		return new SimpleDateFormat(DATE_PATTERN).parse(dateStr);
	}

	private boolean isValidLong(String value) {
		if (value == null || value.isBlank()) return false;
		try {
			Long.parseLong(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	private Date parseDateOrShowError(HttpServletRequest req, HttpServletResponse resp, String dateStr) throws ServletException, IOException {
		try {
			return parseDate(dateStr);
		} catch (ParseException e) {
			req.setAttribute(KEY_MESSAGE, "Date invalide.");
			loadPage(req, resp);
			return null;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		try {
			String action      = req.getParameter("action");
			String idParam     = req.getParameter("id");
			String dateStr     = req.getParameter("dateP");
			String quantityStr = req.getParameter("quantity");
			String productRef  = req.getParameter("product_ref");

			if (ACTION_DELETE.equals(action) && isValidLong(idParam)) {
				purchaseService.delete(Long.parseLong(idParam));
				resp.sendRedirect(REDIRECT_PURCHASE);
				return;
			}

			Date dateP = parseDateOrShowError(req, resp, dateStr);
			if (dateP == null) {
				return; // l'erreur a déjà été affichée
			}

			if (productRef == null || productRef.isBlank()) {
				req.setAttribute(KEY_MESSAGE, "Référence produit obligatoire.");
				loadPage(req, resp);
				return;
			}

			PurchaseDto purchaseDto = PurchaseDto.builder()
					.dateP(dateP)
					.quantity(Double.parseDouble(quantityStr))
					.productRef(productRef)
					.build();

			if (ACTION_UPDATE.equals(action) && isValidLong(idParam)) {
				purchaseDto.setId(Long.parseLong(idParam));
				purchaseService.update(purchaseDto);
			} else {
				purchaseService.save(purchaseDto);
			}

			resp.sendRedirect(REDIRECT_PURCHASE);

		} catch (IOException | ServletException e) {
			logger.error("Error saving Purchase", e);
			try {
				resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			} catch (IOException ioException) {
				logger.error("Failed to send error response", ioException);
			}
		}
	}
}