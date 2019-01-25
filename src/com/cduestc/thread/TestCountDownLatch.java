package com.cduestc.thread;

import java.util.concurrent.CountDownLatch;

/*
 * CountDownLatch：闭锁，在完成某些运算时，
 * 只有其他线程的运算全部完成时，当前运算才继续运行
 */
public class TestCountDownLatch {
	public static void main(String[] args) {
		final CountDownLatch latch = new CountDownLatch(5);
		LatchDemo latchDemo = new LatchDemo(latch);
		long start = System.currentTimeMillis();
		for(int i=0;i<5;i++){
			new Thread(latchDemo).start();
		}
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		System.out.println("time: "+(end-start));
	}

}
class LatchDemo implements Runnable{
	
	private CountDownLatch latch;
	
	public LatchDemo (CountDownLatch latch){
		this.latch = latch;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			for(int i=0;i<5000;i++){
				if(i%2 == 0){
					System.out.println(i);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			latch.countDown();
		}
	}
}
