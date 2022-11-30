package fstt.org.market.controllers;

import fstt.org.market.entities.persistence.Category;
import fstt.org.market.entities.persistence.Client;
import fstt.org.market.entities.persistence.Product;
import fstt.org.market.service.CategoryRepository;
import fstt.org.market.service.ProductRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet
public class ProductController extends HttpServlet {

	ProductRepository productRepository = new ProductRepository();

	CategoryRepository categoryRepository = new CategoryRepository();

	String action = "";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		action = request.getParameter("action");

		if (action.equals("save")) {

			List<Category> list = getCategories();

			request.setAttribute("list", list);

			request.getRequestDispatcher("addProduct.jsp").forward(request, response);

		} else if (action.equals("update")) {

			Long id = Long.parseLong(request.getParameter("id"));

			Product productExists = getOne(id);

			List<Category> list = getCategories();

			request.setAttribute("list", list);

			request.setAttribute("oldName", productExists.getProductName());

			request.setAttribute("oldDescription", productExists.getProductDescription());

			request.setAttribute("oldPrice", productExists.getProductPrice());

			request.setAttribute("oldQuantity", productExists.getProductStockQuantity());

			request.setAttribute("oldCategoryId", productExists.getProductCategory().getCategoryId());

			request.setAttribute("oldCategoryName", productExists.getProductCategory().getCategoryName());

			request.setAttribute("id", productExists.getProductId());

			request.getRequestDispatcher("updateProduct.jsp").forward(request, response);

		} else if (action.equals("delete")) {

			Long id = Long.parseLong(request.getParameter("id"));

			delete(id);

			request.getRequestDispatcher("/product?action=list").forward(request, response);

		} else if (action.equals("list")) {

			Long id = Long.parseLong(request.getParameter("id"));

			List<Product> list = new ArrayList<Product>();
			list = getProductsByCategory(id);

			request.setAttribute("list", list);

			request.getRequestDispatcher("productsByCategory.jsp").forward(request, response);

		} else if (action.equals("get")) {

			Long id = Long.parseLong(request.getParameter("id"));

			Product product = getOne(id);

			request.setAttribute("product", product);

			request.getRequestDispatcher("getProduct.jsp").forward(request, response);

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		action = request.getParameter("action");

		if (action.equals("saved")) {
			Product product;

			String name = request.getParameter("name");

			String description = request.getParameter("description");

			double price = Double.parseDouble(request.getParameter("price"));

			int quantity = Integer.parseInt(request.getParameter("quantity"));

			Long categoryId = Long.parseLong(request.getParameter("category"));

			Category category = getCategory(categoryId);

			product = new Product(name, description, price, quantity, category);

			create(product);

			request.getRequestDispatcher("product?action=list").forward(request, response);

		} else if (action.equals("updated")) {

			String name = request.getParameter("name");

			String description = request.getParameter("description");

			double price = Double.parseDouble(request.getParameter("price"));

			int quantity = Integer.parseInt(request.getParameter("quantity"));

			Long categoryId = Long.parseLong(request.getParameter("category"));

			Long id = Long.parseLong(request.getParameter("id"));

			Category category = getCategory(categoryId);

			Product product = new Product(name, description, price, quantity, category);

			update(id, product);

			request.getRequestDispatcher("/product?action=list").forward(request, response);

		}

	}

	public Category getCategory(Long id) throws IOException {
		return categoryRepository.findCategoryById(id);
	}

	public List<Category> getCategories() {
		return categoryRepository.findAll();
	}

	public List<Product> getProductsByCategory(Long categoryId) {
		return productRepository.getProductsByCategory(categoryId);
	}

	public Product getOne(Long id) throws IOException {
		return productRepository.findProductById(id);
	}

	public List<Product> getAll() {
		return productRepository.findAll();
	}

	public void create(Product product) {
		productRepository.createProduct(product);
	}

	public void update(Long id, Product product) throws IOException {
		productRepository.updateProduct(id, product);
	}

	public void delete(Long productId) throws IOException {
		productRepository.deleteProduct(productId);
	}
}