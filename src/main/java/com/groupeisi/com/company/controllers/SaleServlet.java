package com.groupeisi.com.company.controllers;

import com.groupeisi.com.company.dao.product.ProductDao;
import com.groupeisi.com.company.dto.SaleDto;
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
	private ISaleService saleService;
	private final Logger logger = LoggerFactory.getLogger(SaleServlet.class);

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
        this.saleService = new SaleService(new ProductDao());
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			loadPage(req, resp);
		} catch (Exception exception) {
			logger.error("Error loading Sale list", exception);
			resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal server error");
		}
	}

	private void loadPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("salesList", saleService.getAll());
		req.getRequestDispatcher("WEB-INF/jsp/sales/list.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String dateStr = req.getParameter("dateP");
			String quantityStr = req.getParameter("quantity");
			String productRef = req.getParameter("product_ref");

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date dateP = null;

			try {
				if (dateStr != null && !dateStr.isEmpty()) {
					dateP = formatter.parse(dateStr);
				}
			} catch (ParseException e) {
				e.printStackTrace();
				req.setAttribute("errorMessage", "Date invalide.");
				loadPage(req, resp);
				return;
			}

			int quantity = 0;
			try {
				if (quantityStr != null && !quantityStr.isEmpty()) {
					quantity = Integer.parseInt(quantityStr);
				} else {
					req.setAttribute("errorMessage", "Quantité obligatoire.");
					loadPage(req, resp);
					return;
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
				req.setAttribute("errorMessage", "Quantité invalide.");
				loadPage(req, resp);
				return;
			}

			if (productRef == null || productRef.isEmpty()) {
				req.setAttribute("errorMessage", "Référence produit obligatoire.");
				loadPage(req, resp);
				return;
			}

			SaleDto saleDto = SaleDto.builder()
					.dateP(dateP)
					.quantity(quantity)
					.product_ref(productRef)
					.build();

			saleService.save(saleDto);

			loadPage(req, resp);
		} catch (Exception exception) {
			logger.error("Error saving Sale", exception);
			resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal server error");
		}
	}

}
