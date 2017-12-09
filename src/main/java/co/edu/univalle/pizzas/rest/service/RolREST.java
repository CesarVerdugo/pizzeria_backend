/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.univalle.pizzas.rest.service;


import co.edu.univalle.jpa.pizzas.entities.Rol;
import co.edu.univalle.jpa.pizzas.sessions.RolFacade;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Cesar Verdugo
 */
@Path("roles")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RolREST {
   
     @EJB
    private RolFacade rolEJB;
    
    /**
     * Obtiene todos los rol
     * @return lista de rol
     */
    @GET
    public List<Rol> findAll(){
        return rolEJB.findAll();
    }
    
    /**
     * Busca rol por su id
     * @param id
     * @return rol
     */
    @GET
    @Path("{id}")
    public Rol findById(@PathParam("id") Integer id){
        return rolEJB.find(id);
    }
    
       /**
     * Obtiene todos los roles
     *
     * @return lista de roles
     */
  
    
    @POST
    public Response create(Rol rol) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        try{
            rolEJB.create(rol);
            return Response.status(Response.Status.CREATED).entity(gson.toJson("El Plan se ha creado satisfactoriamente!")).build();
        }catch (Exception e) {
            //Logger.getLogger(AlquileresREST.class.getName()).log(Level.SEVERE, null, e);
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson("Error al guardar el plan!.")).build();
        }
       
    }
    
    /**
     * Edita un rol
     * @param id
     * @param rol 
     */
    @PUT
    @Path("{id}")
    public void edit(@PathParam("id") Integer id, Rol rol){
        rolEJB.edit(rol);
    }
}
