package com.example.webinterface.controller;

import com.example.springweb.service.ExportToExcelService;
import com.example.springweb.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@ComponentScan(basePackages = "com.example.springweb")
@RequestMapping(value = "/product")
public class ExportToExcelController {

    private ExportToExcelService exportToExcelService;
    private ProductService productService;


    // получение списка категории
    @GetMapping(value = "/exportexcel/{category}")
    public ModelAndView exportExcelAlcohol(@PathVariable String category) {
        ModelAndView modelAndView = new ModelAndView("adminpages/exportexcel");
        modelAndView.addObject("dataForCategory",  exportToExcelService.exportCategoryList(category));
        return modelAndView;
    }

    // получение списка категории для Гостя
    @GetMapping(value = "/exportexcelguest/{category}")
    public ModelAndView exportExcelAlcoholGuest(@PathVariable String category) {
        ModelAndView modelAndView = new ModelAndView("guestpages/exportexcelguest");
        modelAndView.addObject("dataForCategoryG",  exportToExcelService.exportCategoryList(category));
        return modelAndView;
    }

    @Autowired
    public void setExportToExcel(ExportToExcelService exportToExcelService) {
        this.exportToExcelService = exportToExcelService;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
}