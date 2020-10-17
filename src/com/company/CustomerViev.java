package com.company;

import javax.swing.*;
import java.awt.*;

public class CustomerViev extends JFrame {
    private static final int DEFAULT_WIDTH = 600;
    private static final int DEFAULT_HEIGHT = 300;
    private Person activePerson;
    private JTable table;

    private JTextField idOrder = new JTextField();
    private JTextField howManyOrder = new JTextField();
    private JButton buyButton = new JButton("buy");
    private JButton exitButton = new JButton("exit");

    public CustomerViev(Person activePerson) {
        this.activePerson = activePerson;
        this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.setTitle("Welcome "+activePerson.getFirstName()+ " "+activePerson.getLastName());
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        ImageIcon icon = new ImageIcon("shop-icon.png");
        this.setIconImage(icon.getImage());
        this.initializeView();
    }
    void initializeView(){
        String[] columnNames = {"id","name","cost","total","booked"};
        Object[][] data = {
                {"1","TV","1000","10","0"},
                {"2","Adidas","667","5","4"},
                {"3","Banana","5","100","21"},
                {"4","Pizza","29","54","45"},
        };
        table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(400,50));
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);
        this.add(scrollPane,BorderLayout.NORTH);

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new GridLayout(1, 6));
        southPanel.add(new JLabel("What: ", SwingConstants.CENTER));
        southPanel.add(idOrder);

        southPanel.add(new JLabel("How many: ", SwingConstants.CENTER));
        southPanel.add(howManyOrder);
        southPanel.add(buyButton);
        southPanel.add(exitButton);
        this.add(southPanel,BorderLayout.SOUTH);
    }
}
