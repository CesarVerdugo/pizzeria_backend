/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.univalle.pizzas.rest.service;


import co.edu.univalle.jpa.pizzas.entities.Factura;
import co.edu.univalle.jpa.pizzas.sessions.FacturaFacade;
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
@Path("facturas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FacturaREST {
    
    @EJB
    private FacturaFacade facturaEJB;
    
    /**
     * Obtiene todos los factura
     * @return lista de factura
     */
    @GET
    public List<Factura> findAll(){
        return facturaEJB.findAll();
    }
    
    /**
     * Busca factura por su id
     * @param id
     * @return factura
     */
    @GET
    @Path("{id}")
    public Factura findById(@PathParam("id") Integer id){
        return facturaEJB.find(id);
    }
    
    /**
     * Crear un factura
     * @param factura 
     * @return  Factura
     */
    @POST
    public Factura create(Factura factura){
        return facturaEJB.crearFactura(factura);
    }
    
    /**
     * Edita un factura
     * @param id
     * @param factura 
     */
    @PUT
    @Path("{id}")
    public void edit(@PathParam("id") Integer id, Factura factura){
        facturaEJB.edit(factura);
    }
    
}
