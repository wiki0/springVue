package com.anyun.cloud.web.proxzoneserviceweb.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: yangfan
 * @date: 18-2-9 下午1:29
 * @description: 远程请求异常统一处理
 */

@ControllerAdvice
@ResponseBody
public class RestExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestExceptionHandler.class);
    /**
     * 所有异常报错
     * @param request
     * @param exception
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value=Exception.class)
    public String allExceptionHandler(HttpServletRequest request, Exception exception)
    {
        LOGGER.error("控制器地址:"+request.getRequestURL().toString()+"?"+request.getQueryString());
        LOGGER.error("错误描述:"+exception.getMessage());
        return "{\"code\":false,\"content\":\"服务器内部错误\"}";
    }

    @ExceptionHandler(value=RestClientException.class)
    public String RsetExceptionHandler(HttpServletRequest request, Exception exception)
    {
        LOGGER.error("控制器地址:"+request.getRequestURL().toString()+"?"+request.getQueryString());
        LOGGER.error("错误描述:"+exception.getMessage());
        return "{\"code\":false,\"content\":\"请求服务器失败\"}";
    }


    @ExceptionHandler(value=NullPointerException.class)
    public String NullExceptionHandler(HttpServletRequest request, Exception exception)
    {
        LOGGER.error("控制器地址:"+request.getRequestURL().toString()+"?"+request.getQueryString());
        LOGGER.error("错误描述:"+exception.getMessage());
        return "{\"code\":false,\"content\":\"解析数据格式异常\"}";
    }

}