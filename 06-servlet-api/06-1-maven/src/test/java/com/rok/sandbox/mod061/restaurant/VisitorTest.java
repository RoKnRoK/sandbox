package com.rok.sandbox.mod061.restaurant;

import junit.framework.TestCase;
import com.rok.sandbox.mod061.restaurant.model.Visitor;

public class VisitorTest extends TestCase {

	public void testPerson() {
		Visitor p1 = new Visitor("unknown name");
		assertEquals("unknown name", p1.getName());
		assertEquals(3, p1.getMaximumTablesToBook());
	}

	public void testSetName() {
		Visitor p2 = new Visitor("Fred");
		assertEquals("Fred", p2.getName());
	}

	public void testSetMaximumBooks() {
		Visitor p3 = new Visitor("Fred");
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
