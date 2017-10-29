package com.rok.sandbox.mod112.restaurant.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Visitor implements Serializable{

	private static final long serialVersionUID = 2882516343614167364L;

	@Id
	@Column
	private String name;

	@Column
	private int maximumTablesToBook;
	
	public Visitor(){
		name = "unknown name";
		maximumTablesToBook = 3;
	}

	public Visitor(String name) {
		this.name = name;
		maximumTablesToBook = 3;
	}

	//methods
	public String getName(){
		return name;	
	}
	
	public void setName(String anyName){
		this.name = anyName == null ? "NoName" : anyName;
	}

	public int getMaximumTablesToBook() {
		return maximumTablesToBook;
	}

	public void setMaximumTablesToBook(int maximumTablesToBook) {
		this.maximumTablesToBook = maximumTablesToBook;
	}	
	
	public String toString(){
		return this.getName() + " (max tables he can book: " + this.getMaximumTablesToBook()+")";
	}
}
