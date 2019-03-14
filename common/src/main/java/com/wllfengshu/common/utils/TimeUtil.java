package com.wllfengshu.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具类
 * @author wllfengshu
 */
public class TimeUtil {

    private static Logger logger = LoggerFactory.getLogger(TimeUtil.class);
    private final static SimpleDateFormat sdf_ym = new SimpleDateFormat("yyyyMM");
    private final static SimpleDateFormat sdf_ymd = new SimpleDateFormat("yyyyMMdd");
    private final static SimpleDateFormat sdf_ymdhms = new SimpleDateFormat("yyyyMMddHHmmss");

    /**
     * 得到当前时间
     * @return
     */
    public static String getCurrentYearMonth(String regex) {
        if ("ym".equals(regex)){
            return sdf_ym.format(new Date());
        }else if ("ymd".equals(regex)){
            return sdf_ymd.format(new Date());
        }else {
            return sdf_ymdhms.format(new Date());
        }
    }
}
