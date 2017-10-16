package com.rok.sandbox.mod0512.restaurant;

import com.rok.sandbox.mod0512.restaurant.model.Table;
import com.rok.sandbox.mod0512.restaurant.model.Visitor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:app-context.xml"})
public class RestaurantTest {

	private final Table b1 = new Table(1, "Table1");
	private final Table b2 = new Table(2, "Table2");
	private final Visitor p1 = new Visitor("Fred");
	private final Visitor p2 = new Visitor("Sue");

	@Autowired
	private Restaurant m1;

	@Before
	public void setup() {
		m1.getVisitors().clear();
		m1.getTables().clear();

		m1.addTable(b1);
		m1.addTable(b2);
		m1.addVisitor(p1);
		m1.addVisitor(p2);
	}

	@Test
	public void testRestaraunt(){

		assertEquals("Happy Whale", m1.getName());

		assertTrue(m1.getTables() != null);
		assertTrue(m1.getVisitors() != null);
	}

	@Test
	public void testAddTable() {

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
	public void testbookTable() {

		assertTrue("Book did not check out correctly", m1.bookTable(b1, p1));

		assertEquals("Fred", b1.getVisitor().getName());

		assertFalse("Book was already checked out", m1.bookTable(b1, p2));

		assertTrue("Book check in failed", m1.unbookTable(b1));

		assertFalse("Book was already checked in", m1.unbookTable(b1));

		assertFalse("Book was never checked out", m1.unbookTable(b2));

	}


	@Test
	public void testgetBookedTables() {
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
	public void testgetFreeTables() {
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
	public void testgetOccupiedTables() {
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
		assertEquals("Happy Whale: 2 tables; 2 visitors.", m1.toString());
	}

}
