package com.cduestc.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * 编写一个程序，开启三个线程，这三个线程的ID分别是A,B,C,
 * 每个线程将自己的ID在屏幕上打印10遍，要求输出的结果必须按照顺序显示
 * 如 abcabcabc。。。。
 * 
 */
public class TestABCIternate {
	public static void main(String[] args) {
		AlternateDemo aDemo = new AlternateDemo();
		new Thread(new Runnable() {
			public void run() {
				for(int i=0;i<20;i++)
					aDemo.loopA();
			}
		},"A").start();
		new Thread(new Runnable() {
			public void run() {
				for(int i=0;i<20;i++)
					aDemo.loopB();
			}
		},"B").start();
		new Thread(new Runnable() {
			public void run() {
				for(int i=0;i<20;i++)
					aDemo.loopC();
			}
		},"C").start();
	}
}
class AlternateDemo{
	private int number =1;
	
	private Lock lock = new ReentrantLock();
	private Condition condition1 = lock.newCondition();
	private Condition condition2 = lock.newCondition();
	private Condition condition3 = lock.newCondition();
	
	public void loopA(){
		lock.lock();
		try {
			if(number!=1){
				condition1.await();
			}
			System.out.println(Thread.currentThread().getName()+"\t");
			
			number=2;
			condition2.signal();
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			lock.unlock();
		}
	}
	public void loopB(){
		lock.lock();
		try {
			if(number!=2){
				condition2.await();
			}
			System.out.println(Thread.currentThread().getName()+"\t");
			
			number=3;
			condition3.signal();
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			lock.unlock();
		}
	}
	public void loopC(){
		lock.lock();
		try {
			if(number!=3){
				condition3.await();
			}
			System.out.println(Thread.currentThread().getName()+"\t");
			
			number=1;
			condition1.signal();
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			lock.unlock();
		}
	}
}