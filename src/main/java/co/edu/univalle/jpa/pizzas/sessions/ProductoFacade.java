/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.univalle.jpa.pizzas.sessions;


import co.edu.univalle.jpa.pizzas.entities.ClaseProducto;
import co.edu.univalle.jpa.pizzas.entities.Producto;
import co.edu.univalle.jpa.pizzas.entities.Producto_;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author Cesar Verdugo
 */
@Stateless
public class ProductoFacade extends AbstractFacade<Producto> {

    @PersistenceContext(unitName = "co.edu.univalle_Pizzas_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductoFacade() {
        super(Producto.class);
    }
    
    
     /**
     * Este método sirve para buscar producto en un autocomplete
     * Además si se pasa el id del la clase producto en el autocomplete solo 
     * sale las productoes pertenecientes a esa clase de producto
     * @param query
     * @param clasesProductosId
     * @return lista de Producto
     */
    public List<Producto> findProductoByNombre(String query, Integer clasesProductosId) {
        
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Producto> cq = cb.createQuery(Producto.class);
        Root<Producto> producto = cq.from(Producto.class);
        
        Predicate restrictions = cb.conjunction();
        
        if(clasesProductosId != null){
            restrictions = cb.and(restrictions, cb.equal(producto.get(Producto_.clasesProductosId), new ClaseProducto(clasesProductosId)));
        }
        
        if(query != null){
            restrictions = cb.and(restrictions, cb.like(producto.get(Producto_.nombre), "%" + query + "%"));
        }
        
        cq.where(restrictions);
        cq.orderBy(cb.asc(producto.get(Producto_.nombre)));
        TypedQuery<Producto> q = em.createQuery(cq);
        try {
            return q.setMaxResults(10).getResultList();
        } catch (NoResultException ex) {
            return null;
        }
    }
    
    
    public Producto createProducto(Producto p){
    
    getEntityManager().persist(p);
    getEntityManager().flush();
    return p;
    }
}
