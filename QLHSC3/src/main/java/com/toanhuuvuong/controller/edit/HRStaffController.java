package com.toanhuuvuong.controller.edit;

import java.io.File;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDatePicker;
import com.toanhuuvuong.model.Account;
import com.toanhuuvuong.model.HRStaff;
import com.toanhuuvuong.service.impl.HRStaffService;
import com.toanhuuvuong.utils.DateTimeUtils;
import com.toanhuuvuong.utils.SessionUtils;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class HRStaffController extends GenericController<HRStaff> implements Initializable
{	
	// ------------------------------------------- Attributes
	@FXML
	private Circle avatarCircle;
	@FXML
	private TextField codeTextField;
	@FXML
	private Label codeErrorLabel;
	@FXML
	private TextField nameTextField;
	@FXML
	private Label nameErrorLabel;
	@FXML
	private TextField phoneTextField;
	@FXML
	private Label phoneErrorLabel;
	@FXML
	private ComboBox<String> genderComboBox;
	@FXML
	private Label genderErrorLabel;
	@FXML
	private JFXDatePicker birthDatePicker;
	@FXML
	private Label birthErrorLabel;
	@FXML
	private TextField addressTextField;
	@FXML
	private Label addressErrorLabel;
	@FXML
	private TextField salaryTextField;
	@FXML
	private Label salaryErrorLabel;
	
	private HRStaffService hrStaffService = new HRStaffService();
	
	private File avatarFile;
	private Account accountModel = (Account)SessionUtils.getInstance().getValue("accountModel");
	// ------------------------------------------- Methods
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{		
		super.initialize(location, resources);
		
		initGenderComboBox();
	}
	private void initGenderComboBox()
	{		
		genderComboBox.setItems(FXCollections.observableArrayList("Nam", "Nữ", "Khác"));
		genderComboBox.setValue(null);
	}
	@Override
	protected void applyUI() 
	{
		super.applyUI();
		
		if(index != null)
			titleLabel.setText("SỬA/XÓA NHÂN SỰ");
		else
			titleLabel.setText("THÊM NHÂN SỰ");
	}
	@Override
	public void fillForm() 
	{		
		if(model != null)
		{
			String path = model.getAvatarpath();
			Image image = path != null ? new Image(path) : null;
			if(image != null)
				avatarCircle.setFill(new ImagePattern(image));
			codeTextField.setText(model.getCode());
			nameTextField.setText(model.getName());
			phoneTextField.setText(model.getPhone());
			genderComboBox.setValue(model.getGender());
			if(model.getBirth() != null)
				birthDatePicker.setValue(DateTimeUtils.parseDate(model.getBirth().toString()));
			addressTextField.setText(model.getAddress());
			if(model.getSalary() != null)
				salaryTextField.setText(model.getSalary().toString());
			isDeletedComboBox.setValue(model.getIsDeleted() ? "Khóa" : "Mở");
		}
	}
	@Override
	public void mappingForm() 
	{	
		if(model == null)
			model = new HRStaff();
		
		model.setCode(codeTextField.getText().trim());
		model.setName(nameTextField.getText().trim());
		model.setPhone(phoneTextField.getText().trim());
		if(genderComboBox.getValue() != null)
			model.setGender(genderComboBox.getValue());
		if(birthDatePicker.getValue() != null)
			model.setBirth(new Date(birthDatePicker.getValue().toString()));
		model.setAddress(addressTextField.getText().trim());
		model.setSalary(Integer.parseInt(salaryTextField.getText().trim()));
		if(isDeletedComboBox.getValue() != null)
			model.setIsDeleted(isDeletedComboBox.getValue().equals("Khóa"));
	}
	@Override
	protected boolean validateForm() 
	{
		return (model != null &&
				(avatarFile != null || model.getAvatarpath() != null) &&
				!model.getCode().isEmpty() &&
				!model.getName().isEmpty() &&
				!model.getPhone().isEmpty() &&
				model.getGender() != null &&
				model.getBirth() != null &&
				!model.getAddress().isEmpty() &&
				model.getSalary() != null);
	}
	@Override
	public void delete()
	{
		
	}
	@Override
	public void update()
	{
		
	}
	@Override
	public void insert()
	{	
		
	}
}
