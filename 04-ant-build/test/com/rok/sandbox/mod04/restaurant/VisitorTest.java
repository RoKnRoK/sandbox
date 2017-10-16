package com.rok.sandbox.mod04.restaurant;

import com.rok.sandbox.mod04.restaurant.model.Visitor;
import junit.framework.TestCase;

public class VisitorTest extends TestCase {

	public void testVisitor() {
		Visitor p1 = new Visitor("Fred");
		assertEquals("Fred", p1.getName());
		assertEquals(3, p1.getMaximumTablesToBook());
	}

	public void testSetName() {
		Visitor p2 = new Visitor("Fred");
		p2.setName("Harry");
		assertEquals("Harry", p2.getName());
	}

	public void testSetMaximumTables() {
		Visitor p3 = new Visitor("George");
		p3.setMaximumTablesToBook(10);
		assertEquals(10, p3.getMaximumTablesToBook());
	}

	public void testToString(){
		Visitor p4 = new Visitor("Fred Flinstone");
		p4.setMaximumTablesToBook(7);
		String testString = "Fred Flinstone (max tables he can book: 7)";
		assertEquals(testString, p4.toString());
	}
}
