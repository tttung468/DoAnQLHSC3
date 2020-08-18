package com.toanhuuvuong.controller.edit;

import java.io.File;
import java.net.URL;
import java.sql.Date;
import java.util.Map;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDatePicker;
import com.toanhuuvuong.constant.SystemConstant;
import com.toanhuuvuong.model.Account;
import com.toanhuuvuong.model.HRStaff;
import com.toanhuuvuong.service.impl.HRStaffService;
import com.toanhuuvuong.utils.SessionUtils;
import com.toanhuuvuong.utils.UploadUtils;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

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
		
		initSalaryTextField();
	}
	private void initGenderComboBox()
	{		
		genderComboBox.setItems(FXCollections.observableArrayList("Nam", "Nữ", "Khác"));
		genderComboBox.setValue(null);
	}
	private void initSalaryTextField()
	{		
		salaryTextField.setText("0");
	}
	@Override
	protected void applyUI() 
	{
		super.applyUI();
		
		if(index != null)
		{
			titleLabel.setText("SỬA/XÓA NHÂN SỰ");
			codeTextField.setEditable(false);
		}
		else
			titleLabel.setText("THÊM NHÂN SỰ");
	}
	@Override
	public void fillForm() 
	{		
		if(model != null)
		{
			String path = model.getAvatarpath();
			URL url = path != null ? getClass().getResource(path) : null;
			Image image = url != null ? new Image(url.toString()) : null;
			if(image != null)
				avatarCircle.setFill(new ImagePattern(image));
			codeTextField.setText(model.getCode());
			nameTextField.setText(model.getName());
			phoneTextField.setText(model.getPhone());
			genderComboBox.setValue(model.getGender());
			if(model.getBirth() != null)
				birthDatePicker.setValue(new java.sql.Date(model.getBirth().getTime()).toLocalDate());
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
			model.setBirth(Date.valueOf(birthDatePicker.getValue().toString()));
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
	public boolean delete()
	{
		Long[] deletedIds = { model.getId() };
		hrStaffService.delete(deletedIds);
		
		messageLabel.setTextFill(Color.GREEN);
		model.setMessageCode("delete_success");
		messageLabel.setText(model.getMessage());
		
		return true;
	}
	@Override
	public boolean update()
	{
		if(avatarFile != null)
		{
			String destFolder = getClass().getResource(SystemConstant.HRSTAFF_IMAGES_PATH).toString().substring(6);
			String fileName = UploadUtils.uploadImage(avatarFile, destFolder);
			model.setAvatarpath(SystemConstant.HRSTAFF_IMAGES_PATH + "/" + fileName);
		}
		
		model = hrStaffService.updateOne(model, accountModel);
			
		if(model != null)
		{
			messageLabel.setTextFill(Color.GREEN);
			model.setMessageCode("update_success");
			messageLabel.setText(model.getMessage());
			
			return true;
		}
		messageLabel.setTextFill(Color.RED);
		model.setMessageCode("update_fail");
		messageLabel.setText(model.getMessage());
		
		return false;
	}
	@Override
	public boolean insert()
	{	
		Map<String, String> map = hrStaffService.validate(model);
		model.setMessageCode(map.get("messageCode"));
		
		if(map.get("alert").equals("success"))
		{
			messageLabel.setTextFill(Color.GREEN);
			
			if(avatarFile != null)
			{
				String destFolder = getClass().getResource(SystemConstant.HRSTAFF_IMAGES_PATH).toString().substring(6);
				String fileName = UploadUtils.uploadImage(avatarFile, destFolder);
				model.setAvatarpath(SystemConstant.HRSTAFF_IMAGES_PATH + "/" + fileName);
			}
			
			model = hrStaffService.insertOne(model, accountModel);
			messageLabel.setText(model.getMessage());
			
			return true;
		}
		
		messageLabel.setTextFill(Color.RED);
		messageLabel.setText(model.getMessage());
		
		return false;
	}
	@FXML
	public void chooseAvatarButtonOnAction(ActionEvent event)
	{
		mappingForm();
		
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Chọn ảnh đại diện");
		fileChooser.setSelectedExtensionFilter(new ExtensionFilter("Image file", "*.png", "*.jpg"));
		File dest = fileChooser.showOpenDialog(((Node)event.getSource()).getScene().getWindow());
		
		if(dest != null)
		{
			avatarFile = dest;
			
			avatarCircle.setFill(new ImagePattern(new Image(dest.toURI().toString())));
		}
	}
}
