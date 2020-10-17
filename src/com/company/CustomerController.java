package com.company;

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
    }
}
