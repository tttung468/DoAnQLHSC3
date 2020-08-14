package com.toanhuuvuong.controller.ontap;

public class HelloRunnable implements Runnable
{
	@Override
	public void run() 
	{
		System.out.println("Hello Runnable!");
	}
	public static void main(String[] agrs)
	{
		(new Thread(new HelloRunnable())).start();
	}
}
