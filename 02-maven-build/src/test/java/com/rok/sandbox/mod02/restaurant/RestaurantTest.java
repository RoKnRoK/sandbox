package com.rok.sandbox.mod02.restaurant;

import com.rok.sandbox.mod02.restaurant.model.Table;
import com.rok.sandbox.mod02.restaurant.model.Visitor;
import junit.framework.TestCase;

import java.util.List;

public class RestaurantTest extends TestCase {

	private Table b1;
	private Table b2;
	private Visitor p1;
	private Visitor p2;
	private Restaurant m1;

	public void testRestaraunt(){
		Restaurant m1 = new Restaurant("Test");

		assertEquals("Test", m1.getName());

		assertTrue(m1.getTables() != null);
		assertTrue(m1.getVisitors() != null);
	}

	private void setup(){
		b1 = new Table(1, "Table1");
		b2 = new Table(2, "Table2");

		p1 = new Visitor("Fred");
		p2 = new Visitor("Sue");

		m1 = new Restaurant("Test");

	}

	public void testAddTable(){

		setup();

		assertEquals(0, m1.getTables().size());

		m1.addTable(b1);
		m1.addTable(b2);

		assertEquals(2, m1.getTables().size());
		assertEquals(0, m1.getTables().indexOf(b1));
		assertEquals(1, m1.getTables().indexOf(b2));

		m1.removeTable(b1);
		assertEquals(1, m1.getTables().size());
		assertEquals(0, m1.getTables().indexOf(b2));

		m1.removeTable(b2);
		assertEquals(0, m1.getTables().size());
	}

	public void testCheckOut(){
		setup();
		addItems();

		assertTrue("Table wasn't booked correctly", m1.bookTable(b1, p1));

		assertEquals("Fred", b1.getVisitor().getName());

		assertFalse("Table was already booked", m1.bookTable(b1, p2));

		assertTrue("Table unbooking failed", m1.unbookTable(b1));

		assertFalse("Table was already booked", m1.unbookTable(b1));

		assertFalse("Table was never booked", m1.unbookTable(b2));

		setup();
		p1.setMaximumTablesToBook(1);
		addItems();

		assertTrue("First table did not get booked", m1.bookTable(b2, p1));
		assertFalse("Second table should not have been booked", m1.bookTable(b1, p1));
	}

	private void addItems() {
		m1.addTable(b1);
		m1.addTable(b2);
		m1.addVisitor(p1);
		m1.addVisitor(p2);
	}

	public void testGetTablesForVisitor(){
		setup();
		addItems();
		assertEquals(0, m1.getBookedTables(p1).size());

		m1.bookTable(b1, p1);

		List<Table> testTables = m1.getBookedTables(p1);
		assertEquals(1, testTables.size());
		assertEquals(0, testTables.indexOf(b1));

		m1.bookTable(b2, p1);
		testTables = m1.getBookedTables(p1);
		assertEquals(2, testTables.size());
		assertEquals(1, testTables.indexOf(b2));
	}

	public void testGetFreeTables(){
		setup();
		addItems();
		List<Table> testTables = m1.getFreeTables();
		assertEquals(2, testTables.size());
		assertEquals(1, testTables.indexOf(b2));

		m1.bookTable(b1, p1);

		testTables = m1.getFreeTables();
		assertEquals(1, testTables.size());
		assertEquals(0, testTables.indexOf(b2));

		m1.bookTable(b2, p1);
		testTables = m1.getFreeTables();
		assertEquals(0, testTables.size());
	}

	public void testGetBookedTables(){
		setup();
		addItems();
		assertEquals(0, m1.getOccupiedTables().size());

		m1.bookTable(b1, p1);

		List<Table> testTables = m1.getOccupiedTables();
		assertEquals(1, testTables.size());
		assertEquals(0, testTables.indexOf(b1));

		m1.bookTable(b2, p1);
		testTables = m1.getOccupiedTables();
		assertEquals(2, testTables.size());
		assertEquals(1, testTables.indexOf(b2));
	}

	public void testToString() {
		setup();
		addItems();
		assertEquals("Test: 2 tables; 2 visitors.", m1.toString());
	}

}
