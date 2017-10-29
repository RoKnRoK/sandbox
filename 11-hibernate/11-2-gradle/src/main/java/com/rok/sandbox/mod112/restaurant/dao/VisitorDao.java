package com.rok.sandbox.mod112.restaurant.dao;

import com.rok.sandbox.mod112.restaurant.model.Visitor;

import java.util.List;

/**
 * Created by roman.kulikov on 6/13/2017.
 * All rights reserved =D
 */
public interface VisitorDao {

    List<Visitor> getVisitors();

    void addVisitor(Visitor visitor);
    void removeVisitor(Visitor visitor);

    Visitor findVisitorByName(String visitorName);
}
