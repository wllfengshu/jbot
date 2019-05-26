package com.wllfengshu.jbot.utils;

import com.wllfengshu.jbot.exception.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 文件工具类
 *
 * @author wllfengshu
 */
public class FileUtil {

    private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

    /**
     * 递归的把文件压缩为zip文件
     *
     * @param zipFileName
     * @param inputFile
     */
    public static void fileToZip(String zipFileName, String inputFile) throws CustomException {
        logger.info("开始压缩文件，zipFileName:{},inputFile:{}", zipFileName, inputFile);
        FileOutputStream fos = null;
        ZipOutputStream out = null;
        try {
            fos = new FileOutputStream(zipFileName);
            out = new ZipOutputStream(fos);
            zip(out, new File(inputFile), "");
        } catch (Exception e) {
            logger.error("压缩文件异常", e);
            throw new CustomException("压缩文件异常", CustomException.ExceptionName.FailedZipFile);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (Exception e) {
                logger.error("压缩文件异常 finally", e);
            }
        }
        logger.info("文件压缩完毕");
    }

    /**
     * 递归压缩文件
     *
     * @param out
     * @param f
     * @param base
     * @throws CustomException
     */
    private static void zip(ZipOutputStream out, File f, String base) throws CustomException {
        FileInputStream in = null;
        try {
            if (f.isDirectory()) {
                File[] files = f.listFiles();
                out.putNextEntry(new ZipEntry(base + "/"));
                base += "/";
                for(File cf:files){
                    zip(out, cf, base+cf.getName());
                }
            } else {
                out.putNextEntry(new ZipEntry(base));
                in = new FileInputStream(f);
                byte[] b = new byte[1024];
                int n = -1;
                while ((n=in.read(b)) != -1) {
                    out.write(b,0,n);
                }
            }
        } catch (Exception e) {
            logger.error("zip 异常", e);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e) {
                logger.error("zip 异常 finally", e);
            }
        }
    }

    /**
     * 替换文件内容(注意：oldStr[]元素个数必须小于等于newStr[])
     *
     * @param filePath 文件名（文件路径+文件名）
     * @param oldStr   待替换内容
     * @param newStr   替换后内容
     */
    public static void replace(String filePath, String[] oldStr, String[] newStr) throws CustomException {
        logger.info("开始替换文件内容，fielPath:{},oldStr size:{},newStr size:{}", filePath, oldStr.length, newStr.length);
        if (oldStr.length > newStr.length) {
            logger.error("oldStr元素个数:{}必须小于等于newStr:{}", oldStr.length, newStr.length);
            return;
        }
        File file = new File(filePath);
        if (!file.exists()) {
            logger.error("待替换文件不存在，filePath:{}", filePath);
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
            logger.error("替换文件内容异常", e);
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
                logger.error("替换文件内容异常 finally", e);
            }
        }
        logger.info("文件替换完成");
    }

    /**
     * 文件下载
     *
     * @param fileName 文件名（带路径）
     * @param response 请求体
     */
    public static void download(String fileName, HttpServletResponse response) throws CustomException {
        logger.info("开始文件下载，文件名：{}", fileName);
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                logger.error("待下载文件不存在，fileName:{}", fileName);
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
        } catch (Exception e) {
            logger.error("下载文件异常", e);
            throw new CustomException("下载文件异常", CustomException.ExceptionName.FailedDownloadFile);
        } finally {
            try {
                if (bis != null) {
                    bis.close();
                }
                if (bos != null) {
                    bos.close();
                }
            } catch (Exception e) {
                logger.error("下载文件异常 finally", e);
            }
        }
    }

    /**
     * 把文件读到List中
     * 注意：1、一行只允许一个字符串；
     *       2、以#开头的字符串将被忽略；
     *
     * @param fileName
     * @return
     */
    public static List<String> readFile2Set(String fileName) throws CustomException {
        logger.info("开始把文件读到List中，fileName:{}", fileName);
        List<String> items = new ArrayList<>();
        Resource resource = new ClassPathResource(fileName);
        if (!resource.exists()) {
            logger.error("把文件读到List中时，资源文件不存在");
            return items;
        }
        InputStream input = null;
        Reader reader = null;
        BufferedReader br = null;
        try {
            input = resource.getInputStream();
            reader = new InputStreamReader(input, "UTF-8");
            br = new BufferedReader(reader);
            String temp = null;
            while (null != (temp = br.readLine())) {
                if (null != temp && !"".equals(temp) && !"\n".equals(temp) && !"\r\n".equals(temp) && !temp.startsWith("#")) {
                    items.add(temp);
                }
            }
        } catch (Exception e) {
            logger.error("把文件读到List中时异常", e);
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (reader != null) {
                    reader.close();
                }
                if (input != null) {
                    input.close();
                }
            } catch (Exception e) {
                logger.error("把文件读到List中时异常 finally", e);
            }
        }
        logger.info("文件读到List中完毕");
        return items;
    }

    /**
     * 创建文件
     *
     * @param fileName
     * @return
     */
    public static void createFile(String fileName, String content) throws CustomException {
        File file = new File(fileName);
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            if (file.getParentFile() != null && !file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
            fw = new FileWriter(file.getAbsoluteFile());
            bw = new BufferedWriter(fw);
            bw.write(content);
        } catch (Exception e) {
            logger.error("创建文件异常", e);
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
                if (fw != null) {
                    fw.close();
                }
            } catch (Exception e) {
                logger.error("创建文件异常 finally", e);
            }
        }
    }

    /**
     * 创建目录
     *
     * @param destDirName
     * @return
     */
    public static void createDir(String destDirName) throws CustomException {
        File dir = new File(destDirName);
        if (dir.exists() && dir.isDirectory()) {
            return;
        }
        dir.mkdirs();
    }

    /**
     * 复制文件
     *
     * @param oldPath
     * @param newPath
     */
    public static void copyFile(String oldPath, String newPath) throws CustomException {
        logger.info("开始复制文件，oldPath:{},newPath:{}", oldPath, newPath);
        Resource resource = new ClassPathResource(oldPath);
        if (!resource.exists()) {
            logger.error("复制文件时，资源文件不存在");
            return;
        }
        InputStream input = null;
        FileOutputStream out = null;
        try {
            File file = new File(newPath);
            if (file.getParentFile() != null && !file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
            input = resource.getInputStream();
            out = new FileOutputStream(file);
            byte[] buffer = new byte[2048];
            int readByte = 0;
            while ((readByte = input.read(buffer)) != -1) {
                out.write(buffer, 0, readByte);
            }
        } catch (Exception e) {
            logger.error("复制文件异常", e);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (input != null) {
                    input.close();
                }
            } catch (Exception e) {
                logger.error("复制文件异常 finally", e);
            }
        }
    }

    /**
     * 删除一个文件。
     *
     * @param filename
     */
    public static void deleteFile(String filename) throws CustomException {
        logger.info("删除文件,文件名{}",filename);
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
     *
     * @param dir
     */
    public static void deleteDir(File dir) throws CustomException {
        if (!dir.exists()) {
            return;
        }
        if (dir.isFile()) {
            dir.delete();
            return;
        }
        File[] files = dir.listFiles();
        for (File f : files) {
            deleteDir(f);
        }
        dir.delete();
    }
}
