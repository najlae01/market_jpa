package fstt.org.market.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import fstt.org.market.entities.persistence.Product;


public class ProductRepository {
	
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("unit");
	private static EntityManager entityManager = emf.createEntityManager();
	private static EntityTransaction transactionObj = entityManager.getTransaction();
	
    public List<Product> findAll() {
    	List<Product> list = new ArrayList<Product>();
		list = entityManager.createQuery("select i from Product i", Product.class).getResultList();
		return list;
    }
    
    public List<Product> getProductsByCategory(Long categoryId) {
    	List<Product> list = new ArrayList<Product>();
    	list = entityManager
                .createNativeQuery("select * from product where category_id = ?", Product.class)
                .setParameter(1, categoryId)
                .getResultList();
		return list;
    }
    
    public Product findProductById(Long id) throws IOException {
    	Product product = entityManager.find(Product.class, id);
        if (product == null) {
        	throw new IOException();
        }
        return product;
    }
    
    public void updateProduct(Long id, Product product) throws IOException {
    	Product productToUpdate = findProductById(id);
    	
    	productToUpdate.setProductName(product.getProductName());
    	productToUpdate.setProductDescription(product.getProductDescription());
    	productToUpdate.setProductPrice(product.getProductPrice());
    	productToUpdate.setProductStockQuantity(product.getProductStockQuantity());
    	productToUpdate.setProductCategory(product.getProductCategory());
    }
    
    public void createProduct(Product product) {
    	if(!transactionObj.isActive()) {
			transactionObj.begin();
		}
        entityManager.persist(product);
        transactionObj.commit();
    }

    public void deleteProduct(Long productId) throws IOException {
    	if(!transactionObj.isActive()) {
			transactionObj.begin();
		}
    	entityManager.createNativeQuery("delete from Product where productId = ?")
        .setParameter(1, productId)
        .executeUpdate();
    }
}