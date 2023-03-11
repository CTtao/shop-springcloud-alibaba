package com.ct.shop.user.config;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.sleuth.instrument.async.LazyTraceExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.Resource;
import java.util.concurrent.Executor;

/**
 * @author CT
 * @description
 */
@Configuration
@EnableAutoConfiguration
public class ThreadPoolTaskExecutorConfig extends AsyncConfigurerSupport {
    @Resource
    private BeanFactory beanFactory;

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(5);
        executor.setQueueCapacity(10);
        executor.setThreadNamePrefix("trace-thread-");
        executor.initialize();
        return new LazyTraceExecutor(this.beanFactory,executor);
    }
}
