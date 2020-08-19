package com.toanhuuvuong.controller.ontap;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.concurrent.WorkerStateEvent;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*;

public class CopyController implements Initializable
{
	@FXML
	private TextField srcTextField;
	@FXML
	private TextField destTextField;
	@FXML
	private ProgressBar copyProgressBar;
	@FXML
	private Label copyResultLabel;
	
	private CopyTask copyTask;
	
	@Override
	public void initialize(URL url, ResourceBundle resource) 
	{
		
	}
	@FXML
	public void srcButtonOnAction(ActionEvent event)
	{
		DirectoryChooser dirChooser = new DirectoryChooser();
		dirChooser.setTitle("Chọn thư mục nguồn");
		Window window = ((Node)(event.getSource())).getScene().getWindow();
		File file = dirChooser.showDialog(window);
		
		if(file != null)
			srcTextField.setText(file.getAbsolutePath());
	}
	@FXML
	public void destButtonOnAction(ActionEvent event)
	{
		DirectoryChooser dirChooser = new DirectoryChooser();
		dirChooser.setTitle("Chọn thư mục nguồn");
		Window window = ((Node)(event.getSource())).getScene().getWindow();
		File file = dirChooser.showDialog(window);
		
		if(file != null)
			destTextField.setText(file.getAbsolutePath());
	}
	@FXML
	public void startButtonOnAction(ActionEvent event)
	{
		if(srcTextField.getText().trim().isEmpty() ||
			destTextField.getText().trim().isEmpty())
			return;
		
		copyTask = new CopyTask(srcTextField.getText().trim(), destTextField.getText().trim());
		
		copyProgressBar.setProgress(0);
		copyProgressBar.progressProperty().unbind();
		copyProgressBar.progressProperty().bind(copyTask.progressProperty());
		
		copyResultLabel.textProperty().unbind();
		copyResultLabel.textProperty().bind(copyTask.messageProperty());
		
		new Thread(copyTask).start();
	}
	@FXML
	public void cancelButtonOnAction(ActionEvent event)
	{
		
	}
}
