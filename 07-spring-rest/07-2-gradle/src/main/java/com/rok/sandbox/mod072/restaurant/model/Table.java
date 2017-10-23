package com.rok.sandbox.mod072.restaurant.model;

import org.apache.commons.lang3.StringEscapeUtils;

import java.text.MessageFormat;

public class Table {
	private int number;
	private String description;
	private Visitor visitor;

	public Table(int number) {
		setDescription("Just ordinary table");
		setNumber(number);
	}

	public Table(int number, String description) {
		setDescription(description);
		setNumber(number);
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description == null ? "" : description;
	}

	public void setVisitor(Visitor visitor) {
		this.visitor = visitor;
	}

	public Visitor getVisitor() {
		return this.visitor;
	}

	public String toString() {
		String isFree = (this.getVisitor() == null) ? "Free" : "Booked by " + this.getVisitor().getName();

		return MessageFormat.format("Table # {0} ({1}); {2}", this.getNumber(), StringEscapeUtils.unescapeXml(this.getDescription().toLowerCase()), isFree);
	}


}
