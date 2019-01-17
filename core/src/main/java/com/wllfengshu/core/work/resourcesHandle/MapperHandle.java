package com.wllfengshu.core.work.resourcesHandle;

import com.wllfengshu.common.constant.Collective;
import com.wllfengshu.common.entity.DBInfo;
import com.wllfengshu.common.entity.TableInfo;
import com.wllfengshu.common.utils.FileUtil;
import com.wllfengshu.core.utils.MapperUtil;

public class MapperHandle {

    public static void start(String projectName, String packageName, DBInfo dbInfo){
        //1、根据dbInfo生成对应的xml文件
        genFile(projectName,packageName,dbInfo);
    }

    private static void genFile(String projectName,String packageName,DBInfo dbInfo){
        for (TableInfo tableInfo:dbInfo.getTables()) {
            String mapper=MapperUtil.genMapper(projectName,packageName,tableInfo);
            FileUtil.createFile(Collective.TARGET_PROJECT_HOME+"/"+projectName+"/src/main/resources/mapper/"+tableInfo.getTableName()+".xml",mapper);
        }
    }
}
