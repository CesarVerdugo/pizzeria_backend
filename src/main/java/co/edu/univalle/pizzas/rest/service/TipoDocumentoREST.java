/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.univalle.pizzas.rest.service;


import co.edu.univalle.jpa.pizzas.entities.TipoDocumento;
import co.edu.univalle.jpa.pizzas.sessions.TipoDocumentoFacade;
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
@Path("tipodocumentos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TipoDocumentoREST {
     @EJB
    private TipoDocumentoFacade tipodocumentoEJB;
    
    /**
     * Obtiene todos los tipodocumento
     * @return lista de tipodocumento
     */
    @GET
    public List<TipoDocumento> findAll(){
        return tipodocumentoEJB.findAll();
    }
    
    /**
     * Busca tipodocumento por su id
     * @param id
     * @return tipodocumento
     */
    @GET
    @Path("{id}")
    public TipoDocumento findById(@PathParam("id") Integer id){
        return tipodocumentoEJB.find(id);
    }
    
    /**
     * Crear un tipodocumento
     * @param tipodocumento 
     */
    @POST
    public void create(TipoDocumento tipodocumento){
        tipodocumentoEJB.create(tipodocumento);
    }
    
    /**
     * Edita un tipodocumento
     * @param id
     * @param tipodocumento 
     */
    @PUT
    @Path("{id}")
    public void edit(@PathParam("id") Integer id, TipoDocumento tipodocumento){
        tipodocumentoEJB.edit(tipodocumento);
    }
}
