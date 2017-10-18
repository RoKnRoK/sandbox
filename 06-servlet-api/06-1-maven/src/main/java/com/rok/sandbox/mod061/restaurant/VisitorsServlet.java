package com.rok.sandbox.mod061.restaurant;

import com.rok.sandbox.mod061.restaurant.model.Restaurant;
import com.rok.sandbox.mod061.restaurant.model.Visitor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by roman.kulikov on 6/15/2017.
 * All rights reserved =D
 */
@WebServlet(urlPatterns = {
        "/restaurant/visitors",
        "/restaurant/visitors/add",
        "/restaurant/visitors/remove"})
public class VisitorsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String requestURI = request.getRequestURI();
        if (!requestURI.endsWith("/visitors")) {
            response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            out.println("use GET method to receive list of visitors!");
            return;
        }

        // Set response content type
        Restaurant restaurant = (Restaurant) getServletContext().getAttribute("restaurant");

        restaurant.getVisitors().forEach(out::println);

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

        String visitorName = request.getParameter("name");
        if (visitorName == null) {
            out.println("Cannot create visitor without name!" );
            return;
        }

        Visitor newVisitor = new Visitor(visitorName);
        restaurant.addVisitor(newVisitor);

        out.println("Visitor " + visitorName + " created");

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

        String visitorName = request.getParameter("name");
        if (visitorName == null) {
            out.println("Cannot remove visitor without name!" );
            return;
        }
        Visitor visitor = restaurant.findVisitorByName(visitorName);
        restaurant.removeVisitor(visitor);

        out.println("Visitor " + visitorName + " removed");

    }
}
