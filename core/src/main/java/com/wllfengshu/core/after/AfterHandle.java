package com.wllfengshu.core.after;

import com.wllfengshu.common.utils.FileZipUtil;
import com.wllfengshu.common.model.RequestModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 负责core结束后的善后工作
 * @author wllfengshu
 */
public class AfterHandle {

    private static Logger logger = LoggerFactory.getLogger(AfterHandle.class);

    public static void start(RequestModel model){
        //1、把目标项目压缩为zip文件
        produceZip(model);
    }

    private static void produceZip(RequestModel model){
        FileZipUtil.fileToZip(model.getProjectPath()+".zip",model.getProjectPath());
    }
}
