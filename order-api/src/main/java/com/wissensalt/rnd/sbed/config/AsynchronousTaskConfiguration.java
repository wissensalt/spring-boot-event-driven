package com.wissensalt.rnd.sbed.config;

import java.util.concurrent.Executor;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsynchronousTaskConfiguration implements AsyncConfigurer {
	private static final String TASK_EXECUTOR_NAME_PREFIX_STUCK_OFFLINE = "StuckOfflineTaskExecutor-";
	public static final String TASK_EXECUTOR_STUCK_OFFLINE = "StuckOfflineExecutor";
	
	private final AsynchronousTaskProperties asyncTaskProperties;
	
	public AsynchronousTaskConfiguration(final AsynchronousTaskProperties asyncTaskProperties) {
		this.asyncTaskProperties = asyncTaskProperties;
	}
	
	@Bean(name = TASK_EXECUTOR_STUCK_OFFLINE)
	public Executor getStuckOfflineExecutor() {
		return newTaskExecutor(TASK_EXECUTOR_NAME_PREFIX_STUCK_OFFLINE);
	}
	
	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		return new SimpleAsyncUncaughtExceptionHandler();
	}
	
	private Executor newTaskExecutor(final String taskExecutorNamePrefix) {
		final AsynchronousTaskProperties.Async asyncProperties = asyncTaskProperties.getAsync();
		final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(asyncProperties.getCorePoolSize());
		executor.setMaxPoolSize(asyncProperties.getMaxPoolSize());
		executor.setQueueCapacity(asyncProperties.getQueueCapacity());
		executor.setThreadNamePrefix(taskExecutorNamePrefix);
		return executor;
	}
}
