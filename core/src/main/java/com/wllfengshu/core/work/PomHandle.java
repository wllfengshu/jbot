package com.wllfengshu.core.work;

import com.wllfengshu.common.constant.Collective;
import com.wllfengshu.common.utils.FileReplaceUtil;
import com.wllfengshu.common.utils.StringUtil;
import com.wllfengshu.core.model.RequestModel;

/**
 * 处理pom.xml文件
 * @author wllfengshu
 */
public class PomHandle {

    public static void start(RequestModel requestModel){
        //1、修改里面的“包名”和“项目名”
        replace(requestModel);
    }

    private static void replace(RequestModel requestModel){
        FileReplaceUtil.replace(
                    requestModel.getProjectPath()+"/pom.xml",
                             new String[]{Collective.MODEL_PACKAGE_NAME,
                                          Collective.MODEL_PROJECT_NAME,
                                          StringUtil.toFirstCharUpperCase(Collective.MODEL_PROJECT_NAME)},
                             new String[]{requestModel.getPackageName(),
                                          requestModel.getProjectName(),
                                          StringUtil.toFirstCharUpperCase(requestModel.getProjectName())}
        );
    }
}
