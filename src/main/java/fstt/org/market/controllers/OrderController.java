package fstt.org.market.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fstt.org.market.entities.persistence.Category;
import fstt.org.market.entities.persistence.Client;
import fstt.org.market.entities.persistence.OrderC;
import fstt.org.market.entities.persistence.Product;
import fstt.org.market.service.ClientRepository;
import fstt.org.market.service.OrderRepository;

@WebServlet
public class OrderController extends HttpServlet{
	
	OrderRepository orderRepository = new OrderRepository();
	
	ClientRepository clientRepository = new ClientRepository();
	
	String action = "";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		action = request.getParameter("action");

		if (action.equals("save")) {

			List<Client> list = getClients();

			request.setAttribute("list", list);

			request.getRequestDispatcher("addOrder.jsp").forward(request, response);

		} else if (action.equals("update")) {

			Long id = Long.parseLong(request.getParameter("id"));

			OrderC orderExists = getOne(id);

			List<Client> list = getClients();

			request.setAttribute("list", list);

			request.setAttribute("oldClientId", orderExists.getClient().getClientId());

			request.setAttribute("oldClientName", orderExists.getClient().getClientName());
			
			request.setAttribute("id", orderExists.getOrderId());

			request.getRequestDispatcher("updateOrder.jsp").forward(request, response);

		} else if (action.equals("delete")) {

			Long id = Long.parseLong(request.getParameter("id"));

			delete(id);

			request.getRequestDispatcher("index.jsp").forward(request, response);

		} else if (action.equals("list")) {

			Long id = Long.parseLong(request.getParameter("id"));

			List<OrderC> list = new ArrayList<OrderC>();
			list = getOrdersByClient(id);

			request.setAttribute("list", list);

			request.getRequestDispatcher("ordersByClient.jsp").forward(request, response);

		} else if (action.equals("get")) {

			Long id = Long.parseLong(request.getParameter("id"));

			OrderC order = getOne(id);

			request.setAttribute("order", order);

			request.getRequestDispatcher("getOrder.jsp").forward(request, response);

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		action = request.getParameter("action");

		if (action.equals("saved")) {
			OrderC order;

			Long clientId = Long.parseLong(request.getParameter("client"));

			Client client = getClient(clientId);

			order = new OrderC(client);

			create(order);

			//request.getRequestDispatcher("index.jsp").forward(request, response);

		} else if (action.equals("updated")) {

			Long clientId = Long.parseLong(request.getParameter("client"));
			
			Long id = Long.parseLong(request.getParameter("id"));

			Client client = getClient(clientId);

			OrderC order = new OrderC(client);

			update(id, order);

			//request.getRequestDispatcher("index.jsp").forward(request, response);

		}

	}
	
	public Client getClient(Long id) throws IOException {
		return clientRepository.findClientById(id);
	}
	
	public List<OrderC> getOrdersByClient(Long orderId) {
		return orderRepository.getOrdersByClient(orderId);
	}

	public List<Client> getClients() {
		return clientRepository.findAll();
	}
	
	public OrderC getOne(Long id) throws IOException {
		return orderRepository.findOrderById(id);
	}
	
    public List<OrderC> getAll() {
        return orderRepository.findAll();
    }

    public void create(OrderC order) {
    	orderRepository.createOrder(order);
    }
    

    public void update(Long id, OrderC order) throws IOException {
    	orderRepository.updateOrder(id, order);
    }

    public void delete(Long orderId) throws IOException {
    	orderRepository.deleteOrder(orderId);
    }
}