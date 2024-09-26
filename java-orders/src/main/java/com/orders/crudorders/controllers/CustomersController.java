package com.orders.crudorders.controllers;

import com.orders.crudorders.models.Customers;
import com.orders.crudorders.services.CustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

@RestController
public class CustomersController
{
    @Autowired
    private CustomersService customersService;

    //GET http://localhost:2019/customer/order
    @GetMapping(value = "/customer/order", produces = {"application/json"})
    public ResponseEntity<?> listAllCustomers()
    {
        List<Customers> myCustomers = customersService.findAll();
        return new ResponseEntity<>(myCustomers, HttpStatus.OK);
    }

    //GET http://localhost:2019/customer/name/{custname}
    @GetMapping(value = "/customer/name/{custname}", produces = {"application/json"})
    public ResponseEntity<?> getCustomerOrdersByName(@PathVariable String custname)
    {
        Customers c = customersService.findOrdersByCustomerName(custname);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }

    //POST http://localhost:2019/data/customer/new
    @PostMapping(value = "/data/customer/new", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> addNewCustomer(@Valid
                                            @RequestBody Customers newCustomer) throws URISyntaxException
    {
        newCustomer = customersService.save(newCustomer);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newCustomerURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{custcode}")
                .buildAndExpand(newCustomer.getCustcode()).toUri();
        responseHeaders.setLocation(newCustomerURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    //PUT http://localhost:2019/data/customer/update/{custcode}
    @PutMapping(value= "data/customer/update/{custcode}", produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<?> updateCustomer(@RequestBody Customers updateRes, @PathVariable long custcode)
    {
        customersService.update(updateRes, custcode);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //DELETE http://localhost:2019/data/customer/delete/{custcode}
    @DeleteMapping(value="data/customer/delete/{custcode}")
    public ResponseEntity<?> deleteCustomerById(@PathVariable long custcode)
    {
        customersService.delete(custcode);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
