/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.Car;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tha
 */
public class CarDTO {
    private String manufacturer;
    private int year;
    private String model;
    private int price;
    private int quantity;

    public CarDTO(Car car) {
        this.manufacturer = car.getManufacturer();
        this.year = car.getYear();
        this.model = car.getModel();
        this.price = car.getPrice();
        this.quantity = car.getQuantity();
    }
    
    public static List<CarDTO> getDtos(List<Car> rms){
        List<CarDTO> rmdtos = new ArrayList();
        rms.forEach(rm->rmdtos.add(new CarDTO(rm)));
        return rmdtos;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CarDTO{" + "manufacturer=" + manufacturer + ", year=" + year + ", model=" + model + ", price=" + price + ", quantity=" + quantity + '}';
    }
    
    


    

  
    
    
    
    
    
}
