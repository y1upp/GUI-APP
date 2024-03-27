/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.icaguiapplication;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
/**
 * @author joshua
 */
public class ROFC_GUI extends JFrame implements Serializable{
    
    // declaring variables for the GUI components
    private JFrame frame; // JFrame object
    private JPanel leftPanel; //JPanel for the left side
    private JPanel rightPanel; // JPanel for the right side
    private JComboBox<String> woodTypeComboBox; // ComboBox to select the type of wood for furniture
    private JComboBox<String> BaseType = new JComboBox<>(); // ComboBox to select the base type for a table
    private JLabel[] imageLabels = new JLabel[9]; // array of JLabels for displaying furniture images
    private Order order; // order object to store cusmtomer orders
    
    public ROFC_GUI() {
        
        // create objects for OrderHistory and Order
        OrderHistory OrderHistory = new OrderHistory();
        order = new Order();
        
        // create the main JFrame object
        frame = new JFrame("ROFC GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // create a dimension object to use for buttons
        Dimension LeftbuttonSize = new Dimension (120,100);
        
        // create the top panel for the company banner
        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.RED);
        topPanel.setPreferredSize(new Dimension(800, 100));
        topPanel.setLayout(new GridLayout(1, 3, 10, 10));
        
        // Create the right panel for images
        rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(600, 600));
        rightPanel.setLayout(new BorderLayout());
        
        // add 9 JLabels with images to rightPanel here
        JPanel imagePanel = new JPanel(new GridLayout(3, 3, 5, 5));
        imagePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        for (int i = 1; i <= 9; i++) {
            JPanel cell= new JPanel();
            cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            imagePanel.add(cell);
            JLabel imageLabel = new JLabel();
            cell.add(imageLabel);
            imageLabels[i-1] = imageLabel;
        }
        // adding the imagePanel to the rightPanel
        rightPanel.add(imagePanel, BorderLayout.CENTER);
        
        // Create the left panel for buttons
        JButton AddChair = new JButton("Add Chair");
        JButton AddTable = new JButton("Add Table");
        JButton AddDesk = new JButton("Add Desk");
        JButton ClearAll = new JButton("Clear All");
        JButton Summary = new JButton("Summary");
        JButton TotalPrice = new JButton("Total Price");
        JButton PlaceOrder = new JButton("Place Order");
        JButton ReviewOrder = new JButton("Review Order");
        
        // setting thepreffered size for theleft panel JButtons
        AddChair.setPreferredSize(LeftbuttonSize);
        AddTable.setPreferredSize(LeftbuttonSize);
        AddDesk.setPreferredSize(LeftbuttonSize);
        ClearAll.setPreferredSize(LeftbuttonSize);
        Summary.setPreferredSize(LeftbuttonSize);
        TotalPrice.setPreferredSize(LeftbuttonSize);
        PlaceOrder.setPreferredSize(LeftbuttonSize);
        ReviewOrder.setPreferredSize(LeftbuttonSize);
        
