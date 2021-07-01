package com.businessx.costumersmanagementapp.models;

import java.util.ArrayList;

public class Client {

    private final String name;
    private final ArrayList<String> addressList;

    public Client(String name, ArrayList<String> addressList) {
        this.name = name;
        this.addressList = addressList;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getAddressList() {
        return addressList;
    }

    public String getAdressListSizeStr(){
        return String.valueOf(addressList.size());
    }

}
