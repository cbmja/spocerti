package com.spo.certificate.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestCon {

    @GetMapping("/test")
    public String index(){

        return "view/main/main";
    }


    @GetMapping("/layout")
    public String layout(){

        return "layouts/layout";
    }

}
