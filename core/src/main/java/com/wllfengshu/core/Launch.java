package com.wllfengshu.core;

import com.wllfengshu.common.entity.DBInfo;
import com.wllfengshu.common.utils.LogUtil;

/**
 * 把model工程按照需求进行修改
 */
public class Launch {
    public static boolean start(String projectName, String packageName, DBInfo dbInfo){
        LogUtil.info(null,"jbot core:start-------->dbInfo:%s,projectName:%s,packageName:%s",dbInfo,projectName,packageName);
        System.out.println("开始调用jbot核心启动类...");
        return false;
    }
}
