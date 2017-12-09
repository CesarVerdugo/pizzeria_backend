/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.univalle.pizzas.rest.service;


import co.edu.univalle.jpa.pizzas.entities.Pais;
import co.edu.univalle.jpa.pizzas.sessions.PaisFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 *
 * @author Cesar Verdugo
 */
@Path("paises")
public class PaisREST {
    @EJB
    private PaisFacade paisesEJB;
    
   /**
     * Obtiene todos los paises
     * @return lista de paises
     */
    @GET
    public List<Pais> findAll(){
        return paisesEJB.findAll();
    }
    
}
