package com.wllfengshu.jbot.exception;

/**
 * 自定义异常响应
 *
 * @author wllfengshu
 */
public class WebResponse {

    private String errorMessage;
    private Integer errorCode;
    private String instanceId;

    public WebResponse(String errorMessage, Integer errorCode) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
        this.instanceId = System.getenv("instanceId");
    }

    public WebResponse() {
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }
}
