package com.wllfengshu.core.work.javaHandle;

import com.wllfengshu.common.constant.Collective;
import com.wllfengshu.common.entity.DBInfo;
import com.wllfengshu.common.entity.TableInfo;
import com.wllfengshu.common.utils.FileUtil;
import com.wllfengshu.common.utils.StringUtil;
import com.wllfengshu.core.utils.RestUtil;

public class RestHandle {

    public static void start(String projectName, String packageName, DBInfo dbInfo){
        //1、根据dbInfo生成对应的rest文件
        genFile(projectName,packageName,dbInfo);
    }

    private static void genFile(String projectName,String packageName,DBInfo dbInfo){
        for (TableInfo tableInfo:dbInfo.getTables()) {
            String rest=RestUtil.genRest(projectName,packageName,tableInfo);
            FileUtil.createFile(Collective.TARGET_PROJECT_HOME+"/"+projectName+"/src/main/java/"+StringUtil.spotToSlash(packageName)+"/"+projectName+"/rest/"+StringUtil.toFirstCharUpperCase(StringUtil.underlineToHump(StringUtil.delTUnderline(tableInfo.getTableName())))+"Rest.java",rest);
        }
    }
}
