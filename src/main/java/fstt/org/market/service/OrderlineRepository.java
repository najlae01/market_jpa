package fstt.org.market.service;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import fstt.org.market.entities.persistence.Orderline;


public class OrderlineRepository {
	
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("unit");
	private static EntityManager entityManager = emf.createEntityManager();
	private static EntityTransaction transactionObj = entityManager.getTransaction();
	
    public List<Orderline> findAll() {
    	List<Orderline> list = new ArrayList<Orderline>();
		list = entityManager.createQuery("select i from Orderline i", Orderline.class).getResultList();
		return list;
    }
    public Orderline findOrderlineById(Long id) throws IOException {
    	Orderline orderline = entityManager.find(Orderline.class, id);
        if (orderline == null) {
            throw new IOException();
        }
        return orderline;
    }
    

    public void updateOrderline(Long id, Orderline orderline) throws IOException {
    	Orderline orderlineToUpdate = findOrderlineById(id);
    	
    	orderlineToUpdate.setOrderlineQuantity(orderline.getOrderlineQuantity());
    	orderlineToUpdate.setOrderlineProduct(orderline.getOrderlineProduct());
    	orderlineToUpdate.setOrderlineOrder(orderline.getOrderlineOrder());
    }
    
    public List<Orderline> getOrderlinesByOrder(Long orderId) {
    	List<Orderline> list = new ArrayList<Orderline>();
    	list = entityManager
                .createNativeQuery("select * from orderline where order_id = ?", Orderline.class)
                .setParameter(1, orderId)
                .getResultList();
		return list;
    }
    

    public void createOrderline(Orderline orderline) {
    	if(!transactionObj.isActive()) {
			transactionObj.begin();
		}
        entityManager.persist(orderline);
        transactionObj.commit();
    }


    public void deleteOrderline(Long orderlineId) throws IOException {
    	if(!transactionObj.isActive()) {
			transactionObj.begin();
		}
    	entityManager.createNativeQuery("delete from Orderline where orderlineId = ?")
        .setParameter(1, orderlineId)
        .executeUpdate();
    }
}
