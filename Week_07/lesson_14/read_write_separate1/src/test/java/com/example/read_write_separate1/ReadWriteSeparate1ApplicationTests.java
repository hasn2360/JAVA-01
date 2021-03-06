package com.example.read_write_separate1;

import com.example.read_write_separate1.mapper.OrderMapper;
import com.example.read_write_separate1.model.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.math.BigDecimal;

@SpringBootTest
class ReadWriteSeparate1ApplicationTests {

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
        order.setOrderId("2");
        order.setProductCode("abc");
        order.setUserName("ssd");
        order.setCount(1);
        order.setUnitPrice(BigDecimal.TEN);
        order.setRealPrice(BigDecimal.TEN);
        orderMapper.insertSelective(order);
    }
}
