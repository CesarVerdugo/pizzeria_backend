package co.edu.univalle.jpa.pizzas.entities;

import co.edu.univalle.jpa.pizzas.entities.Producto;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-11-29T19:57:56")
@StaticMetamodel(ClaseProducto.class)
public class ClaseProducto_ { 

    public static volatile SingularAttribute<ClaseProducto, String> codigo;
    public static volatile ListAttribute<ClaseProducto, Producto> productoList;
    public static volatile SingularAttribute<ClaseProducto, Integer> id;
    public static volatile SingularAttribute<ClaseProducto, String> nombre;

}