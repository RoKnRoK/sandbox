package com.rok.sandbox.mod052.restaurant;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.rok.sandbox.mod052.restaurant.model.Table;
import com.rok.sandbox.mod052.restaurant.model.Visitor;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/app-context.xml" })
public class TableTest implements ApplicationContextAware{

	private ApplicationContext context;

	@Test
	public void testBook(){
		Table b1 = (Table) context.getBean("table1");
		assertEquals(1, b1.getNumber());
		assertEquals("For two persons", b1.getDescription());
	}

	@Test
	public void testGetPerson(){
		Table b2 = (Table) context.getBean("table2");
		Visitor p2 = new Visitor("Elvis");

		b2.setVisitor(p2);

		String testName = b2.getVisitor().getName();
		assertEquals("Elvis", testName);
	}

	@Test
	public void testToString(){
		Table b2 = (Table) context.getBean("table2");
		Visitor p2 = new Visitor("Elvis");

		assertEquals("Table # 2 (massive wooden & steel table); Free", b2.toString());
		b2.setVisitor(p2);
		assertEquals("Table # 2 (massive wooden & steel table); Booked by Elvis", b2.toString());
	}
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.context = applicationContext;
	}
}
