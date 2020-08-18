package com.toanhuuvuong.utils;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class UploadUtils 
{
	public static String uploadImage(File srcFile, String destFolder)
	{
		try 
		{
			long currentTime = System.currentTimeMillis();
			String fileName = "" + currentTime + srcFile.getName();
			String extension = fileName.substring(fileName.lastIndexOf('.') + 1, fileName.length()).toUpperCase();
			String absPath = destFolder + "/" + fileName;
			
			File destFile = new File(absPath);
			destFile.createNewFile();
			
			BufferedImage bi = ImageIO.read(srcFile);
			ImageIO.write(bi, extension, destFile);
	        
	        return fileName; 
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return null;
	}
}
