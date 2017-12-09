/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.univalle.pizzas.rest.service;

import co.edu.univalle.jpa.pizzas.entities.Rol;
import co.edu.univalle.jpa.pizzas.entities.Usuario;
import co.edu.univalle.jpa.pizzas.sessions.UsuarioFacade;
import co.edu.univalle.pizzas.rest.auth.DigestUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.security.RolesAllowed;
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
@Path("usuarios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class UsuarioREST {
     @EJB
    private UsuarioFacade usuarioEJB;
    
    /**
     * Obtiene todos los usuario
     * @return lista de usuario
     */
    @GET
    public List<Usuario> findAll(){
        return usuarioEJB.findAll();
    }
    
    /**
     * Busca usuario por su id
     * @param id
     * @return usuario
     */
    @GET
    @Path("{id}")
    public Usuario findById(@PathParam("id") Integer id){
        return usuarioEJB.find(id);
    }
    
    
    
    /**
     * Crear un usuario
     *
     * @param usuario
     * @return 
     */
    @POST
    public Response create(Usuario usuario) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        try {
            if (usuarioEJB.findUsuarioByEmail(usuario.getEmail()) == null) {
                if (usuarioEJB.findUsuarioByNumDocumento(usuario.getNumDocumento()) == null) {
                    usuario.setPassword(DigestUtil.cifrarPassword(usuario.getPassword()));
                    
                    ArrayList<Rol> roles = new ArrayList<>();
                    roles.add(new Rol("USER"));
                    usuario.setRolList(roles);
                    usuarioEJB.create(usuario);
                    return Response.status(Response.Status.CREATED).entity(gson.toJson("El usuario se creó correctamente!")).build();
                } else {
                    return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson("El número de documento ya se encuentra registrado!.")).build();
                }
            } else {
                return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson("El email ya se encuentra registrado!.")).build();
            }
        } catch (Exception e) {
            Logger.getLogger(UsuarioREST.class.getName()).log(Level.SEVERE, null, e);
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson("Error al guardar el usuario!.")).build();
        }
    }
    
    /**
     * Obtiene todos los usuarios con rol empleado
     *
     * @return lista de usuarios
     */
     @GET
   // @RolesAllowed({"ADMIN"})
    @Path("empleados")
    public List<Usuario> findAllUsuariosByRol() {
        return usuarioEJB.findAllUsuariosByRol("EMPLEADO");
    }
    
    
    /**
     * Obtiene todos los usuarios con rol empleado
     *
     * @return lista de usuarios
     */
     @GET
   // @RolesAllowed({"ADMIN"})
    @Path("administradores")
    public List<Usuario> findAllUsuariosADMINByRol() {
        return usuarioEJB.findAllUsuariosByRol("ADMIN");
    }
    
    
    
    @POST
   // @RolesAllowed({"ADMIN","EMPLEADO"})
    @Path("administradores")
    public Response createADMIN(Usuario usuario) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        try {
            if (usuarioEJB.findUsuarioByEmail(usuario.getEmail()) == null) {
                if (usuarioEJB.findUsuarioByNumDocumento(usuario.getNumDocumento()) == null) {
                    
                    usuario.setPassword(DigestUtil.cifrarPassword(usuario.getPassword()));
                    usuario.setRolList(new ArrayList<Rol>());
                    usuario.getRolList().add(new Rol("ADMIN"));
                    usuarioEJB.create(usuario);
                    return Response.status(Response.Status.CREATED).entity(gson.toJson("El usuario administrador se creó correctamente!")).build();

                
                } else {
                    return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson("El número de documento ya se encuentra registrado!.")).build();
                }
            } else {
                return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson("El email ya se encuentra registrado!.")).build();
            }
        } catch (Exception e) {
            Logger.getLogger(UsuarioREST.class.getName()).log(Level.SEVERE, null, e);
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson("Error al guardar el usuario!.")).build();
        }
    }
    
   
   // @RolesAllowed({"ADMIN","EMPLEADO"})
    @POST
    @Path("empleados")
    public Response createEmpleado(Usuario usuario) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        try {
            if (usuarioEJB.findUsuarioByEmail(usuario.getEmail()) == null) {
                if (usuarioEJB.findUsuarioByNumDocumento(usuario.getNumDocumento()) == null) {
                    
                    usuario.setPassword(DigestUtil.cifrarPassword(usuario.getPassword()));
                    usuario.setRolList(new ArrayList<Rol>());
                    usuario.getRolList().add(new Rol("EMPLEADO"));
                    usuarioEJB.create(usuario);
                    return Response.status(Response.Status.CREATED).entity(gson.toJson("El usuario empleado se creó correctamente!")).build();

                
                } else {
                    return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson("El número de documento ya se encuentra registrado!.")).build();
                }
            } else {
                return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson("El email ya se encuentra registrado!.")).build();
            }
        } catch (Exception e) {
            Logger.getLogger(UsuarioREST.class.getName()).log(Level.SEVERE, null, e);
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson("Error al guardar el usuario!.")).build();
        }
    }


    
    
    /**
     * Edita un usuario
     * @param id
     * @param usuario 
     */
    @PUT
    @Path("{id}")
    public void edit(@PathParam("id") Integer id, Usuario usuario){
        usuarioEJB.edit(usuario);
    }
}
