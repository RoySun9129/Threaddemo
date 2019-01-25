package com.cduestc.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/*
 * 创建执行线程的方式三：实现Callable接口 这个方式可以有返回值，并且可以抛出异常
 */
public class TestCallable {
	public static void main(String[] args) {
		
		ThreadDemo1 td = new ThreadDemo1();
		
		//	执行Callable 方式，需要FutureTask 实现类的支持，用于接收运算结果
		//	FutureTask 是Future接口的实现类
		FutureTask<Integer> resultFutureTask = new FutureTask<Integer>(td);
	
		new Thread(resultFutureTask).start();
	
		//2接收线程运算的结果
		try {
			Integer sum = resultFutureTask.get();//FutureTask 可用于闭锁
			System.out.println(sum);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
class ThreadDemo1 implements Callable<Integer>{

	@Override
	public Integer call() throws Exception {
		// TODO Auto-generated method stub
		int sum=0;
		for(int i=0;i<=100;i++){
			System.out.println(i);
			sum+=i;
		}
		return sum;
	}
	
}
