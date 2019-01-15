package com.wllfengshu.common.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 字符串工具类
 */
public class StringUtil {

    /**
     * 把单词首字母变为大写
     * @param str
     * @return
     */
    public static String wordToUpperCase(String str){
        if (isEmpty(str)){
            return null;
        }
        if (str.length()>=2){
            return str.substring(0,1).toUpperCase()+str.substring(1);
        }else {
            return str.substring(0,1);
        }
    }

    /**
     * 判断字符串是否是空
     * @param str
     * @return ture 空
     *          false 非空
     */
    public static boolean isEmpty(String str){
        if (null==str || "".equals(str)){
            return true;
        }
        return false;
    }

    /**
     * 把字符串按正则表达式分割，转为list
     * @param listStr
     * @param regex
     * @return
     */
    public static List<String> stringToList(String listStr,String regex){
        List<String> list = new ArrayList<>();
        String[] sp = listStr.split(regex);
        for(String s:sp){
            list.add(s);
        }
        return list;
    }
}
