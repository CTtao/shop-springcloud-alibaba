package com.ct.shop.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CT
 * @description
 */
@Slf4j
@RefreshScope
@RestController
public class NacosController {

    private final ConfigurableApplicationContext context;

    @Autowired
    public NacosController(ConfigurableApplicationContext context){
        this.context = context;
    }
    @Value("${author.name}")
    private String nacosAuthorName;

    @GetMapping("/nacos/test")
    public String nacosTest(){
        String authorName = context.getEnvironment().getProperty("author.name");
        log.info("获得的作者姓名为：{}",authorName);
        return authorName;
    }

    @GetMapping("/nacos/name")
    public String nacosName(){
        log.info("从nacos中获得的作者姓名为：{}",nacosAuthorName);
        return nacosAuthorName;
    }
}
