package com.rok.sandbox.mod0511.restaurant;

import com.rok.sandbox.mod0511.restaurant.AppConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.rok.sandbox.mod0511.restaurant.model.Table;
import com.rok.sandbox.mod0511.restaurant.model.Visitor;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class })
public class TableTest implements ApplicationContextAware{

	private ApplicationContext context;

	@Test
	public void testBook(){
		Table b1 = (Table) context.getBean("book1");
		assertEquals(1, b1.getNumber());
		assertEquals("For two persons", b1.getDescription());
	}

	@Test
	public void testGetPerson(){
		Table b2 = (Table) context.getBean("book2");
		Visitor p2 = new Visitor("Elvis");

		b2.setVisitor(p2);
		
		String testName = b2.getVisitor().getName();
		assertEquals("Elvis", testName);
	}

	@Test
	public void testToString(){
		Table b2 = (Table) context.getBean("book2");
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
