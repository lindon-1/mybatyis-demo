package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

/**
 * @Description：
 * @Author: ldl
 * @CreateDate: 2019/12/10 16:56
 */
//@Configuration
//public class RedisConfig {
//
//    @Bean
//    public RestTemplate restTemplate() {
//        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.getMessageConverters().add(0, new  StringHttpMessageConverter(Charset.forName("UTF-8")));
//        return restTemplate;
//    }
//}
