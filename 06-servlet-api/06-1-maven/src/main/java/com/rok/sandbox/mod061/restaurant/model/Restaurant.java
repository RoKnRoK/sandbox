package com.rok.sandbox.mod061.restaurant.model;

import com.rok.sandbox.mod061.restaurant.dao.TableDao;
import com.rok.sandbox.mod061.restaurant.dao.Tables;
import com.rok.sandbox.mod061.restaurant.dao.VisitorDao;
import com.rok.sandbox.mod061.restaurant.dao.VisitorsList;

import java.util.List;

public class Restaurant implements TableDao, VisitorDao {


    private String name;

    private Tables tables;
    private VisitorsList visitorsList;

    public Restaurant() {
        this.name = "NoName Restaurant";
        this.tables = new Tables();
        this.visitorsList = new VisitorsList();
    }

    public Restaurant(String name) {
        this.setName(name);
        this.tables = new Tables();
        this.visitorsList = new VisitorsList();
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

    public void addVisitor(Visitor p1) {
        this.visitorsList.addVisitor(p1);
    }

    public void removeVisitor(Visitor p1) {
        this.visitorsList.removeVisitor(p1);
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

    public Visitor findVisitorByName(String name){
        return visitorsList.findVisitorByName(name);
    }
    public Table findTableByNumber(int description){
        return tables.findTableByNumber(description);
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


}
