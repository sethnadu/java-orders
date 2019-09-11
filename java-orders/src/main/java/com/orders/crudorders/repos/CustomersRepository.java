package com.orders.crudorders.repos;

import com.orders.crudorders.models.Customers;
import org.springframework.data.repository.CrudRepository;

public interface CustomersRepository extends CrudRepository<Customers, Long>
{
    Customers findByName(String custname);
}
