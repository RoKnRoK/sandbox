package com.rok.sandbox.mod061.restaurant;

import com.rok.sandbox.mod061.restaurant.model.Table;
import com.rok.sandbox.mod061.restaurant.model.Restaurant;
import com.rok.sandbox.mod061.restaurant.model.Visitor;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by roman.kulikov on 6/15/2017.
 * All rights reserved =D
 */
@WebListener
public class RestaurantInitializer implements ServletContextListener {

    public void contextInitialized(ServletContextEvent event) {

        // Do required initialization
        Restaurant restaurant = new Restaurant("Happy Whale");
        restaurant.addTable(new Table(1, "For two persons"));
        restaurant.addTable(new Table(2, "Massive wooden &amp; steel table"));
        Table tableThree = new Table(3, "Billiards table ");
        restaurant.addTable(tableThree);

        restaurant.addVisitor(new Visitor("Jim"));
        Visitor sue = new Visitor("Sue");
        restaurant.addVisitor(sue);

        restaurant.bookTable(tableThree, sue);

        event.getServletContext().setAttribute("restaurant", restaurant);
    }

    public void contextDestroyed(ServletContextEvent event) {
        // Do stuff during webapp's shutdown.
    }

}
