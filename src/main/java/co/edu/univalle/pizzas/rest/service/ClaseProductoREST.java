/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.univalle.pizzas.rest.service;


import co.edu.univalle.jpa.pizzas.entities.ClaseProducto;
import co.edu.univalle.jpa.pizzas.sessions.ClaseProductoFacade;
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
@Path("clasesProductos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ClaseProductoREST {
 
        
    @EJB
    private ClaseProductoFacade claseproductoEJB;
    
    /**
     * Obtiene todos los claseproducto
     * @return lista de claseproducto
     */
    @GET
    public List<ClaseProducto> findAll(){
        return claseproductoEJB.findAll();
    }
    
    /**
     * Busca claseproducto por su id
     * @param id
     * @return claseproducto
     */
    @GET
    @Path("{id}")
    public ClaseProducto findById(@PathParam("id") Integer id){
        return claseproductoEJB.find(id);
    }
    
    /**
     * Crear un claseproducto
     * @param claseproducto 
     */
    @POST
    public void create(ClaseProducto claseproducto){
        claseproductoEJB.create(claseproducto);
    }
    
    /**
     * Edita un claseproducto
     * @param id
     * @param claseproducto 
     */
    @PUT
    @Path("{id}")
    public void edit(@PathParam("id") Integer id, ClaseProducto claseproducto){
        claseproductoEJB.edit(claseproducto);
    }
}
