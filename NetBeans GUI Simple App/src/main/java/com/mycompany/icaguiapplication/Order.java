/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.icaguiapplication;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author joshua
 */
public class Order implements Serializable {
    
    // identifyers for the orders
    private int id;
    private List<FurnitureClass> items;
    private double totalPrice;
    
    // constructor
    public Order() {
        items = new ArrayList<>();
        totalPrice = 0;
    }
    
    // add furniture item to the order
    public boolean addItem(FurnitureClass item) {
        if(items.size()>=9) {
            return false; // if the order has 9 items more cannot be added
        }
        items.add(item); // add new item to the list of items
        totalPrice += item.getItemPrice();// add the price of the item 
        return true; // successfully added
    }
    
    // returns  the total price for the order
    public double getTotalPrice() { 
        return totalPrice;
    }
    
    // sets the total price for the order
    public void setTotalPrice(double totalPrice){
            this.totalPrice = totalPrice;
    }
   
    // sets the list of items in the order
    public void setItems(List<FurnitureClass> items) {
        this.items = items;
    }
    
    // returns the list of items in the order
    public List<FurnitureClass> getItems() {
        return items;
    }
    
    // clears all items in the order
    public void clearALLItems() {
        items.clear();
        totalPrice = 0;
    }
    
    // returns id for an order
    public int getId(){
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
}
