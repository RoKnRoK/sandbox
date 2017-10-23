package com.rok.sandbox.mod092.restaurant.dao;

import com.rok.sandbox.mod092.restaurant.model.Visitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by roman.kulikov on 6/12/2017.
 * All rights reserved =D
 */
public class VisitorsList implements VisitorDao {

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

    public Visitor findVisitorByName(String personName) {
        if (personName == null) {return null;}
        return people.stream()
                .filter(p -> Objects.equals(p.getName().toLowerCase(), personName.toLowerCase()))
                .findFirst()
                .orElse(null);
    }
}
