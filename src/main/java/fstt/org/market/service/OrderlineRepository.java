package fstt.org.market.service;



import java.io.IOException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import fstt.org.market.entities.persistence.Orderline;


public class OrderlineRepository {
	
	@PersistenceContext
	private EntityManager entityManager = Persistence.createEntityManagerFactory("unit").createEntityManager();
	
    public List<Orderline> findAll() {
        return entityManager.createNamedQuery("Orderline.findAll", Orderline.class).getResultList();
    }
    public Orderline findOrderlineById(Integer id) throws IOException {
    	Orderline orderline = entityManager.find(Orderline.class, id);
        if (orderline == null) {
            throw new IOException();
        }
        return orderline;
    }
    

    public void updateOrderline(Integer id, Orderline orderline) throws IOException {
    	Orderline orderlineToUpdate = findOrderlineById(id);
    	
    	orderlineToUpdate.setOrderlineQuantity(orderline.getOrderlineQuantity());
    	orderlineToUpdate.setOrderlineProduct(orderline.getOrderlineProduct());
    	orderlineToUpdate.setOrderlineOrder(orderline.getOrderlineOrder());
    }
    

    public void createOrderline(Orderline orderline) {
        entityManager.persist(orderline);
    }


    public void deleteOrderline(Integer orderlineId) throws IOException {
    	Orderline orderline = findOrderlineById(orderlineId);
        entityManager.remove(orderline);
    }
}
