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
    private Object[][] data;

    public CustomerViev(Person activePerson, Object[][] data) {
        this.activePerson = activePerson;
        this.data = data;
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

        table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(400,150));
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);
        this.add(scrollPane,BorderLayout.NORTH);

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new GridLayout(2, 5));
        southPanel.add(new JLabel("What: ", SwingConstants.CENTER));
        southPanel.add(idOrder);

        southPanel.add(new JLabel("How many: ", SwingConstants.CENTER));
        southPanel.add(howManyOrder);
        southPanel.add(buyButton);
        southPanel.add(exitButton);
        southPanel.add(new JLabel("", SwingConstants.CENTER));
        southPanel.add(new JLabel("", SwingConstants.CENTER));
        southPanel.add(new JLabel("", SwingConstants.CENTER));
        southPanel.add(new JLabel("", SwingConstants.CENTER));
        this.add(southPanel,BorderLayout.SOUTH);
    }
}
