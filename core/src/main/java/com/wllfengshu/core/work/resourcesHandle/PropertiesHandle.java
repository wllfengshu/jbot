package com.wllfengshu.core.work.resourcesHandle;

import com.wllfengshu.common.constant.Collective;
import com.wllfengshu.common.utils.FileReplaceUtil;

/**
 * 处理application.properties文件
 */
public class PropertiesHandle {

    public static void start(String projectName,String packageName){
        //1、修改里面的“mapper映射实体类的包的路径”
        replace(projectName,packageName);
    }

    private static void replace(String projectName,String packageName){
        FileReplaceUtil.replace(
                Collective.TARGET_PROJECT_HOME+"/"+projectName+"/src/main/resources/application.properties",
                new String[]{"com.wllfengshu",
                             "model"},
                new String[]{packageName,
                        projectName}
        );
    }
}
