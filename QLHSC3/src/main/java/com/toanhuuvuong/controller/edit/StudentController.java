package com.toanhuuvuong.controller.edit;

import java.io.File;
import java.net.URL;
import java.sql.Date;
import java.util.Collection;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import com.jfoenix.controls.JFXDatePicker;
import com.toanhuuvuong.constant.SystemConstant;
import com.toanhuuvuong.model.Account;
import com.toanhuuvuong.model.Ethnic;
import com.toanhuuvuong.model.Nationality;
import com.toanhuuvuong.model.Religion;
import com.toanhuuvuong.model.Student;
import com.toanhuuvuong.service.impl.EthnicService;
import com.toanhuuvuong.service.impl.NationalityService;
import com.toanhuuvuong.service.impl.ReligionService;
import com.toanhuuvuong.service.impl.StudentService;
import com.toanhuuvuong.utils.AutoCompleteComboBoxListener;
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

public class StudentController extends GenericController<Student> implements Initializable
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
	private TextField identifyCardTextField;
	@FXML
	private Label identifyCardErrorLabel;
	@FXML
	private TextField emailTextField;
	@FXML
	private Label emailErrorLabel;
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
	private ComboBox<String> ethnicComboBox;
	@FXML
	private Label ethnicErrorLabel;
	@FXML
	private ComboBox<String> religionComboBox;
	@FXML
	private Label religionErrorLabel;
	@FXML
	private ComboBox<String> nationalityComboBox;
	@FXML
	private Label nationalityErrorLabel;
	@FXML
	private ComboBox<String> statusComboBox;
	@FXML
	private Label statusErrorLabel;
	
	private StudentService studentService = new StudentService();
	private EthnicService ethnicService = new EthnicService();
	private ReligionService religionService = new ReligionService();
	private NationalityService nationalityService = new NationalityService();
	
	private File avatarFile;
	private Account accountModel = (Account)SessionUtils.getInstance().getValue("accountModel");
	// ------------------------------------------- Methods
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{		
		super.initialize(location, resources);
		
		initGenderComboBox();
		
		initEthnicComboBox();
		
		initReligionComboBox();
		
		initNationalityComboBox();
		
		initStatusComboBox();
	}
	private void initGenderComboBox()
	{		
		genderComboBox.setItems(FXCollections.observableArrayList("Nam", "Nữ", "Khác"));
		genderComboBox.setValue(null);
	}
	private void initEthnicComboBox()
	{
		Collection<Ethnic> models = ethnicService.findAll();
		Collection<String> ethnicNames = (models == null) ? null
		: models.stream().map(item ->
		{
			return item.getName();
		}).collect(Collectors.toSet());
		
		if(ethnicNames != null)
			ethnicComboBox.setItems(FXCollections.observableArrayList(ethnicNames));
		ethnicComboBox.setValue(null);
		
		new AutoCompleteComboBoxListener<String>(ethnicComboBox);
	}
	private void initReligionComboBox()
	{
		Collection<Religion> models = religionService.findAll();
		Collection<String> religionNames = (models == null) ? null
		: models.stream().map(item ->
		{
			return item.getName();
		}).collect(Collectors.toSet());
		
		if(religionNames != null)
			religionComboBox.setItems(FXCollections.observableArrayList(religionNames));
		religionComboBox.setValue(null);
		
		new AutoCompleteComboBoxListener<String>(religionComboBox);
	}
	private void initNationalityComboBox()
	{
		Collection<Nationality> models = nationalityService.findAll();
		Collection<String> nationalitytNames = (models == null) ? null
		: models.stream().map(item ->
		{
			return item.getName();
		}).collect(Collectors.toSet());
		
		if(nationalitytNames != null)
			nationalityComboBox.setItems(FXCollections.observableArrayList(nationalitytNames));
		nationalityComboBox.setValue(null);
		
		new AutoCompleteComboBoxListener<String>(nationalityComboBox);
	}
	private void initStatusComboBox()
	{
		statusComboBox.setItems(FXCollections.observableArrayList("Chuyển đến", "Chuyển đi", "Thôi học", "Đang học"));
		statusComboBox.setValue(null);
	}
	@Override
	protected void applyUI() 
	{
		super.applyUI();
		
		if(index != null)
		{
			titleLabel.setText("SỬA/XÓA HỌC SINH");
			codeTextField.setEditable(false);
		}
		else
			titleLabel.setText("THÊM HỌC SINH");
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
			identifyCardTextField.setText(model.getIdentifyCard());
			emailTextField.setText(model.getEmail());
			genderComboBox.setValue(model.getGender());
			if(model.getBirth() != null)
				birthDatePicker.setValue(new java.sql.Date(model.getBirth().getTime()).toLocalDate());
			addressTextField.setText(model.getAddress());
			ethnicComboBox.setValue(model.getEthnic().getName());
			religionComboBox.setValue(model.getReligion().getName());
			nationalityComboBox.setValue(model.getNationality().getName());
			statusComboBox.setValue(model.getStatus());
			isDeletedComboBox.setValue(model.getIsDeleted() ? "Khóa" : "Mở");
		}
	}
	@Override
	public void mappingForm() 
	{	
		if(model == null)
			model = new Student();
		
		model.setCode(codeTextField.getText().trim());
		model.setName(nameTextField.getText().trim());
		model.setPhone(phoneTextField.getText().trim());
		model.setIdentifyCard(identifyCardTextField.getText().trim());
		model.setEmail(emailTextField.getText().trim());
		if(genderComboBox.getValue() != null)
			model.setGender(genderComboBox.getValue());
		if(birthDatePicker.getValue() != null)
			model.setBirth(Date.valueOf(birthDatePicker.getValue().toString()));
		model.setAddress(addressTextField.getText().trim());
		if(ethnicComboBox.getValue() != null)
		{
			Ethnic ethnic = new Ethnic();
			ethnic.setName(ethnicComboBox.getValue());
			model.setEthnic(ethnic);
		}
		if(religionComboBox.getValue() != null)
		{
			Religion religion = new Religion();
			religion.setName(religionComboBox.getValue());
			model.setReligion(religion);
		}
		if(nationalityComboBox.getValue() != null)
		{
			Nationality nationality = new Nationality();
			nationality.setName(nationalityComboBox.getValue());
			model.setNationality(nationality);
		}
		if(statusComboBox.getValue() != null)
			model.setStatus(statusComboBox.getValue());
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
				!model.getIdentifyCard().isEmpty() &&
				!model.getEmail().isEmpty() &&
				model.getGender() != null &&
				model.getBirth() != null &&
				!model.getAddress().isEmpty() &&
				model.getEthnic() != null &&
				model.getReligion() != null &&
				model.getNationality() != null &&
				model.getStatus() != null);
	}
	@Override
	public boolean delete()
	{
		Long[] deletedIds = { model.getId() };
		studentService.delete(deletedIds);
		
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
			String destFolder = getClass().getResource(SystemConstant.STUDENT_IMAGES_PATH).toString().substring(6);
			String fileName = UploadUtils.uploadImage(avatarFile, destFolder);
			model.setAvatarpath(SystemConstant.STUDENT_IMAGES_PATH + "/" + fileName);
		}
		
		model = studentService.updateOne(model, accountModel);
			
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
		Map<String, String> map = studentService.validate(model);
		model.setMessageCode(map.get("messageCode"));
		
		if(map.get("alert").equals("success"))
		{
			messageLabel.setTextFill(Color.GREEN);
			
			if(avatarFile != null)
			{
				String destFolder = getClass().getResource(SystemConstant.STUDENT_IMAGES_PATH).toString().substring(6);
				String fileName = UploadUtils.uploadImage(avatarFile, destFolder);
				model.setAvatarpath(SystemConstant.STUDENT_IMAGES_PATH + "/" + fileName);
			}
			
			model = studentService.insertOne(model, accountModel);
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
