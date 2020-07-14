package com.xujiale.tools.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author xujiale 2020/7/10 19:35
 */
@Controller
public class PageForwardController {


    @GetMapping("/index")
    public String indexPage() {
        return "index.html";
    }

    @GetMapping("/hello")
    public String hello() {
        return "login.html";
    }


}
