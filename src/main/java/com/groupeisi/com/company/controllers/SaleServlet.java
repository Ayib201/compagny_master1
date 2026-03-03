package com.groupeisi.com.company.controllers;

import com.groupeisi.com.company.dao.product.ProductDao;
import com.groupeisi.com.company.dto.SaleDto;
import com.groupeisi.com.company.services.produit.ProductService;
import com.groupeisi.com.company.services.sale.ISaleService;
import com.groupeisi.com.company.services.sale.SaleService;
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

@WebServlet(name = "sale", value = "/sale")
public class SaleServlet extends HttpServlet {

	private transient ISaleService saleService;
	private transient ProductService productService;
	private static final Logger logger = LoggerFactory.getLogger(SaleServlet.class);
	private static final String KEY_MESSAGE  = "errorMessage";

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.productService = new ProductService();
		this.saleService    = new SaleService(new ProductDao());
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String action  = req.getParameter("action");
			String idParam = req.getParameter("id");

			if ("delete".equals(action) && idParam != null) {
				saleService.delete(Long.parseLong(idParam));
				resp.sendRedirect("sale");
				return;
			}

			if ("edit".equals(action) && idParam != null) {
				saleService.get(Long.parseLong(idParam)).ifPresent(s ->
						req.setAttribute("editSale", s)
				);
			}

			loadPage(req, resp);
		} catch (Exception e) {
			logger.error("Error loading Sale list", e);
			try {
				resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			} catch (IOException ioException) {
				logger.error("Failed to send error response", ioException);
			}
		}
	}

	private void loadPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("salesList",    saleService.getAll());
		req.setAttribute("productsList", productService.getAll());
		req.getRequestDispatcher("WEB-INF/jsp/sales/list.jsp").forward(req, resp);
	}

	private Date parseDate(String dateStr) throws ParseException {
		if (dateStr == null || dateStr.isEmpty()) return null;
		return new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String action      = req.getParameter("action");
			String idParam     = req.getParameter("id");
			String dateStr     = req.getParameter("dateP");
			String quantityStr = req.getParameter("quantity");
			String productRef  = req.getParameter("product_ref");

			// Delete via POST
			if ("delete".equals(action) && idParam != null) {
				saleService.delete(Long.parseLong(idParam));
				resp.sendRedirect("sale");
				return;
			}

			Date dateP = parseDateOrShowError(req, resp, dateStr);
			if (dateP == null) {
				return; // l'erreur a déjà été affichée
			}

			if (productRef == null || productRef.isEmpty()) {
				req.setAttribute(KEY_MESSAGE, "Référence produit obligatoire.");
				loadPage(req, resp);
				return;
			}

			SaleDto saleDto = SaleDto.builder()
					.dateP(dateP)
					.quantity(Double.parseDouble(quantityStr))
					.productRef(productRef)
					.build();

			if ("update".equals(action) && idParam != null && !idParam.isEmpty()) {
				saleDto.setId(Long.parseLong(idParam));
				saleService.update(saleDto);
			} else {
				saleService.save(saleDto);
			}

			resp.sendRedirect("sale");
		} catch (Exception e) {
			logger.error("Error saving Sale", e);
			try {
				resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			} catch (IOException ioException) {
				logger.error("Failed to send error response", ioException);
			}
		}
	}
}