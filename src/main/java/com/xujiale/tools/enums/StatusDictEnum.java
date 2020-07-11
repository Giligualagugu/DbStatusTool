package com.xujiale.tools.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * mysql 状态字段字典
 */
@Getter
@AllArgsConstructor
public enum StatusDictEnum {
    COM_XA_PREPARE("Com_xa_prepare", "", Long.class, "backup");

    /**
     * key 值;
     */
    private final String key;

    /**
     * key 描述
     */
    private final String keyDescription;

    /**
     * value 类型
     */
    private final Class<?> typeClass;

    /**
     * value 备注
     */
    private String note;


}
