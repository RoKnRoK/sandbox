package com.rok.sandbox.mod02.restaurant;

import com.rok.sandbox.mod02.restaurant.model.Table;
import com.rok.sandbox.mod02.restaurant.model.Visitor;
import junit.framework.TestCase;

public class TableTest extends TestCase {

	public void testTable(){
		Table b1 = new Table(1, "table for 4");
		assertEquals("table for 4", b1.getDescription());
		assertEquals(1, b1.getNumber());
	}

	public void testGetVisitor(){
		Table b2 = new Table(2, "Table for three");
		Visitor p2 = new Visitor("Elvis");

		b2.setVisitor(p2);

		String testName = b2.getVisitor().getName();
		assertEquals("Elvis", testName);
	}

	public void testToString(){
		Table b2 = new Table(3, "Table for three");
		Visitor p2 = new Visitor("Elvis");

		assertEquals("Table # 3 (table for three); Free", b2.toString());
		b2.setVisitor(p2);
		assertEquals("Table # 3 (table for three); Booked by Elvis", b2.toString());
	}

}
