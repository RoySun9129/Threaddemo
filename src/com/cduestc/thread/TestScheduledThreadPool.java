package com.cduestc.thread;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/*
 * 线程池：提供一个线程队列，队列中保存着所有等待状态的线程。
 * 避免了创建与销毁额外开销
 * 二：线程池的体系结构
 * 	java.util.concurrent.Executor:负责线程的使用与调度的根接口
 * 		|--ExecutorService 子接口：线程池的主要接口
 * 			|--ThreadPoolExecutor 线程池的实现类
 * 			|--ScheduleExecutorService 子接口：负责线程的调度
 * 				|--ScheduledThreadPoolExecutor:继承了ThreadPoolExecutor，实现了ScheduleExecutorService 
 * 
 * 三：工具类Executors
 * ExecutorService newFixedThreadPool():创建固定大小的线程池
 * ExecutorService newCachedThreadPool()：缓存线程池，线程池的数量不固定，可以根据需要自动的更改数量
 * ExecutorService newSingleThreadExecutor():创建多个线程池，线程池中只有一个线程
 * 
 * ScheduledExecutorService newScheduledThreadPool():创建固定大小的线程，可以延迟或定时的执行任务
 * 
 */
public class TestScheduledThreadPool {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ScheduledExecutorService poolExecutorService =Executors.newScheduledThreadPool(5);
		
		for(int i=0;i<10;i++){
			Future<Integer> result = 
					poolExecutorService.schedule(new Callable<Integer>() {
						@Override
						public Integer call() throws Exception {
							// TODO Auto-generated method stub
							int num = new Random().nextInt(100);
							System.out.println(Thread.currentThread().getName()+" : "+num);
							return num;
						}
					}, 3, TimeUnit.SECONDS);
			System.out.println(result.get());
		}
		
		poolExecutorService.shutdown();
	}
}
