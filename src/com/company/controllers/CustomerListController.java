package com.company.controllers;

import com.company.models.*;
import com.company.views.CustomerListView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller for customerListView, view that is shown after clicking list button in customerView.
 * @author Daniel
 */
public class CustomerListController {
    /**
     * person that is currently logged in
     */
    private Person activePerson;
    /**
     * all of data for shop
     */
    private ShopModel shopModel;
    /**
     * customer list view
     */
    private CustomerListView customerListView;
    /**
     * constructor
     * @param shopModel all of data for shop
     * @param activePerson person that is currently logged in
     */
    public CustomerListController(Person activePerson, ShopModel shopModel) {
        this.activePerson = activePerson;
        this.shopModel = shopModel;
        this.customerListView = new CustomerListView(activePerson, shopModel.getOrderDataCustomer(activePerson.getId()));
        this.customerListView.addBackButtonListener(event->{customerListView.setVisible(false);
            new CustomerController(shopModel,activePerson);});
        this.customerListView.addDeleteButtoNlistener(new DeleteButtonListener());
    }

    /**
     * ActionListener for delete button
     */
    class DeleteButtonListener implements  ActionListener{

        /**
         * reads parameters from customerListView and acts accordingly
         * @param actionEvent action event
         */
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            TableModel tableModel = customerListView.getTableModel();
            int orderId;
            int howManyToDelete;
            try{
                orderId = Integer.parseInt(customerListView.getIdOrder());
                howManyToDelete = Integer.parseInt(customerListView.getHowManyDelete());

            }catch (NumberFormatException e ){
                JOptionPane.showMessageDialog(customerListView,"Please input only numbers. Thank you!");
                return;
            }
            Order order = shopModel.getOrder(orderId);
            if(order==null){
                JOptionPane.showMessageDialog(customerListView,"Please check your order number. Thank you!");
                return;
            }
            Item item = order.getItem();
            if(order.getHowManyOrdered() < howManyToDelete){
                JOptionPane.showMessageDialog(customerListView,"Unfortunately you try to delete to much!");
                return;
            }
            if(howManyToDelete <=0){
                JOptionPane.showMessageDialog(customerListView,"You can only delete positive number of things.");
                return;
            }
            shopModel.updateOrderedOrder(order.getHowManyOrdered()-howManyToDelete,orderId);
            shopModel.updateHowManyLeftItems(item.getHowManyLeft()+howManyToDelete,item.getId());
            shopModel.deleteOrdersWithZeros();
            tableModel.changeData(shopModel.getOrderDataCustomer(activePerson.getId()));
            tableModel.fireTableDataChanged();
        }
    }
}
