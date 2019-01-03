package com.wllfengshu.jbot.entity;

import java.util.List;

public class DBInfo {
    /**
     * 数据库名
     */
    private String dbName = null;
    /**
     * 表集合
     */
    private List<TableInfo> tables = null;

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public List<TableInfo> getTables() {
        return tables;
    }

    public void setTables(List<TableInfo> tables) {
        this.tables = tables;
    }

    @Override
    public String toString() {
        return "DBInfo{" +
                "dbName='" + dbName + '\'' +
                ", tables=" + tables +
                '}';
    }
}
