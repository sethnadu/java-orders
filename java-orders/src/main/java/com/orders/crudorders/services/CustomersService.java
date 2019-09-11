package com.orders.crudorders.services;

import com.orders.crudorders.models.Customers;

import java.util.*;

public interface CustomersService
{
    //GET
    List<Customers> findAll();

    //GET
    Customers findOrdersByCustomerName(String custname);

    //POST
    Customers save(Customers customer);

    //PUT
    Customers update(Customers customer, long custcode);

    //DELETE
    void delete(long custcode);


}
