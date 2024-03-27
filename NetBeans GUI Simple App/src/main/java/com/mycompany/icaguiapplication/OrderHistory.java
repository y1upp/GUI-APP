/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.icaguiapplication;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;

/**
 * @author joshua
 */
public class OrderHistory implements Serializable {
    
    // instance variables
    private int orderID;
    private List<FurnitureClass> items;
    private double totalPrice;
    private List<Order> orderList;  
    
    public OrderHistory(int orderID, List<FurnitureClass> items, double totalPrice) {
        this.orderID = orderID;
        this.items = items;
        this.totalPrice = totalPrice;
    }
    
    // constructor initializes the order list
    public OrderHistory(){
        orderList = new ArrayList<>();
    }
    
    // method to add an  order to the list
    public void addOrder(Order order) {
        orderList.add(order);
    }
    
    // method to get the order from the  list
    public List<Order> getOrderList(){
        return orderList;
    }
    
    // method to clear the order history
    public void clearOrderHistory(){
        orderList.clear();}
    
    // method  to serialize an order history object to a file
    public static void serializeOrderHistory(OrderHistory orderHistory, String filename) throws IOException {
        
        // default file name
        String fileName = "IcaGuiApplication\\orders";
        
        // create a file chooser dialog to let the user select a file 
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            fileName = selectedFile.getAbsolutePath();
        }
        
        // write the order history object to the selected file
        FileOutputStream fileOut = new FileOutputStream(fileName);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(orderHistory);
        out.close();
        fileOut.close();
        
        
    }
    
}
