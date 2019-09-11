package com.orders.crudorders.services;

import com.orders.crudorders.models.Agents;
import com.orders.crudorders.models.Customers;
import com.orders.crudorders.models.Orders;
import com.orders.crudorders.repos.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "customersService")
public class CustomersServiceImpl implements CustomersService
{
    @Autowired
    private CustomersRepository custrepo;

    @Override
    public List<Customers> findAll()
    {
        List<Customers> custList = new ArrayList<>();
        custrepo.findAll().iterator().forEachRemaining(custList::add);
        return custList;
    }

    @Override
    public Customers findOrdersByCustomerName(String custname)
    {
        Customers customer = custrepo.findByName(custname);

        if (customer == null)
        {
            throw new EntityNotFoundException("Customer: " + custname);
        }
        return customer;
    }

    @Override
    public Customers save(Customers customer)
    {
        Customers newCustomer = new Customers();

        newCustomer.setCustname(customer.getCustname());
        newCustomer.setCustcity(customer.getCustcity());
        newCustomer.setWorkingarea(customer.getWorkingarea());
        newCustomer.setCustcountry(customer.getCustcountry());
        newCustomer.setGrade(customer.getGrade());
        newCustomer.setOpeningamt(customer.getOpeningamt());
        newCustomer.setRecieveamt(customer.getRecieveamt());
        newCustomer.setPaymentamt(customer.getPaymentamt());
        newCustomer.setOutstandingamt(customer.getOutstandingamt());
        newCustomer.setPhone(customer.getPhone());
        newCustomer.setAgent(customer.getAgent());

        for (Orders o: customer.getOrders())
        {
            newCustomer.getOrders().add(new Orders(o.getOrdamount(), o.getAdvanceamount(), newCustomer, o.getOrddescription()));
        }
        return custrepo.save(newCustomer);
    }

    @Transactional
    @Override
    public Customers update(Customers customer, long custcode)
    {
        Customers currentCustomer = custrepo.findById(custcode)
                .orElseThrow(() -> new EntityNotFoundException(Long.toString(custcode)));
        if (customer.getCustname() != null)
        {
            currentCustomer.setCustname(customer.getCustname());
        }
        if (customer.getCustcity() != null)
        {
            currentCustomer.setCustcity(customer.getCustcity());
        }
        if (customer.getWorkingarea() != null)
        {
            currentCustomer.setWorkingarea(customer.getWorkingarea());
        }
        if (customer.getCustcountry() != null)
        {
            currentCustomer.setCustcountry(customer.getCustcountry());
        }
        if (customer.getGrade() != null)
        {
            currentCustomer.setGrade(customer.getGrade());
        }
        currentCustomer.setOpeningamt(customer.getOpeningamt());
        currentCustomer.setRecieveamt(customer.getRecieveamt());
        currentCustomer.setPaymentamt(customer.getPaymentamt());
        currentCustomer.setOutstandingamt(customer.getOutstandingamt());

        if (customer.getPhone() != null)
        {
            currentCustomer.setPhone(customer.getPhone());
        }
        if (customer.getAgent() != null)
        {
            currentCustomer.setAgent(customer.getAgent());
        }
        if (customer.getOrders().size() > 0)
        {
            for (Orders o: customer.getOrders())
            {
                currentCustomer.getOrders().add(new Orders(o.getOrdamount(), o.getAdvanceamount(), currentCustomer, o.getOrddescription()));
            }
        }
        return custrepo.save(currentCustomer);
    }

    @Override
    public void delete(long custcode)
    {
        if(custrepo.findById(custcode).isPresent())
        {
            custrepo.deleteById(custcode);
        } else {
            throw new EntityNotFoundException("Customer Code: " + custcode);
        }
    }
}
