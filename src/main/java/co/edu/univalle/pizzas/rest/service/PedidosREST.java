/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.univalle.pizzas.rest.service;




import co.edu.univalle.jpa.pizzas.entities.Pedido;
import co.edu.univalle.jpa.pizzas.sessions.PedidoFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Cesar Verdugo
 */
@Path("pedidos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PedidosREST {
  @EJB
    private PedidoFacade pedidoEJB;
  
   /**
     * Obtiene todos los pedido
     * @return lista de pedido
     */
    @GET
    public List<Pedido> findAll(){
        return pedidoEJB.findAll();
    }
    
    /**
     * Busca pedido por su id
     * @param id
     * @return pedido
     */
    @GET
    @Path("{id}")
    public Pedido findById(@PathParam("id") Integer id){
        return pedidoEJB.find(id);
    }
    
    /**
     * Crear un pedido
     * @param pedido 
     */
    @POST
    public void create(Pedido pedido){
        pedidoEJB.create(pedido);
    }
    
    /**
     * Edita un pedido
     * @param id
     * @param pedido 
     */
    @PUT
    @Path("{id}")
    public void edit(@PathParam("id") Integer id, Pedido pedido){
        pedidoEJB.edit(pedido);
    }
  
}
