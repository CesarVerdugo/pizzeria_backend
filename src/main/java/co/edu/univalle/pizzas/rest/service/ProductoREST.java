/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.univalle.pizzas.rest.service;


import co.edu.univalle.jpa.pizzas.entities.Producto;
import co.edu.univalle.jpa.pizzas.sessions.ProductoFacade;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

/**
 *
 * @author Cesar Verdugo
 */
@Path("productos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductoREST {
    private static final String UPLOAD_FOLDER_PRODUCTOS = "C:/Users/Cesar Verdugo/Documents/recientes/mas del sena/pizzeria1/client/assets/imgproductos/";
    
     @EJB
    private ProductoFacade productoEJB;
    
    /**
     * Obtiene todos los producto
     * @return lista de producto
     */
    @GET
    public List<Producto> findAll(){
        return productoEJB.findAll();
    }
    
    /**
     * Busca producto por su id
     * @param id
     * @return producto
     */
    @GET
    @Path("{id}")
    public Producto findById(@PathParam("id") Integer id){
        return productoEJB.find(id);
    }
    
    /**
     * Crear un producto
     * @param in
     * @param producto 
     */
    
    @POST
    @Path("upload")
    @Consumes({MediaType.MULTIPART_FORM_DATA, MediaType.APPLICATION_JSON})
    public Response create(@FormDataParam("file") InputStream in,
            @FormDataParam("file") FormDataContentDisposition info,
            @FormDataParam("idProducto") Integer idProducto) throws IOException{
        
        File upload = new File(UPLOAD_FOLDER_PRODUCTOS.concat(info.getFileName()));
        System.out.println("PRODUCTOS");
        try {
            Producto producto = productoEJB.find(idProducto);
             System.out.println("name "+producto);
            if (upload.exists()) {
                String message = "file: " + upload.getName() + " already exists";
                return Response.status(Response.Status.CONFLICT).entity(message).build();
            } else {
                Files.copy(in, upload.toPath());
                producto.setUrlImagen(upload.getName());
                System.out.println("name "+producto.getNombre());
                productoEJB.edit(producto);
                return Response.status(Response.Status.OK).build();
            }
        } catch (IOException e) {
            return Response.status(Response.Status.CONFLICT).entity(e).build();
        }
        
    }
    
    /**
     * Edita un producto
     * @param id
     * @param producto 
     */
    @PUT
    @Path("{id}")
    public void edit(@PathParam("id") Integer id, Producto producto){
        productoEJB.edit(producto);
    }
    
    
    
     /**
     * Es utilizado para los autocomplete de productos  en el menu 
     *
     * @param nombreProducto 
     * @param clasesProductosId 
     
     * @return Lista de productos
     */
    @GET
    @Path("find")
    public List<Producto> findByNombre(
            @QueryParam("nombre") String nombreProducto,
            @QueryParam("clasesProductosId") Integer clasesProductosId) {
        return productoEJB.findProductoByNombre(nombreProducto, clasesProductosId);
    }
    
    @POST
    public Producto createProducto(Producto p){
    
    return productoEJB.createProducto(p);
    }
}
