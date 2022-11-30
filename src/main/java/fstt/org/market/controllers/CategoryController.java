package fstt.org.market.controllers;


import fstt.org.market.entities.persistence.Category;
import fstt.org.market.entities.persistence.Product;
import fstt.org.market.service.CategoryRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet
public class CategoryController  extends HttpServlet {
	
	CategoryRepository categoryRepository = new CategoryRepository();

	String action = "";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		action = request.getParameter("action");

		if (action.equals("save")) {
			
			request.getRequestDispatcher("addCategory.jsp").forward(request, response);

		} else if (action.equals("update")) {

			Long id = Long.parseLong(request.getParameter("id"));

			Category categoryExists = getOne(id);

			request.setAttribute("oldName", categoryExists.getCategoryName());

			request.setAttribute("oldDescription", categoryExists.getCategoryDescription());

			//request.setAttribute("oldProducts", categoryExists.getProducts());
			
			request.setAttribute("id", categoryExists.getCategoryId());

			request.getRequestDispatcher("updateCategory.jsp").forward(request, response);

		}else if(action.equals("delete")) {
			
			Long id = Long.parseLong(request.getParameter("id"));

			delete(id);

			request.getRequestDispatcher("/category?action=list").forward(request, response);

		}else if(action.equals("list")) {
			
			List<Category> list = new ArrayList<Category>();
			list = getAll();
			
			request.setAttribute("list", list);
		
			request.getRequestDispatcher("categories.jsp").forward(request, response);

		}else if(action.equals("get")) {
			
			Long id = Long.parseLong(request.getParameter("id"));
			
			Category category = getOne(id);
			
			request.setAttribute("category", category);
		
			request.getRequestDispatcher("getCategory.jsp").forward(request, response);

		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		action = request.getParameter("action");

		if (action.equals("saved")) {
			Category category;

			String name = request.getParameter("name");

			String description = request.getParameter("description");

			category = new Category(name, description);

			create(category);
			
			request.getRequestDispatcher("category?action=list").forward(request, response);

		} else if(action.equals("updated")) {
			
			String name = request.getParameter("name");

			String description = request.getParameter("description");
			
			Long id = Long.parseLong(request.getParameter("id"));
			
			List<Product> oldProducts = getOne(id).getProducts();

			Category category = new Category(id, name, description, oldProducts);
			
			update(id, category);
			
			request.getRequestDispatcher("/category?action=list").forward(request, response);
			
		}
		
	}

	public Category getOne( Long id) throws IOException{
		return categoryRepository.findCategoryById(id);
	}
	
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }
    
    
    public void create(Category category) {
    	categoryRepository.createCategory(category);
    }
    
    public void update( Long id, Category category) throws IOException {
    	categoryRepository.updateCategory(id, category);
    }
    
    public void delete( Long categoryId) throws IOException {
    	categoryRepository.deleteCategory(categoryId);
    }
}
