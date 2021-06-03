package com.example.springweb.service;

import com.example.springweb.bean.Person;
import com.example.springweb.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonServiceTest {

    @MockBean
    private PersonRepository personRepository;

    PersonService personService;

    @BeforeEach
    void setUp() {
        personService = new PersonService();
        personService.setPersonRepository(personRepository);
    }

    @Test
    void verificationOfAuthenticityTrue() {
        Mockito.when(personRepository.findAll())
                .thenReturn(List.of(new Person(1, "admin1", "password2"),
                        new Person(2, "admin", "password")));
        assertTrue(personService.verificationOfAuthenticity("admin", "password"));
    }
    @Test
    void verificationOfAuthenticityFalse() {
        Mockito.when(personRepository.findAll())
                .thenReturn(List.of(new Person(1, "admin1", "password2"),
                        new Person(2, "admin", "password")));
        assertFalse(personService.verificationOfAuthenticity("admin1", "password"));
    }

    @Test
    void findAllPerson() {
        Mockito.when(personRepository.findAll())
                .thenReturn(List.of(new Person(1, "admin1", "pasword2"),
                        new Person(2, "admin", "pasword")));

        List <Person> expected = List.of(new Person(1, "admin1", "pasword2"),
        new Person(2, "admin", "pasword"));

        List <Person> actual = personService.findAllPerson();

        assertEquals(expected, actual);
    }
}