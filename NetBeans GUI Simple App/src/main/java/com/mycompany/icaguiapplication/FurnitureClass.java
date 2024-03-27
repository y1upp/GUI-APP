/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.icaguiapplication;

import java.util.List;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 * @author joshua
 */
public class FurnitureClass {
    
    // holds variables 
    private static int idNum = 0;
    private  char typeOfWood;
    protected double itemPrice;
    protected int quantity;
    protected ImageIcon image;
    
    // list to store all objects
    private static List<FurnitureClass> objects = new ArrayList<>();
    
    // constants to hold prices of oak and walnut
    protected static final double OAK_PRICE_PER_UNIT = 0.05;
    protected static final double WALNUT_PRICE_PER_UNIT = 0.03;
    
    // method to add furniture object to the list
    public static void storeObject(FurnitureClass object){
        objects.add(object);
    }
    
    // method to print out the furniture objects
    public static void PrintObject(FurnitureClass object) {
        System.out.println(object);
    }
    
    // constructor to create a furniture object withthe  specified wood  type and quantity
    public FurnitureClass(char typeOfWood, int quantity) {
        this.idNum = idNum;
        this.typeOfWood = typeOfWood;
        this.quantity = quantity;
        this.itemPrice = calculateItemPrice(typeOfWood, quantity, false);
    }
    
    // method to get the id of a furniture object
    public static int getIdNum(){
        return idNum;
    }
    public void setIdNum(int idNum) {
        this.idNum = idNum;
    }
    
    // method to get the type of wood used in the furniture object
    public char getTypeOfWood() {
        return typeOfWood;
    }
    public void setTypeOfWood(char typeOfWood) {
        this.typeOfWood = typeOfWood;
    }
    
    // method to select the type of wood used in the furniture object
    public void selectWood(char woodType){
        if (woodType == ('o')||woodType == ('W')) {
            setTypeOfWood(woodType);
        } else {
            System.out.println("Invalid wood type");
        }
    }
    
    // method to get the quantityof the furniture object
    public int getQuantity() {
        return  quantity;
        
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    // method to calculate the price of the furnitrue object 
    public double calculateItemPrice(char typeOfWood,int quantity, boolean hasArmRests){
        double pricePerUnit;
        if (typeOfWood == 'O'){
            pricePerUnit = OAK_PRICE_PER_UNIT;
        } else if (typeOfWood == 'W'){
            pricePerUnit = WALNUT_PRICE_PER_UNIT;
        }else{
            pricePerUnit = 0;
        } 
        
        double Price = pricePerUnit* quantity;
        return Price;
    }
    
    // method to get the price of the furniture object
    public double getItemPrice() {
        return itemPrice;
    }
    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }
    
    // method to set the image of the furniture object
    public void setImage(ImageIcon image){
        this.image = image;
    }
    
    // method to return a string of the object
    public String toString() {
        return "Furniture " + idNum + " made of "
                + typeOfWood + ", price: £" + itemPrice
                + ", quantity: " + quantity;
    }

    public int calcUnits() {
        return quantity;
    }
    
    
}
