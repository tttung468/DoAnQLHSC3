package com.toanhuuvuong.controller.edit;

import java.io.File;
import java.net.URL;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import com.jfoenix.controls.JFXDatePicker;
import com.toanhuuvuong.constant.SystemConstant;
import com.toanhuuvuong.model.Account;
import com.toanhuuvuong.model.AccountModel;
import com.toanhuuvuong.model.HRStaff;
import com.toanhuuvuong.model.HRStaffModel;
import com.toanhuuvuong.model.Role;
import com.toanhuuvuong.service.IHRStaffService;
import com.toanhuuvuong.service.IRoleService;
import com.toanhuuvuong.service.impl.HRStaffService;
import com.toanhuuvuong.service.impl.RoleService;
import com.toanhuuvuong.utils.AutoCompleteComboBoxListener;
import com.toanhuuvuong.utils.DateTimeUtils;
import com.toanhuuvuong.utils.SessionUtils;
import com.toanhuuvuong.utils.UploadUtils;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;

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
	private ToggleGroup genderToggleGroup;
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
	@FXML
	private ComboBox<String> subjectComboBox;
	@FXML
	private Label subjectErrorLabel;
	@FXML
	private ComboBox<String> roleComboBox;
	@FXML
	private Label roleErrorLabel;
	
	private HRStaffService hrStaffService = new HRStaffService();
	private IRoleService roleService = new RoleService();
	
	private File avatarFile;
	private Account accountModel = (Account)SessionUtils.getInstance().getValue("accountModel");
	// ------------------------------------------- Methods
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{		
		super.initialize(location, resources);
		
		initSubjectComboBox();
	}
	private void initSubjectComboBox()
	{		
		Collection<SubjectModel> subjects = subjectService.findAll();
		Collection<String> subjectNames = subjects.stream().map(item ->
		{
			return item.getName();
		}).collect(Collectors.toList());
		
		subjectComboBox.setItems(FXCollections.observableArrayList(subjectNames));
		subjectComboBox.setValue(null);
		new AutoCompleteComboBoxListener<String>(subjectComboBox);
		
		subjectComboBox.valueProperty().addListener((observable, oldValue, newValue) ->
		{
			mappingForm();
			
			if(model.getSubjectId() == null)
			{
				subjectErrorLabel.setText("Bộ môn không hợp lệ :(");
				subjectErrorLabel.setVisible(true);
				
				editButton.setDisable(true);
			}
			else
			{
				subjectErrorLabel.setVisible(false);
				
				if(validateForm())
					editButton.setDisable(false);
			}
		});
	}
	@Override
	protected void applyUI() 
	{
		super.applyUI();
		
		if(index != null)
			titleLabel.setText("SỬA NHÂN SỰ");
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
			avatarCircle.setFill(new ImagePattern(image));
			codeTextField.setText(model.getCode());
			nameTextField.setText(model.getName());
			phoneTextField.setText(model.getPhone());
			for(Toggle toggle : genderToggleGroup.getToggles())
			{
				if(((RadioButton)toggle).getText().equals(model.getGender()))
					genderToggleGroup.selectToggle(toggle);
			}
			if(model.getBirth() != null)
				birthDatePicker.setValue(DateTimeUtils.parseDate(model.getBirth().toString()));
			addressTextField.setText(model.getAddress());
			if(model.getSalary() != null)
				salaryTextField.setText(model.getSalary().toString());
			subjectComboBox.setValue(model.getSubject().getName());
			roleComboBox.setValue(model.getRole().getName());
			for(Toggle toggle : isDeletedToggleGroup.getToggles())
			{
				if(((RadioButton)toggle).getText().equals(model.getIsDeleted() ? "Khóa" : "Mở"))
					isDeletedToggleGroup.selectToggle(toggle);
			}
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
		model.setGender(((RadioButton)(genderToggleGroup.getSelectedToggle())).getText().trim());
		if(birthDatePicker.getValue() != null)
			model.setBirth(new Date(birthDatePicker.getValue().toString()));
		model.setAddress(addressTextField.getText().trim());
		model.setSalary(Integer.parseInt(salaryTextField.getText().trim()));
		if(subjectComboBox.getValue() != null)
		{
			SubjectModel subjectModel = subjectService.findByName(subjectComboBox.getValue());
			
			if(subjectModel != null)
			{
				model.setSubjectId(subjectModel.getId());
				model.setSubject(subjectModel);
			}
			else
			{
				model.setSubjectId(null);
				model.setSubject(null);
			}
		}
		if(roleComboBox.getValue() != null)
		{
			Role roleModel = roleService.findByName(roleComboBox.getValue());
			
			if(roleModel != null)
				model.setRole(roleModel);
			else
				model.setRole(null);
		}
		model.setIsDeleted(((RadioButton)(isDeletedToggleGroup.getSelectedToggle())).getText().trim().equals("Khóa"));
	}
	@Override
	protected boolean validateForm() 
	{
		return (model != null &&
				(avatarFile != null || model.getAvatarPath() != null) &&
				!model.getCode().isEmpty() &&
				!model.getName().isEmpty() &&
				!model.getPhone().isEmpty() &&
				model.getGender() != null &&
				model.getBirth() != null &&
				!model.getAddress().isEmpty() &&
				model.getSalary() != null &&
				model.getSubjectId() != null &&
				model.getRoleId() != null);
	}
	@Override
	public void delete()
	{
		
	}
	@Override
	public void update()
	{
		Map<String, String> map = conferenceService.validate(model);
		model.setMessageCode(map.get("messageCode"));
		
		if(map.get("alert").equals("success"))
		{
			messageLabel.setTextFill(Color.GREEN);
			
			if(thumbnailFile != null)
			{
				String destFolder = getClass().getResource(SystemConstant.CONFERENCE_IMAGES_PATH).toString().substring(6);
				String fileName = UploadUtils.upload(thumbnailFile, destFolder);
				model.setThumbnail(SystemConstant.CONFERENCE_IMAGES_PATH + "/" + fileName);
			}
			
			model = conferenceService.updateOne(model, accountModel);
		}
		else
			messageLabel.setTextFill(Color.RED);
		messageLabel.setText(model.getMessage());
		
		super.showDialog();
	}
	@Override
	public void insert()
	{	
		Map<String, String> map = conferenceService.validate(model);
		model.setMessageCode(map.get("messageCode"));
		
		if(map.get("alert").equals("success"))
		{
			messageLabel.setTextFill(Color.GREEN);
			
			String destFolder = getClass().getResource(SystemConstant.CONFERENCE_IMAGES_PATH).toString().substring(6);
			String fileName = UploadUtils.upload(thumbnailFile, destFolder);
			model.setThumbnail(SystemConstant.CONFERENCE_IMAGES_PATH + "/" + fileName);
			
			model = conferenceService.insertOne(model, accountModel);
		}
		else
			messageLabel.setTextFill(Color.RED);
		messageLabel.setText(model.getMessage());
		
		super.showDialog();
	}
	@FXML
	public void nameTextFieldOnKeyReleased()
	{
		mappingForm();
		
		if(model.getName().isEmpty())
		{
			nameErrorLabel.setText("Tên hội nghị không được trống :(");
			nameErrorLabel.setVisible(true);
			
			editButton.setDisable(true);
		}
		else
		{
			nameErrorLabel.setVisible(false);
			
			if(validateForm())
				editButton.setDisable(false);
		}
	}
	@FXML
	public void shortDescriptionTextAreaOnKeyReleased()
	{
		mappingForm();
		
		if(model.getShortDescription().isEmpty())
		{
			shortDescriptionErrorLabel.setText("Mô tả không được trống :(");
			shortDescriptionErrorLabel.setVisible(true);
			
			editButton.setDisable(true);
		}
		else
		{
			shortDescriptionErrorLabel.setVisible(false);
			
			if(validateForm())
				editButton.setDisable(false);
		}
	}
	@FXML
	public void contentTextAreaOnKeyReleased()
	{
		mappingForm();
		
		if(model.getContent().isEmpty())
		{
			contentErrorLabel.setText("Nội dung không được trống :(");
			contentErrorLabel.setVisible(true);
			
			editButton.setDisable(true);
		}
		else
		{
			contentErrorLabel.setVisible(false);
			
			if(validateForm())
				editButton.setDisable(false);
		}
	}
	@FXML
	public void thumbnailButtonOnAction(ActionEvent event)
	{
		mappingForm();
		
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Choose image");
		File dest = fileChooser.showOpenDialog(((Node)event.getSource()).getScene().getWindow());
		
		if(dest == null)
		{
			thumbnailErrorLabel.setText("Ảnh không được trống :(");
			thumbnailErrorLabel.setVisible(true);
			
			editButton.setDisable(true);
		}
		else
		{
			thumbnailFile = dest;
			
			thumbnailImageView.setImage(new Image(dest.toURI().toString()));
			
			thumbnailErrorLabel.setVisible(false);
			
			if(validateForm())
				editButton.setDisable(false);
		}
	}
	@FXML
	public void startTimeDatePickerOnAction(ActionEvent action)
	{
		startTimeDatePickerOnKeyReleased();
	}
	@FXML
	public void startTimeDatePickerOnKeyReleased()
	{
		mappingForm();
		
		if(model.getStartTimeDate() == null)
		{
			startTimeErrorLabel.setText("Ngày bắt đầu không hợp lệ :(");
			startTimeErrorLabel.setVisible(true);
			
			editButton.setDisable(true);
		}
		else if(model.getFinishTime() != null &&
				model.getStartTime() != null &&
				model.getFinishTime().compareTo(model.getStartTime()) <= 0)
		{
			startTimeErrorLabel.setText("Thời gian bắt đầu phải nhỏ hơn ngày kết thúc :(");
			startTimeErrorLabel.setVisible(true);
			
			editButton.setDisable(true);
		}
		else
		{
			startTimeErrorLabel.setVisible(false);
			
			if(validateForm())
				editButton.setDisable(false);
		}
	}
	@FXML
	public void startTimeTimePickerOnAction(ActionEvent action)
	{
		startTimeTimePickerOnKeyReleased();
	}
	@FXML
	public void startTimeTimePickerOnKeyReleased()
	{
		mappingForm();
		
		if(model.getStartTimeTime() == null)
		{
			startTimeErrorLabel.setText("Giờ bắt đầu không hợp lệ :(");
			startTimeErrorLabel.setVisible(true);
			
			editButton.setDisable(true);
		}
		else if(model.getFinishTime() != null &&
				model.getStartTime() != null &&
				model.getFinishTime().compareTo(model.getStartTime()) <= 0)
		{
			startTimeErrorLabel.setText("Thời gian bắt đầu phải nhỏ hơn ngày kết thúc :(");
			startTimeErrorLabel.setVisible(true);
			
			editButton.setDisable(true);
		}
		else
		{
			startTimeErrorLabel.setVisible(false);
			
			if(validateForm())
				editButton.setDisable(false);
		}
	}
	@FXML
	public void finishTimeDatePickerOnAction(ActionEvent action)
	{
		finishTimeDatePickerOnKeyReleased();
	}
	@FXML
	public void finishTimeDatePickerOnKeyReleased()
	{
		mappingForm();
		
		if(model.getFinishTimeDate() == null)
		{
			finishTimeErrorLabel.setText("Ngày kết thúc không hợp lệ :(");
			finishTimeErrorLabel.setVisible(true);
			
			editButton.setDisable(true);
		}
		else if(model.getFinishTime() != null &&
				model.getStartTime() != null &&
				model.getFinishTime().compareTo(model.getStartTime()) <= 0)
		{
			finishTimeErrorLabel.setText("Thời gian bắt đầu phải nhỏ hơn ngày kết thúc :(");
			finishTimeErrorLabel.setVisible(true);
			
			editButton.setDisable(true);
		}
		else
		{
			finishTimeErrorLabel.setVisible(false);
			
			if(validateForm())
				editButton.setDisable(false);
		}
	}
	@FXML
	public void finishTimeTimePickerOnAction(ActionEvent action)
	{
		finishTimeTimePickerOnKeyReleased();
	}
	@FXML
	public void finishTimeTimePickerOnKeyReleased()
	{
		mappingForm();
		
		if(model.getFinishTimeTime() == null)
		{
			finishTimeErrorLabel.setText("Giờ kết thúc không hợp lệ :(");
			finishTimeErrorLabel.setVisible(true);
			
			editButton.setDisable(true);
		}
		else if(model.getFinishTime() != null &&
				model.getStartTime() != null &&
				model.getFinishTime().compareTo(model.getStartTime()) <= 0)
		{
			finishTimeErrorLabel.setText("Thời gian bắt đầu phải nhỏ hơn ngày kết thúc :(");
			finishTimeErrorLabel.setVisible(true);
			
			editButton.setDisable(true);
		}
		else
		{
			finishTimeErrorLabel.setVisible(false);
			
			if(validateForm())
				editButton.setDisable(false);
		}
	}
}
