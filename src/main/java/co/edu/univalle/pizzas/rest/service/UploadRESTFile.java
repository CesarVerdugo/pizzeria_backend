
package co.edu.univalle.pizzas.rest.service;


import co.edu.univalle.jpa.pizzas.entities.Producto;
import co.edu.univalle.jpa.pizzas.entities.Usuario;
import co.edu.univalle.jpa.pizzas.sessions.ProductoFacade;
import co.edu.univalle.jpa.pizzas.sessions.UsuarioFacade;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

/**
 *
 * @author ruber
 */
@Path("upload")
@Produces(MediaType.APPLICATION_JSON)
public class UploadRESTFile {
    
    //Se debe cambiar por la URL donde se quiera guardar la imagen
    private static final String UPLOAD_FOLDER = "C:/Users/Cesar Verdugo/Documents/recientes/mas del sena/pizzeria1/client/assets/images/";
    private static final String UPLOAD_FOLDER_USUARIOS = "C:/Users/Cesar Verdugo/Desktop/ejemplo/ejemplo_java_angular_frontend/client/assets";
    //private static final String UPLOAD_FOLDER_PRODUCTOS = "C:/Users/Cesar Verdugo/Documents/recientes/mas del sena/pizzeria1/client/assets/imgproductos";
    
    @EJB
    private UsuarioFacade usuarioEJB;
    
    @EJB
    private ProductoFacade productoEJB;
    
    
    
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile(
            @FormDataParam("file") InputStream in,
            @FormDataParam("file") FormDataContentDisposition info) throws IOException {
        
        File upload = new File(UPLOAD_FOLDER.concat(info.getFileName()));

        try {
            if (upload.exists()) {
                String message = "file: " + upload.getName() + " already exists";
                return Response.status(Response.Status.CONFLICT).entity(message).build();
            } else {
                Files.copy(in, upload.toPath());
                return Response.status(Response.Status.OK).build();
            }
        } catch (IOException e) {
            return Response.status(Response.Status.CONFLICT).entity(e).build();
        }

    }
    
    @PUT
    @Path("usuario")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFileUsuario(Integer idUser,
            @FormDataParam("file") InputStream in,
            @FormDataParam("file") FormDataContentDisposition info) throws IOException {
        
        File upload = new File(UPLOAD_FOLDER_USUARIOS.concat(info.getFileName()));

        try {
            if (upload.exists()) {
                String message = "file: " + upload.getName() + " already exists";
                return Response.status(Response.Status.CONFLICT).entity(message).build();
            } else {
                Files.copy(in, upload.toPath());
                Usuario user = usuarioEJB.find(idUser);
                user.setUrlImagen(upload.getPath());
                usuarioEJB.edit(user);
                return Response.status(Response.Status.OK).build();
            }
        } catch (IOException e) {
            return Response.status(Response.Status.CONFLICT).entity(e).build();
        }

    }
    
   /*     @PUT
    @Path("productos")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFileProductos(Integer idProducto,
            @FormDataParam("file") InputStream in,
            @FormDataParam("file") FormDataContentDisposition info) throws IOException {
        
        File upload = new File(UPLOAD_FOLDER_PRODUCTOS.concat(info.getFileName()));

        try {
            if (upload.exists()) {
                String message = "file: " + upload.getName() + " already exists";
                return Response.status(Response.Status.CONFLICT).entity(message).build();
            } else {
                Files.copy(in, upload.toPath());
                Producto producto = productoEJB.find(idProducto);
                producto.setUrlImagen(upload.getPath());
                productoEJB.edit(producto);
                return Response.status(Response.Status.OK).build();
            }
        } catch (IOException e) {
            return Response.status(Response.Status.CONFLICT).entity(e).build();
        }

    }*/
    
}
