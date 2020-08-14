package com.toanhuuvuong.controller.ontap;

public class SynchronizedCounter 
{
	private static int count = 0;
	
	public void increase()
	{
		synchronized(this)
		{
			count++;
		}
	}
	public synchronized void decrease()
	{
		count--;
	}
	public static int getCount()
	{
		synchronized(SynchronizedCounter.class)
		{
			return count;
		}
	}
}
