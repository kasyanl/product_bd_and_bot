package com.example.springweb.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ExportToExcelControllerTest {

    @Autowired
    public MockMvc mockMvc;
    ExportToExcelController exportToExcelController;

    @BeforeEach
    void setUp() {
        exportToExcelController = new ExportToExcelController();
    }

    @Test
    void exportExcelAlcohol() throws Exception {

        mockMvc.perform(get("/product/exportexcel/{category}",  "MEAT"))
                .andExpect(status().isOk());
    }

    @Test
    void exportExcelAlcoholGuest() throws Exception {
        mockMvc.perform(get("/product/exportexcelguest/{category}",  "MEAT"))
                .andExpect(status().isOk());
    }
}