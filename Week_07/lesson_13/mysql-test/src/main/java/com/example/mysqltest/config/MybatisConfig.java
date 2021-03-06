package com.example.mysqltest.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: song.huai
 * @Date: 2021/3/2 00:32
 * @Description:
 */
@Configuration
@MapperScan("com.example.mysqltest.mapper")
public class MybatisConfig {
}
