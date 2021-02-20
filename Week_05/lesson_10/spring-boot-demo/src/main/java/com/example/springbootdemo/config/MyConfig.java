package com.example.springbootdemo.config;

import com.example.springbootdemo.bean.Klass;
import com.example.springbootdemo.bean.School;
import com.example.springbootdemo.bean.Student;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @Auther: song.huai
 * @Date: 2021/2/19 23:30
 * @Description: Student/Klass/School 自动装配
 */
@Configuration
@ConditionalOnProperty(prefix = "auto.config", name = "enabled", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(StudentConfig.class)
public class MyConfig {

    @Resource
    private StudentConfig studentConfig;

    @Bean
    public Student buildStudent(){
        Student s = Student.builder().id(studentConfig.getId()).name(studentConfig.getName()).build();
        return s;
    }

    @Bean
    public Klass buildKlass(){
        Klass k = new Klass();
        k.setStudents(Lists.newArrayList(buildStudent()));
        return k;
    }

    @Bean
    public School buildSchool(){
        School school = new School();
        school.setClass1(buildKlass());
        return school;
    }
}
