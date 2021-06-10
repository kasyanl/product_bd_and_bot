package com.example.webinterface.controller;

import com.example.springweb.service.ExportToExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@ComponentScan(basePackages = "com.example.springweb")
@RequestMapping(value = "/product")
public class ExportToExcelController {

    private ExportToExcelService exportToExcelService;


    // получение списка категории
    @GetMapping(value = "/exportexcel/{category}")
    @ResponseBody
    public ModelAndView exportExcelAlcohol(@PathVariable String category) {
        var modelAndView = new ModelAndView("adminpages/exportexcel");
        exportToExcelService.exportCategoryList(category);
        return modelAndView;
    }

    // получение списка категории для Гостя
    @GetMapping(value = "/exportexcelguest/{category}")
    @ResponseBody
    public ModelAndView exportExcelAlcoholGuest(@PathVariable String category) {
        var modelAndView = new ModelAndView("guestpages/exportexcelguest");
        exportToExcelService.exportCategoryList(category);
        return modelAndView;
    }

    @Autowired
    public void setExportToExcel(ExportToExcelService exportToExcelService) {
        this.exportToExcelService = exportToExcelService;
    }
}