package com.wllfengshu.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;

/**
 * 自定义异常的handler
 * @author wllfengshu
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = CustomException.class)
    @ResponseBody
    public WebResponse jsonErrorHandler(HttpServletResponse resp, CustomException exception) throws Exception {
        WebResponse webResponse = new WebResponse();
        webResponse.setErrorCode(exception.getExceptionName().getCode());
        webResponse.setErrorMessage(exception.getMessage());
        webResponse.setInstanceId(System.getenv("instanceId"));
        resp.setStatus(exception.getExceptionName().getCode());
        exception.printStackTrace();
        return webResponse;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public WebResponse exceptionHandler(Exception ex) {
        WebResponse webResponse = new WebResponse();
        webResponse.setErrorCode(400);
        webResponse.setErrorMessage(ClassUtils.getShortName(ex.getClass()));
        ex.printStackTrace();
        return webResponse;
    }
}
