package fstt.org.market.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import fstt.org.market.entities.persistence.Client;
import fstt.org.market.entities.persistence.OrderC;
import fstt.org.market.entities.persistence.Product;


public class OrderRepository {
	
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("unit");
	private static EntityManager entityManager = emf.createEntityManager();
	private static EntityTransaction transactionObj = entityManager.getTransaction();
	
    public List<OrderC> findAll() {
    	List<OrderC> list = new ArrayList<OrderC>();
		list = entityManager.createQuery("select i from OrderC i", OrderC.class).getResultList();
		return list;
    }
    public OrderC findOrderById(Long id) throws IOException {
    	OrderC order = entityManager.find(OrderC.class, id);
        if (order == null) {
            throw new IOException();
        }
        return order;
    }
    
    public List<OrderC> getOrdersByClient(Long clientId) {
    	List<OrderC> list = new ArrayList<OrderC>();
    	list = entityManager
                .createNativeQuery("select * from orderc where client_id = ?", OrderC.class)
                .setParameter(1, clientId)
                .getResultList();
		return list;
    }
    
    public void updateOrder(Long id, OrderC order) throws IOException {
    	OrderC orderToUpdate = findOrderById(id);
    	Client oldClient = orderToUpdate.getClient();
    	List<OrderC> oldClientOrders = oldClient.getOrders();
    	oldClientOrders.remove(order);
    	oldClient.setOrders(oldClientOrders);
    	
    	orderToUpdate.setOrderDate(orderToUpdate.getOrderDate());
    	orderToUpdate.setClient(order.getClient());
    	orderToUpdate.setOrderlines(orderToUpdate.getOrderlines());
    	Client client = entityManager.find(Client.class, order.getClient().getClientId());
    	List<OrderC> orders = client.getOrders();
    	orders.add(order);
    	client.setOrders(orders);
    }
    

    public void createOrder(OrderC order) {
    	if(!transactionObj.isActive()) {
			transactionObj.begin();
		}
        entityManager.persist(order);
        transactionObj.commit();
    }

    public void deleteOrder(Long orderId) throws IOException {
    	if(!transactionObj.isActive()) {
			transactionObj.begin();
		}
    	entityManager.createNativeQuery("delete from OrderC where orderId = ?")
        .setParameter(1, orderId)
        .executeUpdate();
    }
}
