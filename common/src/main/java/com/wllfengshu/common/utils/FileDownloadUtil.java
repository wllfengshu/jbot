package com.wllfengshu.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.net.URLEncoder;

/**
 * 文件下载工具类
 * @author wllfengshu
 */
public class FileDownloadUtil {

    private static Logger logger = LoggerFactory.getLogger(FileDownloadUtil.class);

    /**
     * 文件下载
     * @param fileName 文件名（带路径）
     * @param response 请求体
     */
    public static void download(String fileName, HttpServletResponse response){
        logger.info("开始文件下载，文件名：{}",fileName);
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            File file = new File(fileName);
            if (!file.exists()){
                logger.error("待下载文件不存在，fileName:{}",fileName);
                return;
            }
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode(file.getName(), "UTF-8"));
            response.setHeader("Content-Length", String.valueOf(file.length()));
            bis = new BufferedInputStream(new FileInputStream(file));
            bos = new BufferedOutputStream(response.getOutputStream());
            byte[] buff = new byte[2048];
            while (true) {
                int bytesRead;
                if (-1 == (bytesRead = bis.read(buff, 0, buff.length))) {
                    break;
                }
                bos.write(buff, 0, bytesRead);
            }
            logger.info("文件下载完成");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (bis != null){
                    bis.close();
                }
                if (bos != null){
                    bos.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
