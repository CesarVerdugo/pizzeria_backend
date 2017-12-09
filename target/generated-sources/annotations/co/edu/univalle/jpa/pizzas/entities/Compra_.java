package co.edu.univalle.jpa.pizzas.entities;

import co.edu.univalle.jpa.pizzas.entities.Factura;
import co.edu.univalle.jpa.pizzas.entities.Insumo;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-11-29T19:57:56")
@StaticMetamodel(Compra.class)
public class Compra_ { 

    public static volatile SingularAttribute<Compra, Date> fecha;
    public static volatile SingularAttribute<Compra, String> unidadMedida;
    public static volatile SingularAttribute<Compra, Factura> facturasId;
    public static volatile SingularAttribute<Compra, BigDecimal> valorTotal;
    public static volatile SingularAttribute<Compra, Integer> id;
    public static volatile SingularAttribute<Compra, BigDecimal> cantidad;
    public static volatile SingularAttribute<Compra, Insumo> insumosId;

}