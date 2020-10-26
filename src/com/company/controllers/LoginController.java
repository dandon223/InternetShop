package com.company.controllers;

import com.company.views.LoginView;
import com.company.models.Person;
import com.company.models.ShopModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * controller for loginView, first view after start of an app
 * @author Daniel
 */
public class LoginController {

    /**
     * login view
     */
    private LoginView loginView;
    /**
     * all of data for shop
     */
    private ShopModel shopModel;

    /**
     * constructor
     * @param shopModel all of data for shop
     */
    public LoginController( ShopModel shopModel){
        this.loginView = new LoginView();
        this.shopModel = shopModel;
        this.loginView.addLoginListener(new LoginButtonListener());
    }

    /**
     * ActionListener for login button
     */
    class LoginButtonListener implements ActionListener{
        /**
         *  either CustomerController . StaffController or JOptionPane with warning shows
         * @param actionEvent action event
         */
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            Person person = shopModel.getPerson(loginView.getLoginText(),new String(loginView.getPasswordText()), loginView.getPersonType());
            if(person==null){
                loginView.clearTexts();
                JOptionPane.showMessageDialog(loginView,"Please check your login or password. Thank you!");
                return;
            }
            loginView.setVisible(false);
            if(person.getType().equals("Customer"))
                 new CustomerController(shopModel,person);
            else
                 new StaffController(shopModel,person);
        }
    }
}
