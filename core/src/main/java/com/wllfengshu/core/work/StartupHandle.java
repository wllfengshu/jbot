package com.wllfengshu.core.work;

import com.wllfengshu.common.constant.Collective;
import com.wllfengshu.common.utils.FileReplaceUtil;

/**
 * 处理startup.sh文件
 */
public class StartupHandle {
    public static void start(String projectName){
        //1、修改里面的“model”
        replaceProjectName(projectName);
    }

    private static void replaceProjectName(String projectName){
        FileReplaceUtil.replace(
                Collective.TARGET_PROJECT_HOME+"/"+projectName+"/startup.sh",
                        new String[]{"model"},
                        new String[]{projectName}
        );
    }

    public static void main(String[] args) {
        replaceProjectName("aaaa");
    }
}
