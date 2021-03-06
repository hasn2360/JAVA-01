package com.example.read_write_sharding_jdbc;

import com.example.read_write_sharding_jdbc.mapper.OrderMapper;
import com.example.read_write_sharding_jdbc.model.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.math.BigDecimal;

@SpringBootTest
class ReadWriteShardingJdbcApplicationTests {

    @Resource
    private OrderMapper orderMapper;

    @Test
    void contextLoads() {
    }
    @Test
    void testSelect(){
        Order order = orderMapper.selectByPrimaryKey(1L);
        System.out.println(order);
    }

    @Test
    void testInsert(){
        Order order = new Order();
        order.setOrderId("3");
        order.setProductCode("3abc");
        order.setUserName("ssd3");
        order.setCount(1);
        order.setUnitPrice(BigDecimal.TEN);
        order.setRealPrice(BigDecimal.TEN);
        orderMapper.insertSelective(order);
    }
}
