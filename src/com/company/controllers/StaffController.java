package com.company.controllers;

import com.company.models.*;
import com.company.views.StaffView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

public class StaffController {
    private Person activePerson;
    private ShopModel shopModel;
    private StaffView staffView;

    public StaffController( ShopModel shopModel, Person activePerson) {
        this.shopModel = shopModel;
        this.activePerson = activePerson;
        this.staffView = new StaffView(activePerson, shopModel.getOrderDataStaff());
        this.staffView.setLogOffButtonListener(new LogOffButtonListener());
        this.staffView.setToBuyButtonListener(new BuyButtonListener());
        this.staffView.setToBookButtonListener(new BookButtonListener());
    }
    class LogOffButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            staffView.setVisible(false);
            LoginController loginController = new LoginController(shopModel);
        }
    }
    class BuyButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            List<Integer> list = getInputs();
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
            List<Integer> list = getInputs();
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
     * @return list of 4 integers or 0 if something wnet wrong
     */
    public List<Integer> getInputs(){
        int orderId;
        int howMany;
        try{
            orderId = Integer.parseInt(staffView.getIdOrder());
            howMany = Integer.parseInt(staffView.getHowMany());

        }catch (NumberFormatException e ){
            JOptionPane.showMessageDialog(staffView,"Please input only numbers. Thank you!");
            return new LinkedList<>();
        }
        Order order = shopModel.getOrder(orderId);
        if(order==null){
            JOptionPane.showMessageDialog(staffView,"Please check your order number. Thank you!");
            return new LinkedList<>();
        }
        if(order.getHowManyOrdered() <howMany){
            JOptionPane.showMessageDialog(staffView,"Unfortunately there are not that many ordered!");
            return new LinkedList<>();
        }
        if(howMany <=0){
            JOptionPane.showMessageDialog(staffView,"You can only change positive number of things.");
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
