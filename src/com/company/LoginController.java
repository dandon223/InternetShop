package com.company;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {

    private LoginView loginView;
    private ShopModel shopModel;
    private Person activePerson;
    private CustomerController customerController;

    public LoginController(LoginView loginView, ShopModel shopModel, CustomerController customerController){
        this.loginView = loginView;
        this.shopModel = shopModel;
        this.customerController = customerController;
        this.loginView.addLoginListener(new LoginButtonListener());
    }

    class LoginButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            Person person = shopModel.getPerson(loginView.getLoginText(),new String(loginView.getPasswordText()), loginView.getPersonType());
            if(person!=null){
                activePerson = person;
                loginView.setVisible(false);
                customerController.setActivePerson(activePerson);
                customerController.setCustomerViev(new CustomerViev(activePerson));
            }
        }
    }
}
