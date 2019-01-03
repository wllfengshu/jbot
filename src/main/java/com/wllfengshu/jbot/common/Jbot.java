package com.wllfengshu.jbot.common;

import com.wllfengshu.jbot.entity.ConnectInfo;
import com.wllfengshu.jbot.entity.DBInfo;

/**
 * 用户需要的数据信息都缓存在该类中
 */
public class Jbot {
    /**
     * 项目名
     */
    public static String projectName = null;
    /**
     * 包名
     */
    public static String packageName = null;
    /**
     * 数据库连接信息
     */
    public static ConnectInfo connectInfo = null;
    /**
     * 数据库信息（包含表和字段）
     */
    public static DBInfo dbInfo = null;
}
