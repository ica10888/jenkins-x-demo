package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ica10888
 * @version 1.0
 * @date 2018/11/16 11:25
 */
@RestController
public class SampleController {
    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }
    @RequestMapping("/health")
    String health() {
        return "{\"status\": \"UP\"}";
    }
}
