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
class DownloadsControllerTest {

    @Autowired
    public MockMvc mockMvc;

    DownloadsController downloadsController;

    @BeforeEach
    void setUp() {
        downloadsController = new DownloadsController();

    }
    @Test
    void downloadPDFResource() throws Exception {
        mockMvc.perform(get("/download/xls/{fileName:.+}",  "check.xls"))
                .andExpect(status().isOk());
    }
}