package com.wllfengshu.core;

import com.wllfengshu.common.entity.DBInfo;
import com.wllfengshu.core.after.AfterHandle;
import com.wllfengshu.core.before.BeforeHandle;
import com.wllfengshu.core.work.DockerfileHandle;
import com.wllfengshu.core.work.PomHandle;
import com.wllfengshu.core.work.ReadmeHandle;
import com.wllfengshu.core.work.StartupHandle;
import com.wllfengshu.core.work.resourcesHandle.LogbackHandle;
import com.wllfengshu.core.work.resourcesHandle.MapperHandle;
import com.wllfengshu.core.work.resourcesHandle.PropertiesHandle;
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
        //1、准备工作
        BeforeHandle.start(projectName,packageName);
        //2、配置文件修改
        DockerfileHandle.start(projectName);
        PomHandle.start(projectName,packageName);
        ReadmeHandle.start(projectName);
        StartupHandle.start(projectName);
        //3、java文件修改

        //4、resources文件修改
        LogbackHandle.start(projectName,packageName);
        MapperHandle.start(projectName,packageName,dbInfo);
        PropertiesHandle.start(projectName, packageName);
        //5、善后工作
        AfterHandle.start(projectName);
        return true;
    }
}
