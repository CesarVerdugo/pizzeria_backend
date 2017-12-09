package co.edu.univalle.jpa.pizzas.entities;

import co.edu.univalle.jpa.pizzas.entities.Compra;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-11-29T19:57:56")
@StaticMetamodel(Insumo.class)
public class Insumo_ { 

    public static volatile SingularAttribute<Insumo, BigDecimal> precio;
    public static volatile SingularAttribute<Insumo, Integer> id;
    public static volatile ListAttribute<Insumo, Compra> compraList;
    public static volatile SingularAttribute<Insumo, String> nombre;

}