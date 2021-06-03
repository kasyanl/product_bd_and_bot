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
class IndexControllerTest {

    IndexController indexController;

    @Autowired
    public MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        indexController = new IndexController();
    }

    @Test
    void index() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    @Test
    void content() throws Exception {
        mockMvc.perform(get("/content"))
                .andExpect(status().isOk());
    }

    @Test
    void contentguest() throws Exception {
        mockMvc.perform(get("/contentguest"))
                .andExpect(status().isOk());
    }

    @Test
    void selectCategoryByRead() throws Exception {
        mockMvc.perform(get("/selectcategorybyread"))
                .andExpect(status().isOk());
    }

    @Test
    void selectCategoryByReadGuest() throws Exception {
        mockMvc.perform(get("/selectcategorybyreadguest"))
                .andExpect(status().isOk());
    }

    @Test
    void exportExcelDel() throws Exception {
        mockMvc.perform(get("/exportexceldel"))
                .andExpect(status().isOk());
    }

    @Test
    void exportExcel() throws Exception {
        mockMvc.perform(get("/exportexcel"))
                .andExpect(status().isOk());
    }

    @Test
    void exportExcelGuest() throws Exception {
        mockMvc.perform(get("/exportexcelguest"))
                .andExpect(status().isOk());
    }
}