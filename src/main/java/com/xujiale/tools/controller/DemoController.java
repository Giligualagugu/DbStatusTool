package com.xujiale.tools.controller;

import com.xujiale.tools.dto.DbRateDTO;
import com.xujiale.tools.service.DbStatusService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author xujiale 2020/7/10 17:06
 */
@Slf4j
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
    public DbRateDTO selectQps(@RequestParam("time") Long time, @RequestParam("count") Long count, HttpServletRequest httpServletRequest) {
        log.info("上次时间戳:{} 上次查询总量:{}", time, count);

//        int maxInactiveInterval = httpServletRequest.getSession().getMaxInactiveInterval();
//
//        System.out.println(maxInactiveInterval);

        return dbStatusService.selectQps();
    }


    @GetMapping("/tps")
    public DbRateDTO commitTps() {
        return dbStatusService.commitTps();
    }
}
