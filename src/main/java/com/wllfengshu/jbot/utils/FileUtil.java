package com.wllfengshu.jbot.utils;

import com.wllfengshu.jbot.common.Constant;
import com.wllfengshu.jbot.exception.CustomException;
import com.wllfengshu.jbot.work.TemplateBoot;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 文件工具类
 *
 * @author wllfengshu
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FileUtil {

    /**
     * 递归的把文件压缩为zip文件
     *
     * @param zipFileName
     * @param inputFile
     */
    public static void fileToZip(String zipFileName, String inputFile) throws CustomException {
        log.info("开始压缩文件，zipFileName:{},inputFile:{}", zipFileName, inputFile);
        try (FileOutputStream fos = new FileOutputStream(zipFileName);
             ZipOutputStream out = new ZipOutputStream(fos)) {
            zip(out, new File(inputFile), "");
            out.flush();
            fos.flush();
        } catch (Exception e) {
            log.error("压缩文件异常", e);
            throw new CustomException("压缩文件异常", CustomException.ExceptionName.FAILED_ZIP_FILE);
        }
        log.info("文件压缩完毕");
    }

    /**
     * 递归压缩文件
     *
     * @param out
     * @param f
     * @param base
     * @throws CustomException
     */
    private static void zip(ZipOutputStream out, File f, String base) throws Exception {
        if (f.isDirectory()) {
            File[] files = f.listFiles();
            if (null == files) {
                return;
            }
            out.putNextEntry(new ZipEntry(base + "/"));
            base += "/";
            for (File cf : files) {
                zip(out, cf, base + cf.getName());
            }
        } else {
            try (FileInputStream in = new FileInputStream(f)) {
                out.putNextEntry(new ZipEntry(base));
                byte[] b = new byte[1024];
                int n;
                while ((n = in.read(b)) != -1) {
                    out.write(b, 0, n);
                }
            } catch (Exception e) {
                log.error("zip 异常", e);
            }
        }
    }

    /**
     * 文件下载
     *
     * @param fileName 文件名（带路径）
     * @param response 请求体
     */
    public static void download(String fileName, HttpServletResponse response) throws CustomException {
        log.info("开始文件下载，文件名：{}", fileName);
        File file = new File(fileName);
        if (!file.exists()) {
            log.error("待下载文件不存在，fileName:{}", fileName);
            return;
        }
        try {
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode(file.getName(), "UTF-8"));
            response.setHeader("Content-Length", String.valueOf(file.length()));
        } catch (Exception e) {
            log.error("设置响应头异常", e);
            throw new CustomException("设置响应头异常", CustomException.ExceptionName.FAILED_DOWNLOAD_FILE);
        }

        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
             BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream())) {
            byte[] buff = new byte[2048];
            while (true) {
                int bytesRead;
                if (-1 == (bytesRead = bis.read(buff, 0, buff.length))) {
                    break;
                }
                bos.write(buff, 0, bytesRead);
            }
            log.info("文件下载完成");
        } catch (Exception e) {
            log.error("下载文件异常", e);
            throw new CustomException("下载文件异常", CustomException.ExceptionName.FAILED_DOWNLOAD_FILE);
        }
    }

    /**
     * 把文件读到List中
     * 注意：1、一行只允许一个字符串；
     * 2、以#开头的字符串将被忽略；
     *
     * @param fileName
     * @return
     */
    public static List<String> readFile2Set(String fileName) {
        log.info("开始把文件读到List中，fileName:{}", fileName);
        List<String> items = new ArrayList<>();
        Resource resource = new ClassPathResource(fileName);
        if (!resource.exists()) {
            log.error("把文件读到List中时，资源文件不存在");
            return items;
        }
        try (InputStream input = resource.getInputStream();
             Reader reader = new InputStreamReader(input, StandardCharsets.UTF_8);
             BufferedReader br = new BufferedReader(reader)) {
            String temp;
            while (null != (temp = br.readLine())) {
                if (!"".equals(temp) && !"\n".equals(temp) && !"\r\n".equals(temp) && !temp.startsWith("#")) {
                    items.add(temp);
                }
            }
        } catch (Exception e) {
            log.error("把文件读到List中时异常", e);
        }
        log.info("文件读到List中完毕");
        return items;
    }

    /**
     * 删除文件夹及其下面的子文件夹
     *
     * @param dir
     */
    public static void deleteDir(File dir) {
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

    /**
     * 参数必须是一个存在的目录
     *
     * @param dir
     * @return
     */
    public static boolean isExistsAndDir(File dir) {
        return null != dir && dir.exists() && dir.isDirectory();
    }

    /**
     * 获取本地资源文件（即resources目录下的文件或文件夹）
     *
     * @param path 不包含"./src/main/resources/"路径
     * @return 文件或文件夹
     */
    public static File giveLocalResourcesFile(String path) {
        //1 尝试本地直接查找模板文件所在目录
        File templateDir = new File(Constant.RESOURCES_ROOT_PATH + path);
        //2 尝试使用getResource方式查找
        if (!templateDir.exists()) {
            try {
                log.info("本地直接查找-失败；尝试使用getResource方式查找");
                String url = TemplateBoot.class.getClassLoader().getResource(path).getPath();
                templateDir = new File(URLDecoder.decode(url, "UTF-8"));
            } catch (Exception e) {
                log.error("尝试使用getResource方式查找-发生异常", e);
            }
        }
        //3 尝试使用jar包方式查找
        if (!templateDir.exists()) {
            try {
                log.info("使用getResource方式查找-失败；尝试使用jar包方式查找");
                ClassPathResource resource = new ClassPathResource(path + "/");
                if (null != resource && resource.exists()) {
                    templateDir = resource.getFile();
                } else {
                    log.error("尝试使用jar包方式查找-失败");
                }
            } catch (Exception e) {
                log.error("尝试使用jar包方式查找-发生异常", e);
            }
        }
        return templateDir;
    }

    /**
     * 获取指定目录下所有的文件名（无子目录中的文件）
     *
     * @param path 不包含"./src/main/resources/"路径
     * @return
     */
    public static List<String> giveFileName4Dir(String path) {
        List<String> result = new ArrayList<>(4);
        File file = FileUtil.giveLocalResourcesFile(path);
        if (file.isFile()){
            log.warn("当前file不是一个目录");
            return result;
        }
        for (File f : Objects.requireNonNull(file.listFiles())) {
            if (f.isFile()){
                result.add(f.getAbsolutePath().split(path + "\\\\")[1]);
            }
        }
        return result;
    }

    /**
     * 获取指定目录下所有的文件名（有子目录中的文件）
     *
     * @param path 不包含"./src/main/resources/"路径
     * @return
     */
    public static List<String> giveFileName4DirNeedChild(String path) {
        List<String> result = new ArrayList<>(16);
        File file = FileUtil.giveLocalResourcesFile(path);
        if (file.isFile()){
            log.warn("当前file不是一个目录");
            return result;
        }
        giveFileName(path, file, result);
        return result;
    }

    /**
     * 递归读取文件
     *
     * @param path
     * @param f
     * @param result
     */
    private static void giveFileName(final String path, File f,final List<String> result) {
        File[] files = f.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                String name = file.getName();
                // 以下几个包单独处理，不在这里处理
                if ("dao".equals(name) || "entity".equals(name) || "rest".equals(name) ||
                    "service".equals(name) || "impl".equals(name)) {
                    continue;
                }
                giveFileName(path, file, result);
            }else {
                String ping = file.getAbsolutePath().split(path + "\\\\")[1];
                ping = ping.replace('\\','/');
                result.add(ping);
            }
        }
    }
}
