package com.example.read_write_separate1.config;

import lombok.extern.slf4j.Slf4j;

/**
 * @Auther: song.huai
 * @Date: 2021/3/6 00:40
 * @Description:
 */
@Slf4j
public class DynamicDBContext {
    private static final ThreadLocal<DBTypeEnum> contextHolder = new InheritableThreadLocal<>();

    public static void set(DBTypeEnum dbType) {
        contextHolder.set(dbType);
    }

    public static DBTypeEnum get() {
        return contextHolder.get();
    }

    public static void master() {
        set(DBTypeEnum.MASTER);
        log.info("切换到master");
    }

    public static void slave() {
        set(DBTypeEnum.SLAVE1);
        log.info("切换到slave1");
    }

    public static void remove() {
        contextHolder.remove();
    }
}
