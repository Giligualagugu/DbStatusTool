package com.xujiale.tools.controller;

import com.xujiale.tools.dto.DataSentFlowChart;
import com.xujiale.tools.service.DbStatusService;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public Map<String, Object> getDbStatusInfo() {
        return dbStatusService.loadStatusInfo();
    }


    @GetMapping("/sentFlow")
    public DataSentFlowChart dataSentFlowChart(@RequestParam("lastTotalBytes") Long lastTotalBytes) {
        DataSentFlowChart dataSentFlowChart = new DataSentFlowChart();
        long total = RandomUtils.nextLong(1000L, 99999L);
        dataSentFlowChart.setTimestamp(System.currentTimeMillis())
                .setTotalBytes(total)
                .setIncrementalBytes(total - lastTotalBytes);
        return dataSentFlowChart;
    }
}
