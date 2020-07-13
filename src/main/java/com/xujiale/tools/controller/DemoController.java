package com.xujiale.tools.controller;

import com.xujiale.tools.dto.DbRateDTO;
import com.xujiale.tools.service.DbStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author xujiale 2020/7/10 17:06
 */
@RestController
@RequestMapping("/chart")
public class DemoController {

    @Autowired
    DbStatusService dbStatusService;

    @GetMapping("/status-info")
    public Map<String, String> getDbStatusInfo() {
        return dbStatusService.loadStatusInfo();
    }


    @GetMapping("/sentFlow")
    public DbRateDTO dataSentFlowChart() {
        return dbStatusService.sentFlow();
    }

    @GetMapping("/qps")
    public DbRateDTO selectQps() {
        return dbStatusService.selectQps();
    }


    @GetMapping("/tps")
    public DbRateDTO commitTps() {
        return dbStatusService.commitTps();
    }
}
