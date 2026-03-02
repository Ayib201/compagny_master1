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

@WebServlet(name = "purchase", value = "/purchase")
public class PurchaseServlet extends HttpServlet {

	private IPurchaseService purchaseService;
	private IProductService productService;
	private final Logger logger = LoggerFactory.getLogger(PurchaseServlet.class);

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.productService  = new ProductService();
		this.purchaseService = new PurchaseService(new ProductDao());
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String action  = req.getParameter("action");
			String idParam = req.getParameter("id");

			if ("delete".equals(action) && idParam != null) {
				purchaseService.delete(Long.parseLong(idParam));
				resp.sendRedirect("purchase");
				return;
			}

			if ("edit".equals(action) && idParam != null) {
				purchaseService.get(Long.parseLong(idParam)).ifPresent(p ->
						req.setAttribute("editPurchase", p)
				);
			}

			loadPage(req, resp);
		} catch (Exception e) {
			logger.error("Error loading Purchase list", e);
			resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal server error");
		}
	}

	private void loadPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("purchasesList", purchaseService.getAll());
		req.setAttribute("productsList",  productService.getAll());
		req.getRequestDispatcher("WEB-INF/jsp/purchases/list.jsp").forward(req, resp);
	}

	private Date parseDate(String dateStr) throws ParseException {
		if (dateStr == null || dateStr.isEmpty()) return null;
		return new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String action      = req.getParameter("action");
			String idParam     = req.getParameter("id");
			String dateStr     = req.getParameter("dateP");
			String quantityStr = req.getParameter("quantity");
			String productRef  = req.getParameter("product_ref");

			// Delete via POST
			if ("delete".equals(action) && idParam != null) {
				purchaseService.delete(Long.parseLong(idParam));
				resp.sendRedirect("purchase");
				return;
			}

			Date dateP;
			try {
				dateP = parseDate(dateStr);
			} catch (ParseException e) {
				req.setAttribute("errorMessage", "Date invalide.");
				loadPage(req, resp);
				return;
			}

			if (productRef == null || productRef.isEmpty()) {
				req.setAttribute("errorMessage", "Référence produit obligatoire.");
				loadPage(req, resp);
				return;
			}

			PurchaseDto purchaseDto = PurchaseDto.builder()
					.dateP(dateP)
					.quantity(Double.parseDouble(quantityStr))
					.product_ref(productRef)
					.build();

			if ("update".equals(action) && idParam != null && !idParam.isEmpty()) {
				purchaseDto.setId(Long.parseLong(idParam));
				purchaseService.update(purchaseDto);
			} else {
				purchaseService.save(purchaseDto);
			}

			resp.sendRedirect("purchase");
		} catch (Exception e) {
			logger.error("Error saving Purchase", e);
			resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal server error");
		}
	}
}