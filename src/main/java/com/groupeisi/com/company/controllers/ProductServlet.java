package com.groupeisi.com.company.controllers;

import com.groupeisi.com.company.dto.ProductDto;
import com.groupeisi.com.company.services.produit.IProductService;
import com.groupeisi.com.company.services.produit.ProductService;
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

	private IProductService productService;
	private final Logger logger = LoggerFactory.getLogger(ProductServlet.class);

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		productService = new ProductService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String action = req.getParameter("action");
			String ref    = req.getParameter("id");

			if ("delete".equals(action) && ref != null) {
				productService.delete(ref);
				resp.sendRedirect("produit");
				return;
			}

			if ("edit".equals(action) && ref != null) {
				productService.get(ref).ifPresent(p ->
						req.setAttribute("editProduct", p)
				);
			}

			loadPage(req, resp);
		} catch (Exception e) {
			logger.error("Error loading product list", e);
			resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal server error");
		}
	}

	private void loadPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("productsList", productService.getAll());
		req.getRequestDispatcher("WEB-INF/jsp/products/list.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String action  = req.getParameter("action");
			String id      = req.getParameter("id");
			String name    = req.getParameter("name");
			String ref     = req.getParameter("ref");
			String stockStr = req.getParameter("stock");

			double stock = 0;
			try {
				stock = Double.parseDouble(stockStr);
			} catch (NumberFormatException e) {
				logger.warn("Invalid stock value: {}", stockStr);
				req.setAttribute("errorMessage", "Stock must be a number.");
				loadPage(req, resp);
				return;
			}

			if ("delete".equals(action) && id != null) {
				productService.delete(id);
				resp.sendRedirect("produit");
				return;
			}

			ProductDto productDto = ProductDto.builder()
					.name(name)
					.ref(ref)
					.stock(stock)
					.build();

			if ("update".equals(action) && id != null && !id.isEmpty()) {
				productService.update(productDto);
			} else {
				productService.save(productDto);
			}

			resp.sendRedirect("produit");
		} catch (Exception e) {
			logger.error("Error saving product", e);
			resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal server error");
		}
	}
}