package com.example.springbootdemo;

import com.example.springbootdemo.bean.Student;
import com.example.springbootdemo.dao.StudentDao;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class SpringBootDemoApplicationTests {
    @Resource
    private StudentDao jdbcStudentDao;

    @Test
    void contextLoads() {
    }

    @Test
    public void testJdbcSelect(){
        Student student = jdbcStudentDao.selectById(2);
        System.out.println(student);
    }

    @Test
    public void testJdbcInsert(){
        jdbcStudentDao.insert(Student.builder().name("xxx").build());
    }

    @Test
    public void testTransactionUpdate(){
        Student student1 = Student.builder().id(1).name("aaaaa").build();
        Student student2 = Student.builder().id(2).name("zxcv").build();
        jdbcStudentDao.transactionUpdate(student1, student2);
    }

}
