package com.wllfengshu.jbot.common.utils;

import com.wllfengshu.jbot.common.exception.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 文件压缩工具类
 * @author wllfengshu
 */
public class FileZipUtil {

    private static Logger logger = LoggerFactory.getLogger(FileZipUtil.class);

    /**
     * 递归的把文件压缩为zip文件
     * @param zipFileName
     * @param inputFile
     */
    public static void fileToZip(String zipFileName,String inputFile)throws CustomException {
        logger.info("开始压缩文件，zipFileName:{},inputFile:{}",zipFileName,inputFile);
        ZipOutputStream out = null;
        try {
            out = new ZipOutputStream(new FileOutputStream(zipFileName));
            zip(out,new File(inputFile),"");
        } catch (Exception e) {
            logger.error("压缩文件异常",e);
            throw new CustomException("压缩文件异常", CustomException.ExceptionName.FailedZipFile);
        } finally {
            try {
                if (out != null){
                    out.close();
                }
            }catch (Exception e){
                logger.error("压缩文件异常",e);
            }
        }
        logger.info("文件压缩完毕");
    }

    private static void zip(ZipOutputStream out, File f, String base) throws CustomException {
        FileInputStream in = null;
        try {
            if(f.isDirectory()){
                File[] files = f.listFiles();
                out.putNextEntry(new ZipEntry(base+"/"));
                base=base+"/";
                for (int i = 0; i < files.length; i++) {
                    zip(out,files[i],base+files[i].getName());
                }
            }else{
                out.putNextEntry(new ZipEntry(base));
                in = new FileInputStream(f);
                int b;
                while ((b = in.read()) != -1) {
                    out.write(b);
                }
            }
        } catch (Exception e) {
            logger.error("zip 异常",e);
        }finally{
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e) {
                logger.error("zip 异常 finally",e);
            }
        }
    }
}
