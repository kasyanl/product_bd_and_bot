module by.kasyan.database {
    requires java.persistence;
    requires lombok;
    requires org.hibernate.orm.core;
    requires spring.data.jpa;
    requires spring.context;
    requires spring.data.commons;
    requires spring.beans;
    requires spring.tx;
    requires org.apache.poi.poi;
    requires spring.boot.autoconfigure;
    requires spring.boot;
    exports by.kasyan.database;
    exports by.kasyan.database.service;
    exports by.kasyan.database.bean;
}