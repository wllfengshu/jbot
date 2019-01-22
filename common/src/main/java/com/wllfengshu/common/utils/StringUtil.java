package com.wllfengshu.common.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 字符串工具类
 */
public class StringUtil {

    /**
     * 判断字符串是否只使用字母、数字、-连字符、_下划线，不超过50个字符
     * @param str
     * @return true 验证通过
     *          false 验证不通过
     */
    public static boolean checkWEU(String str){
        return str.matches("^[a-zA-Z0-9][\\w-_]{1,49}$");
    }

    /**
     * 判断字符串是否只使用字母、数字、点，不超过50个字符
     * @param str
     * @return true 验证通过
     *          false 验证不通过
     */
    public static boolean checkWD(String str){
        return str.matches("^[a-zA-Z0-9][\\w.]{1,99}$");
    }

    /**
     * 把数据库中字段的类型，转换为java中的类型
     * @return
     */
    public static String sqlType2JavaType(String sqlType){
        if (sqlType.equalsIgnoreCase("bit")) {
            return "Boolean";
        } else if (sqlType.equalsIgnoreCase("tinyint")) {
            return "Byte";
        } else if (sqlType.equalsIgnoreCase("smallint")) {
            return "Short";
        } else if (sqlType.equalsIgnoreCase("int")) {
            return "Integer";
        } else if (sqlType.equalsIgnoreCase("bigint")) {
            return "Long";
        } else if (sqlType.equalsIgnoreCase("float")) {
            return "Float";
        } else if (sqlType.equalsIgnoreCase("decimal")
                || sqlType.equalsIgnoreCase("numeric")
                || sqlType.equalsIgnoreCase("real")
                || sqlType.equalsIgnoreCase("money")
                || sqlType.equalsIgnoreCase("double")
                || sqlType.equalsIgnoreCase("smallmoney")) {
            return "Double";
        } else if (sqlType.equalsIgnoreCase("varchar")
                || sqlType.equalsIgnoreCase("char")
                || sqlType.equalsIgnoreCase("nvarchar")
                || sqlType.equalsIgnoreCase("nchar")
                || sqlType.equalsIgnoreCase("text")) {
            return "String";
        } else if (sqlType.equalsIgnoreCase("datetime")) {
            return "Date";
        } else if (sqlType.equalsIgnoreCase("image")) {
            return "Blod";
        }
        return "String";
    }

    /**
     * 点转斜杠
     * @param str
     * @return
     */
    public static String spotToSlash(String str){
        return str.replace('.','/');
    }

    /**
     * 删除单词开头的t_
     * @param str
     * @return
     */
    public static String delTUnderline(String str){
        if (str.startsWith("t_") && str.length()>2){
            return str.substring(2,str.length());
        }
        return str;
    }

    /**
     * 把单词首字母变为大写
     * @param str
     * @return
     */
    public static String toFirstCharUpperCase(String str){
        if (isEmpty(str)){
            return null;
        }
        if (str.length()>=2){
            return str.substring(0,1).toUpperCase()+str.substring(1);
        }else {
            return str.toUpperCase();
        }
    }

    /**
     * 把单词首字母变为小写
     * @param str
     * @return
     */
    public static String toFirstCharLowCase(String str){
        if (isEmpty(str)){
            return null;
        }
        if (str.length()>=2){
            return str.substring(0,1).toLowerCase()+str.substring(1);
        }else {
            return str.toLowerCase();
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

    /***
     * 下划线命名转为驼峰命名
     * @param para 下划线命名的字符串
     */
    public static String underlineToHump(String para){
        StringBuilder result=new StringBuilder();
        String a[]=para.split("_");
        for(String s:a){
            if (!para.contains("_")){
                result.append(s);
                continue;
            }
            if(result.length()==0){
                result.append(s.toLowerCase());
            }else{
                result.append(s.substring(0, 1).toUpperCase());
                result.append(s.substring(1).toLowerCase());
            }
        }
        return result.toString();
    }

    /***
     * 驼峰命名转为下划线命名
     * @param para 驼峰命名的字符串
     */
    public static String humpToUnderline(String para){
        StringBuilder sb=new StringBuilder(para);
        int temp=0;//定位
        for(int i=0;i<para.length();i++){
            if(Character.isUpperCase(para.charAt(i))){
                sb.insert(i+temp, "_");
                temp+=1;
            }
        }
        return sb.toString().toUpperCase();
    }

}
