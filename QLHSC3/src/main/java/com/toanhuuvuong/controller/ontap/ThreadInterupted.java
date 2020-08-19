package com.toanhuuvuong.controller.ontap;

public class ThreadInterupted extends Thread
{
	@Override
	public void run() 
	{
        try 
        {
            Thread.sleep(1000);
            System.out.println("task");
        } 
        catch(InterruptedException e) 
        {
            throw new RuntimeException("Thread interrupted...\n" + e);
        }
        System.out.println("thread is running...");
    }
 
    public static void main(String args[]) 
    {
    	ThreadInterupted thread = new ThreadInterupted();
    	thread.start();
        try 
        {
        	thread.interrupt();
        } 
        catch (Exception e)
        {
            System.out.println("Exception handled \n" + e);
        }
    }

}
