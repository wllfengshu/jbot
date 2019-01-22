package com.wllfengshu.common.entity;

/**
 * 数据库连接信息
 */
public class ConnectInfo {

    /**
     * 数据库ip
     */
    private String dbIp = null;

    /**
     * 数据库port
     */
    private String dbPort = null;

    /**
     * 数据库name
     */
    private String dbName = null;

    /**
     * 数据库username
     */
    private String dbUsername = null;

    /**
     * 数据库password
     */
    private String dbPassword = null;

    /**
     * 数据库driver
     */
    private String dbDriver = null;


    public String getDbIp() {
        return dbIp;
    }

    public void setDbIp(String dbIp) {
        this.dbIp = dbIp;
    }

    public String getDbPort() {
        return dbPort;
    }

    public void setDbPort(String dbPort) {
        this.dbPort = dbPort;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getDbUsername() {
        return dbUsername;
    }

    public void setDbUsername(String dbUsername) {
        this.dbUsername = dbUsername;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    public String getDbDriver() {
        return dbDriver;
    }

    public void setDbDriver(String dbDriver) {
        this.dbDriver = dbDriver;
    }

    @Override
    public String toString() {
        return "ConnectInfo{" +
                "dbIp='" + dbIp + '\'' +
                ", dbPort='" + dbPort + '\'' +
                ", dbName='" + dbName + '\'' +
                ", dbUsername='" + dbUsername + '\'' +
                ", dbPassword='" + dbPassword + '\'' +
                ", dbDriver='" + dbDriver + '\'' +
                '}';
    }
}
