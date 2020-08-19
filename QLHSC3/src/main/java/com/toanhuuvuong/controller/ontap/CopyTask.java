package com.toanhuuvuong.controller.ontap;

import java.io.*;
import java.util.*;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.concurrent.Task;

public class CopyTask implements Runnable
{
	private String src;
	private String dest;
	private List<File> copied;
	private DoubleProperty progressProperty = new SimpleDoubleProperty();
	private StringProperty messageProperty = new SimpleStringProperty();
	
	public CopyTask(String src, String dest)
	{
		this.src = src;
		this.dest = dest;
	}
	@Override
	public void run()
	{
		File dir = new File(src);
		File[] files = dir.listFiles();
		int count = files.length;
		
		int i = 0;
		List<File> copied = new ArrayList<File>();
		for(File file : files)
		{
			try {
				this.copy(file);
			} catch (Exception e) {
				e.printStackTrace();
			}
			i++;
			this.updateProgress(i, count);
			copied.add(file);
		}
	}
	private synchronized void copy(File file) throws Exception 
	{
		this.updateMessage("Copying: " + file.getAbsolutePath());
				 
		Thread.sleep(1000);
	}
	public synchronized DoubleProperty progressProperty() 
	{
		return progressProperty;
	}
	public synchronized double getProgress() 
	{
		return progressProperty.get();
	}
	public synchronized void setProgress(double progress) 
	{
		this.progressProperty.set(progress);
	}
	public synchronized void updateProgress(double pos, double size) 
	{
		this.progressProperty.set(pos / size);
	}
	public synchronized StringProperty messageProperty() 
	{
		return messageProperty;
	}
	public synchronized String getMessage() 
	{
		return messageProperty.get();
	}
	public synchronized void setMessage(String message) 
	{
		this.messageProperty.set(message);
	}
	public synchronized void updateMessage(String message) 
	{
		if(message != null)
			this.messageProperty.setValue(message);
	}
	public synchronized List<File> getCopied() 
	{
		return copied;
	}
	public synchronized void setCopied(List<File> copied) 
	{
		this.copied = copied;
	}
}
