package com.company;

public class CustomerListController {
    private Person activePerson;
    private ShopModel shopModel;
    private CustomerListViev customerListViev;

    public CustomerListController(Person activePerson, ShopModel shopModel) {
        this.activePerson = activePerson;
        this.shopModel = shopModel;
        this.customerListViev = new CustomerListViev(activePerson, shopModel.getOrderData(1));
    }
}
