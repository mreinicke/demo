package com.tech.assignment.model;

import java.util.List;

public class CustomerData {
    List<Customer> customerList;

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    public CustomerData() {
    }

    public CustomerData(List<Customer> customerList) {
        this.customerList = customerList;
    }
}
