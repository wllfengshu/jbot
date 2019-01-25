package com.wllfengshu.core.work.javaHandle;

import com.wllfengshu.common.utils.FileUtil;
import com.wllfengshu.common.utils.StringUtil;
import com.wllfengshu.core.model.RequestModel;
import com.wllfengshu.core.model.TableModel;
import com.wllfengshu.core.utils.EntityUtil;

/**
 * 处理entity文件
 * @author wllfengshu
 */
public class EntityHandle {

    public static void start(RequestModel requestModel){
        //1、生成对应的entity文件
        genFile(requestModel);
    }

    private static void genFile(RequestModel requestModel){
        for (TableModel t:requestModel.getTableModels()) {
            String entity=EntityUtil.genEntity(t.getTableNameFUDTU(),requestModel.getEntityPack(),t.getTableInfo());
            FileUtil.createFile(requestModel.getJavaPath()+"/"+StringUtil.spotToSlash(t.getEntityClassName())+".java",entity);
        }
    }
}
