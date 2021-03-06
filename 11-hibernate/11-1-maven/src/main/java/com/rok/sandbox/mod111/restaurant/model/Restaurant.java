package com.rok.sandbox.mod111.restaurant.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.rok.sandbox.mod111.restaurant.dao.TableDao;
import com.rok.sandbox.mod111.restaurant.dao.Tables;
import com.rok.sandbox.mod111.restaurant.dao.VisitorsList;
import com.rok.sandbox.mod111.restaurant.dao.VisitorDao;

import java.util.List;

@Component
public class Restaurant implements TableDao, VisitorDao {


    private String name;

    @Autowired
    private Tables tables;
    @Autowired
    private VisitorsList visitorsList;

    public Restaurant() {
        this.name = "NoName Restaurant";
    }

    public Restaurant(String name) {
        this();
        this.setName(name);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Table> getTables() {
        return tables.getTables();
    }

    public List<Visitor> getVisitors() {
        return visitorsList.getVisitors();
    }

    public void addVisitor(Visitor visitor) {
        this.visitorsList.addVisitor(visitor);
    }

    public void removeVisitor(Visitor visitor) {
        List<Table> booksForPerson = this.tables.getBookedTables(visitor);
        for (Table table : booksForPerson) {
            this.unbookTable(table);
        }
        this.visitorsList.removeVisitor(visitor);
    }

    @Override
    public Visitor findVisitorByName(String visitorName) {
        return visitorsList.findVisitorByName(visitorName);
    }

    public void removeTable(Table table) {
        this.tables.removeTable(table);
    }

    public void addTable(Table table) {
        this.tables.addTable(table);
    }


    public boolean bookTable(Table table, Visitor visitor) {
        return tables.bookTable(table, visitor);
    }

    public boolean unbookTable(Table table) {
        return tables.unbookTable(table);
    }

    public List<Table> getBookedTables(Visitor visitor) {
        return this.tables.getBookedTables(visitor);
    }

    @Override
    public Table findTableByNumber(int number) {
        return tables.findTableByNumber(number);
    }

    public List<Table> getFreeTables() {
        return this.tables.getFreeTables();
    }

    public List<Table> getOccupiedTables() {
        return this.tables.getOccupiedTables();
    }

    public String toString() {
        return this.getName() + ": " + this.getTables().size() + " tables; " + this.getVisitors().size() + " visitors.";
    }

    public String getStatusInfo() {
        StringBuilder statusInfoBuilder = new StringBuilder();
        statusInfoBuilder.append("Status of restaurant \n").append(this.toString()).append("\n");
        this.getTables().forEach(table -> statusInfoBuilder.append("Table: ").append(table).append("\n"));

        this.getVisitors().forEach(person -> {
            int count = this.getBookedTables(person).size();
            statusInfoBuilder.append(person).append(" has booked ").append(count).append(" tables").append("\n");
        });
        statusInfoBuilder.append("Tables free: ").append(this.getFreeTables().size()).append("\n");
        statusInfoBuilder.append("--- End of report ---");
        return statusInfoBuilder.toString();

    }


}
