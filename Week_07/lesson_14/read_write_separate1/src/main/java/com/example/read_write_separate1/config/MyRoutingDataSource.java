package com.example.read_write_separate1.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @Auther: song.huai
 * @Date: 2021/3/6 00:31
 * @Description:
 */
@Slf4j
public class MyRoutingDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        DBTypeEnum dbTypeEnum = DynamicDBContext.get();
        log.info("实际走的数据源：{}", dbTypeEnum == null ? DBTypeEnum.MASTER : dbTypeEnum.name());
        if (dbTypeEnum == null) {
            return DBTypeEnum.MASTER;
        }
        return dbTypeEnum;
    }
}
