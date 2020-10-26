package com.company.views;

import com.company.models.Person;
import com.company.models.TableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class StaffView extends JFrame {
    private static final int DEFAULT_WIDTH = 700;
    private static final int DEFAULT_HEIGHT = 400;
    private Person activePerson;
    private JTable table;
    private TableModel tableModel ;

    private JTextField idOrder = new JTextField();
    private JTextField howMany = new JTextField();
    private JButton toBuyButton = new JButton("to buy");
    private JButton toBookButton = new JButton("to book");
    private JButton logOffButton = new JButton("log off");
    String[] columnNames = {"id","person name, id","item name","how many booked","how many bought"};
    private Object[][] data;

    public StaffView(Person activePerson, Object[][] data) {
        this.activePerson = activePerson;
        this.data = data;
        this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.setTitle("Welcome "+activePerson.getFirstName()+ " "+activePerson.getLastName());
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon("shop-icon.png");
        this.setIconImage(icon.getImage());
        this.initializeView();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    private void initializeView() {
        tableModel = new TableModel(columnNames, data);
        table = new JTable(tableModel);
        table.setPreferredScrollableViewportSize(new Dimension(600, 150));
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);
        this.add(scrollPane, BorderLayout.NORTH);

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new GridLayout(2, 5));
        southPanel.add(new JLabel("What: ", SwingConstants.CENTER));
        southPanel.add(idOrder);

        southPanel.add(new JLabel("How many: ", SwingConstants.CENTER));
        southPanel.add(howMany);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2,1));
        panel.add(toBuyButton);
        panel.add(toBookButton);

        southPanel.add(panel);
        southPanel.add(logOffButton);
        southPanel.add(new JLabel("", SwingConstants.CENTER));
        southPanel.add(new JLabel("", SwingConstants.CENTER));
        southPanel.add(new JLabel("", SwingConstants.CENTER));
        southPanel.add(new JLabel("", SwingConstants.CENTER));
        this.add(southPanel, BorderLayout.SOUTH);
    }
    /**
     * @param toBuyActionListener button listener
     */
    public void setToBuyButtonListener(ActionListener toBuyActionListener){
        toBuyButton.addActionListener(toBuyActionListener);
    }
    /**
     * @param logOffButtonListener button listener
     */
    public void setLogOffButtonListener(ActionListener logOffButtonListener){
        logOffButton.addActionListener(logOffButtonListener);
    }
    /**
     * @param toBookActionListener button listener
     */
    public void setToBookButtonListener(ActionListener toBookActionListener){
        toBookButton.addActionListener(toBookActionListener);
    }

    /**
     * getter
     * @return id order
     */
    public String getIdOrder() {
        return idOrder.getText();
    }
    /**
     * getter
     * @return how many things youwish to do things with
     */
    public String getHowMany() {
        return howMany.getText();
    }
    /**
     * getter
     * @return table model
     */
    public TableModel getTableModel() {
        return tableModel;
    }
}
