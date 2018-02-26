package com.anyun.cloud.web.proxzoneserviceweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author: yangfan
 * @date: 18-2-26 下午4:28
 * @description: 首页
 */
@Controller
public class DashboradController {

    @RequestMapping(value = "/")
    public String dashboard() {
        return "index";
    }

}

