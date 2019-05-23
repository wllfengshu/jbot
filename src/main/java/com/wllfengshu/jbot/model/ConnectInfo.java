package com.wllfengshu.jbot.model;

import lombok.Data;

/**
 * 数据库连接信息
 *
 * @author wllfengshu
 */
@Data
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

}
