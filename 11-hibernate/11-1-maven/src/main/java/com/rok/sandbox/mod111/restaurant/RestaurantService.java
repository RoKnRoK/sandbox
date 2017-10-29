package com.rok.sandbox.mod111.restaurant;

import com.rok.sandbox.mod111.restaurant.model.Restaurant;
import com.rok.sandbox.mod111.restaurant.model.Table;
import com.rok.sandbox.mod111.restaurant.model.Visitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by roman.kulikov on 6/13/2017.
 * All rights reserved =D
 */
@Service
public class RestaurantService {

    @Autowired
    private Restaurant restaurant;

    /*@PostConstruct
    public void init() {
        restaurant.setName("Happy Whale");
        restaurant.addTable(new Table(1, "For two persons"));
        restaurant.addTable(new Table(2, "Massive wooden &amp; steel table"));
        Table tableThree = new Table(3, "Billiards table ");
        restaurant.addTable(tableThree);

        restaurant.addVisitor(new Visitor("Jim"));
        Visitor sue = new Visitor("Sue");
        restaurant.addVisitor(sue);

        restaurant.bookTable(tableThree, sue);
    }*/

    public Restaurant getRestaurant() {
        return restaurant;
    }


    public List<Table> getTables(String status, String visitorName) {
        if (visitorName != null && !visitorName.isEmpty()) {
            Visitor visitor = restaurant.findVisitorByName(visitorName);
            return restaurant.getBookedTables(visitor);
        }

        if ("available".equals(status)) {
            return restaurant.getFreeTables();
        }
        if ("unavailable".equals(status)) {
            return restaurant.getOccupiedTables();
        }
        return restaurant.getTables();
    }

    public void addTable(int number, String description) {
        Table newTable = new Table(number, description);
        restaurant.addTable(newTable);
    }

    public void removeTable(int number) {
        Table theTable = restaurant.findTableByNumber(number);
        restaurant.removeTable(theTable);
    }

    public void addVisitor(String visitorName) {
        if (visitorName == null) {
            return;
        }
        Visitor visitor = new Visitor(visitorName);
        restaurant.addVisitor(visitor);
    }

    public void removeVisitor(String visitorName) {
        if (visitorName == null) {
            return;
        }
        Visitor visitor = restaurant.findVisitorByName(visitorName);
        restaurant.removeVisitor(visitor);
    }

    public void bookTable(int number, String visitorName) {
        Table table = restaurant.findTableByNumber(number);
        Visitor visitor = restaurant.findVisitorByName(visitorName);
        restaurant.bookTable(table, visitor);
    }

    public void unbookTable(int number) {
        Table table = restaurant.findTableByNumber(number);
        restaurant.unbookTable(table);
    }
}
