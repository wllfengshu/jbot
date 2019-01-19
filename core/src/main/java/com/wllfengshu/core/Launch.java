package com.wllfengshu.core;

import com.wllfengshu.common.entity.DBInfo;
import com.wllfengshu.core.after.AfterHandle;
import com.wllfengshu.core.before.BeforeHandle;
import com.wllfengshu.core.model.RequestModel;
import com.wllfengshu.core.work.DockerfileHandle;
import com.wllfengshu.core.work.PomHandle;
import com.wllfengshu.core.work.ReadmeHandle;
import com.wllfengshu.core.work.StartupHandle;
import com.wllfengshu.core.work.javaHandle.*;
import com.wllfengshu.core.work.resourcesHandle.LogbackHandle;
import com.wllfengshu.core.work.resourcesHandle.MapperHandle;
import com.wllfengshu.core.work.resourcesHandle.PropertiesHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 把model工程按照需求进行修改
 */
public class Launch {

    private static Logger logger = LoggerFactory.getLogger(Launch.class);

    public static boolean start(String projectName, String packageName, DBInfo dbInfo){
        logger.info("jbot core,Launch,start-------->dbInfo:%s,projectName:%s,packageName:%s",dbInfo,projectName,packageName);
        //1、准备工作
        RequestModel model = BeforeHandle.start(projectName, packageName, dbInfo);
        //2、配置文件修改
        DockerfileHandle.start(model);
        PomHandle.start(model);
        ReadmeHandle.start(model);
        StartupHandle.start(model);
        //3、java文件修改
        DaoHandle.start(model);
        ServiceHandle.start(model);
        ServiceImplHandle.start(model);
        RestHandle.start(model);
        EntityHandle.start(model);
        ApplicationHandle.start(model);
        //4、resources文件修改
        LogbackHandle.start(model);
        MapperHandle.start(model);
        PropertiesHandle.start(model);
        //5、善后工作
        AfterHandle.start(model);
        return true;
    }
}
