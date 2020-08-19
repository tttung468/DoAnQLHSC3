package com.toanhuuvuong.controller.ontap;

import java.util.Random;

public class GuardedBlock 
{
	public static class Drop
	{
		// Sản phẩm producer gửi cho consummer
		private String product;
		// True nếu consummer chờ producer gửi sản phẩm
		// False nếu producer chờ consummer sử dụng sản phẩm
		private boolean isEmpty = true;

		public synchronized String take()
		{
			// Chờ đến khi nào producer gửi product
			while(isEmpty)
			{
				try 
				{
					wait();
				} 
				catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
			}
			// Tiếp tục chờ
			isEmpty = true;
			notifyAll();
			
			return product;
		}
		public synchronized void put(String product)
		{
			// Chờ đến khi nào product đã được consummer sử dụng
			while(!isEmpty)
			{
				try 
				{
					wait();
				} 
				catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
			}
			// Tiếp tục chờ
			isEmpty = false;
			notifyAll();
			
			this.product = product;
		}
	}
	public static class Producer extends Thread
	{
		private Drop drop;
		
		public Producer(Drop drop)
		{
			this.drop = drop;
		}
		@Override
		public void run() 
		{
			super.run();
			
			String[] products = {"A", "B", "C", "D", "E", "F"};
			
			for(String product : products)
			{
				drop.put(product);
				
				System.out.println("Send " + product);
				
				try 
				{
					Thread.sleep(new Random().nextInt(1000));
					Thread.sleep(1000);
				} 
				catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
			}
			
			drop.put("DONE");
		}
	}
	public static class Consummer extends Thread
	{
		private Drop drop;
		
		public Consummer(Drop drop)
		{
			this.drop = drop;
		}
		@Override
		public void run() 
		{
			super.run();
			
			for(String product = drop.take(); !product.equals("DONE"); product = drop.take())
			{
				System.out.println("Recieve " + product);
				
				try 
				{
					Thread.sleep(new Random().nextInt(1000));
					Thread.sleep(1000);
				} 
				catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
			}
		}
	}
	public static void main(String[] args) 
	{
		Drop drop = new Drop();
		
		new Producer(drop).start();
		new Consummer(drop).start();
	}
}
