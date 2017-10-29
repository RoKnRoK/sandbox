package com.rok.sandbox.mod112.restaurant.model;

import org.apache.commons.lang3.StringEscapeUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.text.MessageFormat;

@Entity
@javax.persistence.Table(name = "rest_table")
public class Table implements Serializable {


	private static final long serialVersionUID = -5642122700542854935L;
	@Id
	@Column
	private int number;
	@Column
	private String description;

	@ManyToOne(cascade = CascadeType.REFRESH)
	private Visitor visitor;

	public Table() {
		this.number = 100;
		this.description = "Just ordinary table";
	}

	public Table(int number) {
		setDescription("Just ordinary table");
		setNumber(number);
	}

	public Table(int number, String description) {
		setDescription(description);
		setNumber(number);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description == null ? "" : description;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
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
