package com.example.springbootdemo.dao;

import com.example.springbootdemo.bean.Student;

/**
 * @Auther: song.huai
 * @Date: 2021/2/20 00:13
 * @Description:
 */
public interface StudentDao {

    Student selectById(int id);

    void insert(Student student);

    void transactionUpdate(Student student1, Student student2);
}
