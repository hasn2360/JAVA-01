package com.example.shardingdemo;

import com.example.shardingdemo.service.TestOrderService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @Auther: song.huai
 * @Date: 2021/3/14 02:21
 * @Description:
 */
@SpringBootTest
public class TransactionTest {

    @Resource
    private TestOrderService testOrderService;
    @Test
    void test1(){
        testOrderService.insert();
    }
}
