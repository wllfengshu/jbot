package com.wllfengshu.jbot.model;

import lombok.Data;

import java.util.List;

/**
 * 表实体类
 *
 * @author wllfengshu
 */
@Data
public class Table {

    /**
     * 表名
     */
    private String tableName;

    /**
     * 字段集合
     */
    private List<Field> fields;

}
