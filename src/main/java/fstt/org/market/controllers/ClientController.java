package fstt.org.market.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fstt.org.market.entities.persistence.Client;
import fstt.org.market.service.ClientRepository;

@WebServlet
public class ClientController extends HttpServlet {

	ClientRepository clientRepository = new ClientRepository();
	
	String action = "";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		action = request.getParameter("action");

		if (action.equals("save")) {
			
			request.getRequestDispatcher("addClient.jsp").forward(request, response);

		} else if (action.equals("update")) {

			Long id = Long.parseLong(request.getParameter("id"));

			Client clientExists = getOne(id);

			request.setAttribute("oldName", clientExists.getClientName());

			request.setAttribute("oldAddress", clientExists.getClientAddress());

			request.setAttribute("oldPhone", clientExists.getClientPhone());

			request.setAttribute("oldCity", clientExists.getClientCity());

			request.setAttribute("id", clientExists.getClientId());

			request.getRequestDispatcher("updateClient.jsp").forward(request, response);

		}else if(action.equals("delete")) {
			
			Long id = Long.parseLong(request.getParameter("id"));

			delete(id);

			request.getRequestDispatcher("/client?action=list").forward(request, response);

		}else if(action.equals("list")) {
			
			List<Client> list = new ArrayList<Client>();
			list = getAll();
			
			request.setAttribute("list", list);
		
			request.getRequestDispatcher("clients.jsp").forward(request, response);

		}else if(action.equals("get")) {
			
			Long id = Long.parseLong(request.getParameter("id"));
			
			Client client = getOne(id);
			
			request.setAttribute("client", client);
		
			request.getRequestDispatcher("getClient.jsp").forward(request, response);

		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		action = request.getParameter("action");

		if (action.equals("saved")) {
			Client client;

			String name = request.getParameter("name");

			String address = request.getParameter("address");

			String phone = request.getParameter("phone");

			String city = request.getParameter("city");

			client = new Client(name, address, phone, city);

			create(client);
			
			request.getRequestDispatcher("client?action=list").forward(request, response);

		} else if(action.equals("updated")) {
			
			String name = request.getParameter("name");

			String address = request.getParameter("address");

			String phone = request.getParameter("phone");

			String city = request.getParameter("city");
			
			Long id = Long.parseLong(request.getParameter("id"));

			Client client = new Client(id, name, address, phone, city);
			
			update(id, client);
			
			request.getRequestDispatcher("/client?action=list").forward(request, response);
			
		}
		
	}

	public Client getOne( Long id) throws IOException{
		return clientRepository.findClientById(id);
	}
	
	public List<Client> getAll() {
		return clientRepository.findAll();
	}

	public void create(Client client) {
		clientRepository.createClient(client);
		//return Response.status(201).build();
	}

	public void update(Long id, Client client) throws IOException {
		clientRepository.updateClient(id, client);
		//return Response.status(204).build();
	}

	public void delete(Long clientId) throws IOException {
		clientRepository.deleteClient(clientId);
		//return Response.status(204).build();
	}
}