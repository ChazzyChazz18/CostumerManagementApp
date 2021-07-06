package com.businessx.costumersmanagementapp.models;

import java.util.ArrayList;

public class Client {

    private final String firstName;
    private final String lastName;
    private final ArrayList<Address> addressList;

    public Client(String firstName, String lastName, ArrayList<Address> addressList) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.addressList = addressList;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public ArrayList<Address> getAddressList() {
        return addressList;
    }

    public String getAdressListSizeStr(){
        return String.valueOf(addressList.size());
    }

}
