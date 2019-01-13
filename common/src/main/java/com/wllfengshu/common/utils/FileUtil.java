package com.wllfengshu.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * 文件工具类
 */
public class FileUtil {

    /**
     * 创建文件
     * @param destFileName
     * @return
     */
    public static boolean createFile(String destFileName) {
        File file = new File(destFileName);
        try{
            if (file.exists()) {
                return true;
            }
            if (file.createNewFile()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 创建目录
     * @param destDirName
     * @return
     */
    public static boolean createDir(String destDirName) {
        File dir = new File(destDirName);
        if (dir.exists()) {
            return true;
        }
        if (dir.mkdirs()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 复制文件夹
     * @param sourcePath
     * @param newPath
     */
    public static void copyDir(String sourcePath, String newPath) {
        try {
            File file = new File(sourcePath);
            String[] filePath = file.list();
            if (!(new File(newPath)).exists()) {
                (new File(newPath)).mkdir();
            }
            if (filePath==null){
                return;
            }
            for (int i = 0; i < filePath.length; i++) {
                if ((new File(sourcePath + file.separator + filePath[i])).isDirectory()) {
                    copyDir(sourcePath + file.separator + filePath[i], newPath + file.separator + filePath[i]);
                }
                if (new File(sourcePath + file.separator + filePath[i]).isFile()) {
                    copyFile(sourcePath + file.separator + filePath[i], newPath + file.separator + filePath[i]);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 复制文件
     * @param oldPath
     * @param newPath
     */
    public static void copyFile(String oldPath, String newPath) {
        FileInputStream in = null;
        FileOutputStream out = null;
        try {
            File oldFile = new File(oldPath);
            if (!oldFile.exists()){
                return;
            }
            File file = new File(newPath);
            if (!file.exists()){
                file.createNewFile();
            }
            in = new FileInputStream(oldFile);
            out = new FileOutputStream(file);
            byte[] buffer = new byte[2048];
            int readByte = 0;
            while ((readByte = in.read(buffer)) != -1) {
                out.write(buffer, 0, readByte);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (out!=null){
                    out.close();
                }
                if (in!=null){
                    in.close();
                }
            }catch (Exception e1){
                e1.printStackTrace();
            }
        }
    }

    /**
     * 删除一个文件。
     * @param filename
     */
    public static void deleteFile(String filename){
        File file = new File(filename);
        if (file.isDirectory()) {
            return;
        }
        if (!file.exists()) {
            return;
        }
        file.delete();
    }
    /**
     * 删除文件夹及其下面的子文件夹
     * @param dir
     */
    public static void deleteDir(File dir){
        if (dir.isFile()){
            return;
        }
        File[] files = dir.listFiles();
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                File file = files[i];
                if (file.isFile()) {
                    file.delete();
                } else {
                    deleteDir(file);
                }
            }
        }
        dir.delete();
    }
}
