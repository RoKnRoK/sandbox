package com.rok.sandbox.mod0511.restaurant.dao;

import com.rok.sandbox.mod0511.restaurant.model.Visitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roman.kulikov on 6/12/2017.
 * All rights reserved =D
 */
@Component
public class VisitorsList implements VisitorDao {

    @Autowired
    private List<Visitor> people;

    public VisitorsList(){
        people = new ArrayList<>();
    }

    public List<Visitor> getVisitors() {
        return people;
    }

    public void addVisitor(Visitor visitor){
        this.people.add(visitor);
    }

    public void removeVisitor(Visitor visitor){
        this.people.remove(visitor);
    }
}
