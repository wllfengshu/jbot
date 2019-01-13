package com.wllfengshu.core.after;

import com.wllfengshu.common.constant.Collective;
import com.wllfengshu.common.utils.ZipUtil;
import com.wllfengshu.core.before.BeforeHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 负责core结束后的善后工作
 */
public class AfterHandle {

    private static Logger logger = LoggerFactory.getLogger(BeforeHandle.class);

    public static void start(String projectName){
        produceZip(projectName);
    }

    private static void produceZip(String projectName){
        ZipUtil.fileToZip(Collective.TARGET_PROJECT_HOME+"/"+projectName+".zip",Collective.TARGET_PROJECT_HOME+"/"+projectName);
    }

    public static void main(String[] args) {
        start("user");
    }
}
