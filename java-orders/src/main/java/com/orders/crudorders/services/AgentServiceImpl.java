package com.orders.crudorders.services;

import com.orders.crudorders.models.Agents;
import com.orders.crudorders.models.Customers;
import com.orders.crudorders.repos.AgentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "agentService")
public class AgentServiceImpl implements AgentService
{
    @Autowired
    private AgentsRepository agentrepos;

    @Override
    public List<Agents> findAll()
    {
        List<Agents> agentlst = new ArrayList<>();
        agentrepos.findAll().iterator().forEachRemaining(agentlst::add);
        return agentlst;
    }

    @Override
    public void delete(long agentcode)
    {
     Customers newCustomer = new Customers();

    if((newCustomer.getAgent().getAgentcode() != agentcode) && (newCustomer.getOrders() == null))
    {
        if(agentrepos.findById(agentcode).isPresent()){
            agentrepos.deleteById(agentcode);
        } else {
            throw new EntityNotFoundException("Id: " + agentcode);
        }
    }
    }

}
