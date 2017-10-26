package com.rok.sandbox.mod101.restaurant.bean;

import com.rok.sandbox.mod101.restaurant.model.Restaurant;
import com.rok.sandbox.mod101.restaurant.model.Table;
import com.rok.sandbox.mod101.restaurant.model.Visitor;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;

/**
 * Created by roman.kulikov on 6/16/2017.
 * All rights reserved =D
 */
@ManagedBean(eager = true)
@SessionScoped
public class RestaurantBean {


    private Restaurant restaurant;

    @PostConstruct
    public void init() {
        restaurant = new Restaurant("Happy Whale");
        restaurant.addTable(new Table(1, "For two persons"));
        restaurant.addTable(new Table(2, "Massive wooden &amp; steel table"));
        Table tableThree = new Table(3, "Billiards table ");
        restaurant.addTable(tableThree);

        restaurant.addVisitor(new Visitor("Jim"));
        Visitor sue = new Visitor("Sue");
        restaurant.addVisitor(sue);

        restaurant.bookTable(tableThree, sue);
    }

    public List<Table> getTables() {
        return restaurant.getTables();
    }

    public List<Visitor> getVisitors() {
        return restaurant.getVisitors();
    }

    public void addVisitor(String visitorName) {
        Visitor visitor = new Visitor(visitorName);
        restaurant.addVisitor(visitor);
    }

    public void removeVisitor(Visitor visitor) {
        List<Table> visitorTables = restaurant.getBookedTables(visitor);
        for (Table table : visitorTables) {
            restaurant.unbookTable(table);
        }
        restaurant.removeVisitor(visitor);
    }

    public void removeTable(Table table) {
        restaurant.removeTable(table);
    }

    public void addTable(int number, String description) {
        Table newTable = new Table(number, description);
        restaurant.addTable(newTable);
    }


    public void book(int tableNumber, String visitorName) {
        Table table = restaurant.findTableByNumber(tableNumber);
        Visitor visitor = restaurant.findVisitorByName(visitorName);
        restaurant.bookTable(table, visitor);
    }

    public void unbook(Table table) {
        restaurant.unbookTable(table);
    }

    public List<Table> getBookedTables(Visitor visitor) {
        return restaurant.getBookedTables(visitor);
    }

    public List<Table> getFreeTables() {
        return restaurant.getFreeTables();
    }
}
