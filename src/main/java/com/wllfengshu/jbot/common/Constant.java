package com.wllfengshu.jbot.common;

import freemarker.template.Configuration;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * 公共常量集合
 *
 * @author wllfengshu
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constant {

    /**
     * 目标项目临时存放路径
     */
    public static final String TARGET_PROJECT_HOME = "/home/listen/Apps";

    /**
     * 模板文件目录名
     */
    public static final String TEMPLATE_NAME = "templates";

    /**
     * 模板文件目录路径
     */
    public static final String TEMPLATE_PATH = "./src/main/resources/" + TEMPLATE_NAME;

    /**
     * 模板引擎执行器
     */
    public static final Configuration CONFIGURATION = new Configuration(Configuration.VERSION_2_3_22);
}
