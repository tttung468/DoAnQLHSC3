package com.toanhuuvuong.application;
	
import java.net.URL;

import com.toanhuuvuong.constant.SystemConstant;
import com.toanhuuvuong.utils.SceneUtils;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application 
{
	@Override
	public void start(Stage primaryStage) 
	{
		URL url = getClass().getResource("views/score/list.fxml");
		String title = "Danh sách giáo vụ";
		
		SceneUtils.changeSceneWithoutLostFocus(url, primaryStage, title, SystemConstant.FRAME_WIDTH, SystemConstant.FRAME_HEIGHT);
	}
	public static void main(String[] args) 
	{
		launch(args);
	}
}