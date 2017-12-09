package co.edu.univalle.jpa.pizzas.entities;

import co.edu.univalle.jpa.pizzas.entities.Ciudad;
import co.edu.univalle.jpa.pizzas.entities.Factura;
import co.edu.univalle.jpa.pizzas.entities.Rol;
import co.edu.univalle.jpa.pizzas.entities.TipoDocumento;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-11-29T19:57:56")
@StaticMetamodel(Usuario.class)
public class Usuario_ { 

    public static volatile SingularAttribute<Usuario, TipoDocumento> tiposDocumentosId;
    public static volatile SingularAttribute<Usuario, String> numDocumento;
    public static volatile SingularAttribute<Usuario, String> direccion;
    public static volatile ListAttribute<Usuario, Rol> rolList;
    public static volatile SingularAttribute<Usuario, String> nombre;
    public static volatile SingularAttribute<Usuario, Ciudad> idCiudad;
    public static volatile SingularAttribute<Usuario, String> urlImagen;
    public static volatile SingularAttribute<Usuario, String> password;
    public static volatile ListAttribute<Usuario, Factura> facturaList1;
    public static volatile SingularAttribute<Usuario, String> rh;
    public static volatile SingularAttribute<Usuario, String> apellido;
    public static volatile SingularAttribute<Usuario, String> genero;
    public static volatile ListAttribute<Usuario, Factura> facturaList;
    public static volatile SingularAttribute<Usuario, Integer> id;
    public static volatile SingularAttribute<Usuario, String> telefono;
    public static volatile SingularAttribute<Usuario, String> email;
    public static volatile SingularAttribute<Usuario, Boolean> activo;

}