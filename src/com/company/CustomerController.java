package com.company;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerController {

    private Person activePerson;
    private ShopModel shopModel;
    private CustomerViev customerViev;

    public CustomerController( ShopModel shopModel, Person activePerson) {
        this.shopModel = shopModel;
        this.activePerson = activePerson;
        this.customerViev = new CustomerViev(activePerson, shopModel.getData());
        this.customerViev.addBuyButtonListener(new BuyButtonListener());
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
                return;
            }
            if(order > tableModel.getRowCount()){
                return;
            }
            int howManyAlreadyBooked = (int) tableModel.getValueAt(order-1,4);
            int howManyTotal = (Integer) tableModel.getValueAt(order-1,3);
            if(howManyTotal < howManyAlreadyBooked +howMany){
                return;
            }
            tableModel.setValueAt(howMany+howManyAlreadyBooked, order-1,4);
            tableModel.fireTableDataChanged();
        }
    }
}
