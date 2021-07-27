package com.wllfengshu.jbot.utils;

import com.wllfengshu.jbot.common.Constant;
import org.junit.Test;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.List;

public class FileUtilTest {

    @Test
    public void testClassPath(){
        try {
            System.out.println("begin test");
            String filepath = Constant.TEMPLATE_PATH;
            // 此处取项目路径 + 传入的路径,改路径获取不到文件
            // 如果要获取文件需要传入 src/main/resources/log4j2.xml
            File temp = new File(filepath);
            System.out.println(temp.getAbsolutePath());
            //下面四种情况取编译后target\classes 目录下的文件
            // File 形式
            File file = new File(FileUtilTest.class.getClassLoader().getResource(filepath).getFile());
            System.out.println(file.getAbsolutePath());
            // InputStream 形式
            InputStream inputStream = FileUtilTest.class.getClassLoader().getResourceAsStream(filepath);
            System.out.println(inputStream);
            // URL 形式
            URL url = FileUtilTest.class.getClassLoader().getResource(filepath);
            System.out.println(url);
            // URI 形式
            URI uri = FileUtilTest.class.getClassLoader().getResource(filepath).toURI();
            File uriFile = new File(uri);
            System.out.println(uriFile.getAbsolutePath());
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @Test
    public void giveLocalResourcesFile() {
        File file = FileUtil.giveLocalResourcesFile(Constant.TEMPLATE_NAME);
        assert file.exists();
    }

    @Test
    public void giveFileName4Dir() {
        List<String> list = FileUtil.giveFileName4Dir(Constant.TEMPLATE_NAME);
        assert list.size() == 4;
        list.forEach(System.out::println);
    }

    @Test
    public void giveFileName4Dir2() {
        List<String> list = FileUtil.giveFileName4Dir(Constant.TEMPLATE_NAME + "\\\\src\\\\main\\\\java\\\\configs");
        list.forEach(System.out::println);
    }

    @Test
    public void giveFileName4DirNeedChild() {
        List<String> list = FileUtil.giveFileName4DirNeedChild(Constant.TEMPLATE_NAME + Constant.SRC_MAIN_JAVA_PATH);
        list.forEach(System.out::println);
    }
}