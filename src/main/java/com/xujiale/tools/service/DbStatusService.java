package com.xujiale.tools.service;

import com.xujiale.tools.entity.DbStatus;
import com.xujiale.tools.mapper.CoreMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

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
    public Map<String, Object> loadStatusInfo() {
        log.info("load mysql db status info");
        List<DbStatus> globalStatusInfo = coreMapper.getGlobalStatusInfo();
        Map<String, Object> treeMap = new TreeMap<>();
        globalStatusInfo.forEach(e -> treeMap.put(e.getVariableName(), e.getValue()));
        return treeMap;
    }

    @CacheEvict(cacheNames = MYSQL_STATUS_57)
    public void cleanCache() {
        log.info("clean loadStatusInfo...");
    }



}
