package com.company;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerController {

    private Person activePerson;
    private ShopModel shopModel;
    private CustomerViev customerViev;

    public CustomerController( ShopModel shopModel) {
        this.shopModel = shopModel;
    }

    public void setActivePerson(Person activePerson) {
        this.activePerson = activePerson;
    }

    public void setCustomerViev(CustomerViev customerViev) {
        this.customerViev = customerViev;
        this.customerViev.addBuyButtonListener(new BuyButtonListener());
    }

    class BuyButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            String order = customerViev.getIdOrder();
            TableModel tableModel = customerViev.getTableModel();
            tableModel.setValueAt(order,2,3);
            tableModel.fireTableDataChanged();
        }
    }
}
