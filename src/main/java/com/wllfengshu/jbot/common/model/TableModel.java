package com.wllfengshu.jbot.common.model;

import com.wllfengshu.jbot.web.entity.TableInfo;

/**
 * 每张表的model(下面以t_user表为例进行注释)
 * @author wllfengshu
 */
public class TableModel {

    /**
     * 首字母大写、驼峰形式的表名,eg:User
     */
    private String tableNameFUDTU;

    /**
     * 首字母小写、驼峰形式的表名,eg:user
     */
    private String tableNameFLDTU;

    /**
     * 待生成entity的class路径,eg:com.wllfenghu.dns.entity.UserEntity
     */
    private String entityClassPack;

    /**
     * 待生成dao的class路径,eg:com.wllfenghu.dns.dao.UserDao
     */
    private String daoClassPack;

    /**
     * 待生成service的class路径,eg:com.wllfenghu.dns.service.UserService
     */
    private String serviceClassPack;

    /**
     * 待生成serviceImpl的class路径,eg:com.wllfenghu.dns.service.impl.UserServiceImpl
     */
    private String serviceImplClassPack;

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

    public String getEntityClassPack() {
        return entityClassPack;
    }

    public void setEntityClassPack(String entityClassPack) {
        this.entityClassPack = entityClassPack;
    }

    public String getDaoClassPack() {
        return daoClassPack;
    }

    public void setDaoClassPack(String daoClassPack) {
        this.daoClassPack = daoClassPack;
    }

    public String getServiceClassPack() {
        return serviceClassPack;
    }

    public void setServiceClassPack(String serviceClassPack) {
        this.serviceClassPack = serviceClassPack;
    }

    public String getServiceImplClassPack() {
        return serviceImplClassPack;
    }

    public void setServiceImplClassPack(String serviceImplClassPack) {
        this.serviceImplClassPack = serviceImplClassPack;
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
                ", entityClassPack='" + entityClassPack + '\'' +
                ", daoClassPack='" + daoClassPack + '\'' +
                ", serviceClassPack='" + serviceClassPack + '\'' +
                ", serviceImplClassPack='" + serviceImplClassPack + '\'' +
                ", tableInfo=" + tableInfo +
                '}';
    }
}
