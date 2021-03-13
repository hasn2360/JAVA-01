package com.example.shardingdemo.service;

import com.example.shardingdemo.mapper.TOrderMapper;
import com.example.shardingdemo.model.TOrder;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @Auther: song.huai
 * @Date: 2021/3/14 02:32
 * @Description:
 */
@Service
public class TestOrderService {
    @Resource
    private TOrderMapper tOrderMapper;

    @Transactional
    @ShardingTransactionType(TransactionType.XA)
    public void insert(){
        TOrder tOrder = new TOrder();
        tOrder.setOrderId(50L);
        tOrder.setUserId(200L);
        tOrder.setTotalPrice(BigDecimal.TEN);
        tOrderMapper.insert(tOrder);
        TOrder tOrder1 = new TOrder();
        tOrder1.setOrderId(50L);
        tOrder1.setUserId(201L);
        tOrder1.setTotalPrice(BigDecimal.TEN);
        tOrderMapper.insert(tOrder1);
        int i = 1/0;


    }
}
