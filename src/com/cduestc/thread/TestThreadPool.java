package com.cduestc.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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
public class TestThreadPool {
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		//创建一个线程池
		ExecutorService pool = Executors.newFixedThreadPool(5);
		ThreadPoolDemo tpd = new ThreadPoolDemo();
		
		//创建线程方式1：为线程池中的线程分配任务，
		pool.submit(tpd);
		
		//创建线程方式2：建立多个线程
		List<Future<Integer>> futurelList = new ArrayList<Future<Integer>>();
		for(int i=0;i<10;i++){
			
			Future<Integer> future = pool.submit(new Callable<Integer>() {
				
				@Override
				public Integer call() throws Exception {
					// TODO Auto-generated method stub
					int sum =0;
					for(int i=0;i<100;i++){
						sum+=i;
					}
					return sum;
				}
			});
			futurelList.add(future);
		}
		for(Future<Integer> future:futurelList){
			
			System.out.println(future);
		}
		
		//关闭线程池
//		pool.shutdownNow();立即关闭线程池，不管线程任务执行情况，
		pool.shutdown();//等待线程任务执行完毕再关闭线程池
	}

}
class ThreadPoolDemo implements Runnable{
	
	private int i=0;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(i<100){
			System.out.println(Thread.currentThread().getName()+" : "+ i++);
		}
		
	}
	
	
}