package com.wllfengshu.common.utils;

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
    public static void fileToZip(String zipFileName,String inputFile){
        ZipOutputStream out = null;
        try {
            out = new ZipOutputStream(new FileOutputStream(zipFileName));
            zip(out,new File(inputFile),"");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null){
                    out.close();
                }
            }catch (Exception e){

            }
        }
    }

    private static void zip(ZipOutputStream out, File f, String base) {
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
            e.printStackTrace();
        }finally{
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
