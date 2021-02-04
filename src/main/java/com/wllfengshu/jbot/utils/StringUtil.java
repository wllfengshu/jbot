package com.wllfengshu.jbot.utils;

import com.wllfengshu.jbot.model.vo.ConnectInfoVO;
import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * 字符串工具类
 *
 * @author wllfengshu
 */
@Slf4j
public class StringUtil {

    /**
     * 判断字符串：
     * 以字母开头，只允许字母、数字、-连字符、_下划线，不能以-和_结尾，长度大于1小于100
     *
     * @param str
     * @return true 验证通过
     * false 验证不通过
     */
    public static boolean checkProjectName(String str) {
        return str.matches("^[a-zA-Z][a-zA-Z-_\\d]*[a-zA-Z\\d]{1,100}$");
    }

    /**
     * 判断字符串：
     * 以字母开头，只允许字母、数字、点，各个部分都不能以数字开头，不能以点结尾，长度大于1小于100
     *
     * @param str
     * @return true 验证通过
     * false 验证不通过
     */
    public static boolean checkPackageName(String str) {
        //1 验证以字母开头，只允许字母、数字、点，不能以点结尾，长度大于1小于100
        if (!str.matches("^[a-zA-Z][a-zA-Z\\.\\d]*[a-zA-Z\\d]{1,100}$")) {
            return false;
        }
        //2 验证各个部分
        String[] splits = str.split("\\.");
        //2.1 子部分不能超过10层
        if (splits.length < 1 || splits.length > 10) {
            return false;
        }
        for (String s : splits) {
            //2.2 必须以字母开头，只允许字母、数字，长度大于0
            if (!s.matches("^[a-zA-Z][a-zA-Z\\d]{0,50}$")) {
                return false;
            }
        }
        return true;
    }

    /**
     * 把数据库中字段的类型，转换为java中的类型
     *
     * @return
     */
    public static String sqlType2JavaType(String sqlType) {
        if ("bit".equalsIgnoreCase(sqlType)) {
            return "Boolean";
        } else if ("tinyint".equalsIgnoreCase(sqlType)
                || "smallint".equalsIgnoreCase(sqlType)
                || "int".equalsIgnoreCase(sqlType)) {
            return "Integer";
        } else if ("bigint".equalsIgnoreCase(sqlType)) {
            return "Long";
        } else if ("float".equalsIgnoreCase(sqlType)
                || "decimal".equalsIgnoreCase(sqlType)
                || "numeric".equalsIgnoreCase(sqlType)
                || "real".equalsIgnoreCase(sqlType)
                || "money".equalsIgnoreCase(sqlType)
                || "double".equalsIgnoreCase(sqlType)
                || "smallmoney".equalsIgnoreCase(sqlType)) {
            return "Double";
        } else if ("datetime".equalsIgnoreCase(sqlType)
                || "timestamp".equalsIgnoreCase(sqlType)
                || "time".equalsIgnoreCase(sqlType)
                || "date".equalsIgnoreCase(sqlType)) {
            return "Date";
        } else if ("image".equalsIgnoreCase(sqlType)) {
            return "Blod";
        } else {
            return "String";
        }
    }

    /**
     * 点转斜杠
     *
     * @param str
     * @return
     */
    public static String spotToSlash(String str) {
        return str.replace('.', '/');
    }

    /**
     * 删除单词开头的t_
     *
     * @param str
     * @return
     */
    public static String delTUnderline(String str) {
        if (str.startsWith("t_") && str.length() > 2) {
            return str.substring(2);
        }
        return str;
    }

    /**
     * 把单词首字母变为大写
     *
     * @param str
     * @return
     */
    public static String toFirstCharUpperCase(String str) {
        if (isEmpty(str)) {
            return null;
        }
        if (str.length() >= 2) {
            return str.substring(0, 1).toUpperCase() + str.substring(1);
        } else {
            return str.toUpperCase();
        }
    }

    /**
     * 把单词首字母变为小写
     *
     * @param str
     * @return
     */
    public static String toFirstCharLowCase(String str) {
        if (isEmpty(str)) {
            return null;
        }
        if (str.length() >= 2) {
            return str.substring(0, 1).toLowerCase() + str.substring(1);
        } else {
            return str.toLowerCase();
        }
    }

    /**
     * 判断字符串是否是空
     *
     * @param str
     * @return true 空
     * false 非空
     */
    public static boolean isEmpty(String str) {
        return null == str || "".equals(str);
    }

    /**
     * 把字符串按正则表达式分割，转为list
     *
     * @param listStr
     * @param regex   注意：这里需要转义，eg: 双斜线 \\.
     * @return
     */
    public static List<String> stringToList(String listStr, String regex) {
        List<String> list = new ArrayList<>();
        String[] sp = listStr.split(regex);
        for (String s : sp) {
            list.add(s);
        }
        return list;
    }

    /***
     * 下划线命名转为驼峰命名
     * @param para 下划线命名的字符串
     */
    public static String underlineToHump(String para) {
        StringBuilder result = new StringBuilder();
        String[] temp = para.split("_");
        for (String s : temp) {
            if (!para.contains("_")) {
                result.append(s);
                continue;
            }
            if (result.length() == 0) {
                result.append(s.toLowerCase());
            } else {
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
    public static String humpToUnderline(String para) {
        StringBuilder sb = new StringBuilder(para);
        int temp = 0;
        for (int i = 0; i < para.length(); i++) {
            if (Character.isUpperCase(para.charAt(i))) {
                sb.insert(i + temp, "_");
                temp += 1;
            }
        }
        return sb.toString().toUpperCase();
    }

    /**
     * 获取服务器ip
     *
     * @return
     */
    public static String getServerIp() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (Exception e) {
            log.error("获取服务器ip异常");
        }
        return null;
    }

    /**
     * 获取服务器配置的数据库信息
     *
     * @return
     */
    public static ConnectInfoVO getServerDbConnect(String dbUrl, String username, String password) {
        ConnectInfoVO ci = new ConnectInfoVO();
        //1 截取出ip:port/dbName
        String temp = dbUrl.substring("jdbc:mysql://".length(), dbUrl.contains("?") ? dbUrl.indexOf("?") : dbUrl.length());
        //2 分离ip(如果环境变量中是本地ip，则使用服务器ip)
        String[] ipPortDb = temp.split(":");
        if ("localhost".equals(ipPortDb[0]) || "127.0.0.1".equals(ipPortDb[0])) {
            ci.setDbIp(StringUtil.getServerIp());
        } else {
            ci.setDbIp(ipPortDb[0]);
        }
        //3 分离port和dbName
        String[] portDb = ipPortDb[1].split("/");
        ci.setDbPort(portDb[0]);
        ci.setDbName(portDb[1]);
        ci.setDbUsername(username);
        ci.setDbPassword(password);
        return ci;
    }
}
