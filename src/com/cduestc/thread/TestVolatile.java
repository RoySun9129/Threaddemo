package com.cduestc.thread;
/*
 * volatile:关键字 1:当多个线程进行操作共享数据的同时，可以保证内存中的数据可见
 *  相较于synchronized 是一种较为轻量级的同步策略
 *  
 *  注意：
 *  1：volatile不具有互斥性
 *  2：volatile 不能保证变量的原子性，具有可见性和顺序性
 * 	
 */
public class TestVolatile {
	public static void main(String[] args) {
		ThreadDemo tDemo = new ThreadDemo();
		new Thread(tDemo).start();
		
		while(true){
			if(tDemo.isFlag()){
				System.out.println("**************");
				break;
			}
		}
	}
}
class ThreadDemo implements Runnable{
	private volatile boolean flag =false;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		flag=true;
		System.out.println("flag=" + isFlag());
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	
}
