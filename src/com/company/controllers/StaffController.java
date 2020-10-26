package com.company.controllers;

import com.company.models.*;
import com.company.views.StaffView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

/**
 * controller for StaffView
 * @author Daniel
 */
public class StaffController {
    /**
     * all of data for shop
     */
    private ShopModel shopModel;
    /**
     * staff view
     */
    private StaffView staffView;

    /**
     * constructor
     * @param shopModel all of data for shop
     * @param activePerson person that is currently logged in
     */
    public StaffController( ShopModel shopModel, Person activePerson) {
        this.shopModel = shopModel;
        this.staffView = new StaffView(activePerson, shopModel.getOrderDataStaff());
        this.staffView.setLogOffButtonListener(action->{staffView.setVisible(false);
            new LoginController(shopModel);});
        this.staffView.setToBuyButtonListener(new BuyButtonListener());
        this.staffView.setToBookButtonListener(new BookButtonListener());
    }
    class BuyButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            List<Integer> list = getInputs("buy");
            if(list.size()==0)
                return;
            shopModel.updateOrderedOrder(list.get(2)-list.get(1),list.get(0));
            shopModel.updateBoughtOrder(list.get(3)+ list.get(1),list.get(0));
            TableModel tableModel = staffView.getTableModel();
            tableModel.changeData(shopModel.getOrderDataStaff());
            tableModel.fireTableDataChanged();
        }
    }
    class BookButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            List<Integer> list = getInputs("book");
            if(list.size()==0)
                return;
            shopModel.updateOrderedOrder(list.get(2)+list.get(1),list.get(0));
            shopModel.updateBoughtOrder(list.get(3)- list.get(1),list.get(0));
            TableModel tableModel = staffView.getTableModel();
            tableModel.changeData(shopModel.getOrderDataStaff());
            tableModel.fireTableDataChanged();
        }
    }

    /**
     * gets inputs from staffView two JTexts
     * @param what either 'book' or 'buy'
     * @return list of 4 integers or 0 if something went wrong
     */
    public List<Integer> getInputs(String what){
        int orderId;
        int howMany;
        try{
            orderId = Integer.parseInt(staffView.getIdOrder());
            howMany = Integer.parseInt(staffView.getHowMany());

        }catch (NumberFormatException e ){
            JOptionPane.showMessageDialog(staffView,"Please input only numbers. Thank you!");
            return new LinkedList<>();
        }
        if(howMany <=0){
            JOptionPane.showMessageDialog(staffView,"You can only change positive number of things.");
            return new LinkedList<>();
        }
        Order order = shopModel.getOrder(orderId);
        if(order==null){
            JOptionPane.showMessageDialog(staffView,"Please check your order number. Thank you!");
            return new LinkedList<>();
        }
        int howManyInCell;
        if(what.equals("buy"))
            howManyInCell = order.getHowManyOrdered();
        else
            howManyInCell = order.getHowManyBought();

        if(howManyInCell <howMany){
            JOptionPane.showMessageDialog(staffView,"Unfortunately there are not that many ordered!");
            return new LinkedList<>();
        }

        List<Integer> list = new LinkedList<>();
        list.add(orderId);
        list.add(howMany);
        list.add(order.getHowManyOrdered());
        list.add(order.getHowManyBought());
        return list;
    }
}
