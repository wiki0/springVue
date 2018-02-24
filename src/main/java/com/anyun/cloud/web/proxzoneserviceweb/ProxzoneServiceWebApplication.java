package com.anyun.cloud.web.proxzoneserviceweb;

import com.anyun.cloud.web.proxzoneserviceweb.util.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author: yangfan
 * @date: 17-11-1 下午4:28
 * @description:
 */
@SpringBootApplication
@EnableConfigurationProperties
public class ProxzoneServiceWebApplication {

    @Autowired
    private RestTemplateBuilder builder;

    /**
     * 使用RestTemplateBuilder来实例化RestTemplate对象，spring默认已经注入了RestTemplateBuilder实例
     */
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = builder.build();
        //向restTemplate中添加自定义的拦截器
        restTemplate.getInterceptors().add(new TokenInterceptor());
        return restTemplate;
    }

    public static void main(String[] args) {
        SpringApplication.run(ProxzoneServiceWebApplication.class, args);
    }
}