package com.company.controllers;

import com.company.models.*;
import com.company.views.CustomerView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller for customerView, view that is shown after successful login by customer.
 * @author Daniel
 */
public class CustomerController {
    /**
     * person that is currently logged in
     */
    private Person activePerson;
    /**
     * all of data for shop
     */
    private ShopModel shopModel;
    /**
     * customer view
     */
    private CustomerView customerView;

    /**
     * constructor
     * @param shopModel model which consists of data that will be shown by CustomerView
     * @param activePerson person that is currently loged in
     */
    public CustomerController( ShopModel shopModel, Person activePerson) {
        this.shopModel = shopModel;
        this.activePerson = activePerson;
        this.customerView = new CustomerView(activePerson, shopModel.getItemsData());
        this.customerView.addBuyButtonListener(new BuyButtonListener());
        this.customerView.addListButtonListener(event->{customerView.setVisible(false);
            new CustomerListController(activePerson,shopModel);});
        this.customerView.addLogOffButtonListener(event->{customerView.setVisible(false);
            new LoginController(shopModel);});
    }

    /**
     * ActionListener for buy button
     */
    class BuyButtonListener implements ActionListener {
        /**
         * reads parameters from customerView and acts accordingly
         * @param actionEvent action event
         */
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            int itemId;
            int howMany;
            try{
                itemId = Integer.parseInt(customerView.getIdOrder());
                howMany = Integer.parseInt(customerView.getHowManyOrder());

            }catch (NumberFormatException e ){
                JOptionPane.showMessageDialog(customerView,"Please input only numbers. Thank you!");
                return;
            }
            Item item = shopModel.getItem(itemId);
            if(item==null){
                JOptionPane.showMessageDialog(customerView,"Please check your order number. Thank you!");
                return;
            }
            if(item.getHowManyLeft() < howMany){
                JOptionPane.showMessageDialog(customerView,"Unfortunately you try to order to much!");
                return;
            }
            if(howMany <=0){
                JOptionPane.showMessageDialog(customerView,"You can only buy positive number of things.");
                return;
            }
            Order order = shopModel.getOrder(activePerson.getId(),itemId);
            if(order==null)
                shopModel.insertOrder(howMany,activePerson.getId(),itemId);
            else
                shopModel.updateOrderedOrder(howMany +order.getHowManyOrdered(), order.getOrderId());

            shopModel.updateHowManyLeftItems(item.getHowManyLeft()-howMany,itemId);
            TableModel tableModel = customerView.getTableModel();
            tableModel.changeData(shopModel.getItemsData());
            tableModel.fireTableDataChanged();
        }
    }
}
