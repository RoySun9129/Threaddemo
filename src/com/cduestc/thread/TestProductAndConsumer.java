package com.cduestc.thread;

/*
 * 生成者和消费者案例
 */
public class TestProductAndConsumer {
	public static void main(String[] args) {
		Clerk clerk = new Clerk();
		ProductThread pt = new ProductThread(clerk);
		ConsumerThread ct = new ConsumerThread(clerk);
		new Thread(pt).start();
		new Thread(ct).start();
	
	}
}
class Clerk{
	private int product =0;
	
	public Clerk() {
		
	}
	//进货方法
	public synchronized void getProduct(){
		//避免虚假唤醒问题
		while(product >=1){
			System.out.println("满了");
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}//(if else 会造成死锁)
		System.out.println(Thread.currentThread().getName()+ " : "+ ++product);
		this.notifyAll();
		
		
	}
	//售货方法
	public synchronized void saleProduct(){
		
		while(product<=0){
			System.out.println("没有货了");
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		System.out.println(Thread.currentThread().getName()+" : "+ --product);
		this.notifyAll();
	}
}

class ProductThread implements Runnable{
	
	private Clerk clerk;
	public ProductThread(Clerk clerk) {
		// TODO Auto-generated constructor stub
		 this.clerk = clerk;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			System.out.println("开始生产产品");
			try {
				new Thread().sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			clerk.getProduct();
		}
	}
	
}

class ConsumerThread implements Runnable{
	private Clerk clerk;
	
	public ConsumerThread(Clerk clerk){
		this.clerk =clerk;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			System.out.println("开始消费产品");
			try {
				new Thread().sleep(200);
			} catch (Exception e) {
				// TODO: handle exception
			}
			clerk.saleProduct();
		}
	}
}



