package com.rok.sandbox.mod072.restaurant.dao;

import com.rok.sandbox.mod072.restaurant.model.Visitor;

import java.util.List;

/**
 * Created by roman.kulikov on 6/13/2017.
 * All rights reserved =D
 */
public interface VisitorDao {

    List<Visitor> getVisitors();

    void addVisitor(Visitor p1);
    void removeVisitor(Visitor p1);

    Visitor findVisitorByName(String personName);
}
