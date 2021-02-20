package com.example.springbootdemo.bean;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @Auther: song.huai
 * @Date: 2021/2/18 23:46
 * @Description:
 */
@Data
@ToString
public class Klass {
    private List<Student> students;

}
