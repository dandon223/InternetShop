package com.company;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerListController {
    private Person activePerson;
    private ShopModel shopModel;
    private CustomerListViev customerListViev;

    public CustomerListController(Person activePerson, ShopModel shopModel) {
        this.activePerson = activePerson;
        this.shopModel = shopModel;
        this.customerListViev = new CustomerListViev(activePerson, shopModel.getOrderData(activePerson.getId()));
        this.customerListViev.addBackButtonListener(new BackButtonListener());
    }
    class BackButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            customerListViev.setVisible(false);
            CustomerController customerController = new CustomerController(shopModel,activePerson);
        }
    }
}