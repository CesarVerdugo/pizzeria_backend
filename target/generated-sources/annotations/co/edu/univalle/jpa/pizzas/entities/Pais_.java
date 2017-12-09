package co.edu.univalle.jpa.pizzas.entities;

import co.edu.univalle.jpa.pizzas.entities.Departamento;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-11-29T19:57:56")
@StaticMetamodel(Pais.class)
public class Pais_ { 

    public static volatile ListAttribute<Pais, Departamento> departamentoList;
    public static volatile SingularAttribute<Pais, String> id;
    public static volatile SingularAttribute<Pais, String> nombre;
    public static volatile SingularAttribute<Pais, Short> activo;

}