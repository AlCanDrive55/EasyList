/**
 * Created by alanflood on 18/04/15.
 */


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

import java.text.*;

public class ShoppingFrame extends JFrame {
    private JTextArea cartBox; //The text area that gets updated when items are added, edited and removed
    private JLabel totalPrice; //A JLabel that updates the total price of the items in the array list
    private JLabel totalQuantity; //A JLabel that updates the total quantity of the items in the array list
    private JButton addItem; //The button to add items to the list
    private JButton removeItem; //The button to removes items from the list
    private JButton editItem; //The button that allows the user to edit items on the list
    ArrayList<product> shoppingList = new ArrayList<product>(); //Creates a product array called shoppingList
    private DecimalFormat df = new DecimalFormat("#.##"); //To format the variable price to display as €X.XX

    private double totalP()//Used to find the total price of the products times the users quantity for each one
    {
        double sum = 0;
        int i;
        for (i = 0; i < shoppingList.size(); i++) {
            sum += (shoppingList.get(i).getPrice() * shoppingList.get(i).getQuantity());
        }
        return sum;
    }

    private int totalQ()//Adds all the quantity values together
    {
        int sum = 0;
        int i;
        for (i = 0; i < shoppingList.size(); i++) {
            sum += shoppingList.get(i).getQuantity();
        }
        return sum;
    }


    public ShoppingFrame() {
        super("Shopping List");

        BorderLayout layout = new BorderLayout();//ShoppingFrame uses borderlayout to contain the two inside JPanels
        setLayout(layout);

        JPanel listButtons = new JPanel();
        listButtons.setLayout(new GridBagLayout());//ListButtons uses gridbag layout
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        listButtons.setPreferredSize(new Dimension(270, 100));


        JPanel mainPanel = new JPanel();//The list panel only holds one item so borderlayout is used
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createTitledBorder("Product  -  Price  -  Quantity"));

        //The text area that gets updated with the list
        cartBox =
                new JTextArea();
        cartBox.setPreferredSize(new Dimension(270, 580));
        cartBox.setEditable(false);
        mainPanel.add(cartBox);

        //JLabel shows the total price of the list
        totalPrice =
                new JLabel();
        c.weightx = 0.50;
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        listButtons.add(totalPrice, c);

        //JLabel shows the total quantity of the list
        totalQuantity =
                new JLabel();
        c.weightx = 0.50;
        c.gridx = 1;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        listButtons.add(totalQuantity, c);

        //Button to add item to list
        addItem =
                new JButton("Add Item to List");
        addItem.setPreferredSize(new Dimension(200, 20));
        c.weightx = 0.50;
        c.gridx = 0;
        c.gridy = 2;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.insets = new Insets(0 ,5, 0, 0); //Adds padding
        addItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Onclick create a new window
                JTextField productField = new JTextField(10);
                JTextField priceField = new JTextField(4);
                JTextField quantityField = new JTextField(4);

                JPanel addPanel = new JPanel();
                addPanel.setLayout(new FlowLayout());
                addPanel.add(new JLabel("Product Name:"));
                addPanel.add(productField);
                addPanel.add(Box.createHorizontalStrut(15)); //Space between the name and value and quantity text fields.
                addPanel.add(new JLabel("Price:"));
                addPanel.add(priceField);
                addPanel.add(new JLabel("Quantity:"));
                addPanel.add(quantityField);
                addPanel.setVisible(true);

