package com.example.springweb.controller;

import com.example.springweb.service.ProductOfDeleteService;
import com.example.springweb.service.ProductService;
import com.example.springweb.service.UtilService;
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
class ProductCrudOperationControllerTest {

    @Autowired
    public MockMvc mockMvc;
    @MockBean
    private ProductService productService;
    @MockBean
    private ProductOfDeleteService productOfDeleteService;
    @MockBean
    private UtilService utilService;

    ProductCrudOperationController productCrudOperationController;

    @BeforeEach
    void setUp() {
        productCrudOperationController = new ProductCrudOperationController();
        productCrudOperationController.setProductService(productService);
        productCrudOperationController.setProductOfDeleteService(productOfDeleteService);
        productCrudOperationController.setUtilService(utilService);

    }

    @Test
    void findAll() throws Exception {
        mockMvc.perform(get("/product/allproduct"))
                .andExpect(status().isOk());
    }

    @Test
    void findAllForGuest() throws Exception {
        mockMvc.perform(get("/product/allproductguest"))
                .andExpect(status().isOk());
    }

    @Test
    void addproduct() throws Exception {
        mockMvc.perform(get("/product/addproduct"))
                .andExpect(status().isOk());
    }

    @Test
    void add() throws Exception {
        String category = "category";
        String name = "name";
        String price = "2.0";
        String discount = "10.0";
        String totalVolume = "200.0";

        mockMvc.perform(post("/product/addproduct")
                .param("category", category)
                .param("name", name)
                .param("price", price)
                .param("discount", discount)
                .param("totalVolume", totalVolume))
                .andExpect(status().is(302));
    }

    @Test
    void bascket() throws Exception {
        mockMvc.perform(get("/product/bascket"))
                .andExpect(status().is(302));
    }
    @Test
    void bascketIsEmpty() throws Exception {
        Mockito.when(utilService.basketIsEmpty())
                .thenReturn(true);
        mockMvc.perform(get("/product/bascket"))
                .andExpect(status().is(302));
    }

    @Test
    void bascketEmpty() throws Exception {
        mockMvc.perform(get("/product/bascketempty"))
                .andExpect(status().isOk());
    }

    @Test
    void findAllDeletedProduct() throws Exception {
        mockMvc.perform(get("/product/alldeletedproduct"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteproduct() throws Exception {

        String id = "1";
        mockMvc.perform(get("/product/deleteproduct")
                .param("id", id))
                .andExpect(status().is(302));
    }

    @Test
    void deleteproductbasket() throws Exception {
        String id = "1";
        mockMvc.perform(get("/product/deleteproductbasket")
                .param("id", id))
                .andExpect(status().isOk());

        mockMvc.perform(get("/product/recoveredproduct")
                .param("id", id))
                .andExpect(status().isOk());

        mockMvc.perform(get("/product/editproduct")
                .param("id", id))
                .andExpect(status().isOk());
    }

    @Test
    void cleanBascket() throws Exception {
        mockMvc.perform(get("/product/cleanbascket"))
                .andExpect(status().isOk());
    }

    @Test
    void recoveredAllProduct() throws Exception {
        mockMvc.perform(get("/product/recoveredallproduct"))
                .andExpect(status().isOk());
    }

    @Test
    void testEdit() throws Exception {
        String id = "1";
        String category = "category";
        String name = "name";
        String price = "2.0";
        String discount = "10.0";
        String totalVolume = "200.0";


        mockMvc.perform(post("/product/editproduct")
                .param("id", id)
                .param("category", category)
                .param("name", name)
                .param("price", price)
                .param("discount", discount)
                .param("totalVolume", totalVolume))
                .andExpect(status().is(302));
    }

    @Test
    void sortAdmin() throws Exception {
        mockMvc.perform(get("/product/a/{sort}", "sortbyid"))
                .andExpect(status().isOk());
    }

    @Test
    void sortGuest() throws Exception {
        mockMvc.perform(get("/product/g/{sort}", "sortbyidguest"))
                .andExpect(status().isOk());
    }
}