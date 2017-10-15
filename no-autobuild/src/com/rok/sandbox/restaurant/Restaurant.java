package com.rok.sandbox.restaurant;

import com.rok.sandbox.restaurant.model.Table;
import com.rok.sandbox.restaurant.model.Visitor;
import com.rok.sandbox.restaurant.dao.TableDao;
import com.rok.sandbox.restaurant.dao.Tables;
import com.rok.sandbox.restaurant.dao.VisitorDao;
import com.rok.sandbox.restaurant.dao.VisitorsList;

import java.util.List;

@SuppressWarnings("WeakerAccess")
public class Restaurant implements TableDao, VisitorDao {



	private String name;

	private Tables tables;
	private VisitorsList visitorsList;

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
		//create a new Restaurant
		Restaurant restaurant = new Restaurant("Happy Whale");
		Table table1 = initTable(restaurant, 1, "For two persons");
		Table table2 = initTable(restaurant, 2, "Massive wooden table");

		Visitor jim = initVisitor(restaurant, "Jim");
		Visitor sue = initVisitor(restaurant, "Sue");
		
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

	private static Visitor initVisitor(Restaurant restaurant, String visitorName) {
		Visitor visitor = new Visitor(visitorName);
		restaurant.addVisitor(visitor);
		return visitor;
	}

	private static Table initTable(Restaurant restaurant, int tableNumber, String description) {
		Table table = new Table(tableNumber, description);
		restaurant.addTable(table);
		return table;
	}

	private void printStatus(){
		System.out.println("Status of restaurant \n" + this.toString());
		for (Table thisTable : this.getTables()){
			System.out.println(thisTable);
		}
		
		for(Visitor p : this.getVisitors()){
			int count = this.getBookedTables(p).size();
			System.out.println(p + " has booked " + count + " tables");
		}
		System.out.println("Tables free: " + this.getFreeTables().size());
		System.out.println("--- End of report ---");
	}
		
}
