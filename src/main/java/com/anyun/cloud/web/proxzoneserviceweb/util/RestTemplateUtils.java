package com.anyun.cloud.web.proxzoneserviceweb.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
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
import java.util.HashMap;
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

    private final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

    public void set(String key, String value) {
        params.add(key, value);
    }

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
     * 无分页数组返回 GET方法
     *
     * @param url 地址
     * @return list对象
     */
    public String getPagination(String url) {

        String response = restTemplate.getForEntity(host + url, String.class).getBody();
        return codeTranslate(response);
    }

    //分配IP段专用
    public String getPagination(String url, Integer count, Integer blockId,String description) {
        Map<String, Object> params = new HashMap<>();
        params.put("count", count);
        params.put("blockId", blockId);
        params.put("description", description);

        UriComponents uriComponents = UriComponentsBuilder.fromUriString(host + url + "?count={count}&blockId={blockId}&description={description}").build().expand(params).encode();
        URI uri = uriComponents.toUri();
        String response = restTemplate.getForEntity(uri, String.class).getBody();
        return codeTranslate(response);

    }

    /**
     * 返回下拉框 GET方法
     *
     * @param url   地址
     * @param param 参数
     * @return list对象
     */
    public String getSelect(String url, String param) {

        Map<String, Object> params = new HashMap<>();
        params.put("condition", param);
        UriComponents uriComponents = UriComponentsBuilder.fromUriString(host + url + "?condition={condition}").build().expand(params).encode();
        URI uri = uriComponents.toUri();
        String response = restTemplate.getForEntity(uri, String.class).getBody();
        JsonObject returnData = new JsonParser().parse(response).getAsJsonObject();
        if (0 == returnData.get("code").getAsInt()) {

            JsonArray array = returnData.get("content").getAsJsonArray();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("[");
            for (JsonElement obj : array) {
                stringBuilder.append("{\"value\":");
                stringBuilder.append(obj.getAsJsonObject().get("appId").getAsString());
                stringBuilder.append(",\"text\":\"");
                stringBuilder.append(obj.getAsJsonObject().get("name").getAsString());
                stringBuilder.append("\"},");
            }
            if (array.size() > 0) {
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            }
            stringBuilder.append("]");
            return stringBuilder.toString();
        } else {
            LOGGER.error(response);
            return "[]";
        }
    }

    /**
     * 标准修改  POST方法
     *
     * @param url 地址
     * @return
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
     * @param url 地址
     * @return 说明：
     * 1）url: 请求地址；
     * 2）method: 请求类型(如：POST,PUT,DELETE,GET)；
     * 3）requestEntity: 请求实体，封装请求头，请求内容
     * 4）responseType: 响应类型，根据服务接口的返回类型决定
     * 5）uriVariables: url中参数变量值
     */
    public String delete(String url) {

        String response = exchange(host + url , DELETE, null, String.class);
        return codeTranslate(response);
    }

    public String delete(String url, Integer id) {

        String response = exchange(host + url + "/" + id, DELETE, null, String.class);
        return codeTranslate(response);
    }

    public String delete(String url, String id) {

        String response = exchange(host + url + "/" + id, DELETE, null, String.class);
        return codeTranslate(response);
    }


    public String delete(String url, Integer id, String user) {

        String response = exchange(host + url + "/" + id + user, DELETE, null, String.class);
        return codeTranslate(response);
    }

    /**
     * code 翻译
     *
     * @param response
     * @return
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
     * @return
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
        System.out.println(response);
        return codeTranslate(response);
    }

}
