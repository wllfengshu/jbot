package com.wllfengshu.jbot.security;

import com.wllfengshu.jbot.utils.FileUtil;
import com.wllfengshu.jbot.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 参数检测
 *
 * @author wllfengshu
 */
@Slf4j
@Component
public class Interceptor {

    /**
     * 黑名单关键字
     */
    private static final Set<String> BLACK = new HashSet<>();

    static {
        log.info("正在初始化拦截器");
        try {
            BLACK.addAll(Arrays.asList(
                    "java", "model", "xml"
            ));
            BLACK.addAll(FileUtil.readFile2Set("public/javaKeys"));
        } catch (Exception e) {
            log.error("对项目名、包名进行过滤异常", e);
        }
        log.info("拦截器初始化完毕");
    }

    /**
     * 检查project(只能使用字母、数字、中划线、下划线，不超过50个字符)
     *
     * @param deci
     * @return false 不通过
     * true 通过
     */
    public boolean checkProject(String deci) {
        if (StringUtil.isEmpty(deci) || deci.length() < 2 || deci.length() > 50 || !StringUtil.checkProjectName(deci) || BLACK.contains(deci)) {
            return false;
        }
        return true;
    }

    /**
     * 检查package(只能使用字母、数字、点，不超过100个字符)
     *
     * @param deci
     * @return false 不通过
     * true 通过
     */
    public boolean checkPackage(String deci) {
        if (StringUtil.isEmpty(deci) || deci.length() < 2 || deci.length() > 100 || !StringUtil.checkPackageName(deci) || BLACK.contains(deci)) {
            return false;
        }
        return true;
    }
}
