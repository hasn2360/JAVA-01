package com.example.springbootdemo.dao;

import com.example.springbootdemo.bean.Student;
import org.springframework.stereotype.Repository;

/**
 * @Auther: song.huai
 * @Date: 2021/2/20 16:40
 * @Description:
 */
@Repository
public class ConnectPoolStudentDao implements StudentDao {
    @Override
    public Student selectById(int id) {
        return null;
    }

    @Override
    public void insert(Student student) {

    }

    @Override
    public void transactionUpdate(Student student1, Student student2) {

    }
}
