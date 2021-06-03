package com.example.springweb.controller;

import com.example.springweb.service.ProductService;
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
class SearchCategoryControllerTest {

    @Autowired
    public MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    SearchCategoryController searchCategoryController;

    @BeforeEach
    void setUp() {
        searchCategoryController = new SearchCategoryController();
        searchCategoryController.setProductService(productService);
    }

    @Test
    void fineFruits() throws Exception {
        mockMvc.perform(get("/product/finecategory/{category}", "MEAT"))
                .andExpect(status().isOk());
    }

    @Test
    void fineFruitsGuest() throws Exception {
        mockMvc.perform(get("/product/finefruitsguest/{category}", "MEAT"))
                .andExpect(status().isOk());
    }

    @Test
    void alcoholDiscountPage() throws Exception {
        mockMvc.perform(get("/product/categorydiscount/{category}", "MEAT"))
                .andExpect(status().isOk());
    }

    @Test
    void editAlcoholDiscount() throws Exception {

        String discount = "50.0";

        mockMvc.perform(post("/product/categorydiscount/{category}", "MEAT")
                .param("discount", discount))
                .andExpect(status().is(302));
    }
}