package com.xujiale.tools.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author xujiale 2020/7/13 16:15
 */
@Data
@Accessors(chain = true)
public class DbRateDTO {

    @JsonFormat(pattern = "HH:mm:ss")
    private Date time;

    private Double rateCount;

    private String unit;
}