                int result = JOptionPane.showConfirmDialog(null, addPanel,
                        "Please enter a name, price and quantity", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) { //If ok is clicked do this

                    String name = productField.getText();
                    double price = Double.parseDouble(priceField.getText()); //Get a double from the string in priceField
                    int quantity = Integer.parseInt(quantityField.getText()); //Get an Int from the string in quantityField

                    String s = "Added the item " + name + " which is €" + df.format(price) + "\nThe quantity of " + name + " needed is " + quantity;

                    JOptionPane.showMessageDialog(null, s, "Add Item", JOptionPane.PLAIN_MESSAGE); //Acknowledgement message
                    shoppingList.add(new product(name, price, quantity));
                    JOptionPane.showMessageDialog(null, "Item added successfully", "Add Item", JOptionPane.PLAIN_MESSAGE);
                    cartBox.setText(""); //The text area resets itself to nothing
                    for (int i = 0; i < shoppingList.size(); i++) { //Then updates the text for every value in the array
                        cartBox.append(shoppingList.get(i).getName());
                        cartBox.append(" - €");
                        cartBox.append(String.valueOf(df.format(shoppingList.get(i).getPrice())));
                        cartBox.append(" - ");
                        cartBox.append(String.valueOf(shoppingList.get(i).getQuantity()));
                        cartBox.append("\n");
                        totalPrice.setText("Total Price: €" +String.valueOf(df.format(totalP())));
                        totalQuantity.setText("Total Quantity of Items: " + String.valueOf(totalQ()));
                    }
                }
            }
        });
        listButtons.add(addItem, c);

        //Button to remove item from list
        removeItem =
                new JButton("Remove Item from List");
        removeItem.setPreferredSize(new Dimension(200, 20));
        c.weightx = 0.50;
        c.gridx = 1;
        c.gridy = 2;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.insets = new Insets(0 ,5, 0,5);
        removeItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (shoppingList.size() > 0) { //An error will occur if there's no items to remove


                    JComboBox comboBox = new JComboBox();
                    for (int i = 0; i < shoppingList.size(); i++) { //The arraylists name values are added to a combobox
                        String p = shoppingList.get(i).getName();
                        comboBox.addItem(p);
                    }
                    JPanel remPanel = new JPanel();
                    remPanel.setLayout(new FlowLayout());
                    remPanel.add(new JLabel("Select an item to remove:"));
                    remPanel.add(comboBox);
                    remPanel.setVisible(true);
                    int result = JOptionPane.showConfirmDialog(null, remPanel,
                            "Select an item to edit", JOptionPane.OK_CANCEL_OPTION);
                    if (result == JOptionPane.OK_OPTION) {


                        for (int i = 0; i < shoppingList.size(); i++) {
                            if (shoppingList.get(i).getName().equals(comboBox.getSelectedItem()
                                    .toString())) { //The value selected in the combobox is found in the arraylist and then removed using it's index
                                shoppingList.remove(i);
                                comboBox.removeAllItems();
                                String s = "The item has been removed from your list";
                                JOptionPane.showMessageDialog(null, s, "Item Removed", JOptionPane.PLAIN_MESSAGE);
                                cartBox.setText(""); //The text area is reset
                                for (int j = 0; j < shoppingList.size(); j++) {
                                    cartBox.append(shoppingList.get(j).getName());
                                    cartBox.append(" - €");
                                    cartBox.append(String.valueOf(df.format(shoppingList.get(j).getPrice())));
                                    cartBox.append(" - ");
                                    cartBox.append(String.valueOf(shoppingList.get(j).getQuantity()));
                                    cartBox.append("\n");
                                }
                                if (shoppingList.size() < 1) {
                                    totalPrice.setText("");
                                    totalQuantity.setText("");
                                } else {
                                    totalPrice.setText("Total Price: €" + String.valueOf(df.format(totalP())));
                                    totalQuantity.setText("Total Quantity of Items: " + String.valueOf(totalQ()));
                                }
                            }

                        }
                    }
                }
                else {
                    Object[] options = {"OK"};
                    JOptionPane.showOptionDialog(null, "There's no items on your list to remove!", "Error", JOptionPane.PLAIN_MESSAGE, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                }
            }
        });
        listButtons.add(removeItem, c);

        //Button to edit item from list
        editItem =
                new JButton("Edit Item");
        editItem.setPreferredSize(new Dimension(200, 20));
        c.weightx = 0.50;
        c.gridx = 0;
        c.gridy = 3;
        c.gridheight = 1;
        c.gridwidth = 2;
        c.insets = new Insets(10,0,4,0);
        editItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(shoppingList.size() > 0){ //An error will occur if there's no items to edit

                    JComboBox comboBox = new JComboBox();
                    for (int i = 0; i < shoppingList.size(); i++) {
                        String p = shoppingList.get(i).getName();
                        comboBox.addItem(p);
                    }
                    JTextField productField = new JTextField(10);
                    JTextField priceField = new JTextField(5);
                    JTextField quantityField = new JTextField(5);

                    JPanel editPanel = new JPanel();
                    editPanel.setLayout(new FlowLayout());
                    editPanel.add(new JLabel("Select an item to edit:"));
                    editPanel.add(comboBox);

                    editPanel.setLayout(new FlowLayout());
                    editPanel.add(new JLabel("Product Name:"));
                    editPanel.add(productField);
                    editPanel.add(Box.createHorizontalStrut(15));
                    editPanel.add(new JLabel("Price:"));
                    editPanel.add(priceField);
                    editPanel.add(new JLabel("Quantity:"));
                    editPanel.add(quantityField);
                    editPanel.setVisible(true);

                    int result = JOptionPane.showConfirmDialog(null, editPanel,
                            "Item Editor", JOptionPane.OK_CANCEL_OPTION);

                    if (result == JOptionPane.OK_OPTION) {
                        String name = productField.getText();
                        double price = Double.parseDouble(priceField.getText());
                        int quantity = Integer.parseInt(quantityField.getText());
                        for (int i = 0; i < shoppingList.size(); i++) {
                            if (shoppingList.get(i).getName().equals(comboBox.getSelectedItem().toString())) { //The value selected in the combobox is found in the arraylist
                                shoppingList.set(i, new product(name, price, quantity)); //The values selected are then changed to the users input
                                comboBox.removeAllItems();
                                String s = "Item has been edit successfully"; //Acknowledgement window
                                JOptionPane.showMessageDialog(null, s, "Item edited", JOptionPane.PLAIN_MESSAGE);
                                cartBox.setText("");
                                for (int j = 0; j < shoppingList.size(); j++) {
                                    cartBox.append(shoppingList.get(j).getName());
                                    cartBox.append(" - €");
                                    cartBox.append(String.valueOf(df.format(shoppingList.get(j).getPrice())));
                                    cartBox.append(" - ");
                                    cartBox.append(String.valueOf(shoppingList.get(j).getQuantity()));
                                    cartBox.append("\n");
                                    totalPrice.setText("Total Price: €" + String.valueOf(df.format(totalP())));
                                    totalQuantity.setText("Total Quantity of Items: " + String.valueOf(totalQ()));
                                }
                            }
                        }

                    }
                }
                else {
                    Object[] options = {"OK"};
                    JOptionPane.showOptionDialog(null, "There's no items on your list to edit!", "Error", JOptionPane.PLAIN_MESSAGE, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                }
            }
        });
        listButtons.add(editItem, c);


        add(listButtons, BorderLayout.SOUTH); //Add the two panels to the main panel shoppingFrame
        add(mainPanel, BorderLayout.NORTH);
    }
}

