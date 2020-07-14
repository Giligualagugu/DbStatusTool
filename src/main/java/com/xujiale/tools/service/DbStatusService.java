package com.xujiale.tools.service;

import com.xujiale.tools.dto.DbRateDTO;
import com.xujiale.tools.entity.DbStatus;
import com.xujiale.tools.mapper.CoreMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static com.xujiale.tools.config.CacheConfig.MYSQL_STATUS_57;

/**
 * @author xujiale 2020/7/10 18:44
 */
@Slf4j
@Service
public class DbStatusService {


    @Autowired
    CoreMapper coreMapper;

    @Cacheable(cacheNames = MYSQL_STATUS_57)
    public Map<String, String> loadStatusInfo() {
        log.info("load mysql db status info");
        List<DbStatus> globalStatusInfo = coreMapper.getGlobalStatusInfo();
        Map<String, String> treeMap = new TreeMap<>();
        globalStatusInfo.forEach(e -> treeMap.put(e.getVariableName(), e.getValue()));
        return treeMap;
    }

    @CacheEvict(cacheNames = MYSQL_STATUS_57)
    public void cleanCache() {
//        log.info("clean loadStatusInfo...");
    }


    public DbRateDTO selectQps() {
        long time = Long.parseLong(coreMapper.getUpTime().getValue());
        long questions = Long.parseLong(coreMapper.getQuestions().getValue());
        double qpsCount = BigDecimal.valueOf(questions).divide(BigDecimal.valueOf(time), 2, RoundingMode.HALF_UP).doubleValue();
        log.info("查询量 {}:{}", questions, time);
        return new DbRateDTO().setTime(new Date()).setRateCount(qpsCount);
    }

    public DbRateDTO commitTps() {
        long time = Long.parseLong(coreMapper.getUpTime().getValue());
        long commits = Long.parseLong(coreMapper.getCommitCounts().getValue()) + Long.parseLong(coreMapper.getCommitRollBackCounts().getValue());
        double tps = BigDecimal.valueOf(commits).divide(BigDecimal.valueOf(time), 2, RoundingMode.HALF_UP).doubleValue();
        return new DbRateDTO().setTime(new Date()).setRateCount(tps);
    }

    public DbRateDTO sentFlow() {
        long time = Long.parseLong(coreMapper.getUpTime().getValue());
        long sent = Long.parseLong(coreMapper.getSentFlow().getValue());
        double sentRate = BigDecimal.valueOf(sent).divide(BigDecimal.valueOf(time), 2, RoundingMode.HALF_UP).doubleValue();
        return new DbRateDTO().setTime(new Date()).setRateCount(sentRate);
    }

    public List<DbStatus> dbStatuses() {


        return null;
    }

    public DbRateDTO receivedFlow() {
        long time = Long.parseLong(coreMapper.getUpTime().getValue());
        long received = Long.parseLong(coreMapper.getBytesReceived().getValue());
        double receiveRate = BigDecimal.valueOf(received).divide(BigDecimal.valueOf(time), 2, RoundingMode.HALF_UP).doubleValue();
        return new DbRateDTO().setTime(new Date()).setRateCount(receiveRate);
    }
}
