package com.company;

import java.awt.*;

public class MVCShop {

    public static void main(String[] args) {

        ShopModel shopModel = new ShopModel();
        EventQueue.invokeLater(()->{
            LoginController loginController = new LoginController(shopModel);

        });

    }
}
