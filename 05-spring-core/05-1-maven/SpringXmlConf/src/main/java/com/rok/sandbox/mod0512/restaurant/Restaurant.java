package com.rok.sandbox.mod0512.restaurant;

import com.rok.sandbox.mod0512.restaurant.dao.Tables;
import com.rok.sandbox.mod0512.restaurant.dao.VisitorsList;
import com.rok.sandbox.mod0512.restaurant.model.Table;
import com.rok.sandbox.mod0512.restaurant.model.Visitor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Map;

@SuppressWarnings("WeakerAccess")
public class Restaurant {


    private String name;

    private Tables tables;
    private VisitorsList visitorsList;

    public Restaurant() {
        this.setName("Undefined name");
        this.tables = new Tables();
        this.visitorsList = new VisitorsList();
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

    public VisitorsList getVisitorsList() {
        return visitorsList;
    }

    public void setVisitorsList(VisitorsList visitorsList) {
        this.visitorsList = visitorsList;
    }

    public void setTables(Tables tables) {
        this.tables = tables;
    }
    public List<Table> getTables() {
        return tables.getTables();
    }

    public Map<String, Visitor> getVisitors() {
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

    public List<Table> getFreeTables() {
        return this.tables.getFreeTables();
    }

    public List<Table> getOccupiedTables() {
        return this.tables.getOccupiedTables();
    }

    public String toString() {
        return this.getName() + ": " + this.getTables().size() + " tables; " + this.getVisitors().size() + " visitors.";
    }

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("app-context.xml");
        //create a new Restaurant
        Restaurant restaurant = (Restaurant) context.getBean("restaurant");
        Table table1 = restaurant.getTables().get(0);
        Table table2 = restaurant.getTables().get(1);
        Table table3 = restaurant.getTables().get(2);

        Visitor jim = restaurant.getVisitors().get(0);
        Visitor sue = restaurant.getVisitors().get(1);

        System.out.println("Just opened new restaurant");
        restaurant.printStatus();

        System.out.println("Sue booked table 1");
        restaurant.bookTable(table1, sue);
        restaurant.printStatus();

        System.out.println("Do some more stuff");
        restaurant.unbookTable(table1);
        restaurant.bookTable(table2, jim);
        restaurant.printStatus();

    }


    private void printStatus(){
        System.out.println("Status of restaurant \n" + this.toString());
        for (Table thisTable : this.getTables()){
            System.out.println(thisTable);
        }

        for(Visitor p : this.getVisitors().values()){
            int count = this.getBookedTables(p).size();
            System.out.println(p + " has booked " + count + " tables");
        }
        System.out.println("Tables free: " + this.getFreeTables().size());
        System.out.println("--- End of report ---");
    }

}
