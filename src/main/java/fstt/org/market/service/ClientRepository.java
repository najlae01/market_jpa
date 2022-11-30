package fstt.org.market.service;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import fstt.org.market.entities.persistence.Client;

public class ClientRepository {
	
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("unit");
	private static EntityManager entityManager = emf.createEntityManager();
	private static EntityTransaction transactionObj = entityManager.getTransaction();
	
	
    public ClientRepository() {
		super();
		// TODO Auto-generated constructor stub
	}
	public List<Client> findAll() {
		List<Client> list = new ArrayList<Client>();
		list = entityManager.createQuery("select i from Client i", Client.class).getResultList();
		return list;
    }
    public Client findClientById(Long id) throws IOException {
    	Client client = entityManager.find(Client.class, id);
        if (client == null) {
            throw new IOException();
        }
        return client;
    }
    

    public void updateClient(Long id, Client client) throws IOException {
    	Client clientToUpdate = findClientById(id);
    	
    	clientToUpdate.setClientName(client.getClientName());
    	clientToUpdate.setClientAddress(client.getClientAddress());
    	clientToUpdate.setClientPhone(client.getClientPhone());
    	clientToUpdate.setClientCity(client.getClientCity());
    	//clientToUpdate.setClientId(client.getClientId());
    }
    
    public void createClient(Client client) {
    	if(!transactionObj.isActive()) {
			transactionObj.begin();
		}
        entityManager.persist(client);
        transactionObj.commit();
    } 
    
    public void deleteClient(Long clientId) throws IOException {
    	if(!transactionObj.isActive()) {
			transactionObj.begin();
		}
        entityManager.createNativeQuery("delete from Client where clientId = ?")
        .setParameter(1, clientId)
        .executeUpdate();
    }
}
