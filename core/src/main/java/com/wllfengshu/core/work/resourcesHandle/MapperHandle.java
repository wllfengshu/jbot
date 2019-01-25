package com.wllfengshu.core.work.resourcesHandle;

import com.wllfengshu.common.utils.FileUtil;
import com.wllfengshu.core.model.RequestModel;
import com.wllfengshu.core.model.TableModel;
import com.wllfengshu.core.utils.MapperUtil;

/**
 * 处理mapper文件
 * @author wllfengshu
 */
public class MapperHandle {

    public static void start(RequestModel requestModel){
        //1、生成对应的xml文件
        genFile(requestModel);
    }

    private static void genFile(RequestModel requestModel){
        for (TableModel t:requestModel.getTableModels()) {
            String mapper=MapperUtil.genMapper(t.getDaoClassName(),t.getEntityClassName(),t.getTableInfo());
            FileUtil.createFile(requestModel.getResourcesPath()+"/mapper/"+t.getTableNameFUDTU()+".xml",mapper);
        }
    }
}
