package com.wllfengshu.core;

import com.wllfengshu.common.entity.DBInfo;
import com.wllfengshu.core.after.AfterHandle;
import com.wllfengshu.core.before.BeforeHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.OutputStream;

/**
 * 把model工程按照需求进行修改
 */
public class Launch {

    private static Logger logger = LoggerFactory.getLogger(Launch.class);

    public static boolean start(String projectName, String packageName, DBInfo dbInfo){
        logger.info("jbot core,Launch,start-------->dbInfo:%s,projectName:%s,packageName:%s",dbInfo,projectName,packageName);
        BeforeHandle.start(projectName,packageName);
        //...
        AfterHandle.start(projectName);
        return true;
    }
}
