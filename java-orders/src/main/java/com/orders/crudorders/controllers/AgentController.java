package com.orders.crudorders.controllers;

import com.orders.crudorders.models.Agents;
import com.orders.crudorders.services.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AgentController
{
    @Autowired
    private AgentService agentService;

    //http://localhost:2019/agents/agents
    @GetMapping(value = "/agents/agents", produces = {"application/json"})
    public ResponseEntity<?> listAllAgents()
    {
        List<Agents> myAgents = agentService.findAll();
        return new ResponseEntity<>(myAgents, HttpStatus.OK);
    }

    //DELETE http://localhost:2019/agents/{agentcode}
    @DeleteMapping(value = "agents/{agentcode}")
    public ResponseEntity<?> deleteAgentById(@PathVariable long agentcode)
    {
        agentService.delete(agentcode);
        return new ResponseEntity<>(HttpStatus.OK);
    }


//    //DELETE http://localhost:2019/agent/{agentcode}
//    @DeleteMapping(value="/agent/{agentcode}")
//    public ResponseEntity<?> deleteAgent(@PathVariable
//                                                 long agentcode)
//    {
//
//    }

}
