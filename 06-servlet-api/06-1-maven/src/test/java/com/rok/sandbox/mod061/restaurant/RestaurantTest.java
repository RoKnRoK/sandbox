package com.rok.sandbox.mod061.restaurant;

import org.junit.Before;
import org.junit.Test;
import com.rok.sandbox.mod061.restaurant.model.Restaurant;
import com.rok.sandbox.mod061.restaurant.model.Table;
import com.rok.sandbox.mod061.restaurant.model.Visitor;

import java.util.List;

import static org.junit.Assert.*;

public class RestaurantTest {

	private Table b1;
	private Table b2;
	private Visitor p1;
	private Visitor p2;
	private Restaurant m1;

	@Test
	public void testRestaraunt(){
		Restaurant m1 = new Restaurant("Test");

		assertEquals("Test", m1.getName());

		assertTrue(m1.getTables() != null);
		assertTrue(m1.getVisitors() != null);
	}

	@Before
	public void setup(){
		b1 = new Table(1, "Table1");
		b2 = new Table(2, "Table2");

		p1 = new Visitor("Fred");
		p2 = new Visitor("Sue");

		m1 = new Restaurant("Test");
		
		m1.addTable(b1);
		m1.addTable(b2);
		m1.addVisitor(p1);
		m1.addVisitor(p2);
	}


	@Test
	public void testAddBook(){

		assertEquals(2, m1.getTables().size());
		assertEquals(0, m1.getTables().indexOf(b1));
		assertEquals(1, m1.getTables().indexOf(b2));
		
		m1.removeTable(b1);
		assertEquals(1, m1.getTables().size());
		assertEquals(0, m1.getTables().indexOf(b2));
		
		m1.removeTable(b2);
		assertEquals(0, m1.getTables().size());
	}

	@Test
	public void testbookTable(){

		assertTrue("Table did not check out correctly", m1.bookTable(b1,p1));
		
		assertEquals("Fred", b1.getVisitor().getName());
		
		assertFalse("Table was already checked out", m1.bookTable(b1,p2));
		
		assertTrue("Table check in failed", m1.unbookTable(b1));
		
		assertFalse("Table was already checked in", m1.unbookTable(b1));
		
		assertFalse("Table was never checked out", m1.unbookTable(b2));

	}


	@Test
	public void testgetBookedTables(){
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

	@Test
	public void testgetFreeTables(){
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

	@Test
	public void testgetOccupiedTables(){
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

	@Test
	public void testToString() {
		assertEquals("Test: 2 tables; 2 visitors.", m1.toString());
	}
	
}
