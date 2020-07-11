package com.xujiale.tools.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author xujiale 2020/7/10 19:08
 */
@EnableScheduling
@Service
public class TimePeriodService {

    @Autowired
    DbStatusService dbStatusService;

    @Scheduled(cron = "2/10 * * * * *")
    public void doJob() {
        dbStatusService.cleanCache();
    }

}
