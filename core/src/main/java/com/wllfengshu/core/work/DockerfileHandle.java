package com.wllfengshu.core.work;

import com.wllfengshu.common.constant.Collective;
import com.wllfengshu.common.utils.FileReplaceUtil;
import com.wllfengshu.common.utils.TimeUtil;

/**
 * 处理dockerfile文件
 */
public class DockerfileHandle {

    public static void start(String projectName){
        //1、修改里面的“时间”
        replaceTime(projectName);
    }

    private static void replaceTime(String projectName){
        FileReplaceUtil.replace(
                    Collective.TARGET_PROJECT_HOME+"/"+projectName+"/Dockerfile",
                            new String[]{"20190101000000"},
                            new String[]{TimeUtil.getCurrentYearMonth("")}
        );
    }
}
