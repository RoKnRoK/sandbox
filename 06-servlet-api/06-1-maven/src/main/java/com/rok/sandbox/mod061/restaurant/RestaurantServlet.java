package com.rok.sandbox.mod061.restaurant;

import com.rok.sandbox.mod061.restaurant.model.Restaurant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by roman.kulikov on 6/13/2017.
 * All rights reserved =D
 */
@WebServlet("/restaurant")
public class RestaurantServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Restaurant restaurant = (Restaurant) getServletContext().getAttribute("restaurant");
        printStatus(restaurant, response.getWriter());

    }

    private void printStatus(Restaurant restaurant, PrintWriter out) {
        out.println("Status Report of Restaurant \n" + restaurant.toString());
        restaurant.getTables().forEach(table -> out.println("Table: " + table));

        restaurant.getVisitors().forEach(visitor -> {
            int count = restaurant.getBookedTables(visitor).size();
            out.println(visitor + " (has booked " + count + " tables)");
        });
        out.println("Free tables left: " + restaurant.getFreeTables().size());
        out.println("--- End of Status Report ---");
    }
}
