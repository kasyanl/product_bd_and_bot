package com.example.webinterface.controller;

import by.kasyan.database.service.ExportToExcelService;
import by.kasyan.database.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/")
public class IndexController {

    private ExportToExcelService exportToExcelService;
    private ProductService productService;

    //получение стартовой страницы
    @GetMapping
    public ModelAndView index() {
        var modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    // получение начальной страницы для юзера
    @GetMapping(value = "/content")
    public String content() {
        return "adminpages/content";
    }

    // получение начальной страницы для гостя
    @GetMapping(value = "/contentguest")
    public String contentguest() {
        return "guestpages/contentguest";
    }

    // получения списка категории, для последующего выбора продуктов данной категории для юзера
    @GetMapping(value = "/selectcategorybyread")
    public ModelAndView selectCategoryByRead() {
        var modelAndView = new ModelAndView();
        modelAndView.setViewName("adminpages/selectcategorybyread");
        modelAndView.addObject("categoryName", productService.findByCategoryName());
        return modelAndView;
    }

    // получения списка категории, для последующего выбора продуктов данной категории для гостя
    @GetMapping(value = "/selectcategorybyreadguest")
    public ModelAndView selectCategoryByReadGuest() {
        var modelAndView = new ModelAndView();
        modelAndView.setViewName("guestpages/selectcategorybyreadguest");
        modelAndView.addObject("categoryName", productService.findByCategoryName());
        return modelAndView;
    }

// экспорт данных  в excel основной БД для юзера

    @GetMapping(value = "/exportexcel")
    @ResponseBody
    public ModelAndView exportExcel() {
        var modelAndView = new ModelAndView("adminpages/exportexcel");
        exportToExcelService.exportAllList(productService.findAll());
        return modelAndView;
    }

    // экспорт данных  в excel основной БД для гостя
    @GetMapping(value = "/exportexcelguest")
    @ResponseBody
    public ModelAndView exportExcelGuest() {
        var modelAndView = new ModelAndView("guestpages/exportexcelguest");
        exportToExcelService.exportAllList(productService.findAll());
        return modelAndView;

    }

    @Autowired
    public void setExportToExcelService(ExportToExcelService exportToExcelService) {
        this.exportToExcelService = exportToExcelService;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

}