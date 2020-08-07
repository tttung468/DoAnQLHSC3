package com.toanhuuvuong.controller.edit;

import java.io.File;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import com.toanhuuvuong.constant.SystemConstant;
import com.toanhuuvuong.model.AccountModel;
import com.toanhuuvuong.model.ConferenceModel;
import com.toanhuuvuong.model.HRStaffModel;
import com.toanhuuvuong.model.PlaceModel;
import com.toanhuuvuong.service.IConferenceService;
import com.toanhuuvuong.service.IHRStaffService;
import com.toanhuuvuong.service.IPlaceService;
import com.toanhuuvuong.service.IRoleService;
import com.toanhuuvuong.service.impl.ConferenceService;
import com.toanhuuvuong.service.impl.HRStaffService;
import com.toanhuuvuong.service.impl.PlaceService;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;

public class HRStaffController extends GenericController<HRStaffModel> implements Initializable
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
	
	private IHRStaffService hrStaffService = new HRStaffService();
	private ISubjectService subjectService = new SubjectService();
	private IRoleService roleService = new RoleService();
	
	private File avatarFile;
	private AccountModel accountModel = (AccountModel)SessionUtils.getInstance().getValue("accountModel");
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
			nameTextField.setText(model.getName());
			shortDescriptionTextArea.setText(model.getShortDescription());
			contentTextArea.setText(model.getContent());
			thumbnailImageView.setImage(new Image(getClass().getResource(model.getThumbnail()).toString()));
			startTimeDatePicker.setValue(DateTimeUtils.parseDate(model.getStartTime().toString()));
			startTimeTimePicker.setValue(DateTimeUtils.parseTime(model.getStartTime().toString()));
			finishTimeDatePicker.setValue(DateTimeUtils.parseDate(model.getFinishTime().toString()));
			finishTimeTimePicker.setValue(DateTimeUtils.parseTime(model.getFinishTime().toString()));
			participantsTextField.setText(model.getParticipants().toString());
			placeComboBox.setValue(model.getPlace().getName());
			isDeletedComboBox.setValue(model.getIsDeleted() ? "Khóa" : "Mở");
		}
	}
	@Override
	public void mappingForm() 
	{	
		if(model == null)
			model = new ConferenceModel();
		
		model.setName(nameTextField.getText().trim());
		model.setShortDescription(shortDescriptionTextArea.getText().trim());
		model.setContent(contentTextArea.getText().trim());
		if(startTimeDatePicker.getValue() != null)
			model.setStartTimeDate(startTimeDatePicker.getValue().toString());
		if(startTimeTimePicker.getValue() != null)
			model.setStartTimeTime(startTimeTimePicker.getValue().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
		if(model.getStartTimeDate() != null && model.getStartTimeTime() != null)
		{
			String startTime = model.getStartTimeDate() + " " + model.getStartTimeTime();
			model.setStartTime(DateTimeUtils.parseTimestamp(startTime));
		}
		if(finishTimeDatePicker.getValue() != null)
			model.setFinishTimeDate(finishTimeDatePicker.getValue().toString().trim());
		if(finishTimeTimePicker.getValue() != null)
			model.setFinishTimeTime(finishTimeTimePicker.getValue().format(DateTimeFormatter.ofPattern("HH:mm:ss")).trim());
		if(model.getFinishTimeDate() != null && model.getFinishTimeTime() != null)
		{
			String finishTime = model.getFinishTimeDate() + " " + model.getFinishTimeTime();
			model.setFinishTime(DateTimeUtils.parseTimestamp(finishTime));
		}
		model.setParticipants(Integer.parseInt(participantsTextField.getText()));
		if(placeComboBox.getValue() != null)
		{
			PlaceModel placeModel = placeService.findByName(placeComboBox.getValue());
			
			if(placeModel != null)
			{
				model.setPlaceId(placeModel.getId());
				model.setPlace(placeModel);
			}
			else
			{
				model.setPlaceId(null);
				model.setPlace(null);
			}
		}
		if(isDeletedComboBox.getValue() != null)
			model.setIsDeleted(isDeletedComboBox.getValue().equals("Khóa"));
	}
	@Override
	protected boolean validateForm() 
	{
		return (!model.getName().isEmpty() &&
				!model.getShortDescription().isEmpty() &&
				!model.getContent().isEmpty() &&
				(thumbnailFile != null || model.getThumbnail() != null) &&
				model.getStartTimeDate() != null &&
				model.getStartTimeTime() != null &&
				model.getFinishTimeDate() != null &&
				model.getFinishTimeTime() != null &&
				model.getFinishTime().compareTo(model.getStartTime()) > 0 &&
				model.getPlaceId() != null);
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
