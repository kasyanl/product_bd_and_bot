package com.example.webinterface.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

//Контроллер для скачивания файла и подгрузки изображений

@RequestMapping("/download")
@Controller
public class DownloadsController {

    static System.Logger logger;

    @GetMapping("/img/{fileName:.+}")
    public void downloadPDFResource(HttpServletRequest request,
                                    HttpServletResponse response,
                                    @PathVariable("fileName") String fileName) {
        String dataDirectory = request.getServletContext()
                .getRealPath("productservice/webinterface/src/main/resources/static/img/");
        var file = Paths.get(dataDirectory, fileName);
        if (Files.exists(file)) {
            response.setContentType("application/xls");
            response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
            try {
                Files.copy(file, response.getOutputStream());
                response.getOutputStream().flush();
            } catch (IOException ex) {
                logger.log(System.Logger.Level.INFO, ex);
            }
        }
    }
}