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
    @GetMapping(value = "/categoryfind/{category}")
    @ResponseBody
    public ModelAndView fineFruits(@PathVariable("category") String category) {
        var modelAndView = new ModelAndView("adminpages/categoryfind");
        modelAndView.addObject("categoryProduct", productService.fineCategoryForRead(category));
        modelAndView.addObject("categoryName", category);
        return modelAndView;
    }

    // получение списка категории FRUITS для Гостя
    @GetMapping(value = "/categoryfindguest/{category}")
    public ModelAndView fineFruitsGuest(@PathVariable ("category") String category) {
        var modelAndView = new ModelAndView();
        modelAndView.setViewName("guestpages/categoryfindguest");
        modelAndView.addObject("categoryNameGuest", category);
        modelAndView.addObject("finecategoryguest", productService.fineCategoryForRead(category));
        return modelAndView;
    }

    // получение страницы с формой для изменения скидки для категории ALCOHOLIC_BEVERAGES
    @GetMapping(value = "/categorydiscount/{category}")
    public ModelAndView alcoholDiscountPage(@PathVariable ("category") String category) {
        var modelAndView = new ModelAndView("adminpages/insertdiscountcategory");
        modelAndView.addObject("categoryName", category);
        return modelAndView;
    }

    // отправка заданной скидки для категории ALCOHOLIC_BEVERAGES
    @PostMapping(value = "/categorydiscount/{category}")
    public ModelAndView editAlcoholDiscount(@RequestParam(value = "discount") double discount,
                                            @PathVariable ("category") String category) {
        productService.updateDiscountForCategory(category, discount);
        return new ModelAndView("redirect:/product/categoryfind/" + category);
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
}