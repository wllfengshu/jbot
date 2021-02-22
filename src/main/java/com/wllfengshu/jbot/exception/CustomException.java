package com.wllfengshu.jbot.exception;

/**
 * 自定义异常错误码
 *
 * @author wllfengshu
 */
public class CustomException extends Exception {

    protected ExceptionName exceptionName;

    public enum ExceptionName {
        //没有权限
        UNAUTHENTICATED(401),
        //非法参数
        ILLEGAL_PARAM(400),
        //数据库地址不合法
        ILLEGAL_DB_IP(10001),
        //数据库端口不合法
        ILLEGAL_DB_PORT(10002),
        //数据库名不合法
        ILLEGAL_DB_NAME(10003),
        //数据库用户名不合法
        ILLEGAL_DB_USERNAME(10004),
        //数据库密码不合法
        ILLEGAL_DB_PASSWORD(10005),
        //项目名不合法
        ILLEGAL_PROJECT_NAME(10006),
        //包名不合法
        ILLEGAL_PACKAGE_NAME(10007),
        //生成项目失败
        FAILED_PRODUCE_PROJECT(10008),
        //从用户数据库中获取表信息异常
        CANNOT_GET_DB_INFO_FROM_USER_DB(10009),
        //下载文件异常
        FAILED_DOWNLOAD_FILE(10010),
        //替换文件内容异常
        FAILED_REPLACE_FILE(10011),
        //压缩文件异常
        FAILED_ZIP_FILE(10012),
        //等待线程池执行完毕时发生异常
        FAILED_AWAIT_EXECUTOR_SERVICE(10013);

        private int code;

        ExceptionName(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }

    public CustomException(String message, ExceptionName exceptionName) {
        super(message);
        this.exceptionName = exceptionName;
    }

    public ExceptionName getExceptionName() {
        return exceptionName;
    }
}
