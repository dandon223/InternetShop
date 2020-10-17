package com.company;

import javax.swing.*;

public class CustomerViev extends JFrame {
    private static final int DEFAULT_WIDTH = 500;
    private static final int DEFAULT_HEIGHT = 400;
    private Person activePerson;

    public CustomerViev(Person activePerson) {
        this.activePerson = activePerson;
        this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.setTitle("Internet Shop");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        ImageIcon icon = new ImageIcon("shop-icon.png");
        this.setIconImage(icon.getImage());
        this.initializeView();
    }
    void initializeView(){
        //JLabel label = new JLabel();
        //label.setText("Hello "+activePerson.getFirstName()+" "+activePerson.getLastName());
        //this.add(label);
    }
}
