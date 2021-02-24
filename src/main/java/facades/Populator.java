/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.CarDTO;
import dtos.RenameMeDTO;
import entities.Car;
import entities.RenameMe;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import utils.EMF_Creator;

/**
 *
 * @author tha
 */
public class Populator {
    public static void populate(){
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        FacadeExample fe = FacadeExample.getFacadeExample(emf);
        fe.create(new RenameMeDTO(new RenameMe("First 1", "Last 1")));
        fe.create(new RenameMeDTO(new RenameMe("First 2", "Last 2")));
        fe.create(new RenameMeDTO(new RenameMe("First 3", "Last 3")));
        
    }
    
    public static void populateDB() {
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        CarFacade cf = CarFacade.getCarFacade(emf);
        
            cf.create(new CarDTO(new Car("Tesla", 2020, "Model Roadster", 1250000, 15)));
            cf.create(new CarDTO(new Car("Tesla", 2012, "Model S", 1250000, 35)));
            cf.create(new CarDTO(new Car("Tesla", 2014, "Model 3", 1250000, 20)));
            cf.create(new CarDTO(new Car("Tesla", 2017, "Model X", 1250000, 9)));
            cf.create(new CarDTO(new Car("Tesla", 2018, "Model Y", 1250000, 23)));
            cf.create(new CarDTO(new Car("Ford", 2015, "Model Mondeo", 1250000, 19)));
            
            
        
    }
    
    public static void main(String[] args) {
        populate();
        populateDB();
    }
}
