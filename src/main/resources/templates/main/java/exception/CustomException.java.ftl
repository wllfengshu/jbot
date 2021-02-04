package ${packageName}.${projectName}.exception;

/**
 * 自定义异常
 *
 * @author
 */
public class CustomException extends Exception {

    protected ExceptionName exceptionName;

    public enum ExceptionName {
        //没有权限
        Unauthenticated(401),
        //非法参数
        IllegalParam(400),

        //请求Security超时
        SecurityFailed(14001),
        //请求Operation超时
        OperationFailed(14002),
        //进行get请求失败
        HttpDoGetFailed(14003),
        //进行post请求失败
        HttpDoPostFailed(14004);

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
