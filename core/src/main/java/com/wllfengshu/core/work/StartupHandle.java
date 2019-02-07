package com.wllfengshu.core.work;

import com.wllfengshu.common.constant.Collective;
import com.wllfengshu.common.utils.FileReplaceUtil;
import com.wllfengshu.common.model.RequestModel;

/**
 * 处理startup.sh文件
 * @author wllfengshu
 */
public class StartupHandle {

    public static void start(RequestModel requestModel){
        //1、修改里面的“model”
        replace(requestModel);
    }

    private static void replace(RequestModel requestModel){
        FileReplaceUtil.replace(
                    requestModel.getProjectPath()+"/startup.sh",
                             new String[]{Collective.MODEL_PROJECT_NAME},
                             new String[]{requestModel.getProjectName()}
        );
    }
}
