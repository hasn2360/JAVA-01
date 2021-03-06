package com.example.mysqltest;

import com.example.mysqltest.mapper.OrderMapper;
import com.example.mysqltest.model.Order;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class MysqlTestApplicationTests {
    private static final Logger LOGGER = LoggerFactory.getLogger(MysqlTestApplicationTests.class);

    @Resource
    private OrderMapper orderMapper;
    @Resource
    private DataSource dataSource;
    @Resource
    private SqlSessionFactory sqlSessionFactory;

    @Test
    void contextLoads() {
    }

    /**
     * mybatis拼接sql批量插入
     * 耗时：53856ms
     */
    @Test
    void testInsert1() {
        long start = 0;
        for (int j = 0; j < 1000; j++) {
            List<Order> list = new ArrayList<>();
            for (int i = 0; i < 1000; i++) {
                Order order = new Order();
                order.setOrderId(j + "" + i);
                order.setProductCode(j + "" + i);
                order.setUserName("ab");
                order.setCount(1);
                list.add(order);
            }
            long start1 = System.currentTimeMillis();
            orderMapper.insertOrderList(list);
            start = start + (System.currentTimeMillis() - start1);
//            LOGGER.info("插入数据行:{}, 数据量:{}", j, count);
        }
        LOGGER.info("响应时间:{}", start);
    }

    /**
     * jdbc批量插入
     * 耗时：22378ms
     *
     * @throws SQLException
     */
    @Test
    void testInsert2() throws SQLException {
        Connection connection = dataSource.getConnection();
        connection.setAutoCommit(false);
        String sql = "insert into `order`(" +
                "        order_id," +
                "        product_code, user_name, count," +
                "        create_time, update_time)" +
                "        values(?,?,?,?,?,?) ";

        PreparedStatement statement = connection.prepareStatement(sql);
        Date nowDate = new Date(1614700311000L);
        for (int i = 0; i < 1000000; i++) {
            statement.setString(1, i + "");
            statement.setString(2, i + "");
            statement.setString(3, "ab");
            statement.setInt(4, 1);
            statement.setDate(5, nowDate);
            statement.setDate(6, nowDate);
            statement.addBatch();
        }
        long start = System.currentTimeMillis();

        statement.executeBatch();
        connection.commit();
        connection.close();
        statement.close();

        long end = System.currentTimeMillis();
        LOGGER.info("响应时间:{}", end - start);
    }

    /**
     * mybatis 开启batch批量插入
     * 耗时：41837ms
     */
    @Test
    void testInsert3() {
        SqlSession sqlsession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
        OrderMapper orderMapper = sqlsession.getMapper(OrderMapper.class);
        /*java.util.Date nowDate = new java.util.Date();
        for (int i = 0; i < 1000000; i++) {
            Order order = new Order();
            order.setOrderId(i + "");
            order.setProductCode(i + "");
            order.setUserName("ab");
            order.setCount(1);
            order.setCreateTime(nowDate);
            order.setUpdateTime(nowDate);
            orderMapper.insertSelective(order);
        }*/
        long time = 0;
        for (int j = 0; j < 1000; j++) {
            List<Order> list = new ArrayList<>();
            for (int i = 0; i < 1000; i++) {
                Order order = new Order();
                order.setOrderId(j + "" + i);
                order.setProductCode(j + "" + i);
                order.setUserName("ab");
                order.setCount(1);
                list.add(order);
            }
            long start1 = System.currentTimeMillis();
            orderMapper.insertOrderList(list);
            time = time + (System.currentTimeMillis() - start1);
//            LOGGER.info("插入数据行:{}, 数据量:{}", j, count);
        }
        long start = System.currentTimeMillis();
        sqlsession.commit();
        sqlsession.clearCache();
        sqlsession.close();
//        LOGGER.info("响应时间:{}", System.currentTimeMillis() - start);
        LOGGER.info("响应时间:{}", time + (System.currentTimeMillis() - start));
    }
}
