package fstt.org.market.service;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import fstt.org.market.entities.persistence.Category;


public class CategoryRepository {
	
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("unit");
	private static EntityManager entityManager = emf.createEntityManager();
	private static EntityTransaction transactionObj = entityManager.getTransaction();
	
    public List<Category> findAll() {
    	List<Category> list = new ArrayList<Category>();
		list = entityManager.createQuery("select i from Category i", Category.class).getResultList();
		return list;
    }
    public Category findCategoryById(Long id) throws IOException {
    	Category category = entityManager.find(Category.class, id);
        if (category == null) {
            throw new IOException();
        }
        return category;
    }
    
    public void updateCategory(Long id, Category category) throws IOException {
    	Category categoryToUpdate = findCategoryById(id);
    	
    	categoryToUpdate.setCategoryName(category.getCategoryName());
    	categoryToUpdate.setCategoryDescription(category.getCategoryDescription());
    	categoryToUpdate.setProducts(category.getProducts());
    }
    
    public void createCategory(Category category) {
    	if(!transactionObj.isActive()) {
			transactionObj.begin();
		}
        entityManager.persist(category);
        transactionObj.commit();
    }
    
    public void deleteCategory(Long categoryId) throws IOException {
    	if(!transactionObj.isActive()) {
			transactionObj.begin();
		}
    	entityManager.createNativeQuery("delete from Category where categoryId = ?")
        .setParameter(1, categoryId)
        .executeUpdate();
    }
}
