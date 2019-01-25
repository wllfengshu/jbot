package com.wllfengshu.core.after;

import com.wllfengshu.common.utils.FileZipUtil;
import com.wllfengshu.core.model.RequestModel;

/**
 * 负责core结束后的善后工作
 * @author wllfengshu
 */
public class AfterHandle {

    public static void start(RequestModel requestModel){
        //1、把目标项目压缩为zip文件
        produceZip(requestModel);
    }

    private static void produceZip(RequestModel requestModel){
        FileZipUtil.fileToZip(requestModel.getProjectPath()+".zip",requestModel.getProjectPath());
    }
}
