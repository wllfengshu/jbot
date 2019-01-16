package com.wllfengshu.core.work.resourcesHandle;

import com.wllfengshu.common.constant.Collective;
import com.wllfengshu.common.utils.FileReplaceUtil;

/**
 * 处理logback文件
 */
public class LogbackHandle {

    public static void start(String projectName,String packageName){
        //1、修改里面的“包名”
        replace(projectName,packageName);
    }

    private static void replace(String projectName,String packageName){
        FileReplaceUtil.replace(
                Collective.TARGET_PROJECT_HOME+"/"+projectName+"/src/main/resources/logback.xml",
                new String[]{"com.wllfengshu"},
                new String[]{packageName}
        );
    }
}
