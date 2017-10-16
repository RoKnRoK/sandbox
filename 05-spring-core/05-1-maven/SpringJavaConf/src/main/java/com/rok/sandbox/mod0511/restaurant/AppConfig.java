package com.rok.sandbox.mod0511.restaurant;

import com.rok.sandbox.mod0511.restaurant.dao.Tables;
import com.rok.sandbox.mod0511.restaurant.dao.VisitorsList;
import com.rok.sandbox.mod0511.restaurant.model.Table;
import com.rok.sandbox.mod0511.restaurant.model.Visitor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by roman.kulikov on 6/13/2017.
 * All rights reserved =D
 */
@Configuration
public class AppConfig {

    @Bean
    public Table book1() {
        return new Table(1, "For two persons");
    }

    @Bean
    public Table book2() {
        return new Table(2, "Massive wooden &amp; steel table");
    }

    @Bean
    public Table book3() {
        return new Table(3, "Billiards table ");
    }

    @Bean
    public Visitor jim() {
        return new Visitor("Jim");
    }

    @Bean
    public Visitor sue() {
        return new Visitor("Sue");
    }

    @Bean
    public VisitorsList peopleRegistry() {
        return new VisitorsList();
    }

    @Bean
    public Tables bookShelf() {
        return new Tables();
    }

    @Bean
    public Restaurant restaurant() {
        return new Restaurant("Happy Whale");
    }
}
