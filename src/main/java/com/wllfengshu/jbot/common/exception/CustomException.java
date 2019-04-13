package com.wllfengshu.jbot.common.exception;

/**
 * 自定义异常错误码
 *
 * @author wllfengshu
 */
public class CustomException extends Exception {

    protected ExceptionName exceptionName;

    public enum ExceptionName {
        //没有权限
        Unauthenticated(401),
        //非法参数
        IllegalParam(400),
        //数据库地址不合法
        IllegalDbIp(10001),
        //数据库端口不合法
        IllegalDbPort(10002),
        //数据库名不合法
        IllegalDbName(10003),
        //数据库用户名不合法
        IllegalDbUsername(10004),
        //数据库密码不合法
        IllegalDbPassword(10005),
        //项目名不合法
        IllegalProjectName(10006),
        //包名不合法
        IllegalPackageName(10007),
        //生成项目失败
        FailedProduceProject(10008),
        //从用户数据库中获取表信息异常
        CannotGetDbinfoFromUserDb(10009),
        //下载文件异常
        FailedDownloadFile(10010),
        //替换文件内容异常
        FailedReplaceFile(10011),
        //压缩文件异常
        FailedZipFile(10012);

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