        // creating the left panel
        leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(120, 200));
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        
        AtomicInteger idNum = new AtomicInteger(1);
        
        // Chair Action listener
        AddChair.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                // show a box to customise the chair
                JPanel panel = new JPanel(new GridLayout(0,1));
                
                //create a JCheckBox for adding armrests to the chair
                JCheckBox armrestsCheckBox = new JCheckBox("Armrests (250 units of wood)");
                
                // create a JComboBox for selecting the type of wood for the chair
                woodTypeComboBox = new JComboBox<>();
                woodTypeComboBox.addItem("Oak");
                woodTypeComboBox.addItem("Walnut");
                        
                // add the components to the panel
                panel.add(new JLabel("Customize Chair: "));
                panel.add(armrestsCheckBox);
                panel.add(new JLabel("Select wood type: "));
                panel.add(woodTypeComboBox);
                
                // add a JTextField for entering the quantity of chairs
                JTextField quantityTextField = new JTextField();
                panel.add(new JLabel("Enter quantity: "));
                panel.add(quantityTextField);
                
                // add a JLabel for displaying the chair image
                JLabel chairImageLabel = new JLabel();
                panel.add(chairImageLabel);
                
                // display the panel as a dialog box and wait for the users response
                int result = JOptionPane.showConfirmDialog(null, panel, "Customize Chair",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                
                // if the user clicked OK, create a new chair object with the selected options
                if (result == JOptionPane.OK_OPTION) {
                    boolean hasArmrests = armrestsCheckBox.isSelected();
                    String woodType = (String) woodTypeComboBox.getItemAt(woodTypeComboBox.getSelectedIndex());
                    int Quantity = Integer.parseInt(quantityTextField.getText());
                    
                    ChairClass chair = new ChairClass(idNum.getAndIncrement(), woodType.charAt(0), Quantity,hasArmrests, null);
                    
                    // add the new chair to the order and  update the total price
                    boolean added = order.addItem(chair);
                    
                    if(added) {
                        JOptionPane.showMessageDialog(null, "New Chair add with ID code " + (idNum.get()-1));
                        double chairCost = chair.getItemPrice();
                        order.setTotalPrice(order.getTotalPrice()+ chairCost);
                    } else {
                        JOptionPane.showMessageDialog(null, "Order is full! Cannot add more items! ");
                    }
                    
                }
            } 
        });
       
        // Table Action listener --------------------
        AddTable.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
                // show a box to customise the Table
                JPanel panel = new JPanel(new GridLayout(0,1));
                
                // create a JComboBox for selecting the type of wood for the chair
                woodTypeComboBox = new JComboBox<>();
                woodTypeComboBox.addItem("Oak");
                woodTypeComboBox.addItem("Walnut");
                
                // create a JComboBox for the base type of the table
                BaseType = new JComboBox<>();
                BaseType.addItem("Wooden base");
                BaseType.addItem("Chrome base");
                        
                // add the components to the panel
                panel.add(new JLabel("Customize table: "));
                panel.add(new JLabel("Select wood type: "));
                panel.add(woodTypeComboBox);
                panel.add(BaseType);
                
                // adding a text field to allow user to input a diameter
                JTextField DiameterRequest = new JTextField();
                panel.add(new JLabel("Enter Diameter(50cm or more): "));
                panel.add(DiameterRequest);

                // adding a text field to allow user to enter quantity
                JTextField quantityTextField = new JTextField();
                panel.add(new JLabel("Enter quantity: "));
                panel.add(quantityTextField);
                
                // adding a JLabel for displaying an image
                JLabel TableImage = new JLabel();
                panel.add(TableImage);
                
                // display the panel as a dialog box and wait for the users response
                int result = JOptionPane.showConfirmDialog(null, panel, "Customize Table",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                
                // if the user clicked OK, create a new table object with the selected options
                if (result == JOptionPane.OK_OPTION) {
                    
                    // get the values from the user
                    String woodType = (String) woodTypeComboBox.getItemAt(woodTypeComboBox.getSelectedIndex());
                    int Quantity = Integer.parseInt(quantityTextField.getText());
                    int diameter = Integer.parseInt(DiameterRequest.getText());
                    String baseType = (String) BaseType.getItemAt(BaseType.getSelectedIndex());
                    
                    // validate the diameter is at least 50cm
                    if (diameter < 50) {
                        JOptionPane.showMessageDialog(null, "Diameter must be at least 50cm. ");
                        return; // exit the function if the diameter is invalid
                    }
                    
                    // create a new table object with the selected options
                    TableClass Table = new TableClass(idNum.getAndIncrement(), woodType.charAt(0), Quantity, diameter, baseType);
                    
                    // add the table to the stored objects in order
                    boolean added = order.addItem(Table);
                    
                    // give the user the confirmation that a table was added
                    if(added) {
                       JOptionPane.showMessageDialog(null, "New Table add with ID code " + (idNum.get()-1)); 
                    } else {
                        JOptionPane.showMessageDialog(null, "Could not add table to order");
                    }
                    
                }
            }
        });
        
        //Desk Action listener --------------------
        AddDesk.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
                // show a box to customise the Desk
                JPanel panel = new JPanel(new GridLayout(0,1));
                
                // create a combo box for selecting wood type
                woodTypeComboBox = new JComboBox<>();
                woodTypeComboBox.addItem("Oak");
                woodTypeComboBox.addItem("Walnut");
                
                // create text fields for entering the  desk width, depth, number of drawers and quantity
                JTextField WidthRequest = new JTextField();
                JTextField DepthRequest = new JTextField();
                JTextField NumOfDrawers = new JTextField();
                JTextField quantityTextField = new JTextField();
                
                // add the labels and input fields to the panel
                panel.add(new JLabel("Customize Desk: "));
                panel.add(new JLabel("Select wood type: "));
                panel.add(woodTypeComboBox);
                panel.add(new JLabel("Enter Width in cm: "));
                panel.add(WidthRequest);
                panel.add(new JLabel("Enter Depth in cm: "));
                panel.add(DepthRequest);
                panel.add(new JLabel("Enter Number of drawers (max 4): "));
                panel.add(NumOfDrawers);
                panel.add(new JLabel("Enter quantity: "));
                panel.add(quantityTextField);
                
                // display the panel in a dialog box
                int result = JOptionPane.showConfirmDialog(null, panel, "Customize Desk",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                
                // if the user clicks ok, creates a new desk object with the specificed attributes and adds it to the order
                if (result == JOptionPane.OK_OPTION) {
                    
                    String woodType = (String) woodTypeComboBox.getItemAt(woodTypeComboBox.getSelectedIndex());
                    int quantity = Integer.parseInt(quantityTextField.getText());
                    int width= Integer.parseInt(WidthRequest.getText());
                    int depth = Integer.parseInt(DepthRequest.getText());
                    int numOfDrawers = Integer.parseInt(NumOfDrawers.getText());
                    
                    // if the user entered an invalid number of drawers, displays an error messageand returns
                    if (numOfDrawers > 4) {
                        JOptionPane.showMessageDialog(null, "Maximum number of drawers is 4");
                        return;
                    }
                    
                    // create a new deskclass object with the specified attributes
                    DeskClass Desk = new DeskClass(idNum.getAndIncrement(), woodType.charAt(0), quantity, 0, width, depth, numOfDrawers);
                    boolean added = order.addItem(Desk);
                    
                    // display a success message with ID code
                    JOptionPane.showMessageDialog(null, "New Desk add with ID code " + (idNum.get()-1));
                }
            }
        });
        
        // Summary Action listener --------------------
        Summary.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                
                // retrieve all items in the current order
                List<FurnitureClass> items = order.getItems();
                
                // create a stringBuilder object to store the order summary text
                StringBuilder summaryText = new StringBuilder();
                
                // add a summary text
                summaryText.append("Order Summary:\n\n");
                
                // iterate througheach item in the order
                for (FurnitureClass item : items) {
                    
                    // if the item is a chair, add its details to the summary text
                    if (item instanceof ChairClass) {
                        ChairClass chair = (ChairClass) item;
                        summaryText.append("Chair - ");
                        summaryText.append("ID: " + chair.getIdNum() + ", ");
                        summaryText.append("Wood Type: " + chair.getTypeOfWood() + ", ");
                        summaryText.append("Quantity: " + chair.getQuantity() + ", ");
                        summaryText.append("Has Armrests: " + chair.hasArmRests() + ", ");
                        summaryText.append("Price: £" + chair.getItemPrice() + "\n");
                        
                        // if  the item is a table, add its details to the summary text
                    } else if (item instanceof TableClass) {
                        TableClass table = (TableClass) item;
                        summaryText.append("Table - ");
                        summaryText.append(table.toString());
                        summaryText.append("\n");
                        
                        // if the item is a desk, add its details to the summary text
                    } else if (item instanceof DeskClass){
                        DeskClass desk = (DeskClass) item;
                        summaryText.append("desk - ");
                        summaryText.append("ID: " + desk.getIdNum() + ", ");
                        summaryText.append("Wood Type: " + desk.getTypeOfWood() + ", ");
                        summaryText.append("Quantity: " + desk.getQuantity() + ", ");
                        summaryText.append("Price: £" + desk.getItemPrice() + "\n");
                    }
                }
                
                // add the total price of the order to the summary text
                double totalPrice = order.getTotalPrice();
                summaryText.append(String.format("\nTotal Price: £%.2f", totalPrice));
                
                //  display the order summary in a JOptionPane message dialog
                JOptionPane.showMessageDialog(null, summaryText.toString(), "Order summary", JOptionPane.PLAIN_MESSAGE);
            }
        });
        
        // clear all action listener
        ClearAll.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e){
            
            // confirm is a JOptionPane that will show a message asking user if they are sure they want to delete all items
            int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to clear order?");
            
            // if option == yes
            if (confirm == JOptionPane.YES_OPTION){
                
                // the method is called and all items from order is cleared
                order.clearALLItems();
            }
        }
    });
        
        // Total Price Acion listener --------------------
        TotalPrice.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e){
           
            // total price is a variable assigned to the method call .getTotalPrice of the order
            double totalPrice = order.getTotalPrice();
            
            // display the total price
            JOptionPane.showMessageDialog(null, "The total price is £"+totalPrice);
        }
    });
        
        // Place Order Action listener --------------------
        PlaceOrder.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e){
            
            // get the total price for the order via the method call
            double totalPrice = order.getTotalPrice();
            
            // display a confirmation to the customer
            int result = JOptionPane.showConfirmDialog(null, "are you sure you want to place the order? The total price is $" + totalPrice,
                    "Place Order", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            
            int orderID = 0;
            
            // if the user confirms the order, we generate a random order ID and create a new order
            if (result == JOptionPane.YES_OPTION) {
                orderID = (int) (Math.random()*1000000);
                
                // get a list of current items from the cleared order
                List<FurnitureClass> items = order.getItems();
                
                // get the total price of the cleared order 
                double orderTotalPrice = order.getTotalPrice();
                
                // create a new order object and set its items and total price 
                Order order = new Order();
                order.setItems(items);
                order.setTotalPrice(orderTotalPrice);
                
                // create order historyobject
                OrderHistory orderHistory;
                
                // read from the orders.ser file 
                try {
                    FileInputStream fileIn = new FileInputStream("orders.ser");
                    ObjectInputStream in = new ObjectInputStream(fileIn);
                    orderHistory = (OrderHistory) in.readObject();
                    in.close();
                    fileIn.close();
                } catch (IOException i) {
                    orderHistory = new OrderHistory();
                } catch (ClassNotFoundException c) {
                System.out.println("OrderHistory class not found");
                return;
            }
            
                // add the new order to the order history
            orderHistory.addOrder(order);
            
            // write the updated order history to the orders.ser file
            try {
                FileOutputStream fileOut = new FileOutputStream("orders.ser");
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(orderHistory);
                out.close();
                fileOut.close();
            } catch (IOException i) {
                i.printStackTrace();
            }
            
            // clear all items from the order
            order.clearALLItems();
            
            // display a message to the user with the order ID
            JOptionPane.showMessageDialog(null,"Order placed with ID code " + orderID);
        }else if (result == JOptionPane.NO_OPTION) {
            
            // user may cancel the order
            JOptionPane.showMessageDialog(null, "Order placement cancelled. ");
        }
        
        }
        
        });
        
        // Review Order Action Listener --------------------
        ReviewOrder.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e){
        JFrame reviewFrame = new JFrame("Review Orders");
        JTextArea orderArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(orderArea);
        
        // Set the size of the review order window
        reviewFrame.setSize(500, 500);
        reviewFrame.setLayout(new BorderLayout());
        reviewFrame.add(scrollPane, BorderLayout.CENTER);
        
        // Deserialize the orders and display them in the JTextArea
        try {
            FileInputStream fileIn = new FileInputStream("orders.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            OrderHistory orderHistory = (OrderHistory) in.readObject();
            in.close();
            fileIn.close();
            
            List<Order> orders = orderHistory.getOrderList();
            
            for (Order order : orders) {
                double totalPrice = order.getTotalPrice();
                orderArea.append("Order ID: " + order.getId() + "\n");
                List<FurnitureClass> items = order.getItems();
                for (FurnitureClass item : items) {
                    orderArea.append("Item: " + item.getItemPrice()+ " - $" + item.getItemPrice() + "\n");
                }
                orderArea.append("Total Price: $" + totalPrice + "\n\n");
            }
        } catch (IOException i) {i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("OrderHistory class not found");
            return;
        }
        
        // Make the review order window visible
        reviewFrame.setVisible(true);
        
        }
        });
        
        // adding all buttons to  the left panel
        leftPanel.add(Box.createRigidArea(new Dimension(20, 75)));
        leftPanel.add(AddChair);
        
        leftPanel.add(Box.createRigidArea(new Dimension(20, 75)));
        leftPanel.add(AddTable);
        
        leftPanel.add(Box.createRigidArea(new Dimension(20, 75)));
        leftPanel.add(AddDesk);
        
        leftPanel.add(Box.createRigidArea(new Dimension(20, 75)));
        leftPanel.add(Summary);
        
        leftPanel.add(Box.createRigidArea(new Dimension(20, 75)));
        leftPanel.add(ClearAll);
        
        leftPanel.add(Box.createRigidArea(new Dimension(20, 75)));
        leftPanel.add(TotalPrice);
        
        leftPanel.add(Box.createRigidArea(new Dimension(20, 75)));
        leftPanel.add(PlaceOrder);
        
        leftPanel.add(Box.createRigidArea(new Dimension(20, 75)));
        leftPanel.add(ReviewOrder);
        
        // Add all panels to the frame
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(topPanel, BorderLayout.NORTH);
        frame.getContentPane().add(leftPanel, BorderLayout.WEST);
        frame.getContentPane().add(rightPanel, BorderLayout.CENTER);
        
        // pack the frame to fir the preffered size of its components
        frame.pack();
        
        //make the frame visible
        frame.setVisible(true);
   
    }
    
    
}
