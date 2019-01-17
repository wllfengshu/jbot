package com.wllfengshu.web.security;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 对项目名、包名进行过滤
 */
public class Interceptor {

    private static Set<String> black = new HashSet<>();

    static {
        black.addAll(Arrays.asList("java",""));
    }

    /**
     * 判断字符串是否在back中
     * @param deci
     * @return false 不包含
     *          true 包含
     */
    public static boolean check(String deci){
        if (black.contains(deci)){
            return true;
        }
        return false;
    }
}
