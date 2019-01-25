package com.cduestc.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * 生成者和消费者案例
 */
public class TestProductAndConsumer2 {
	public static void main(String[] args) {
		Clerk clerk = new Clerk();
		ProductThread pt = new ProductThread(clerk);
		ConsumerThread ct = new ConsumerThread(clerk);
		new Thread(pt).start();
		new Thread(ct).start();
	
	}
}
class Clerk1{
	private int product =0;
	
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	public Clerk1() {
		
	}
	//进货方法
	public  void getProduct(){
		lock.lock();
		try {
			while(product>=10){
				System.out.println("产品满了");
				try {
					
//					this.wait(); lock接口中用condition接口实现this.wait的代替
					condition.await();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			System.out.println(Thread.currentThread().getName()+" : "+ ++product);
			condition.signalAll();
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			lock.unlock();
		}
	
	}
	//售货方法
	public void saleProduct(){
		
		lock.lock();
		try {
			while(product<=0){
				System.out.println("没有货了");
				try {
					condition.await();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			System.out.println(Thread.currentThread().getName()+"  : "+ --product);
			condition.signalAll();
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			lock.unlock();
		}
		
	}
}

class ProductThread1 implements Runnable{
	
	private Clerk1 clerk;
	public ProductThread1(Clerk1 clerk) {
		// TODO Auto-generated constructor stub
		 this.clerk = clerk;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			System.out.println("开始生产产品");
//			try {
//				new Thread().sleep(10);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			clerk.getProduct();
		}
	}
	
}

class ConsumerThread1 implements Runnable{
	private Clerk1 clerk;
	
	public ConsumerThread1(Clerk1 clerk){
		this.clerk =clerk;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			System.out.println("开始消费产品");
//			try {
//				new Thread().sleep(10);
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
			clerk.saleProduct();
		}
	}
}



