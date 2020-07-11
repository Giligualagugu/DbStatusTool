package com.xujiale.tools.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author xujiale 2020/7/10 19:21
 */
@Data
@Accessors(chain = true)
public class DataSentFlowChart implements Serializable {

    private Long timestamp;

    /**
     * 当前时刻总流量;
     */
    private Long totalBytes;

    /**
     * 距离上次请求时增加的流量;
     */
    private Long IncrementalBytes;

}
