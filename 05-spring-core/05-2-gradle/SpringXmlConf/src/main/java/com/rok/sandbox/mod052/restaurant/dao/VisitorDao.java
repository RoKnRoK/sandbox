package com.rok.sandbox.mod052.restaurant.dao;

import com.rok.sandbox.mod052.restaurant.model.Visitor;

import java.util.Map;

/**
 * Created by roman.kulikov on 6/13/2017.
 * All rights reserved =D
 */
public interface VisitorDao {

    Map<String, Visitor> getVisitors();

    void addVisitor(Visitor p1);
    void removeVisitor(Visitor p1);
}