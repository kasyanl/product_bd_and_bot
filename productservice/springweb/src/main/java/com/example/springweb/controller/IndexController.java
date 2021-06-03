package com.example.springweb.controller;

import com.example.springweb.service.ExportToExcelService;
import com.example.springweb.service.ProductOfDeleteService;
import com.example.springweb.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class IndexController {

    private ExportToExcelService exportToExcelService;
    private ProductService productService;
    private ProductOfDeleteService productOfDeleteService;

    //получение стартовой страницы
    @GetMapping
    public String index() {
        return "index";
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
    public String selectCategoryByRead() {
        return "adminpages/selectcategorybyread";
    }

    // получения списка категории, для последующего выбора продуктов данной категории для гостя
    @GetMapping(value = "/selectcategorybyreadguest")
    public String selectCategoryByReadGuest() {
        return "guestpages/selectcategorybyreadguest";
    }

    // экспорт данных из корзины в excel
    @GetMapping(value = "/exportexceldel")
    public String exportExcelDel() {
        exportToExcelService.exportListOfBasket(productOfDeleteService.findAllDeleted());
        return "adminpages/exportexceldel";
    }

    // экспорт данных  в excel основной БД для юзера
    @GetMapping(value = "/exportexcel")
    public String exportExcel() {
        exportToExcelService.exportAllList(productService.findAll());
        return "adminpages/exportexcel";
    }

    // экспорт данных  в excel основной БД для гостя
    @GetMapping(value = "/exportexcelguest")
    public String exportExcelGuest() {
        exportToExcelService.exportAllList(productService.findAll());
        return "guestpages/exportexcelguest";
    }

    @Autowired
    public void setExportToExcelService(ExportToExcelService exportToExcelService) {
        this.exportToExcelService = exportToExcelService;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setProductOfDeleteService(ProductOfDeleteService productOfDeleteService) {
        this.productOfDeleteService = productOfDeleteService;
    }
}