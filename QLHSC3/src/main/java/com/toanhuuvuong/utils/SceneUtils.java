package com.toanhuuvuong.utils;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneUtils 
{
	public static FXMLLoader changeScene(URL url, Stage stage, String title, Double width, Double height)
	{
		if(url == null)
			return null;
		
		FXMLLoader loader = new FXMLLoader(url);
		Parent root;
		try 
		{
			root = loader.load();
			
			if(stage != null)
			{
				stage.setTitle(title);
				stage.setScene(new Scene(root, width, height));
				stage.show();
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		return loader;
	}
	public static FXMLLoader changeSceneWithoutLostFocus(URL url, Stage stage, String title, Double width, Double height)
	{
		if(url == null)
			return null;
		
		FXMLLoader loader = new FXMLLoader(url);
		Parent root;
		try 
		{
			root = loader.load();
			
			if(stage != null)
			{
				stage.setTitle(title);
				stage.setScene(new Scene(root, width, height));
				stage.setAlwaysOnTop(true);
				stage.show();
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		return loader;
	}
}
