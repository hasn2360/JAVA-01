package com.example.read_write_sharding_jdbc.config;

import com.google.common.collect.Lists;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;
import org.apache.shardingsphere.infra.config.algorithm.ShardingSphereAlgorithmConfiguration;
import org.apache.shardingsphere.replicaquery.api.config.ReplicaQueryRuleConfiguration;
import org.apache.shardingsphere.replicaquery.api.config.rule.ReplicaQueryDataSourceRuleConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.*;

/**
 * @Auther: song.huai
 * @Date: 2021/3/6 00:00
 * @Description:
 */
@Configuration
public class DataSourceConfig {

    @Bean(name = "shardingDataSource")
    public DataSource getDataSource() throws SQLException {
        // 配置真实数据源
        Map<String, DataSource> dataSourceMap = new HashMap<>();
        // 配置第 1 个数据源
        dataSourceMap.put("primary_ds0", masterDataSource());
        // 配置第 2 个数据源
        dataSourceMap.put("primary_ds0_replica0", slave1DataSource());
        ReplicaQueryDataSourceRuleConfiguration configuration = new ReplicaQueryDataSourceRuleConfiguration(
                "ds0", "primary_ds0", Arrays.asList("primary_ds0_replica0"), "roundRobin");
        Map<String, ShardingSphereAlgorithmConfiguration> loadBalanceMaps = new HashMap<>
                (1);
        loadBalanceMaps.put("roundRobin", new ShardingSphereAlgorithmConfiguration("ROUND_ROBIN", new Properties()));
        ReplicaQueryRuleConfiguration replicaQueryRuleConfiguration = new ReplicaQueryRuleConfiguration(Arrays.asList(configuration), loadBalanceMaps);
        Properties otherProperties = new Properties();
        otherProperties.setProperty("sql-show", "true");
        // 创建 ShardingSphereDataSource
        DataSource dataSource = ShardingSphereDataSourceFactory.
                createDataSource(dataSourceMap, Arrays.asList(replicaQueryRuleConfiguration), otherProperties);

        return dataSource;
    }

    @Bean
    @ConfigurationProperties("spring.datasource.slave1")
    public HikariDataSource slave1DataSource() {
        return new HikariDataSource();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.master")
    public HikariDataSource masterDataSource() {
        return new HikariDataSource();
    }

}
