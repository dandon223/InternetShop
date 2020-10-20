package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * View that is shown after successful login by customer.
 * @author Daniel
 */
public class CustomerListViev extends JFrame {
    private static final int DEFAULT_WIDTH = 600;
    private static final int DEFAULT_HEIGHT = 300;
    private Person activePerson;
    private JTable table;
    private TableModel tableModel ;

    private JTextField idOrder = new JTextField();
    private JTextField howManyOrder = new JTextField();
    private JButton deleteButton = new JButton("delete");
    private JButton backButton = new JButton("back");

    private String[] columnNames = {"id","name","cost","howManyBooked","howManyBought"};
    private Object[][] data;

    /**
     *
     * @param activePerson person currently loged in
     * @param data data of orders made by loged in customer
     */
    public CustomerListViev(Person activePerson, Object[][] data) {
        this.activePerson = activePerson;
        this.data = data;
        this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.setTitle("Your list of products");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setVisible(true);
        ImageIcon icon = new ImageIcon("shop-icon.png");
        this.setIconImage(icon.getImage());
        this.initializeView();
    }
    private void initializeView(){
        tableModel = new TableModel(columnNames,data);
        table = new JTable(tableModel);
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
        southPanel.add(deleteButton);
        southPanel.add(backButton);
        southPanel.add(new JLabel("", SwingConstants.CENTER));
        southPanel.add(new JLabel("", SwingConstants.CENTER));
        southPanel.add(new JLabel("", SwingConstants.CENTER));
        southPanel.add(new JLabel("", SwingConstants.CENTER));
        this.add(southPanel,BorderLayout.SOUTH);
    }

    /**
     *
     * @param backButtonListener add ActionListener for button
     */
    void addBackButtonListener(ActionListener backButtonListener){
        backButton.addActionListener(backButtonListener);
    }
}
