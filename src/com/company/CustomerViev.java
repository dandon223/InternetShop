package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 *  view that is shown after successful login by customer
 *  @author Daniel
 */
public class CustomerViev extends JFrame {
    private static final int DEFAULT_WIDTH = 600;
    private static final int DEFAULT_HEIGHT = 300;
    private Person activePerson;
    private JTable table;
    private TableModel tableModel ;

    private JTextField idOrder = new JTextField();
    private JTextField howManyOrder = new JTextField();
    private JButton buyButton = new JButton("buy");
    private JButton exitButton = new JButton("exit");
    private JButton listButton = new JButton("List");
    String[] columnNames = {"id","name","cost","how many left"};
    private Object[][] data;

    /**
     * getter
     * @return id of order as a string
     */
    public String getIdOrder() {
        return idOrder.getText();
    }

    /**
     * getter
     * @return  how many of the thing was orderd as a string
     */
    public String getHowManyOrder(){
        return howManyOrder.getText();
    }

    /**
     * getter
     * @return TableModel which extends AbstractTableModel
     */
    public TableModel getTableModel() {
        return tableModel;
    }

    /**
     *
     * @param activePerson person currently loged in
     * @param data data of items present in the shop
     */
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
        southPanel.add(buyButton);
        southPanel.add(listButton);
        southPanel.add(new JLabel("", SwingConstants.CENTER));
        southPanel.add(new JLabel("", SwingConstants.CENTER));
        southPanel.add(new JLabel("", SwingConstants.CENTER));
        southPanel.add(new JLabel("", SwingConstants.CENTER));
        this.add(southPanel,BorderLayout.SOUTH);
    }
    void addBuyButtonListener(ActionListener buyButtonListener ){
        buyButton.addActionListener(buyButtonListener);
    }
    void addListButtonListener(ActionListener listButtonListener){
        listButton.addActionListener(listButtonListener);
    }
}
