package com.toanhuuvuong.controller.list;

import java.net.URL;
import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import com.toanhuuvuong.constant.SystemConstant;
import com.toanhuuvuong.converter.TeacherConverter;
import com.toanhuuvuong.model.Account;
import com.toanhuuvuong.model.Subject;
import com.toanhuuvuong.model.Teacher;
import com.toanhuuvuong.pagination.PageRequest;
import com.toanhuuvuong.pagination.Pageable;
import com.toanhuuvuong.service.impl.SubjectService;
import com.toanhuuvuong.service.impl.TeacherService;
import com.toanhuuvuong.utils.AutoCompleteComboBoxListener;
import com.toanhuuvuong.utils.CSVUtils;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TeacherController extends GenericController<Teacher> implements Initializable
{
	// ------------------------------------------- Attributes
	@FXML
	private TextField codeFilterTextField;
	@FXML
	private TextField nameFilterTextField;
	@FXML
	private TextField phoneFilterTextField;
	@FXML
	private ComboBox<String> genderFilterComboBox;
	@FXML
	private ComboBox<String> addressFilterComboBox;
	@FXML
	private TextField salaryFilterTextField;
	@FXML
	private ComboBox<String> subjectFilterComboBox;
	@FXML
	private TableColumn<Teacher, ImageView> avatarPathCol;
	@FXML
	private TableColumn<Teacher, String> codeCol;
	@FXML
	private TableColumn<Teacher, String> nameCol;
	@FXML
	private TableColumn<Teacher, String> phoneCol;
	@FXML
	private TableColumn<Teacher, String> genderCol;
	@FXML
	private TableColumn<Teacher, Date> birthCol;
	@FXML
	private TableColumn<Teacher, String> addressCol;
	@FXML
	private TableColumn<Teacher, Integer> salaryCol;
	@FXML
	private TableColumn<Teacher, String> accountUsernameCol;
	@FXML
	private TableColumn<Teacher, String> subjectNameCol;
	
	private TeacherService teacherService = new TeacherService();
	private SubjectService subjectService = new SubjectService();
	// ------------------------------------------- Methods
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		super.initialize(location, resources);
		
		initGenderFilterComboBox();
		
		initAddressFilterComboBox();
		
		initSubjectFilterComboBox();
		
		initTableView();
		
		initListView();
		
		initPageable();
	}
	private void initGenderFilterComboBox()
	{
		genderFilterComboBox.setItems(FXCollections.observableArrayList("Nam", "Nữ", "Khác"));
		genderFilterComboBox.setValue(null);
	}
	private void initAddressFilterComboBox()
	{
		Collection<Teacher> models = teacherService.findAll();
		Collection<String> addresses = (models == null) ? null
		: models.stream().map(item ->
		{
			return item.getAddress();
		}).collect(Collectors.toSet());
		
		if(addresses != null)
			addressFilterComboBox.setItems(FXCollections.observableArrayList(addresses));
		addressFilterComboBox.setValue(null);
		
		new AutoCompleteComboBoxListener<String>(addressFilterComboBox);
	}
	private void initSubjectFilterComboBox()
	{
		Collection<Subject> models = subjectService.findAll();
		Collection<String> subjectNames = (models == null) ? null
		: models.stream().map(item ->
		{
			return item.getName();
		}).collect(Collectors.toSet());
		
		if(subjectNames != null)
			subjectFilterComboBox.setItems(FXCollections.observableArrayList(subjectNames));
		subjectFilterComboBox.setValue(null);
		
		new AutoCompleteComboBoxListener<String>(subjectFilterComboBox);
	}
	private void initTableView()
	{
		avatarPathCol.setCellValueFactory(cell ->
		{
			ObjectProperty<ImageView> prop = new SimpleObjectProperty<ImageView>();
			
			String avatarpath = cell.getValue().getAvatarpath();
			URL url = avatarpath != null ? getClass().getResource(avatarpath) : null;
			String path = url != null ? url.toString() : null;
			Image image = path != null ? new Image(path) : null;
			
			ImageView imageView = new ImageView(image);
			imageView.setFitWidth(180);
			imageView.setFitHeight(150);
			prop.set(imageView);
			
			return prop;
		});
		codeCol.setCellValueFactory(new PropertyValueFactory<Teacher, String>("code"));
		nameCol.setCellValueFactory(new PropertyValueFactory<Teacher, String>("name"));
		phoneCol.setCellValueFactory(new PropertyValueFactory<Teacher, String>("phone"));
		genderCol.setCellValueFactory(new PropertyValueFactory<Teacher, String>("gender"));
		birthCol.setCellValueFactory(new PropertyValueFactory<Teacher, Date>("birth"));
		addressCol.setCellValueFactory(new PropertyValueFactory<Teacher, String>("address"));
		salaryCol.setCellValueFactory(new PropertyValueFactory<Teacher, Integer>("salary"));
		accountUsernameCol.setCellValueFactory(cell ->
		{
			ObjectProperty<String> prop = new SimpleObjectProperty<String>();
			
			Account account = cell.getValue().getAccount();
			String accountUsername = account != null ? account.getUsername() : SystemConstant.UNKNOWN;
			prop.set(accountUsername);
			
			return prop;
		});
		subjectNameCol.setCellValueFactory(cell ->
		{
			ObjectProperty<String> prop = new SimpleObjectProperty<String>();
			
			Subject subject = cell.getValue().getSubject();
			String subjectName = subject != null ? subject.getName() : SystemConstant.UNKNOWN;
			prop.set(subjectName);
			
			return prop;
		});
	}
	private void initListView()
	{
		//listView.setCellFactory(listView -> new AccountListViewCell());
	}
	private void initPageable()
	{
		pageable = new PageRequest<Teacher>(SystemConstant.DEFAULT_PAGE, SystemConstant.DEFAULT_PERPAGE, null, null, null);
		
		changeDataView(pageable);
	}
	@Override
	protected void initHeaderController() 
	{
		headerController.setTitleLabelText("QUẢN LÝ GIÁO VỤ");
	}
	@Override
	protected Teacher getFilterModel()
	{
		String code = codeFilterTextField.getText().trim();
		String name = nameFilterTextField.getText().trim();
		String phone = phoneFilterTextField.getText().trim();
		String gender = genderFilterComboBox.getValue();
		String address = addressFilterComboBox.getValue();
		String salary = salaryFilterTextField.getText().trim();
		String isDeleted = isDeletedFilterComboBox.getValue();
		String subjectName = subjectFilterComboBox.getValue();
		
		if(code.isEmpty() && 
			name.isEmpty() &&
			phone.isEmpty() &&
			gender == null &&
			address == null &&
			salary.isEmpty() &&
			isDeleted == null &&
			subjectName == null)
			return null;
		
		Teacher model = new Teacher();
		
		if(!code.isEmpty())
			model.setCode(code);
		if(!name.isEmpty())
			model.setName(name);
		if(!phone.isEmpty())
			model.setPhone(phone);
		if(gender != null)
			model.setGender(gender);
		if(address != null)
			model.setAddress(address);
		if(!salary.isEmpty())
			model.setSalary(Integer.parseInt(salary));
		if(isDeleted != null)
			model.setIsDeleted(isDeleted.equals("Khóa"));
		if(subjectName != null)
		{
			Subject subject = new Subject();
			subject.setName(subjectName);
			model.setSubject(subject);
		}
		
		return model;
	}
	@Override
	public String getDetailLoaderPath() 
	{
		return "../../application/views/teacher/detail.fxml";
	}
	@Override
	public String getEditLoaderPath() 
	{
		return "../../application/views/teacher/edit.fxml";
	}
	@Override
	protected void exportCSV(String path) 
	{
		CSVUtils.export(path, new TeacherConverter(), observableList);
	}
	@Override
	protected void refresh() 
	{
		super.refresh();
		
		codeFilterTextField.setText("");
		nameFilterTextField.setText("");
		phoneFilterTextField.setText("");
		genderFilterComboBox.setValue(null);
		addressFilterComboBox.setValue(null);
		salaryFilterTextField.setText("");
		subjectFilterComboBox.setValue(null);
	}
	@Override
	protected void changeDataView(Pageable<Teacher> pageable) 
	{
		Pageable<Teacher> totalPageable = new PageRequest<Teacher>(null, null, pageable.getSorter(), pageable.getSearchKey(), pageable.getFilterModel());
		Long count = teacherService.count(totalPageable);
		if(count == null)
			return;
		
		int totalPages = (int)Math.ceil((double) count / pageable.getLimit());
		pagination.setPageCount(totalPages);
	
		List<Teacher> list = teacherService.find(pageable);
		if(list == null)
			return;
		
		observableList.clear();
		observableList.addAll(list);
		
		tableView.setItems(observableList);
		
		listView.setItems(observableList);
	}
}
