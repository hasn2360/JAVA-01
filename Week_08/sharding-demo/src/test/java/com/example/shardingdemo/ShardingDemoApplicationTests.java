package com.example.shardingdemo;

import com.example.shardingdemo.mapper.TOrderMapper;
import com.example.shardingdemo.model.TOrder;
import com.example.shardingdemo.model.TOrderExample;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.math.BigDecimal;

@SpringBootTest
class ShardingDemoApplicationTests {
    @Resource
    private TOrderMapper tOrderMapper;

    @Test
    void contextLoads() {
    }

    @Test
    void testInsert() {

        for (int i = 100; i < 120; i++) {
            TOrder tOrder = new TOrder();
            tOrder.setUserId(Long.valueOf(i));
            tOrder.setTotalPrice(BigDecimal.TEN);
            tOrderMapper.insertSelective(tOrder);

        }
    }

    @Test
    void testSelect(){
        TOrder tOrder = tOrderMapper.selectByPrimaryKey(577678749038870529L);
        System.out.println(tOrder);
    }
}
