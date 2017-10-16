package com.rok.sandbox.mod02.restaurant.dao;

import com.rok.sandbox.mod02.restaurant.model.Table;
import com.rok.sandbox.mod02.restaurant.model.Visitor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by roman.kulikov on 6/13/2017.
 * All rights reserved =D
 */
public class Tables implements TableDao {

    private List<Table> tables = new ArrayList<>();

    @Override
    public List<Table> getTables() {
        return tables;
    }
    public void removeTable(Table table) {
        tables.remove(table);
    }

    public void addTable(Table b1) {
        this.tables.add(b1);
    }

    public boolean bookTable(Table table, Visitor visitor) {
        if (table == null) {return false;}
        int booksOutCount = this.getBookedTables(visitor).size();
        boolean canCheckout = (table.getVisitor() == null) && (booksOutCount < visitor.getMaximumTablesToBook());
        if (canCheckout) {
            table.setVisitor(visitor);
        }
        return canCheckout;

    }

    public boolean unbookTable(Table table) {
        if (table == null) {return false;}
        boolean canCheckIn = table.getVisitor() != null;
        if (canCheckIn) {
            table.setVisitor(null);
        }
        return canCheckIn;
    }

    public List<Table> getBookedTables(Visitor p1) {

        return this.getTables().stream().filter(aBook->{
            boolean bookCheckedOut = aBook.getVisitor() != null;
            return bookCheckedOut && aBook.getVisitor().getName().equals(p1.getName());
        }).collect(Collectors.toList());
    }

    public List<Table> getFreeTables() {
        return this.getTables().stream()
                .filter(book-> book.getVisitor() == null)
                .collect(Collectors.toList());
    }

    public List<Table> getOccupiedTables() {
        return this.getTables().stream()
                .filter(book-> book.getVisitor() != null)
                .collect(Collectors.toList());
    }

}
