package com.wllfengshu.core.work;

import com.wllfengshu.common.utils.FileReplaceUtil;
import com.wllfengshu.common.utils.TimeUtil;
import com.wllfengshu.common.model.RequestModel;

/**
 * 处理dockerfile文件
 * @author wllfengshu
 */
public class DockerfileHandle {

    public static void start(RequestModel requestModel){
        //1、修改里面的“时间”
        replace(requestModel);
    }

    private static void replace(RequestModel requestModel){
        FileReplaceUtil.replace(
                    requestModel.getProjectPath()+"/Dockerfile",
                             new String[]{"20190101000000"},
                             new String[]{TimeUtil.getCurrentYearMonth("")}
        );
    }
}
