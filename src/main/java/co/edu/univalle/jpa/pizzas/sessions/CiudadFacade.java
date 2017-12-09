/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.univalle.jpa.pizzas.sessions;

import co.edu.univalle.jpa.pizzas.entities.Ciudad;
import co.edu.univalle.jpa.pizzas.entities.Ciudad_;
import co.edu.univalle.jpa.pizzas.entities.Departamento;
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
public class CiudadFacade extends AbstractFacade<Ciudad> {

    @PersistenceContext(unitName = "co.edu.univalle_Pizzas_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CiudadFacade() {
        super(Ciudad.class);
    }

    /**
     * Este método sirve para buscar ciudad en un autocomplete Además si se pasa
     * el id del departamento en el autocomplete solo sale las ciudades
     * pertenecientes a ese departamento
     *
     * @param nombreCiudad
     * @param idDepartamento
     * @return lista de Ciudad
     *
    public Ciudad findCiudadByNombre(String nombreCiudad, Integer idDepartamento) {
        return (Ciudad) getEntityManager().createNamedQuery("Ciudad.findByNombreDepartamento")
                .setParameter(":nombre", nombreCiudad)
                .setParameter("idDepto", idDepartamento)
                .getSingleResult();
    }*/

    public List<Ciudad> findCiudadByNombre(String nombreCiudad, Integer idDepartamento) {

        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Ciudad> cq = cb.createQuery(Ciudad.class);
        Root<Ciudad> ciudad = cq.from(Ciudad.class);

        Predicate restrictions = cb.conjunction();

        if (idDepartamento != null) {
            restrictions = cb.and(restrictions, cb.equal(ciudad.get(Ciudad_.idDepartamento), new Departamento(idDepartamento)));
        }

        if (nombreCiudad != null) {
            restrictions = cb.and(restrictions, cb.like(ciudad.get(Ciudad_.nombre), "%" + nombreCiudad + "%"));
        }

        cq.where(restrictions);
        cq.orderBy(cb.asc(ciudad.get(Ciudad_.nombre)));
        TypedQuery<Ciudad> q = em.createQuery(cq);
        try {
            return q.getResultList();
        } catch (NoResultException ex) {
            return null;
        }
    }

}
