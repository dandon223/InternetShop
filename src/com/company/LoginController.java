package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LoginController {

    private LoginView loginView;
    private ShopModel shopModel;
    private Person activePerson;
    private CustomerController customerController;


    public LoginController( ShopModel shopModel){
        this.loginView = new LoginView();
        this.shopModel = shopModel;
        this.loginView.addLoginListener(new LoginButtonListener());
    }

    class LoginButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            Person person = shopModel.getPerson(loginView.getLoginText(),new String(loginView.getPasswordText()), loginView.getPersonType());
            if(person==null){
                loginView.clearTexts();
                JOptionPane.showMessageDialog(loginView,"Please check your login or password. Thank you!");
                return;
            }
                activePerson = person;
                loginView.setVisible(false);
                customerController = new CustomerController(shopModel,activePerson);
        }
    }
}
