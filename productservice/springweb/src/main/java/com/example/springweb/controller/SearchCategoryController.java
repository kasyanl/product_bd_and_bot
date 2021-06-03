package com.example.springweb.controller;

import com.example.springweb.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/product")
public class SearchCategoryController {

    private ProductService productService;

    // получение списка категории FRUITS
    @GetMapping(value = "/finecategory/{category}")
    public ModelAndView fineFruits(@PathVariable String category) {
        var modelAndView = new ModelAndView();
        modelAndView.setViewName("adminpages/finecategory/" + category);
        modelAndView.addObject("finecategory", productService.fineCategoryForRead(category));
        return modelAndView;
    }

    // получение списка категории FRUITS для Гостя
    @GetMapping(value = "/finefruitsguest/{category}")
    public ModelAndView fineFruitsGuest(@PathVariable String category) {
        var modelAndView = new ModelAndView();
        modelAndView.setViewName("guestpages/finecategory/" + category);
        modelAndView.addObject("finecategory", productService.fineCategoryForRead(category));
        return modelAndView;
    }

    // получение страницы с формой для изменения скидки для категории ALCOHOLIC_BEVERAGES
    @GetMapping(value = "/categorydiscount/{category}")
    public String alcoholDiscountPage(@PathVariable String category) {
        return "adminpages/insertdiscountcategory/" + category;
    }

    // отправка заданной скидки для категории ALCOHOLIC_BEVERAGES
    @PostMapping(value = "/categorydiscount/{category}")
    public ModelAndView editAlcoholDiscount(@RequestParam(value = "discount") double discount,
                                            @PathVariable String category) {
        productService.updateDiscountForCategory(category, discount);
        return new ModelAndView("redirect:/product/finecategory/" + category);
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
}