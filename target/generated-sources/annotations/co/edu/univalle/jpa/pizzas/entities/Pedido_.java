package co.edu.univalle.jpa.pizzas.entities;

import co.edu.univalle.jpa.pizzas.entities.Factura;
import co.edu.univalle.jpa.pizzas.entities.Producto;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-11-29T19:57:56")
@StaticMetamodel(Pedido.class)
public class Pedido_ { 

    public static volatile SingularAttribute<Pedido, Factura> facturasId;
    public static volatile SingularAttribute<Pedido, Integer> id;
    public static volatile SingularAttribute<Pedido, Integer> cantidad;
    public static volatile SingularAttribute<Pedido, BigDecimal> totalPagar;
    public static volatile SingularAttribute<Pedido, Producto> productosId;

}