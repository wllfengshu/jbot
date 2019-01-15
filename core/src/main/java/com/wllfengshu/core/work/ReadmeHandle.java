package com.wllfengshu.core.work;

import com.wllfengshu.common.constant.Collective;
import com.wllfengshu.common.utils.FileReplaceUtil;

/**
 * 处理readme.md文件
 */
public class ReadmeHandle {
    public static void start(String projectName){
        //1、修改里面的“模板”
        replaceModel(projectName);
    }

    private static void replaceModel(String projectName){
        FileReplaceUtil.replace(
                Collective.TARGET_PROJECT_HOME+"/"+projectName+"/README.md",
                        new String[]{"标准模板"},
                        new String[]{projectName+"项目"}
        );
    }

    public static void main(String[] args) {
        replaceModel("aaaa");
    }
}
