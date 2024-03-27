/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.icaguiapplication;

import java.io.Serializable;
import javax.swing.ImageIcon;

/**
 * @author joshua
 */
public class ChairClass extends FurnitureClass implements Serializable{
    
    // declare instance variables 
    private boolean hasArmRests;
    private String woodType;
    private int quantity;
    private double itemPrice;
    
    // define a constructor that takes in parameters
    public  ChairClass(int idNum, char typeOfWood, int Quantity, boolean hasArmRests, String imagePath) {
       
        // call the super constructor to set thetype of wood and quantity
       super (typeOfWood, Quantity);
       
       // initialize instance variables
       this.hasArmRests = hasArmRests;
       this.quantity = Quantity;
       this.woodType = Character.toString(typeOfWood);
       this.itemPrice = calculateItemPrice();
    }
    
    // define a method that returns wether the chair has armrests
    public boolean hasArmRests() {
        return hasArmRests;
    }
    
    // define a method that returns the type of wood for the chair
    public String getWoodType () {
        return woodType;
    }
    
    // define a method that returns the quantity of the chair
    public int getQuantity() {
        return quantity;
    }
    
    // define a method to return the item price
    public double getItemPrice() {
        return itemPrice;
    }
    
    // define a method that calculates the item price of the chair
    public double calculateItemPrice() {
        double woodCost = 0;
        
        // check the wood type and corresponding cost
        if(woodType.equals("O")) {
            woodCost = 0.05;
        } else if (woodType.equals("W")) {
            woodCost = 0.03;
        }
        
        // calculate cost of armrests if present 
        double armrestsCost = hasArmRests ? 250 : 0;
        
        // calculate the cost of the total chair
        double chairCost = (1500 + armrestsCost) * quantity * woodCost;
        
        // return the calculated price
        return chairCost;
    }
        
    // define a method to return a string representaion of the chair that was created
    public String toString() {
        return String.format("%d x Chair (wood type: %s, %s armrests)", 
                quantity, woodType, hasArmRests ? "with" : "without"); 
    }
    
    public int calcUnits() {
        return getQuantity();
    }
    
}
