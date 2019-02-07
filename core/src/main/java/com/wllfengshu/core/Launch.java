package com.wllfengshu.core;

import com.wllfengshu.common.entity.DBInfo;
import com.wllfengshu.core.after.AfterHandle;
import com.wllfengshu.core.before.BeforeHandle;
import com.wllfengshu.common.model.RequestModel;
import com.wllfengshu.core.work.DockerfileHandle;
import com.wllfengshu.core.work.PomHandle;
import com.wllfengshu.core.work.ReadmeHandle;
import com.wllfengshu.core.work.StartupHandle;
import com.wllfengshu.core.work.java.*;
import com.wllfengshu.core.work.resources.LogbackHandle;
import com.wllfengshu.core.work.resources.MapperHandle;
import com.wllfengshu.core.work.resources.PropertiesHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 把model工程按照需求进行修改
 */
public class Launch {

    private static Logger logger = LoggerFactory.getLogger(Launch.class);
    private static List<Future<Boolean>> futures = new ArrayList<>();
    private static ExecutorService executorService = Executors.newFixedThreadPool(3);

    public static boolean start(String projectName, String packageName, DBInfo dbInfo){
        logger.info("jbot core,Launch,start-------->dbInfo:%s,projectName:%s,packageName:%s",dbInfo,projectName,packageName);
        try {
            //1、准备工作
            RequestModel model = BeforeHandle.start(projectName, packageName, dbInfo);
            //2、配置文件修改
            futures.add(executorService.submit(() -> {
                DockerfileHandle.start(model);
                PomHandle.start(model);
                ReadmeHandle.start(model);
                StartupHandle.start(model);
                return true;
            }));
            //3、java文件修改
            futures.add(executorService.submit(() -> {
                ApplicationHandle.start(model);
                DaoHandle.start(model);
                EntityHandle.start(model);
                MyMapperHandle.start(model);
                RestHandle.start(model);
                ServiceHandle.start(model);
                ServiceImplHandle.start(model);
                Swagger2ConfigHandle.start(model);
                return true;
            }));
            //4、resources文件修改
            futures.add(executorService.submit(() -> {
                LogbackHandle.start(model);
                MapperHandle.start(model);
                PropertiesHandle.start(model);
                return true;
            }));
            for (Future<Boolean> future : futures) {
                if (!future.isDone()){
                    future.get();
                }
            }
            //5、善后工作
            AfterHandle.start(model);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
