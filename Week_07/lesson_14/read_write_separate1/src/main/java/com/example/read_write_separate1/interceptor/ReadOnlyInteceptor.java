package com.example.read_write_separate1.interceptor;

import com.example.read_write_separate1.config.DBTypeEnum;
import com.example.read_write_separate1.config.DynamicDBContext;
import com.example.read_write_separate1.config.ReadOnly;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @Auther: song.huai
 * @Date: 2021/3/6 00:58
 * @Description:
 */
@Aspect
@Component
@Slf4j
public class ReadOnlyInteceptor {

    @Around("@annotation(readOnly)")
    public Object setResource(ProceedingJoinPoint joinPoint, ReadOnly readOnly) throws Throwable {
        try{
            DynamicDBContext.slave();
            return joinPoint.proceed();
        }finally {
            DynamicDBContext.remove();
            log.info("清除threadLocal");
        }
    }
}
