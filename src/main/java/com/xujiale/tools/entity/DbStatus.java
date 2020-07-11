package com.xujiale.tools.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author xujiale 2020/7/10 17:15
 */
@Data
@Accessors(chain = true)
public class DbStatus implements Serializable {

    private String VariableName;

    private Object value;
}
