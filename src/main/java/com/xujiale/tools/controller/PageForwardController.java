package com.xujiale.tools.controller;

import com.xujiale.tools.entity.DbStatus;
import com.xujiale.tools.service.DbStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author xujiale 2020/7/10 19:35
 */
@Controller
public class PageForwardController {
    @Autowired
    DbStatusService dbStatusService;

    @GetMapping("/index")
    public String indexPage() {
        return "index.html";
    }

    @GetMapping("/hello")
    public String hello() {
        return "login.html";
    }

    @GetMapping("/dblist")
    public String getDbList(Model model) {
        List<DbStatus> dbStatuses = dbStatusService.dbStatuses();
        model.addAttribute("dbList", dbStatuses);
        return "table";
    }

}
