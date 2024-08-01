package com.spo.certificate.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestCon {

    @GetMapping("/test")
    public String test(){

        return "view/test";
    }
}
