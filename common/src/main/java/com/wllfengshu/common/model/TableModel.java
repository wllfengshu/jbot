package com.wllfengshu.common.model;

import com.wllfengshu.common.entity.TableInfo;

/**
 * 每张表的model(下面以t_user表为例进行注释)
 * @author wllfengshu
 */
public class TableModel {

    /**
     * 首字母大写，并且去掉了下划线的表名,eg:User
     */
    private String tableNameFUDTU;

    /**
     * 首字母小写，并且去掉了下划线的表名,eg:user
     */
    private String tableNameFLDTU;

    /**
     * 待生成entity的class名,eg:com.wllfenghu.dns.entity.UserEntity
     */
    private String entityClassName;

    /**
     * 待生成dao的class名,eg:com.wllfenghu.dns.dao.UserDao
     */
    private String daoClassName;

    /**
     * 待生成service的class名,eg:com.wllfenghu.dns.service.UserService
     */
    private String serviceClassName;

    /**
     * 待生成serviceImpl的class名,eg:com.wllfenghu.dns.service.impl.UserServiceImpl
     */
    private String serviceImplClassName;

    /**
     * 表信息
     */
    private TableInfo tableInfo;

    public String getTableNameFUDTU() {
        return tableNameFUDTU;
    }

    public void setTableNameFUDTU(String tableNameFUDTU) {
        this.tableNameFUDTU = tableNameFUDTU;
    }

    public String getTableNameFLDTU() {
        return tableNameFLDTU;
    }

    public void setTableNameFLDTU(String tableNameFLDTU) {
        this.tableNameFLDTU = tableNameFLDTU;
    }

    public String getEntityClassName() {
        return entityClassName;
    }

    public void setEntityClassName(String entityClassName) {
        this.entityClassName = entityClassName;
    }

    public String getDaoClassName() {
        return daoClassName;
    }

    public void setDaoClassName(String daoClassName) {
        this.daoClassName = daoClassName;
    }

    public String getServiceClassName() {
        return serviceClassName;
    }

    public void setServiceClassName(String serviceClassName) {
        this.serviceClassName = serviceClassName;
    }

    public String getServiceImplClassName() {
        return serviceImplClassName;
    }

    public void setServiceImplClassName(String serviceImplClassName) {
        this.serviceImplClassName = serviceImplClassName;
    }

    public TableInfo getTableInfo() {
        return tableInfo;
    }

    public void setTableInfo(TableInfo tableInfo) {
        this.tableInfo = tableInfo;
    }

    @Override
    public String toString() {
        return "TableModel{" +
                "tableNameFUDTU='" + tableNameFUDTU + '\'' +
                ", tableNameFLDTU='" + tableNameFLDTU + '\'' +
                ", entityClassName='" + entityClassName + '\'' +
                ", daoClassName='" + daoClassName + '\'' +
                ", serviceClassName='" + serviceClassName + '\'' +
                ", serviceImplClassName='" + serviceImplClassName + '\'' +
                ", tableInfo=" + tableInfo +
                '}';
    }
}
