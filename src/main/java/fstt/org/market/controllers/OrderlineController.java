package fstt.org.market.controllers;

/*import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import fstt.org.market.entities.persistence.Orderline;
import fstt.org.market.service.OrderlineRepository;


@Path("/orderline")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class OrderlineController {
	
	@Inject OrderlineRepository orderlineRepository;
    @GET
    public List<Orderline> getAll() {
        return orderlineRepository.findAll();
    }
    @POST
    public Response create(Orderline orderline) {
    	orderlineRepository.createOrderline(orderline);
        return Response.status(201).build();
    }
    
    @PUT
    public Response update(@QueryParam("id") Integer id, Orderline orderline) {
    	orderlineRepository.updateOrderline(id, orderline);
        return Response.status(204).build();
    }
    @DELETE
    public Response delete(@QueryParam("id") Integer orderlineId) {
    	orderlineRepository.deleteOrderline(orderlineId);
        return Response.status(204).build();
    }
}*/