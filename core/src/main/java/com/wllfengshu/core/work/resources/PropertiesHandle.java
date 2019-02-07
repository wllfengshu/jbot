package com.wllfengshu.core.work.resources;

import com.wllfengshu.common.constant.Collective;
import com.wllfengshu.common.utils.FileReplaceUtil;
import com.wllfengshu.common.model.RequestModel;

/**
 * 处理application.properties文件（现在叫application.yml文件）
 * @author wllfengshu
 */
public class PropertiesHandle {

    public static void start(RequestModel requestModel){
        //1、修改里面的“mapper映射实体类的包的路径”
        replace(requestModel);
    }

    private static void replace(RequestModel requestModel){
        FileReplaceUtil.replace(
                    requestModel.getResourcesPath()+"/application.yml",
                             new String[]{Collective.MODEL_PACKAGE_NAME,
                                          Collective.MODEL_PROJECT_NAME},
                             new String[]{requestModel.getPackageName(),
                                          requestModel.getProjectName()}
        );
    }
}
