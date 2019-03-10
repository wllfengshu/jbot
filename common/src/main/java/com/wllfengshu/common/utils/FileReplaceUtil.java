package com.wllfengshu.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;

/**
 * 文件内容替换工具类
 * @author wllfengshu
 */
public class FileReplaceUtil {

    /**
     * 替换文件内容(注意：oldStr[]元素个数必须小于等于newStr[])
     * @param filePath 文件名（文件路径+文件名）
     * @param oldStr 待替换内容
     * @param newStr 替换后内容
     */
    public static void replace(String filePath,String[] oldStr,String[] newStr){
        if (oldStr.length>newStr.length){
            return;
        }
        File file = new File(filePath);
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
            e.printStackTrace();
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
                e.printStackTrace();
            }
        }
    }

}
