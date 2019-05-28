package com.wllfengshu.jbot.model.vo;

import lombok.Data;

/**
 * 数据库连接信息
 *
 * @author wllfengshu
 */
@Data
public class ConnectInfoVO {

    /**
     * 数据库ip
     */
    private String dbIp;

    /**
     * 数据库port
     */
    private String dbPort;

    /**
     * 数据库name
     */
    private String dbName;

    /**
     * 数据库username
     */
    private String dbUsername;

    /**
     * 数据库password
     */
    private String dbPassword;

}
