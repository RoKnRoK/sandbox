package com.rok.sandbox.mod03.restaurant.dao;

import com.rok.sandbox.mod03.restaurant.model.Visitor;

import java.util.ArrayList;

/**
 * Created by roman.kulikov on 6/12/2017.
 * All rights reserved =D
 */
public class VisitorsList implements VisitorDao {

    private ArrayList<Visitor> people;

    public VisitorsList(){
        people = new ArrayList<>();
    }

    public ArrayList<Visitor> getVisitors() {
        return people;
    }

    public void addVisitor(Visitor visitor){
        this.people.add(visitor);
    }

    public void removeVisitor(Visitor visitor){
        this.people.remove(visitor);
    }
}
