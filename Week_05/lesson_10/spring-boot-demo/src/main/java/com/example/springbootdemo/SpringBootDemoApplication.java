package com.example.springbootdemo;

import com.example.springbootdemo.bean.Klass;
import com.example.springbootdemo.bean.School;
import com.example.springbootdemo.bean.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@SpringBootApplication
public class SpringBootDemoApplication {

    @Resource
    private Student student;
    @Resource
    private Klass klass;
    @Resource
    private School school;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoApplication.class, args);
    }

    @PostConstruct
    public void init(){
        System.out.println(student);
        System.out.println(klass);
        System.out.println(school);
    }
}
