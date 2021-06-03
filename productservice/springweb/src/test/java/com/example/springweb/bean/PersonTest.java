package com.example.springweb.bean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class PersonTest {

    Person person;
    @BeforeEach
    void setUp(){
        person = new Person();
    }

    @Test
    void testEquals() {
        Person expected = new Person(2, "FRUITS", "Cocos");
        Person actual = new Person(2, "FRUITS", "Cocos");
        assertTrue(expected.equals(actual) && actual.equals(expected));
    }

    @Test
    void testHashCode() {
        Person expected = new Person(2, "FRUITS", "Cocos");
        Person actual = new Person(2, "FRUITS", "Cocos");
        assertEquals(expected.hashCode(), actual.hashCode());
    }

    @Test
    void testToString() {
        person.setId(1);
        person.setLogin("login");
        person.setPassword("password");
        String expected = "Person(id=1, login=login, password=password)";
        assertEquals(expected, person.toString());
    }
}