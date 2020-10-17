package com.company;

import java.awt.*;

public class MVCShop {

    public static void main(String[] args) {

        ShopModel shopModel = new ShopModel();
        EventQueue.invokeLater(()->{
            LoginView loginView = new LoginView();
            CustomerController customerController = new CustomerController(shopModel);
            LoginController loginController = new LoginController(loginView,shopModel,customerController);

        });

    }
}
