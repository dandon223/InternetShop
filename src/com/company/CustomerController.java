package com.company;

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
    }

    class BuyButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            TableModel tableModel = customerViev.getTableModel();
            int order;
            int howMany;
            try{
                order = Integer.parseInt(customerViev.getIdOrder());
                howMany = Integer.parseInt(customerViev.getHowManyOrder());

            }catch (NumberFormatException e ){
                JOptionPane.showMessageDialog(customerViev,"Please input only numbers. Thank you!");
                return;
            }
            if(order > tableModel.getRowCount()){
                JOptionPane.showMessageDialog(customerViev,"Please check your order number. Thank you!");
                return;
            }
            int howManyAlreadyBooked = (int) tableModel.getValueAt(order-1,4);
            int howManyTotal = (Integer) tableModel.getValueAt(order-1,3);
            if(howManyTotal < howManyAlreadyBooked +howMany){
                JOptionPane.showMessageDialog(customerViev,"Unfortunately you try to order to much!");
                return;
            }
            if(howMany <=0){
                JOptionPane.showMessageDialog(customerViev,"You can only buy positive number of things.");
                return;
            }
            tableModel.setValueAt(howMany+howManyAlreadyBooked, order-1,4);
            tableModel.fireTableDataChanged();
            shopModel.updateCellData("Items","booked",howMany+howManyAlreadyBooked,order);
            shopModel.updateOrderTable(activePerson.getId(),order,"howManyOrdered",howMany);
        }
    }
    class ListButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            customerViev.setVisible(false);
            CustomerListController customerListController = new CustomerListController(activePerson,shopModel);
        }
    }
}
