package com.wllfengshu.web.security;

import com.wllfengshu.common.utils.FileUtil;
import com.wllfengshu.common.utils.StringUtil;
import org.springframework.util.ClassUtils;

import java.net.URLDecoder;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 对项目名、包名进行过滤
 * @author wllfengshu
 */
public class Interceptor {

    private static Set<String> black = new HashSet<>();

    static {
        try {
            black.addAll(Arrays.asList(
                    "java","model","xml"
            ));
            black.addAll(FileUtil.readFile2Set(URLDecoder.decode(ClassUtils.getDefaultClassLoader().getResource("public/javaKeys").getPath(),"UTF-8")));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 检查project(只能使用字母、数字、中划线、下划线，不超过50个字符)
     * @param deci
     * @return false 不通过
     *          true 通过
     */
    public static boolean checkProject(String deci){
        if (StringUtil.isEmpty(deci) || deci.length()<2 || deci.length()>50 || !StringUtil.checkProjectName(deci) || black.contains(deci)){
            return false;
        }
        return true;
    }

    /**
     * 检查package(只能使用字母、数字、点，不超过100个字符)
     * @param deci
     * @return false 不通过
     *          true 通过
     */
    public static boolean checkPackage(String deci){
        if (StringUtil.isEmpty(deci) || deci.length()<2 || deci.length()>100 || !StringUtil.checkPackageName(deci) || black.contains(deci)){
            return false;
        }
        return true;
    }
}
