package com.toanhuuvuong.controller.edit;

import java.net.URL;
import java.util.ResourceBundle;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public abstract class GenericController<T> implements Initializable
{
	// ------------------------------------------- Attributes
	@FXML
	protected Label titleLabel;
	@FXML
	protected Label messageLabel;
	@FXML
	protected Label isDeletedLabel;
	@FXML
	protected ComboBox<String> isDeletedComboBox;
	@FXML
	protected Label isDeletedErrorLabel;
	@FXML
	protected Button editButton;
	@FXML
	protected FontAwesomeIcon editIcon;
	@FXML
	protected Button deleteButton;
	
	public com.toanhuuvuong.controller.list.GenericController<T> listDelegate;
	protected Integer index;
	protected T model;
	// ------------------------------------------- Methods
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{		
		initIsDeletedComboBox();
	}
	private void initIsDeletedComboBox()
	{
		isDeletedComboBox.setItems(FXCollections.observableArrayList("Mở", "Khóa"));
		isDeletedComboBox.setValue(null);
	}
	public void setItem(Integer index, T model) 
	{
		this.index = index;
		this.model = model;
		
		applyUI();
	}
	protected void applyUI()
	{	
		if(index != null)
		{
			editButton.setText("Cập nhật");
			editButton.setDisable(false);
			
			editIcon.setIconName("EDIT");
			
			fillForm();
		}
		else
		{
			isDeletedLabel.setVisible(false);
			isDeletedComboBox.setVisible(false);
			
			deleteButton.setVisible(false);
			
			editButton.setText("Thêm");
			editButton.setDisable(true);
			
			editIcon.setIconName("PLUS");
		}
	}
	@FXML
	public void deleteButtonOnAction(ActionEvent event)
	{
		delete();
		
		if(listDelegate != null)
			listDelegate.deleteSelectedItem(index, model);
	}
	@FXML
	public void editButtonOnAction(ActionEvent event)
	{
		messageLabel.setVisible(true);
		
		mappingForm();
		
		if(index != null)
			update();
		else
			insert();
		
		if(listDelegate != null)
			listDelegate.updateSelectedItem(index, model);
	}
	@FXML
	public void cancelButtonOnAction(ActionEvent event)
	{
		Stage stage = (Stage)(((Node)event.getSource()).getScene().getWindow());
		
		stage.close();
	}
	protected abstract void fillForm();
	protected abstract void mappingForm();
	protected abstract boolean validateForm();
	protected abstract void delete();
	protected abstract void update();
	protected abstract void insert();
}
