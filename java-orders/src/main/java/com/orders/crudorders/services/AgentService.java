package com.orders.crudorders.services;

import com.orders.crudorders.models.Agents;

import java.util.List;

public interface AgentService
{
    //GET All Agents
    List<Agents> findAll();

    //Delete
    void delete(long agentcode);
}
