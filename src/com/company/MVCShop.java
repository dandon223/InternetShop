package com.company;

import com.company.controllers.LoginController;
import com.company.models.ShopModel;
import com.company.views.LoginView;

import java.awt.*;

/**
 * starter class with main method
 * @author Daniel
 */
public class MVCShop {
    /**
     * main method for starting an app
     * @param args not needed
     */
    public static void main(String[] args) {

        ShopModel shopModel = new ShopModel();;
        EventQueue.invokeLater(()->{
            LoginController loginController = new LoginController(shopModel);

        });

    }
}
