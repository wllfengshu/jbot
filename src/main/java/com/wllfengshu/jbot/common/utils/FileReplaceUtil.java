package com.wllfengshu.jbot.common.utils;

import com.wllfengshu.jbot.common.exception.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;

/**
 * 文件内容替换工具类
 * @author wllfengshu
 */
public class FileReplaceUtil {

    private static Logger logger = LoggerFactory.getLogger(FileReplaceUtil.class);

    /**
     * 替换文件内容(注意：oldStr[]元素个数必须小于等于newStr[])
     * @param filePath 文件名（文件路径+文件名）
     * @param oldStr 待替换内容
     * @param newStr 替换后内容
     */
    public static void replace(String filePath,String[] oldStr,String[] newStr)throws CustomException {
        logger.info("开始替换文件内容，fielPath:{},oldStr size:{},newStr size:{}",filePath,oldStr.length,newStr.length);
        if (oldStr.length>newStr.length){
            logger.error("oldStr元素个数:{}必须小于等于newStr:{}",oldStr.length,newStr.length);
            return;
        }
        File file = new File(filePath);
        if (!file.exists()){
            logger.error("待替换文件不存在，filePath:{}",filePath);
            return;
        }
        Long fileLength = file.length();
        byte[] fileContext = new byte[fileLength.intValue()];
        FileInputStream in = null;
        PrintWriter out = null;
        try {
            in = new FileInputStream(filePath);
            in.read(fileContext);
            String str = new String(fileContext, "utf-8");
            for (int i = 0; i < oldStr.length; i++) {
                str = str.replace(oldStr[i], newStr[i]);
            }
            out = new PrintWriter(filePath);
            out.write(str);
        } catch (Exception e) {
            logger.error("替换文件内容异常",e);
            throw new CustomException("替换文件内容异常", CustomException.ExceptionName.FailedReplaceFile);
        } finally {
            try {
                if (out != null) {
                    out.flush();
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (Exception e) {
                logger.error("替换文件内容异常 finally",e);
            }
        }
        logger.info("文件替换完成");
    }

}
