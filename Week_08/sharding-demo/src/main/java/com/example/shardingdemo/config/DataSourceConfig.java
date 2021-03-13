package com.example.shardingdemo.config;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;
import org.apache.shardingsphere.infra.config.algorithm.ShardingSphereAlgorithmConfiguration;
import org.apache.shardingsphere.sharding.api.config.ShardingRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.rule.ShardingTableRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.strategy.keygen.KeyGenerateStrategyConfiguration;
import org.apache.shardingsphere.sharding.api.config.strategy.sharding.StandardShardingStrategyConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

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
        dataSourceMap.put("ds_0", ds0DataSource());
        // 配置第 2 个数据源
        dataSourceMap.put("ds_1", ds1DataSource());
        ShardingRuleConfiguration shardingRuleConfiguration = new ShardingRuleConfiguration();
        //t_order分表规则
        ShardingTableRuleConfiguration tOrderRuleConfiguration =
                new ShardingTableRuleConfiguration("t_order", "ds_${0..1}.t_order_${0..15}");
        tOrderRuleConfiguration.setKeyGenerateStrategy(
                new KeyGenerateStrategyConfiguration("order_id", "snowflake"));
        tOrderRuleConfiguration.setTableShardingStrategy(new
                StandardShardingStrategyConfiguration("order_id", "tOrderInlineShardingAlgorithm"));

        Properties tOrderShardingInlineProps = new Properties();
        tOrderShardingInlineProps.setProperty("algorithm-expression", "t_order_${order_id % 16}");
        shardingRuleConfiguration.getShardingAlgorithms().putIfAbsent("tOrderInlineShardingAlgorithm",
                new ShardingSphereAlgorithmConfiguration("INLINE", tOrderShardingInlineProps));

        shardingRuleConfiguration.getTables().add(tOrderRuleConfiguration);
//        shardingRuleConfiguration.getBindingTableGroups().add("t_order");
//        shardingRuleConfiguration.getBroadcastTables().add("t_bank");
        // 分库策略
        shardingRuleConfiguration.setDefaultDatabaseShardingStrategy(new
                StandardShardingStrategyConfiguration("user_id", "default_db_strategy_inline"));
        Properties defaultDatabaseStrategyInlineProps = new Properties();
        defaultDatabaseStrategyInlineProps.setProperty("algorithm-expression", "ds_${user_id % 2}");
        shardingRuleConfiguration.getShardingAlgorithms().put("default_db_strategy_inline",
                new ShardingSphereAlgorithmConfiguration("INLINE", defaultDatabaseStrategyInlineProps));

        Properties snowflakeProperties = new Properties();
        snowflakeProperties.setProperty("worker-id", "123");
        shardingRuleConfiguration.getKeyGenerators().put("snowflake", new
                ShardingSphereAlgorithmConfiguration("SNOWFLAKE", snowflakeProperties));


        Properties otherProperties = new Properties();
        otherProperties.setProperty("sql-show", "true");
        // 创建 ShardingSphereDataSource
        DataSource dataSource =
                ShardingSphereDataSourceFactory.createDataSource(dataSourceMap, Arrays.asList(shardingRuleConfiguration), otherProperties);

        return dataSource;
    }

    @Bean
    @ConfigurationProperties("spring.datasource.ds1")
    public DataSource ds1DataSource() {
        return new HikariDataSource();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.ds0")
    public HikariDataSource ds0DataSource() {
        return new HikariDataSource();
    }

}
