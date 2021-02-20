package com.example.springbootdemo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Auther: song.huai
 * @Date: 2021/2/20 16:58
 * @Description:
 */
@Component
@ConfigurationProperties(prefix = "auto.config.student")
public class StudentConfig {

    private int id;

    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
