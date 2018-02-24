package com.anyun.cloud.web.proxzoneserviceweb.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 * @author: yangfan
 * @date: 18-2-11 下午7:20
 * @description: 拦截器 添加token
 */
public class TokenInterceptor implements ClientHttpRequestInterceptor
{
    private static final Logger LOGGER = LoggerFactory.getLogger(TokenInterceptor.class);

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException
    {
        LOGGER.info("服务器地址:"+request.getURI());
        //todo 远程获取token
        String token = "testToken";
        //将令牌放入请求header中
        request.getHeaders().add("X-Auth-Token",token);
        // 请请求格式
//        MediaType type = MediaType.parseMediaType("application/json;charset=UTF-8");
        // 请求体内容
//        request.getHeaders().setContentType(type);
        return execution.execute(request, body);
    }
}