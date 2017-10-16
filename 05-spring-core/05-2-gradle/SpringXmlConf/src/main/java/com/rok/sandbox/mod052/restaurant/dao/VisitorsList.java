package com.rok.sandbox.mod052.restaurant.dao;

import com.rok.sandbox.mod052.restaurant.model.Visitor;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by roman.kulikov on 6/12/2017.
 * All rights reserved =D
 */
public class VisitorsList implements VisitorDao {

    private Map<String, Visitor> people;

    public VisitorsList(){
        people = new HashMap<>();
    }

    public Map<String, Visitor> getVisitors() {
        return people;
    }

    public void setPeople(Map<String, Visitor> visitors) {
        this.people = visitors;
    }

    public void addVisitor(Visitor visitor){
        this.people.put(visitor.getName(), visitor);
    }

    public void removeVisitor(Visitor visitor){
        this.people.remove(visitor.getName());
    }
}
