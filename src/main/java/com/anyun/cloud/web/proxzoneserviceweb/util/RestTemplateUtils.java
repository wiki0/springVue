package com.anyun.cloud.web.proxzoneserviceweb.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.Map;

import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.PUT;

/**
 * @author: yangfan
 * @date: 18-1-26 上午11:47
 * @description: 获取服务器数据
 */
@Component
public class RestTemplateUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestTemplateUtils.class);

    @Value("${api.host}")
    private String host;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 发送/获取 服务端数据(主要用于解决发送put,delete方法无返回值问题).
     *
     * @return 返回结果(响应体)
     */
    public <T> T exchange(String url, HttpMethod method, String str, Class<T> bodyType) {
        // 请求头
        HttpHeaders headers = new HttpHeaders();
        MimeType mimeType = MimeTypeUtils.parseMimeType("application/json");
        MediaType mediaType = new MediaType(mimeType.getType(), mimeType.getSubtype(), Charset.forName("UTF-8"));
        // 请求体
        headers.setContentType(mediaType);
        // 发送请求
        HttpEntity<String> entity = new HttpEntity<>(str, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<T> resultEntity = restTemplate.exchange(url, method, entity, bodyType);
        return resultEntity.getBody();
    }


    /**
     * 通用分页获取 GET方法
     */
    public String getPage(String url, Map<String, Object> params) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("?");
        for (String key : params.keySet()) {
            stringBuilder.append(key + "={" + key + "}&");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        UriComponents uriComponents = UriComponentsBuilder.fromUriString(host + url + stringBuilder.toString()).build().expand(params).encode();
        URI uri = uriComponents.toUri();
        String response = restTemplate.getForEntity(uri, String.class).getBody();
        return codeTranslate(response);
    }


    /**
     * 获取详情 GET方法
     */
    public String detail(String url) {

        String response = restTemplate.getForEntity(host + url, String.class).getBody();
        return codeTranslate(response);
    }

    /**
     * 标准修改  POST方法
     *
     */
    public String update(String url, String jsonObject) {
        UriComponents uriComponents = UriComponentsBuilder.fromUriString(host + url).build();
        URI uri = uriComponents.toUri();
        ObjectMapper objectMapper = new ObjectMapper();
        Object obj;
        try {
            obj = objectMapper.readValue(jsonObject, Object.class);
        } catch (IOException e) {
            LOGGER.error("readValue error: " + e);
            return "{\"code\":false,\"content\":\"请求参数错误\"}";
        }
        String response = restTemplate.postForEntity(uri, obj, String.class).getBody();
        return codeTranslate(response);
    }

    /**
     * 标准创建 PUT方法
     */
    public String create(String url, String jsonObject) {

        String response = exchange(host + url, PUT, jsonObject, String.class);
        return codeTranslate(response);
    }

    /**
     * 自义定删除 DELETE方法
     *
     */
    public String delete(String url, Integer id) {

        String response = exchange(host + url + "/" + id, DELETE, null, String.class);
        return codeTranslate(response);
    }


    /**
     * code 翻译
     *
     */
    public String codeTranslate(String response) {
        LOGGER.info(response);
        JsonObject returnData = new JsonParser().parse(response).getAsJsonObject();
        Integer code = returnData.get("code").getAsInt();
        if (0 == code) {
            return "{\"code\":true,\"content\":" + returnData.get("content") + "}";
        } else {
            return "{\"code\":false,\"content\":" + returnData.get("content") + "}";
        }
    }

    /**
     * 上传文件
     *
     */
    public String sendFile(MultipartFile file, String url) {
        File f = null;
        String response = "";
        try {
            //转 file 缓存
            f = File.createTempFile("tmp", null);
            file.transferTo(f);
            f.deleteOnExit();
            RestTemplate rest = new RestTemplate();
            FileSystemResource resource = new FileSystemResource(f);
            MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
            param.add("file", resource);
            param.add("fileName", "zip");
            response = rest.postForObject(host + url, param, String.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return codeTranslate(response);
    }

}
