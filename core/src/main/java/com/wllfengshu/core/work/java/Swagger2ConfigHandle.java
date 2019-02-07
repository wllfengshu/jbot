package com.wllfengshu.core.work.java;

import com.wllfengshu.common.constant.Collective;
import com.wllfengshu.common.model.RequestModel;
import com.wllfengshu.common.utils.FileReplaceUtil;

/**
 * 处理Swagger2Config.java
 * @author wllfengshu
 */
public class Swagger2ConfigHandle {

    public static void start(RequestModel requestModel){
        //1、修改里面的“包名”和“项目名”
        replace(requestModel);
    }

    private static void replace(RequestModel requestModel){
        FileReplaceUtil.replace(
                requestModel.getPackageBasePath()+"/configs/Swagger2Config.java",
                new String[]{Collective.MODEL_PACKAGE_NAME,
                        Collective.MODEL_PROJECT_NAME},
                new String[]{requestModel.getPackageName(),
                        requestModel.getProjectName()}
        );
    }
}
