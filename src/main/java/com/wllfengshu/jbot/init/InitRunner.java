package com.wllfengshu.jbot.init;

import com.wllfengshu.jbot.common.Constant;
import com.wllfengshu.jbot.utils.FileUtil;
import com.wllfengshu.jbot.work.TemplateBoot;
import freemarker.template.TemplateExceptionHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.net.URLDecoder;

/**
 * 系统初始化
 *
 * @author wangll
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class InitRunner implements CommandLineRunner {

    @Override
    public void run(String... args) {
        log.info("正在加载模板文件");
        //1 尝试本地直接查找模板文件所在目录
        File templateDir = new File(Constant.TEMPLATE_PATH);
        //2 尝试使用getResource方式查找
        if (!FileUtil.isExistsAndDir(templateDir)) {
            try {
                log.info("本地直接查找-失败；尝试使用getResource方式查找");
                String url = TemplateBoot.class.getClassLoader().getResource(Constant.TEMPLATE_NAME).getPath();
                templateDir = new File(URLDecoder.decode(url, "UTF-8"));
            } catch (Exception e) {
                log.error("尝试使用getResource方式查找-发生异常", e);
            }
        }
        //3 尝试使用jar包方式查找
        if (!FileUtil.isExistsAndDir(templateDir)) {
            try {
                log.info("使用getResource方式查找-失败；尝试使用jar包方式查找");
                ClassPathResource resource = new ClassPathResource(Constant.TEMPLATE_NAME + "/");
                if (null != resource && resource.exists()) {
                    templateDir = resource.getFile();
                } else {
                    log.error("尝试使用jar包方式查找-失败");
                }
            } catch (Exception e) {
                log.error("尝试使用jar包方式查找-发生异常", e);
            }
        }
        //4 全部方式都无法加载，只能退出程序
        if (FileUtil.isExistsAndDir(templateDir)) {
            try {
                Constant.CONFIGURATION.setDirectoryForTemplateLoading(templateDir);
                Constant.CONFIGURATION.setDefaultEncoding("UTF-8");
                Constant.CONFIGURATION.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            } catch (Exception e) {
                log.error("创建template配置错误", e);
            }
        } else {
            log.error("尝试了所有方法都无法加载模板文件，程序退出");
            System.exit(-1);
        }
        log.info("模板文件加载完毕");
    }
}