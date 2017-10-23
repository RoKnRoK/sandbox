package com.rok.sandbox.mod072.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.rok.sandbox.mod072.restaurant.model.Table;
import com.rok.sandbox.mod072.restaurant.model.Restaurant;
import com.rok.sandbox.mod072.restaurant.model.Visitor;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by roman.kulikov on 6/13/2017.
 * All rights reserved =D
 */
@RestController
@RequestMapping(value = "/restaurant")
public class RestaurantController {

    @Autowired
    private Restaurant restaurant;

    @PostConstruct
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
    }

    @RequestMapping(value="", produces = MimeTypeUtils.TEXT_PLAIN_VALUE)
    public String restaurantStatus() {
        return restaurant.getStatusInfo();
    }

    @RequestMapping(value = "/tables", method = RequestMethod.GET, produces = MimeTypeUtils.TEXT_PLAIN_VALUE)
    public String getTablesAsString(@RequestParam(value = "status", required = false) String status, @RequestParam(value = "visitorName", required = false) String visitorName) {
        List<Table> tables = getTables(status, visitorName);
        return tables.stream().map (Table::toString).collect(Collectors.joining("\n"));
    }

    private List<Table> getTables(@RequestParam(value = "status", required = false) String status, @RequestParam(value = "visitorName", required = false) String visitorName) {
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

    @RequestMapping(value = "/tables/add", method = RequestMethod.POST)
    public String addTable(@RequestParam(value = "title") int number) {
        Table newTable = new Table(number);
        restaurant.addTable(newTable);
        return "Table #" + number + " created";
    }

    @RequestMapping(value = "/tables/remove", method = RequestMethod.DELETE)
    public String removeTable(@RequestParam(value = "number") int number) {

        Table theTable = restaurant.findTableByNumber(number);
        restaurant.removeTable(theTable);
        return "Table " + number + " removed";
    }

    @RequestMapping(value = "/tables/book", method = RequestMethod.PUT)
    public String bookTable(@RequestParam(value = "number") int number, @RequestParam(value = "visitor") String visitorName) {
        Table table = restaurant.findTableByNumber(number);
        Visitor visitor = restaurant.findVisitorByName(visitorName);
        boolean booked = restaurant.bookTable(table, visitor);
        return "Table " + number + (booked ? "" : "not") + " booked by " + visitorName;
    }

    @RequestMapping(value = "/tables/unbook", method = RequestMethod.PUT)
    public String unbookTable(@RequestParam(value = "number") int number) {
        Table table = restaurant.findTableByNumber(number);
        boolean checkedIn = restaurant.unbookTable(table);
        return "Table " + number + (checkedIn ? "" : "not") + " unbooked";
    }

    @RequestMapping(value = "/visitors", method = RequestMethod.GET, produces = MimeTypeUtils.TEXT_PLAIN_VALUE)
    public String getVisitors() {
        List<Visitor> visitors = restaurant.getVisitors();
        return visitors.stream().map (Visitor::toString).collect(Collectors.joining("\n"));
    }

    @RequestMapping(value = "/visitors/add", method = RequestMethod.POST)
    public String addVisitor(@RequestParam(value = "name") String visitorName) {
        if (visitorName == null) {
            return "Cannot create visitor without name!";
        }
        Visitor visitor = new Visitor(visitorName);
        restaurant.addVisitor(visitor);
        return "Visitor " + visitorName + " created";
    }

    @RequestMapping(value = "/visitors/remove", method = RequestMethod.DELETE)
    public String removeVisitor(@RequestParam(value = "name") String visitorName) {
        if (visitorName == null) {
            return "Cannot remove visitor without name!" ;
        }
        Visitor visitor = restaurant.findVisitorByName(visitorName);
        restaurant.removeVisitor(visitor);
        return "Visitor " + visitorName + " removed";
    }
}
