package com.toanhuuvuong.controller.edit;

import java.io.File;
import java.net.URL;
import java.sql.Date;
import java.util.Collection;
import java.util.Map;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDatePicker;
import com.toanhuuvuong.constant.SystemConstant;
import com.toanhuuvuong.model.Account;
import com.toanhuuvuong.model.Subject;
import com.toanhuuvuong.model.Teacher;
import com.toanhuuvuong.service.impl.SubjectService;
import com.toanhuuvuong.service.impl.TeacherService;
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
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Callback;

public class TeacherController extends GenericController<Teacher> implements Initializable
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
	@FXML
	private ComboBox<Subject> subjectComboBox;
	
	private TeacherService teacherService = new TeacherService();
	private SubjectService subjectService = new SubjectService();
	
	private File avatarFile;
	private Account accountModel = (Account)SessionUtils.getInstance().getValue("accountModel");
	// ------------------------------------------- Methods
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{		
		super.initialize(location, resources);
		
		initGenderComboBox();
		
		initSalaryTextField();
		
		initSubjectComboBox();
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
	private void initSubjectComboBox()
	{
		Collection<Subject> models = subjectService.findAll();
		
		subjectComboBox.setItems(FXCollections.observableArrayList(models));
		subjectComboBox.setValue(null);
		
		subjectComboBox.setCellFactory(new Callback<ListView<Subject>, ListCell<Subject>>() 
		{
            @Override
            public ListCell<Subject> call(ListView<Subject> param) 
            {
                final ListCell<Subject> cell = new ListCell<Subject>() 
                {
                    @Override
                    protected void updateItem(Subject item, boolean empty) 
                    {
                        super.updateItem(item, empty);
                        if (item != null && !empty)
                            setText(item.getName());
                        else 
                            setText(null);
                    }
                };
                return cell;
            }
        });
		subjectComboBox.setButtonCell(new ListCell<Subject>()
		{
            @Override
            protected void updateItem(Subject item, boolean empty) 
            {
                super.updateItem(item, empty);
                if (item != null && !empty)
                    setText(item.getName());
                else
                    setText(null);
            }
        });
		
		new AutoCompleteComboBoxListener<Subject>(subjectComboBox);
	}
	@Override
	protected void applyUI() 
	{
		super.applyUI();
		
		if(index != null)
		{
			titleLabel.setText("SỬA/XÓA GIÁO VIÊN");
			codeTextField.setEditable(false);
		}
		else
			titleLabel.setText("THÊM GIÁO VIÊN");
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
			if(model.getSubject() != null)
			{
				for(Subject type : subjectComboBox.getItems())
				{
					if(type.getId() == model.getSubject().getId())
					{
						subjectComboBox.setValue(type);
						break;
					}
				}
			}	
			isDeletedComboBox.setValue(model.getIsDeleted() ? "Khóa" : "Mở");
		}
	}
	@Override
	public void mappingForm() 
	{	
		if(model == null)
			model = new Teacher();
		
		model.setCode(codeTextField.getText().trim());
		model.setName(nameTextField.getText().trim());
		model.setPhone(phoneTextField.getText().trim());
		if(genderComboBox.getValue() != null)
			model.setGender(genderComboBox.getValue());
		if(birthDatePicker.getValue() != null)
			model.setBirth(Date.valueOf(birthDatePicker.getValue().toString()));
		model.setAddress(addressTextField.getText().trim());
		model.setSalary(Integer.parseInt(salaryTextField.getText().trim()));
		if(subjectComboBox.getValue() != null)
			model.setSubject(subjectComboBox.getValue());
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
				model.getSalary() != null &&
				model.getSubject() != null);
	}
	@Override
	public boolean delete()
	{
		Long[] deletedIds = { model.getId() };
		teacherService.delete(deletedIds);
		
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
			String destFolder = getClass().getResource(SystemConstant.TEACHER_IMAGES_PATH).toString().substring(6);
			String fileName = UploadUtils.uploadImage(avatarFile, destFolder);
			model.setAvatarpath(SystemConstant.TEACHER_IMAGES_PATH + "/" + fileName);
		}
		
		model = teacherService.updateOne(model, accountModel);
			
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
		Map<String, String> map = teacherService.validate(model);
		model.setMessageCode(map.get("messageCode"));
		
		if(map.get("alert").equals("success"))
		{
			messageLabel.setTextFill(Color.GREEN);
			
			if(avatarFile != null)
			{
				String destFolder = getClass().getResource(SystemConstant.TEACHER_IMAGES_PATH).toString().substring(6);
				String fileName = UploadUtils.uploadImage(avatarFile, destFolder);
				model.setAvatarpath(SystemConstant.TEACHER_IMAGES_PATH + "/" + fileName);
			}
			
			model = teacherService.insertOne(model, accountModel);
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
