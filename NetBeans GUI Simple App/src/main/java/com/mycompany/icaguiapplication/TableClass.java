/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.icaguiapplication;

import java.io.Serializable;

/**
 * @author joshua
 */
public class TableClass extends FurnitureClass implements Serializable{
    
    // private instance variables 
    private int diameter;
    private String baseType;
    private double charge;
    
    //constructor method to take parameters
    public TableClass(int idNum,char typeOfWood, int Quantity, int diameter, String baseType) {
        
       super(typeOfWood, Quantity);
       this.diameter = diameter;
       this.baseType = baseType;
       this.charge = calculateCharge(diameter);
       this.itemPrice = calculateItemPrice(typeOfWood, Quantity, baseType, charge, diameter);
    }
    
    // method to reutn diameter
    public int diameter(){
        return diameter;
    }
    
    // method to reutn base type 
    public String getBaseType(){
        return baseType;
    }
    
    // method to reutn the charge 
    public double getCharge(){
        return charge;
    }
    
    // method to calculate the charge on table based on extra units
    public double calculateCharge(int diameter) {
        if (diameter >= 50) {
            int units = diameter * diameter;
            return units * 0.05;
        } else{
            return 0;
        }
    }
    
    // method to calculate the totalprice of the table 
    public double calculateItemPrice(char typeOfWood, int quantity, String baseType, double charge, int diameter) {
        double pricePerUnit = 0;
        double basePrice = 0;
        
        // Setting the base type
        if(baseType.equals("Wooden base")) {
            basePrice = 40;
        } else if (baseType.contains("Chrome base")) 
        {
            basePrice = 50;
        } else {
            basePrice = 0;
        }
        
        // setting the woodtype
        String woodType;
        if(typeOfWood  == 'O') {
            woodType = "Oak";
            pricePerUnit = OAK_PRICE_PER_UNIT;
        } else if (typeOfWood == 'W'){
            woodType = "Walnut";
            pricePerUnit = WALNUT_PRICE_PER_UNIT;
        }
        
        double totalUnitsOfWood = (diameter/2.0) * (diameter/2.0) * Math.round(Math.PI * 100.0) / 100.0;
        
        double woodPrice = totalUnitsOfWood * pricePerUnit;
        double itemPrice = woodPrice + basePrice + charge;
        return itemPrice * quantity;
        
        
    }
      
    // creating a method to create a string from the object
    public String toString() {
        return "Table "+ getIdNum() + " made of " + getTypeOfWood() 
                + ", diameter: " + diameter + "cm, base type: " 
                + getBaseType() + ", price: £" + getItemPrice() 
                + ",quantity " + getQuantity();
    }
    
}
