package com.rok.sandbox.mod081.restaurant.dao;

import com.rok.sandbox.mod081.restaurant.model.Table;
import com.rok.sandbox.mod081.restaurant.model.Visitor;

import java.util.List;

/**
 * Created by roman.kulikov on 6/13/2017.
 * All rights reserved =D
 */
public interface TableDao {
    void removeTable(Table table);

    void addTable(Table b1);

    boolean bookTable(Table table, Visitor visitor);
    boolean unbookTable(Table table);

    List<Table> getBookedTables(Visitor p1);
    Table findTableByNumber(int description);

    List<Table> getTables();
    List<Table> getFreeTables();
    List<Table> getOccupiedTables();

}
