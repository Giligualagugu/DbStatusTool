package com.xujiale.tools.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author xujiale 2020/7/17 14:32
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @PostMapping("/check")
    public Object valiedPdf(MultipartFile file) {

        try {
            PDDocument load = PDDocument.load(file.getBytes());
            float version = load.getVersion();
            int numberOfPages = load.getNumberOfPages();
            return version + ":" + numberOfPages;
        } catch (IOException e) {
            e.printStackTrace();
        }


        return "ok";
    }
}
