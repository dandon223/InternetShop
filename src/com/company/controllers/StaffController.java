package com.company.controllers;

import com.company.models.Person;
import com.company.models.ShopModel;
import com.company.views.CustomerView;
import com.company.views.StaffView;

public class StaffController {
    private Person activePerson;
    private ShopModel shopModel;
    private StaffView staffView;

    public StaffController( ShopModel shopModel, Person activePerson) {
        this.shopModel = shopModel;
        this.activePerson = activePerson;
        this.staffView = new StaffView(activePerson, shopModel.getOrderDataStaff());
    }
}
