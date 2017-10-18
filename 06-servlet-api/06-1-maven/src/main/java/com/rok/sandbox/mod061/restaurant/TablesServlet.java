package com.rok.sandbox.mod061.restaurant;

import com.rok.sandbox.mod061.restaurant.model.Restaurant;
import com.rok.sandbox.mod061.restaurant.model.Table;
import com.rok.sandbox.mod061.restaurant.model.Visitor;

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
@WebServlet(urlPatterns = {
        "/restaurant/tables",
        "/restaurant/tables/add",
        "/restaurant/tables/remove",
        "/restaurant/tables/book",
        "/restaurant/tables/unbook"})
public class TablesServlet extends HttpServlet{


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String requestURI = request.getRequestURI();
        if (!requestURI.endsWith("/tables")) {
            response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            out.println("use GET method to receive list of tables!");
            return;
        }

        // Set response content type
        Restaurant restaurant = (Restaurant) getServletContext().getAttribute("restaurant");


        String visitor = request.getParameter("visitor");
        if (visitor != null) {
            out.println("Tables for " + visitor + ": " );
            restaurant.getBookedTables(new Visitor(visitor)).forEach(out::println);
            return;
        }

        String status = request.getParameter("status");

        if ("free".equals(status)) {
            restaurant.getFreeTables().forEach(out::println);
        }
        else if ("booked".equals(status)) {
            restaurant.getOccupiedTables().forEach(out::println);
        }
        else {
            restaurant.getTables().forEach(out::println);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();

        String requestURI = request.getRequestURI();
        if (!requestURI.endsWith("/add")) {
            response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            out.println("Method POST allowed only for 'add' operation!");
            return;
        }

        Restaurant restaurant = (Restaurant) getServletContext().getAttribute("restaurant");

        int number;
        try {
            number = Integer.parseInt(request.getParameter("number"));
        } catch (NumberFormatException e) {
            out.println("Cannot create table with number " + request.getParameter("number") );
            return;
        }
        String description = request.getParameter("desc");

        Table newTable = description == null ? new Table(number) : new Table(number, description);
        restaurant.addTable(newTable);

        out.println("Table #" + number + " created");

    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        String requestURI = request.getRequestURI();
        if (!requestURI.endsWith("/remove")) {
            out.println("Method DELETE allowed only for 'remove' operation!");
            response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            return;
        }

        Restaurant restaurant = (Restaurant) getServletContext().getAttribute("restaurant");

        int number;
        try {
            number = Integer.parseInt(request.getParameter("number"));
        } catch (NumberFormatException e) {
            out.println("Cannot remove table with incorrect number " + request.getParameter("number"));
            return;
        }
        Table table = restaurant.findTableByNumber(number);
        restaurant.removeTable(table);

        out.println("Table #" + number + " removed");

    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        String requestURI = request.getRequestURI();
        if (!requestURI.endsWith("/book") && !requestURI.endsWith("/unbook")) {
            response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            out.println("Method PUT allowed only for 'book' and 'unbook' operations!");
            return;
        }
        boolean isUnbooking = requestURI.endsWith("/book");
        Restaurant restaurant = (Restaurant) getServletContext().getAttribute("restaurant");

        int number;
        try {
            number = Integer.parseInt(request.getParameter("number"));
        } catch (NumberFormatException e) {
            out.println("Cannot book or unbook table with incorrect number " + request.getParameter("number"));
            return;
        }
        if (isUnbooking) {
            Table table = restaurant.findTableByNumber(number);
            boolean unbooked = restaurant.unbookTable(table);
            out.println("Table " + number + (unbooked ? "" : "not") + " unbooked");
        }
        else {
            String visitorName = request.getParameter("visitor");
            if (visitorName == null) {
                out.println("Cannot book table to no one!" );
                return;
            }
            Visitor visitor = restaurant.findVisitorByName(visitorName);
            Table table = restaurant.findTableByNumber(number);
            boolean booked = restaurant.bookTable(table, visitor);
            out.println("Table " + number + (booked ? "" : "not") + " booked by " + visitorName);
        }


    }

}
