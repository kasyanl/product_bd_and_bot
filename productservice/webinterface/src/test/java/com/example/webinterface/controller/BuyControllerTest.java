package com.example.webinterface.controller;

import by.kasyan.database.service.BuyProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
class BuyControllerTest {

    @Autowired
    public MockMvc mockMvc;

    @MockBean
    private BuyProductService buyProductService;

    BuyController buyController;

    @BeforeEach
    void setUp() {
        buyController = new BuyController();
        buyController.setBuyProductService(buyProductService);
    }

    @Test
    void buyStarted() throws Exception {
        mockMvc.perform(get("/product/buystarted"))
                .andExpect(status().isOk());
    }

    @Test
    void buyProductGet() throws Exception {

        mockMvc.perform(get("/product/buyproduct"))
                .andExpect(status().isOk());
    }

    @Test
    void buyProductPost1() throws Exception {
        String id = "1";
        String quantity = "20.0";
        String totalVolume = "1.0";

        mockMvc.perform(post("/product/buyproduct")
                .param("id", id)
                .param("quantity", quantity)
                .param("totalVolume", totalVolume))
                .andExpect(status().is(302));
    }
    @Test
    void buyProductPost2() throws Exception {
        String id = "1";
        String quantity = "20.0";
        String totalVolume = "40.0";

        mockMvc.perform(post("/product/buyproduct")
                .param("id", id)
                .param("quantity", quantity)
                .param("totalVolume", totalVolume))
                .andExpect(status().is(302));
    }

    @Test
    void endBuyProductGet() throws Exception {

        mockMvc.perform(get("/product/endbuyproduct"))
                .andExpect(status().isOk());
    }

    @Test
    void checkend() throws Exception {

        mockMvc.perform(get("/product/checkend"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteproduct() throws Exception {
        String id = "1";
        mockMvc.perform(get("/product/deleteproductbuy").param("id", id))
                .andExpect(status().is(302));
    }

    @Test
    void failbuyproduct() throws Exception {

        mockMvc.perform(get("/product/failbuyproduct"))
                .andExpect(status().isOk());
    }
}