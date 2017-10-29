package com.rok.sandbox.mod112.restaurant.dao;

import com.rok.sandbox.mod112.restaurant.model.Visitor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by roman.kulikov on 6/12/2017.
 * All rights reserved =D
 */
@SuppressWarnings("unchecked")
@Repository
@Transactional
public class VisitorsList implements VisitorDao {

    @PersistenceContext
    EntityManager entityManager;


    public List<Visitor> getVisitors() {
        return (List<Visitor>) entityManager.createQuery("SELECT p FROM Visitor p").getResultList();
    }

    public void addVisitor(Visitor visitor){
        entityManager.persist(visitor);
    }

    public void removeVisitor(Visitor visitor){
        entityManager.remove(visitor);
    }

    @Override
    public Visitor findVisitorByName(String visitorName) {
        return (Visitor) entityManager
                .createQuery("SELECT p FROM Visitor p WHERE p.name = :name")
                .setParameter("name", visitorName)
                .getSingleResult();
    }
}
