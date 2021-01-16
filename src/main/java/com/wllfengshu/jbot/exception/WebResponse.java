package com.wllfengshu.jbot.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * 自定义异常响应
 *
 * @author wllfengshu
 */
@Getter
@Setter
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
}
