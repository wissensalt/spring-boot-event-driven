package com.wissensalt.rnd.sbed.config;

import java.util.concurrent.Executor;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsynchronousTaskConfiguration implements AsyncConfigurer {
	// brew services start mongodb
	/*
	 * Then access the shell by
	 * 
	 * mongo
	 * 
	 * You can shut down your db by
	 * 
	 * brew services stop mongodb
	 * 
	 * sample doc:
	 * db.createUser({
	 * ... user: 'admin',
	 * ... pwd: 'm0k4P0S',
	 * ... roles: [{role:'readWrite', db:'stuck_offline_db'}]
	 * ... })
	 * */
	private static final String TASK_EXECUTOR_STUCK_OFFLINE = "StuckOfflineTaskExecutor-";
	private final AsynchronousTaskProperties asyncTaskProperties;
	
	public AsynchronousTaskConfiguration(final AsynchronousTaskProperties asyncTaskProperties) {
		this.asyncTaskProperties = asyncTaskProperties;
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
