package com.example.springbootdemo.bean;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * @Auther: song.huai
 * @Date: 2021/2/18 23:45
 * @Description:
 */
@Data
@ToString
@Builder
public class Student {

    private int id;

    private String name;


}
