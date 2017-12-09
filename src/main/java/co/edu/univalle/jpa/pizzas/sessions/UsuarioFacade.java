/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.univalle.jpa.pizzas.sessions;


import co.edu.univalle.jpa.pizzas.entities.Ciudad;
import co.edu.univalle.jpa.pizzas.entities.Ciudad_;
import co.edu.univalle.jpa.pizzas.entities.Departamento;
import co.edu.univalle.jpa.pizzas.entities.Rol;
import co.edu.univalle.jpa.pizzas.entities.Rol_;
import co.edu.univalle.jpa.pizzas.entities.TipoDocumento;
import co.edu.univalle.jpa.pizzas.entities.Usuario;
import co.edu.univalle.jpa.pizzas.entities.Usuario_;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.ListJoin;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author Cesar Verdugo
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "co.edu.univalle_Pizzas_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
     /**
     * Busca usuario por email
     *
     * @param email
     * @return Usuario
     */
    public Usuario findUsuarioByEmail(String email) {

        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Usuario> cq = cb.createQuery(Usuario.class);
        Root<Usuario> usuario = cq.from(Usuario.class);
        cq.where(cb.equal(usuario.get(Usuario_.email), email));
        TypedQuery<Usuario> q = getEntityManager().createQuery(cq);
        try {
            return (Usuario) q.getSingleResult();
        } catch (NonUniqueResultException ex) {
            throw ex;
        } catch (NoResultException ex) {
            return null;
        }
    }
    
    /**
     * Busca usuario por numDocumento
     *
     * @param numDocumento
     * @return Usuario
     */
    public Usuario findUsuarioByNumDocumento(String numDocumento) {

        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Usuario> cq = cb.createQuery(Usuario.class);
        Root<Usuario> usuario = cq.from(Usuario.class);
        cq.where(cb.equal(usuario.get(Usuario_.numDocumento), numDocumento));
        TypedQuery<Usuario> q = getEntityManager().createQuery(cq);
        try {
            return (Usuario) q.getSingleResult();
        } catch (NonUniqueResultException ex) {
            throw ex;
        } catch (NoResultException ex) {
            return null;
        }
    }
    
    
    /**
     * Busca usuario por rol
     *
     * @param rol
     * @return Usuario
     */
    public List<Usuario> findAllUsuariosByRol(String rol) {

        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Usuario> cq = cb.createQuery(Usuario.class);
        Root<Rol> rootRoles = cq.from(Rol.class);
        cq.where(cb.equal(rootRoles.get(Rol_.id), rol));
        ListJoin<Rol,Usuario> joinRoles = rootRoles.join(Rol_.usuarioList);
        CriteriaQuery<Usuario> cqq = cq.select(joinRoles);
        
        TypedQuery<Usuario> q = getEntityManager().createQuery(cq);
        try {
            return  q.getResultList();
       
        } catch (NoResultException ex) {
            return null;
        }
    }
    
    
    public List<Usuario> findUsers(Integer idUsuario, String genero, Boolean activo,
            String numDocumento, String email, Integer idCiudad, Integer idDepartamento,
            String idTipoDocumento){
        
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Usuario> cq = cb.createQuery(Usuario.class);
        Root<Usuario> usuario = cq.from(Usuario.class);
        
        Predicate data = cb.conjunction();
        
        if(idUsuario != null){
            data = cb.and(data, cb.equal(usuario.get(Usuario_.id), idUsuario));
        }
        
        if(genero != null){
            data = cb.and(data, cb.equal(usuario.get(Usuario_.genero), genero));
        }
        
        if(activo != null){
            data = cb.and(data, cb.equal(usuario.get(Usuario_.activo), activo));
        }
        
        if(numDocumento != null){
            data = cb.and(data, cb.equal(usuario.get(Usuario_.numDocumento), numDocumento));
        }
        
        if(email != null){
            data = cb.and(data, cb.equal(usuario.get(Usuario_.email), email));
        }
        
        if(idCiudad != null){
            data = cb.and(data, cb.equal(usuario.get(Usuario_.idCiudad), new Ciudad(idCiudad)));
        }
        
        if(idDepartamento != null){
            Join<Usuario, Ciudad> joinCiudad = usuario.join(Usuario_.idCiudad);
            data = cb.and(data, cb.equal(joinCiudad.get(Ciudad_.idDepartamento), new Departamento(idDepartamento)));
        }
        
        if(idTipoDocumento != null){
            data = cb.and(data, cb.equal(usuario.get(Usuario_.tiposDocumentosId), new TipoDocumento(idTipoDocumento)));
        }
        
        cq.where(data);
        cq.orderBy(cb.asc(usuario.get(Usuario_.apellido)));
        TypedQuery<Usuario> tq = em.createQuery(cq);
        
        try {
            return tq.getResultList();
        } catch (NoResultException ex) {
            return null;
        }
    }
    
}
