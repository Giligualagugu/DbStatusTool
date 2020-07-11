package com.xujiale.tools.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author xujiale 2020/7/10 19:35
 */
@Controller
public class PageForwardController {


    @GetMapping("/hello")
    public String helloPage(Model model) {
        model.addAttribute("name", "我是徐可乐");
        return "hello";
    }
}
