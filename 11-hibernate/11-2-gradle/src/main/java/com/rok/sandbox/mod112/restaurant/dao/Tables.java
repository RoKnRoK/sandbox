package com.rok.sandbox.mod112.restaurant.dao;

import com.rok.sandbox.mod112.restaurant.model.Table;
import com.rok.sandbox.mod112.restaurant.model.Visitor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.List;

/**
 * Created by roman.kulikov on 6/13/2017.
 * All rights reserved =D
 */
@SuppressWarnings("unchecked")
@Repository
@Transactional
public class Tables implements TableDao {

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    EntityManager entityManager;

    @Override
    public List<Table> getTables() {
        return (List<Table>) entityManager.createQuery("SELECT b FROM Table b").getResultList();
    }
    public void removeTable(Table table) {
        entityManager.remove(table);
    }

    public void addTable(Table table) {
        entityManager.persist(table);
    }

    public boolean bookTable(Table table, Visitor visitor) {
        if (table == null) {return false;}
        System.out.println("b1 attached: " + entityManager.contains(table));
        System.out.println("p1 attached: " + entityManager.contains(visitor));
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

    public List<Table> getBookedTables(Visitor visitor) {
        return (List<Table>) entityManager
                .createQuery("SELECT b FROM Table b WHERE b.visitor = :visitor")
                .setParameter("visitor", visitor)
                .getResultList();
    }

    @Override
    public Table findTableByNumber(int number) {
        return (Table) entityManager
                .createQuery("SELECT b FROM Table b WHERE b.number = :number")
                .setParameter("number", number).getSingleResult();
    }

    public List<Table> getFreeTables() {
        return (List<Table>) entityManager
                .createQuery("SELECT b FROM Table b WHERE b.visitor IS NULL")
                .getResultList();
    }

    public List<Table> getOccupiedTables() {
        return (List<Table>) entityManager
                .createQuery("SELECT b FROM Table b WHERE b.visitor IS NOT NULL")
                .getResultList();
    }

}
