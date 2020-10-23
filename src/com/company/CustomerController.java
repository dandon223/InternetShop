package com.company;

import com.mysql.cj.log.Log;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller for customerView, view that is shown after successful login by customer.
 * @author Daniel
 */
public class CustomerController {

    private Person activePerson;
    private ShopModel shopModel;
    private CustomerViev customerViev;

    /**
     *
     * @param shopModel model which consists of data that will be shown by CustomerView
     * @param activePerson person that is currently loged in
     */
    public CustomerController( ShopModel shopModel, Person activePerson) {
        this.shopModel = shopModel;
        this.activePerson = activePerson;
        this.customerViev = new CustomerViev(activePerson, shopModel.getItemsData());
        this.customerViev.addBuyButtonListener(new BuyButtonListener());
        this.customerViev.addListButtonListener(new ListButtonListener());
        this.customerViev.addLogOffButtonListener(new LogOffButtonListener());
    }

    class BuyButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            TableModel tableModel = customerViev.getTableModel();
            int itemId;
            int howMany;
            try{
                itemId = Integer.parseInt(customerViev.getIdOrder());
                howMany = Integer.parseInt(customerViev.getHowManyOrder());

            }catch (NumberFormatException e ){
                JOptionPane.showMessageDialog(customerViev,"Please input only numbers. Thank you!");
                return;
            }
            Item item = shopModel.getItem(itemId);
            if(item==null){
                JOptionPane.showMessageDialog(customerViev,"Please check your order number. Thank you!");
                return;
            }
            if(item.getHowManyLeft() < howMany){
                JOptionPane.showMessageDialog(customerViev,"Unfortunately you try to order to much!");
                return;
            }
            if(howMany <=0){
                JOptionPane.showMessageDialog(customerViev,"You can only buy positive number of things.");
                return;
            }
            Order order = shopModel.findOrder(activePerson.getId(),itemId);
            if(order==null)
                shopModel.insertOrder(howMany,activePerson.getId(),itemId);
            else
                shopModel.updateOrderedOrder(howMany +order.getHowManyOrdered(), order.getOrderId());

            shopModel.updateHowManyLeftItems(item.getHowManyLeft()-howMany,itemId);
            tableModel.changeData(shopModel.getItemsData());
            tableModel.fireTableDataChanged();
        }
    }
    class ListButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            customerViev.setVisible(false);
            CustomerListController customerListController = new CustomerListController(activePerson,shopModel);
        }
    }
    class LogOffButtonListener implements  ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            customerViev.setVisible(false);
            LoginController loginController = new LoginController(shopModel);
        }
    }
}
