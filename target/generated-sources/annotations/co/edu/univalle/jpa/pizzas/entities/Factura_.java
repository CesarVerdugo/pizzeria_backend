package co.edu.univalle.jpa.pizzas.entities;

import co.edu.univalle.jpa.pizzas.entities.Compra;
import co.edu.univalle.jpa.pizzas.entities.Pedido;
import co.edu.univalle.jpa.pizzas.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-11-29T19:57:56")
@StaticMetamodel(Factura.class)
public class Factura_ { 

    public static volatile SingularAttribute<Factura, Date> fecha;
    public static volatile SingularAttribute<Factura, String> numDocumento;
    public static volatile SingularAttribute<Factura, Usuario> idCliente;
    public static volatile SingularAttribute<Factura, Usuario> idEmpleado;
    public static volatile SingularAttribute<Factura, String> apellido;
    public static volatile ListAttribute<Factura, Pedido> pedidoList;
    public static volatile SingularAttribute<Factura, Integer> id;
    public static volatile ListAttribute<Factura, Compra> compraList;
    public static volatile SingularAttribute<Factura, String> nombre;

}