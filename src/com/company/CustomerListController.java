package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller for customerListView, view that is shown after clicking list button in customerView.
 * @author Daniel
 */
public class CustomerListController {
    private Person activePerson;
    private ShopModel shopModel;
    private CustomerListViev customerListViev;
    /**
     *
     * @param shopModel model which consists of data that will be shown by CustomerListView
     * @param activePerson person that is currently logged in
     */
    public CustomerListController(Person activePerson, ShopModel shopModel) {
        this.activePerson = activePerson;
        this.shopModel = shopModel;
        this.customerListViev = new CustomerListViev(activePerson, shopModel.getOrderData(activePerson.getId()));
        this.customerListViev.addBackButtonListener(new BackButtonListener());
        this.customerListViev.addDeleteButtoNlistener(new DeleteButtonListener());
    }
    class BackButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            customerListViev.setVisible(false);
            CustomerController customerController = new CustomerController(shopModel,activePerson);
        }
    }
    class DeleteButtonListener implements  ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            TableModel tableModel = customerListViev.getTableModel();
            int orderId;
            int howManyToDelete;
            try{
                orderId = Integer.parseInt(customerListViev.getIdOrder());
                howManyToDelete = Integer.parseInt(customerListViev.getHowManyDelete());

            }catch (NumberFormatException e ){
                JOptionPane.showMessageDialog(customerListViev,"Please input only numbers. Thank you!");
                return;
            }
            Order order = shopModel.getOrder(orderId);
            if(order==null){
                JOptionPane.showMessageDialog(customerListViev,"Please check your order number. Thank you!");
                return;
            }
            Item item = order.getItem();
            if(order.getHowManyOrdered() < howManyToDelete){
                JOptionPane.showMessageDialog(customerListViev,"Unfortunately you try to delete to much!");
                return;
            }
            if(howManyToDelete <=0){
                JOptionPane.showMessageDialog(customerListViev,"You can only delete positive number of things.");
                return;
            }
            shopModel.updateOrderedOrder(order.getHowManyOrdered()-howManyToDelete,orderId);
            shopModel.updateHowManyLeftItems(item.getHowManyLeft()+howManyToDelete,item.getId());
            shopModel.deleteOrdersWithZeroBooked();
            tableModel.changeData(shopModel.getOrderData(activePerson.getId()));
            tableModel.fireTableDataChanged();
        }
    }
}
