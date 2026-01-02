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
public class ProduitServlet extends HttpServlet {

	private IProductService productService;
	private final Logger logger = LoggerFactory.getLogger(ProduitServlet.class);

	@Override
	public void init(ServletConfig config) throws ServletException {
		productService = new ProductService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			loadPage(req, resp);
		} catch (Exception exception) {
			logger.error("Error : ", exception);
		}
	}

	private void loadPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("productsList", productService.getAll());

		req.getRequestDispatcher("WEB-INF/jsp/products/list.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String name = req.getParameter("name");
		String ref = req.getParameter("ref");
		String stock = req.getParameter("stock");

        new ProductDto();
        ProductDto productDto = ProductDto.builder()
				.name(name)
				.ref(ref)
				.stock(Double.parseDouble(stock))
				.build();
		
		productService.save(productDto);

		try {
			loadPage(req, resp);
		} catch (Exception exception) {
			logger.error("Error : ", exception);
		}
	}
}
