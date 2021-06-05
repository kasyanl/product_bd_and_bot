package com.example.springweb.controller;

import com.example.springweb.service.ExportToExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/product")
public class ExportToExcelController {

    private ExportToExcelService exportToExcelService;

    // получение списка категории
    @GetMapping(value = "/exportexcel/{category}")
    public String exportExcelAlcohol(@PathVariable String category) {
        exportToExcelService.exportCategoryList(category);
        return "adminpages/exportexcel";
    }

    // получение списка категории для Гостя
    @GetMapping(value = "/exportexcelguest/{category}")
    public String exportExcelAlcoholGuest(@PathVariable String category) {
        exportToExcelService.exportCategoryList(category);
        return "guestpages/exportexcelguest";
    }

    @Autowired
    public void setExportToExcel(ExportToExcelService exportToExcelService) {
        this.exportToExcelService = exportToExcelService;
    }
}