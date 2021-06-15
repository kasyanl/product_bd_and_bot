package com.example.webinterface.controller;

import by.kasyan.database.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PersonControllerTest {

    @Autowired
    public MockMvc mockMvc;
    @MockBean
    private PersonService personService;

    PersonController personController;
    @BeforeEach
    void setUp(){
        personController = new PersonController();
        personController.setPersonService(personService);
    }
    @Test
    void startLogin() throws Exception {
        mockMvc.perform(get("/person/startlogin"))
                .andExpect(status().isOk());
    }

    @Test
    void startLoginPost() throws Exception {
        String login = "admin";
        String password = "password";

        mockMvc.perform(post("/person/startlogin")
                .param("login", login)
                .param("password", password))
                .andExpect(status().isOk());
    }

    @Test
    void startLoginPostIsPresent() throws Exception {
        String login = "admin";
        String password = "password";

        Mockito.when(personService.verificationOfAuthenticity(login,password)).thenReturn(true);
        mockMvc.perform(post("/person/startlogin")
                .param("login", login)
                .param("password", password))
                .andExpect(status().isOk());
    }

    @Test
    void currentlogin() throws Exception {
        mockMvc.perform(get("/person/currentlogin"))
                .andExpect(status().isOk());
    }

    @Test
    void nocurrentlogin() throws Exception {
        mockMvc.perform(get("/person/nocurrentlogin"))
                .andExpect(status().isOk());
    }
}