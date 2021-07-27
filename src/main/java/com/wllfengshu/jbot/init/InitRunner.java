package com.wllfengshu.jbot.init;

import com.wllfengshu.jbot.common.Constant;
import com.wllfengshu.jbot.utils.FileUtil;
import freemarker.template.TemplateExceptionHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;

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
        log.info("正在加载模板文件...");
        File templateDir = FileUtil.giveLocalResourcesFile(Constant.TEMPLATE_NAME);
        //如果无法加载，只能退出程序
        if (!FileUtil.isExistsAndDir(templateDir)) {
            log.error("无法加载模板文件，程序退出");
            System.exit(-1);
        }

        try {
            Constant.CONFIGURATION.setDirectoryForTemplateLoading(templateDir);
            Constant.CONFIGURATION.setDefaultEncoding("UTF-8");
            Constant.CONFIGURATION.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        } catch (Exception e) {
            log.error("创建template配置错误", e);
        }
        log.info("模板文件加载完毕");
    }
}