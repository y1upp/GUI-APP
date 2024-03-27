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
public class DeskClass extends FurnitureClass implements Serializable{
    
    // declare the  variables for width, depth and number of drawers
    private int width;
    private int depth;
    private int numOfDrawers;
    
    // define the constructor with parameters for id number, type of wood, quantity item price, width, depth, number of drawers
    public DeskClass(int idNum, char typeOfWood, int quantity, double itemPrice, int width, int depth, int numOfDrawers) {
        
        // callthe superclass constructor and pass type of wood and quantity as arguments
        super(typeOfWood, quantity);
        
        // set the values of width depth and number of drawers using the constructor parameters
        this.width = width;
        this.depth = depth;
        this.numOfDrawers = numOfDrawers;
        
        // define the price per unit of surface area and calculate the total item price based on the dimensions and number of drawers
        double pricePerUnit= 1.5;
        double itemPriceTotal = calculateItemPrice(width, depth, numOfDrawers, pricePerUnit);
        setItemPrice(itemPriceTotal);
    }
    
    // getter method for width
    public int getWidth(){
        return width;
    }
    // getter method for depth
    public int getDepth() {
        return depth;
    }
    // getter method for number of drawers
    public int getNumOfDrawers() {
        return numOfDrawers;
    }
    
    // define the method to calulate the item price based on dimensions and number of drawers
    public double calculateItemPrice(double width, double depth, int numDrawers,  double pricePerUnit) {
        
        // height of desk is always 85
        double height = 85;
        
        // calculate total surface area of the desk and cost of drawers
        double totalSurfaceArea = (height + width +depth) * 14 + depth * width* pricePerUnit;
        double totalDrawersCost = numDrawers *7.25;
        
        // return the item price
        return totalSurfaceArea + totalDrawersCost;
    }
    
    // define the method to return a string representation of the desk
    public String toString() {
        return "Desk " + getIdNum() + " made of " + getTypeOfWood() 
                + ", width: " + getWidth() + "cm, depth: " 
                + getDepth() + " drawers, price: £" + getItemPrice() 
                +", quantity: " + getQuantity(); 
    }
    
    // define the method to caclulate the number of units
    public int calcUnits() {
        return getQuantity();
    }
}
