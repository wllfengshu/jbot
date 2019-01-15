package com.wllfengshu.core.work;

import com.wllfengshu.common.constant.Collective;
import com.wllfengshu.common.utils.FileReplaceUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 处理dockerfile文件
 */
public class DockerfileHandle {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void start(String projectName){
        //1、修改里面的“时间”
        replaceTime(projectName);
    }

    private static void replaceTime(String projectName){
        FileReplaceUtil.replace(
                    Collective.TARGET_PROJECT_HOME+"/"+projectName+"/Dockerfile",
                            new String[]{"2019-01-01 00:00:00"},
                            new String[]{sdf.format(new Date())}
        );
    }

    public static void main(String[] args) {
        replaceTime("aaaa");
    }
}
