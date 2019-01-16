package com.wllfengshu.core.work;

import com.wllfengshu.common.constant.Collective;
import com.wllfengshu.common.utils.FileReplaceUtil;
import com.wllfengshu.common.utils.StringUtil;

/**
 * 处理pom.xml文件
 */
public class PomHandle {

    public static void start(String projectName,String packageName){
        //1、修改里面的“包名”和“项目名”
        replace(projectName,packageName);
    }

    private static void replace(String projectName,String packageName){
        FileReplaceUtil.replace(
                Collective.TARGET_PROJECT_HOME+"/"+projectName+"/pom.xml",
                new String[]{"com.wllfengshu",
                             "model",
                             "Model"},
                new String[]{packageName,
                             projectName,
                             StringUtil.toFirstCharUpperCase(projectName)}
        );
    }
}
