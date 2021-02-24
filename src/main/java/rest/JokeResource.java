/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.JokeDTO;
import facades.FacadeExample;
import facades.FacadeJoke;
import facades.JokePopulator;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import utils.EMF_Creator;

/**
 * REST Web Service
 *
 * @author PC
 */
@Path("joke")
public class JokeResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
       
    private static final FacadeJoke FACADE =  FacadeJoke.getFacadeJoke(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    

    @Path ("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getByID(@PathParam("id") Long id) {
       JokeDTO jdto=FACADE.getById(id);
       return new Gson().toJson(jdto);
    }

    @Path ("/all")
    @GET
    @Produces (MediaType.APPLICATION_JSON)
    public String getAll(){
        List<JokeDTO> jokes=FACADE.getAllJokes();
       return new Gson().toJson(jokes);
    }
    
    @Path ("/populate")
    @GET
    @Produces (MediaType.APPLICATION_JSON)
    public void populate(){
        JokePopulator.populate();
    }
}