package com.xujiale.tools.service;

import com.xujiale.tools.mapper.FoobarMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author xujiale 2020/7/10 19:08
 */
@EnableScheduling
@Service
@Slf4j
public class TimePeriodService {

    @Autowired
    DbStatusService dbStatusService;

    @Autowired
    FoobarMapper foobarMapper;

    @Scheduled(cron = "2/10 * * * * *")
    public void doJob() {
        dbStatusService.cleanCache();
    }

    @Scheduled(cron = "6/10 * * * * *")
    public void doTestDB() {

        Integer integer = foobarMapper.selectCount(null);
        log.info("测试数据总量:" + integer);
    }
}
