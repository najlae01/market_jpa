package fstt.org.market.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fstt.org.market.entities.persistence.OrderC;
import fstt.org.market.entities.persistence.Orderline;
import fstt.org.market.entities.persistence.Product;
import fstt.org.market.service.OrderRepository;
import fstt.org.market.service.OrderlineRepository;
import fstt.org.market.service.ProductRepository;

@WebServlet
public class OrderlineController extends HttpServlet{

	OrderlineRepository orderlineRepository = new OrderlineRepository();
	OrderRepository orderRepository = new OrderRepository();

	ProductRepository productRepository = new ProductRepository();

	String action = "";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		action = request.getParameter("action");

		if (action.equals("save")) {

			List<OrderC> orders = getOrders();

			request.setAttribute("orders", orders);
			
			List<Product> products = getProducts();
			
			request.setAttribute("products", products);

			request.getRequestDispatcher("addOrderline.jsp").forward(request, response);

		} else if (action.equals("update")) {

			Long id = Long.parseLong(request.getParameter("id"));
			
			List<OrderC> orders = getOrders();

			request.setAttribute("orders", orders);
			
			List<Product> products = getProducts();
			
			request.setAttribute("products", products);

			Orderline orderlineExists = getOne(id);

			request.setAttribute("oldQuantity", orderlineExists.getOrderlineQuantity());

			request.setAttribute("oldProductId", orderlineExists.getOrderlineProduct().getProductId());

			request.setAttribute("oldProductName", orderlineExists.getOrderlineProduct().getProductName());
			
			request.setAttribute("oldOrderId", orderlineExists.getOrderlineOrder().getOrderId());
			
			request.setAttribute("id", orderlineExists.getOrderlineId());

			request.getRequestDispatcher("updateOrderline.jsp").forward(request, response);

		} else if (action.equals("delete")) {

			Long id = Long.parseLong(request.getParameter("id"));

			delete(id);

			request.getRequestDispatcher("index.jsp").forward(request, response);

		} else if (action.equals("list")) {

			Long id = Long.parseLong(request.getParameter("id"));

			List<Orderline> list = new ArrayList<Orderline>();
			list = getOrderlinesByOrder(id);

			request.setAttribute("list", list);

			request.getRequestDispatcher("orderlinesByOrder.jsp").forward(request, response);

		} else if (action.equals("get")) {

			Long id = Long.parseLong(request.getParameter("id"));

			Orderline orderline = getOne(id);

			request.setAttribute("orderline", orderline);

			request.getRequestDispatcher("getOrderline.jsp").forward(request, response);

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		action = request.getParameter("action");

		if (action.equals("saved")) {
			Orderline orderline;

			int quantity = Integer.parseInt(request.getParameter("quantity"));

			Long productId = Long.parseLong(request.getParameter("product"));

			Long orderId = Long.parseLong(request.getParameter("order"));

			OrderC order = getOrder(orderId);

			Product product = getProduct(productId);

			orderline = new Orderline(quantity, order, product);

			create(orderline);

			// request.getRequestDispatcher("index.jsp").forward(request, response);

		} else if (action.equals("updated")) {

			int quantity = Integer.parseInt(request.getParameter("quantity"));

			Long productId = Long.parseLong(request.getParameter("product"));

			Long orderId = Long.parseLong(request.getParameter("order"));

			Long id = Long.parseLong(request.getParameter("id"));

			OrderC order = getOrder(orderId);

			Product product = getProduct(productId);

			Orderline orderline = new Orderline(id, quantity, order, product);

			update(id, orderline);

			// request.getRequestDispatcher("index.jsp").forward(request, response);

		}

	}

	public OrderC getOrder(Long id) throws IOException {
		return orderRepository.findOrderById(id);
	}

	public Product getProduct(Long id) throws IOException {
		return productRepository.findProductById(id);
	}

	public List<Orderline> getOrderlinesByOrder(Long orderId) {
		return orderlineRepository.getOrderlinesByOrder(orderId);
	}

	public List<OrderC> getOrders() {
		return orderRepository.findAll();
	}
	
	public List<Product> getProducts() {
		return productRepository.findAll();
	}

	public Orderline getOne(Long id) throws IOException {
		return orderlineRepository.findOrderlineById(id);
	}

	public List<Orderline> getAll() {
		return orderlineRepository.findAll();
	}

	public void create(Orderline orderline) {
		orderlineRepository.createOrderline(orderline);
	}

	public void update(Long id, Orderline orderline) throws IOException {
		orderlineRepository.updateOrderline(id, orderline);
	}

	public void delete(Long orderlineId) throws IOException {
		orderlineRepository.deleteOrderline(orderlineId);
	}
}