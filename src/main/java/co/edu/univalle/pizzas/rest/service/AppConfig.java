/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.univalle.pizzas.rest.service;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;


/**
 *
 * @author Cesar Verdugo
 */
@ApplicationPath("api")
public class AppConfig extends ResourceConfig{
    public AppConfig(){
       packages("co.edu.univalle.pizzas.rest.service;co.edu.univalle.pizzas.rest.auth");
       register(RolesAllowedDynamicFeature.class);
       register(MultiPartFeature.class);
    }
}
