/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.CarDTO;
import entities.Car;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import utils.EMF_Creator;

/**
 *
 * @author tha
 */
public class Populator {
   
    
    
    public static void populateDB() {
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        CarFacade cf = CarFacade.getCarFacade(emf);
        
            cf.create(new CarDTO(new Car("Tesla", 1250000, "Model Roadster", 2020, 15)));
            cf.create(new CarDTO(new Car("Tesla", 750000, "Model S", 2012, 35)));
            cf.create(new CarDTO(new Car("Tesla", 335545, "Model 3", 2014, 20)));
            cf.create(new CarDTO(new Car("Tesla", 975499, "Model X", 2017, 9)));
            cf.create(new CarDTO(new Car("Tesla", 325999, "Model Y", 2018, 23)));
            cf.create(new CarDTO(new Car("Ford", 389499, "Model Mondeo", 2015, 19)));
            
            
        
    }
    
    public static void main(String[] args) {
        populateDB();
    }
}
