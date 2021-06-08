package com.example.webinterface.controller;

import com.example.springweb.service.BuyProductService;
import com.example.springweb.service.ExportToExcelService;
import com.example.springweb.service.ProductService;
import com.example.springweb.service.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@ComponentScan(basePackages = "com.example.springweb")
@RequestMapping(value = "/product")
public class BuyController {

    private ExportToExcelService exportToExcelService;
    private BuyProductService buyProductService;
    private ProductService productService;
    private UtilService utilService;


    // получение страницы с формой для добавления продукта
    @GetMapping(value = "/buystarted")
    public String buyStarted() {
        buyProductService.cleanBuyDB();
        return "adminpages/buystarted";
    }

    @GetMapping(value = "/buyproduct")
    public ModelAndView buyProductGet() {
        var modelAndView = new ModelAndView();
        modelAndView.setViewName("adminpages/buyproduct");
        modelAndView.addObject("buyProduct", productService.findAll());
        modelAndView.addObject("totalPrice", utilService.totalPrise());
        return modelAndView;
    }

    // отправка данных для добавления продуктов в БД и перенаправления на страницу со всем списком
    @PostMapping(value = "/buyproduct")
    public ModelAndView buyProductPost(@RequestParam(value = "id") int id,
                                       @RequestParam(value = "quantity") double quantity,
                                       @RequestParam(value = "totalVolume") double totalVolume,
                                       Model model) {
        model.addAttribute("id", id)
                .addAttribute("quantity", quantity)
                .addAttribute("totalVolume", totalVolume);

        if (utilService.checkingForNumber(quantity, totalVolume)) {
            buyProductService.saveBayProduct(id, quantity);
        }
        var modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/product/buyproduct");
        return modelAndView;
    }

    // получение страницы с формой для добавления продукта
    @GetMapping(value = "/endbuyproduct")
    public ModelAndView endBuyProductGet() {
        var modelAndView = new ModelAndView();
        modelAndView.setViewName("adminpages/endbuyproduct");
        modelAndView.addObject("product", buyProductService.findAllBuyProduct());
        modelAndView.addObject("totalPriceEnd", utilService.totalPrise());
        return modelAndView;
    }

    // получение страницы с формой для добавления продукта
    @GetMapping(value = "/checkend")
    public ModelAndView checkend() {
        var modelAndView = new ModelAndView();
        modelAndView.setViewName("adminpages/checkend");
        modelAndView.addObject("chek", exportToExcelService.check(buyProductService.findAllBuyProduct()));
        utilService.endTransaction();
        modelAndView.addObject("product", buyProductService.findAllBuyProduct());
        return modelAndView;
    }

    // получение страницы с сообщением, что продукт удален из основной БД
    @GetMapping(value = "/deleteproductbuy")
    public ModelAndView deleteproduct(@RequestParam(value = "id") int id) {
        var modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/product/endbuyproduct");
        buyProductService.deleteBuy(id);
        return modelAndView;
    }

    // получение страницы с сообщением, что продукт удален из основной БД
    @GetMapping(value = "/failbuyproduct")
    public ModelAndView failbuyproduct() {
        var modelAndView = new ModelAndView();
        modelAndView.setViewName("adminpages/content");
        buyProductService.cleanBuyDB();
        return modelAndView;
    }

    @Autowired
    public void setExportToExcelService(ExportToExcelService exportToExcelService) {
        this.exportToExcelService = exportToExcelService;
    }

    @Autowired
    public void setBuyProductService(BuyProductService buyProductService) {
        this.buyProductService = buyProductService;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setUtilService(UtilService utilService) {
        this.utilService = utilService;
    }
}