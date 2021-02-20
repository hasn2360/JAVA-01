package com.example.springbootdemo.dao;

import com.example.springbootdemo.bean.Student;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @Auther: song.huai
 * @Date: 2021/2/20 00:13
 * @Description:
 */
@Repository
public class JdbcStudentDao implements StudentDao {
    @Resource
    private DataSource dataSource;

    @Override
    public Student selectById(int id) {
        Connection con = null;        //连接
        PreparedStatement pstmt = null;    //使用预编译语句
        ResultSet rs = null;    //获取的结果集
        try {
            con = dataSource.getConnection();

            String sql = "select * from student where id = ?"; //设置的预编译语句格式
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs == null) {
                return null;
            }
            if (rs.next()) {
                return Student.builder().id(rs.getInt("id")).name(rs.getString("name")).build();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            //关闭资源
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (Exception e) {
            }
        }
        return null;
    }

    @Override
    public void insert(Student student) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = dataSource.getConnection();

            String sql = "insert into student values(null, ?)"; //设置的预编译语句格式
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, student.getName());
            pstmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            //关闭资源
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public void transactionUpdate(Student student1, Student student2) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = dataSource.getConnection();
            con.setAutoCommit(false);
            //第一条采用预编译
            String sql = "update student set name = ? where id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, student1.getName());
            pstmt.setInt(2, student1.getId());
            pstmt.executeUpdate();

            //第二条不采用预编译
            Statement statement = con.createStatement();
            statement.executeUpdate("update student set name = '" + student2.getName() + "' where id = " + student2.getId());
            con.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            //关闭资源
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (Exception e) {
            }
        }
    }
}
