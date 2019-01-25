package com.cduestc.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * 用于解决多线程安全问题的方式
 * synchronized:
 * 1：同步代码块:
 * 2：同步方法：
 * 
 * jdk 1.5后
 * 3：同步锁Lock
 * 是一个显示锁，需要通过lock()方法上锁，必须通过unlock()方法进行释放锁
 * 
 */
public class TestLock {
	public static void main(String[] args) {
		Ticket ticket = new Ticket();
		new Thread(ticket,"一号窗口").start();
		new Thread(ticket,"二号窗口").start();
		new Thread(ticket,"三号窗口").start();
		new Thread(ticket,"四号窗口").start();
	}
}
class Ticket implements Runnable{

	private int ticket =100;
	
	private Lock lock = new ReentrantLock();
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			
			lock.lock();//上锁
			
			try {
				if(ticket>0){
					try {
						Thread.sleep(500);
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				
					System.out.println(Thread.currentThread().getName()+" 完成售票，余额为: "+--ticket);
				}
				
			}catch (Exception e) {
				// TODO: handle exception
			}finally {
				lock.unlock();//释放锁
				}
				
			}
		}
}	

