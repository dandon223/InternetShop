package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


class LoginView extends JFrame {
    private static final int DEFAULT_WIDTH = 500;
    private static final int DEFAULT_HEIGHT = 400;

    private JTextField loginField = new JTextField();
    private JPasswordField passwordField = new JPasswordField();
    private JRadioButton customerButton = new JRadioButton("Customer", true);
    private JRadioButton staffButton = new JRadioButton("Staff", false);
    private JButton loginButton = new JButton("login");
    private JButton registerButton = new JButton("register");
    private ButtonGroup group = new ButtonGroup();

    public LoginView() {
        this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.setTitle("Internet Shop");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        ImageIcon icon = new ImageIcon("shop-icon.png");
        this.setIconImage(icon.getImage());
        this.initializeView();
    }

    String getLoginText(){
        return loginField.getText();
    }

    char[] getPasswordText(){
        return passwordField.getPassword();
    }
    String getPersonType()
    {
        if(staffButton.isSelected())
            return "Staff";
        else
            return "Customer";
    }
    void addLoginListener(ActionListener LoginButtonListener){
        loginButton.addActionListener(LoginButtonListener);
    }

    void initializeView() {

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new GridLayout(4, 2));
        southPanel.add(new JLabel("Login: ", SwingConstants.CENTER));
        southPanel.add(loginField);

        southPanel.add(new JLabel("Password: ", SwingConstants.CENTER));
        southPanel.add(passwordField);

        southPanel.add(new JLabel("Status:", SwingConstants.CENTER));

        JPanel buttonPanel = new JPanel();

        group.add(customerButton);
        buttonPanel.add(customerButton);

        group.add(staffButton);
        buttonPanel.add(staffButton);

        southPanel.add(buttonPanel);

        southPanel.add(loginButton);

        southPanel.add(registerButton);

        this.add(southPanel, BorderLayout.SOUTH);
        JLabel label = new JLabel();
        label.setIcon(new ImageIcon("shop-icon.png"));
        this.add(label,BorderLayout.CENTER);
    }
    public void clearTexts(){
        loginField.setText("");
        passwordField.setText("");
    }
}
