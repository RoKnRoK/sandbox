package com.rok.sandbox.mod061.restaurant;

import com.rok.sandbox.mod061.restaurant.model.Table;
import org.junit.Test;
import com.rok.sandbox.mod061.restaurant.model.Visitor;

import static org.junit.Assert.assertEquals;

public class TableTest {

	@Test
	public void testBook(){
		Table b1 = new Table(1, "For two persons");
		assertEquals(1, b1.getNumber());
		assertEquals("For two persons", b1.getDescription());
	}

	@Test
	public void testGetPerson(){
		Table b2 = new Table(2, "book2");
		Visitor p2 = new Visitor("Elvis");

		b2.setVisitor(p2);

		String testName = b2.getVisitor().getName();
		assertEquals("Elvis", testName);
	}

	@Test
	public void testToString(){
		Table b2 = new Table(2, "massive wooden & steel table");
		Visitor p2 = new Visitor("Elvis");

		assertEquals("Table # 2 (massive wooden & steel table); Free", b2.toString());
		b2.setVisitor(p2);
		assertEquals("Table # 2 (massive wooden & steel table); Booked by Elvis", b2.toString());
	}

}
