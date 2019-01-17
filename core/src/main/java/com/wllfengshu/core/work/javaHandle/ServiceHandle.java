package com.wllfengshu.core.work.javaHandle;

import com.wllfengshu.common.constant.Collective;
import com.wllfengshu.common.entity.DBInfo;
import com.wllfengshu.common.entity.TableInfo;
import com.wllfengshu.common.utils.FileUtil;
import com.wllfengshu.core.utils.ServiceUtil;
import com.wllfengshu.common.utils.StringUtil;

public class ServiceHandle {

    public static void start(String projectName, String packageName, DBInfo dbInfo){
        //1、根据dbInfo生成对应的service文件
        genFile(projectName,packageName,dbInfo);
}

    private static void genFile(String projectName,String packageName,DBInfo dbInfo){
        for (TableInfo tableInfo:dbInfo.getTables()) {
            String service= ServiceUtil.genService(projectName,packageName,tableInfo);
            FileUtil.createFile(Collective.TARGET_PROJECT_HOME+"/"+projectName+"/src/main/java/"+StringUtil.spotToSlash(packageName)+"/"+projectName+"/service/"+StringUtil.toFirstCharUpperCase(StringUtil.underlineToHump(StringUtil.delTUnderline(tableInfo.getTableName())))+"Service.java",service);
        }
    }
}
