package com.cduestc.thread;

import java.util.concurrent.atomic.AtomicInteger;

/*
 * 原子变量：java.util.concurrent.atomic包下提供了常用的原子变量
 * 	1：volatile保证内存可见性
 * 	2：CAS算法保证数据的原子性
 * 	CAS算法是硬件对于并发操作共享数据的支持
 * 	CAS包含了三个操作数
 * 	内存值V
 *  预估值A
 *  更新值B
 *  当V==A 时，V=B，才进行操作，否则不进行任何操作
 */
public class TestAtomicDemo {
	public static void main(String[] args) {
		AtomicDemo aDemo = new AtomicDemo();
		for(int i=0;i<10;i++){
			new Thread(aDemo).start();
		}
	}

}
class AtomicDemo implements Runnable{
	
	private AtomicInteger serialNumber =new AtomicInteger();

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(1100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+":"+getSerialNumber());
	}

	public int getSerialNumber() {
		return serialNumber.getAndIncrement();
	}

	
}
