package com.toanhuuvuong.controller.list;

import java.net.URL;
import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import com.toanhuuvuong.constant.SystemConstant;
import com.toanhuuvuong.converter.StudentConverter;
import com.toanhuuvuong.model.Ethnic;
import com.toanhuuvuong.model.Nationality;
import com.toanhuuvuong.model.Religion;
import com.toanhuuvuong.model.Student;
import com.toanhuuvuong.pagination.PageRequest;
import com.toanhuuvuong.pagination.Pageable;
import com.toanhuuvuong.service.impl.EthnicService;
import com.toanhuuvuong.service.impl.NationalityService;
import com.toanhuuvuong.service.impl.ReligionService;
import com.toanhuuvuong.service.impl.StudentService;
import com.toanhuuvuong.utils.AutoCompleteComboBoxListener;
import com.toanhuuvuong.utils.CSVUtils;
import com.toanhuuvuong.utils.SceneUtils;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class StudentController extends GenericController<Student> implements Initializable
{
	// ------------------------------------------- Attributes
	@FXML
	private TextField codeFilterTextField;
	@FXML
	private TextField nameFilterTextField;
	@FXML
	private TextField phoneFilterTextField;
	@FXML
	private TextField identifyCardFilterTextField;
	@FXML
	private TextField emailFilterTextField;
	@FXML
	private ComboBox<String> genderFilterComboBox;
	@FXML
	private ComboBox<String> addressFilterComboBox;
	@FXML
	private ComboBox<String> statusFilterComboBox;
	@FXML
	private ComboBox<String> ethnicFilterComboBox;
	@FXML
	private ComboBox<String> religionFilterComboBox;
	@FXML
	private ComboBox<String> nationalityFilterComboBox;
	@FXML
	private Button evaluateButton;
	@FXML
	private TableColumn<Student, ImageView> avatarPathCol;
	@FXML
	private TableColumn<Student, String> codeCol;
	@FXML
	private TableColumn<Student, String> nameCol;
	@FXML
	private TableColumn<Student, String> phoneCol;
	@FXML
	private TableColumn<Student, String> identifyCardCol;
	@FXML
	private TableColumn<Student, String> emailCol;
	@FXML
	private TableColumn<Student, String> genderCol;
	@FXML
	private TableColumn<Student, Date> birthCol;
	@FXML
	private TableColumn<Student, String> addressCol;
	@FXML
	private TableColumn<Student, String> statusCol;
	@FXML
	private TableColumn<Student, String> ethnicNameCol;
	@FXML
	private TableColumn<Student, String> religionNameCol;
	@FXML
	private TableColumn<Student, String> nationalityNameCol;
	
	private StudentService studentService = new StudentService();
	private EthnicService ethnicService = new EthnicService();
	private ReligionService religionService = new ReligionService();
	private NationalityService nationalityService = new NationalityService();
	// ------------------------------------------- Methods
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		super.initialize(location, resources);
		
		initGenderFilterComboBox();
		
		initAddressFilterComboBox();
		
		initStatusFilterComboBox();
		
		initEthnicFilterComboBox();
		
		initReligionFilterComboBox();
		
		initNationalityFilterComboBox();
		
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
		Collection<Student> models = studentService.findAll();
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
	private void initStatusFilterComboBox()
	{
		statusFilterComboBox.setItems(FXCollections.observableArrayList("Chuyển đến", "Chuyển đi", "Thôi học", "Đang học"));
		statusFilterComboBox.setValue(null);
	}
	private void initEthnicFilterComboBox()
	{
		Collection<Ethnic> models = ethnicService.findAll();
		Collection<String> ethnicNames = (models == null) ? null
		: models.stream().map(item ->
		{
			return item.getName();
		}).collect(Collectors.toSet());
		
		if(ethnicNames != null)
			ethnicFilterComboBox.setItems(FXCollections.observableArrayList(ethnicNames));
		ethnicFilterComboBox.setValue(null);
		
		new AutoCompleteComboBoxListener<String>(ethnicFilterComboBox);
	}
	private void initReligionFilterComboBox()
	{
		Collection<Religion> models = religionService.findAll();
		Collection<String> religionNames = (models == null) ? null
		: models.stream().map(item ->
		{
			return item.getName();
		}).collect(Collectors.toSet());
		
		if(religionNames != null)
			religionFilterComboBox.setItems(FXCollections.observableArrayList(religionNames));
		religionFilterComboBox.setValue(null);
		
		new AutoCompleteComboBoxListener<String>(religionFilterComboBox);
	}
	private void initNationalityFilterComboBox()
	{
		Collection<Nationality> models = nationalityService.findAll();
		Collection<String> nationalitytNames = (models == null) ? null
		: models.stream().map(item ->
		{
			return item.getName();
		}).collect(Collectors.toSet());
		
		if(nationalitytNames != null)
			nationalityFilterComboBox.setItems(FXCollections.observableArrayList(nationalitytNames));
		nationalityFilterComboBox.setValue(null);
		
		new AutoCompleteComboBoxListener<String>(nationalityFilterComboBox);
	}
	private void initTableView()
	{
		tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> 
		{
		    if (newValue != null)
		    {
		    	if(tableView.getSelectionModel().getSelectedItems().size() == 1)	
		    		evaluateButton.setVisible(true);
		    	else
		    		evaluateButton.setVisible(false);
		    }
		    else
		    	evaluateButton.setVisible(false);
		});
		
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
		codeCol.setCellValueFactory(new PropertyValueFactory<Student, String>("code"));
		nameCol.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
		phoneCol.setCellValueFactory(new PropertyValueFactory<Student, String>("phone"));
		identifyCardCol.setCellValueFactory(new PropertyValueFactory<Student, String>("identifyCard"));
		emailCol.setCellValueFactory(new PropertyValueFactory<Student, String>("email"));
		genderCol.setCellValueFactory(new PropertyValueFactory<Student, String>("gender"));
		birthCol.setCellValueFactory(new PropertyValueFactory<Student, Date>("birth"));
		addressCol.setCellValueFactory(new PropertyValueFactory<Student, String>("address"));
		statusCol.setCellValueFactory(new PropertyValueFactory<Student, String>("status"));
		ethnicNameCol.setCellValueFactory(cell ->
		{
			ObjectProperty<String> prop = new SimpleObjectProperty<String>();
			
			Ethnic ethnic = cell.getValue().getEthnic();
			String ethnicName = ethnic != null ? ethnic.getName() : SystemConstant.UNKNOWN;
			prop.set(ethnicName);
			
			return prop;
		});
		religionNameCol.setCellValueFactory(cell ->
		{
			ObjectProperty<String> prop = new SimpleObjectProperty<String>();
			
			Religion religion = cell.getValue().getReligion();
			String religionName = religion != null ? religion.getName() : SystemConstant.UNKNOWN;
			prop.set(religionName);
			
			return prop;
		});
		nationalityNameCol.setCellValueFactory(cell ->
		{
			ObjectProperty<String> prop = new SimpleObjectProperty<String>();
			
			Nationality nationality = cell.getValue().getNationality();
			String nationalityName = nationality != null ? nationality.getName() : SystemConstant.UNKNOWN;
			prop.set(nationalityName);
			
			return prop;
		});
	}
	private void initListView()
	{
		//listView.setCellFactory(listView -> new AccountListViewCell());
	}
	private void initPageable()
	{
		pageable = new PageRequest<Student>(SystemConstant.DEFAULT_PAGE, SystemConstant.DEFAULT_PERPAGE, null, null, null);
		
		changeDataView(pageable);
	}
	@Override
	protected void initHeaderController() 
	{
		headerController.setTitleLabelText("QUẢN LÝ HỌC SINH");
	}
	@Override
	protected Student getFilterModel()
	{
		String code = codeFilterTextField.getText().trim();
		String name = nameFilterTextField.getText().trim();
		String phone = phoneFilterTextField.getText().trim();
		String indentifyCard = identifyCardFilterTextField.getText().trim();
		String email = emailFilterTextField.getText().trim();
		String gender = genderFilterComboBox.getValue();
		String address = addressFilterComboBox.getValue();
		String status = statusFilterComboBox.getValue();
		String isDeleted = isDeletedFilterComboBox.getValue();
		String ethnicName = ethnicFilterComboBox.getValue();
		String religionName = religionFilterComboBox.getValue();
		String nationalityName = nationalityFilterComboBox.getValue();
		
		if(code.isEmpty() && 
			name.isEmpty() &&
			phone.isEmpty() &&
			indentifyCard.isEmpty() &&
			email.isEmpty() &&
			gender == null &&
			address == null &&
			status == null &&
			isDeleted == null &&
			ethnicName == null &&
			religionName == null &&
			nationalityName == null)
			return null;
		
		Student model = new Student();
		
		if(!code.isEmpty())
			model.setCode(code);
		if(!name.isEmpty())
			model.setName(name);
		if(!phone.isEmpty())
			model.setPhone(phone);
		if(!indentifyCard.isEmpty())
			model.setIdentifyCard(indentifyCard);
		if(!email.isEmpty())
			model.setEmail(email);
		if(gender != null)
			model.setGender(gender);
		if(address != null)
			model.setAddress(address);
		if(status != null)
			model.setStatus(status);
		if(isDeleted != null)
			model.setIsDeleted(isDeleted.equals("Khóa"));
		if(ethnicName != null)
		{
			Ethnic ethnic = new Ethnic();
			ethnic.setName(ethnicName);
			model.setEthnic(ethnic);
		}
		if(religionName != null)
		{
			Religion religion = new Religion();
			religion.setName(religionName);
			model.setReligion(religion);
		}
		if(nationalityName != null)
		{
			Nationality nationality = new Nationality();
			nationality.setName(nationalityName);
			model.setNationality(nationality);
		}
		
		return model;
	}
	@Override
	public String getDetailLoaderPath() 
	{
		return "../../application/views/student/detail.fxml";
	}
	@Override
	public String getEditLoaderPath() 
	{
		return "../../application/views/student/edit.fxml";
	}
	@Override
	protected void exportCSV(String path) 
	{
		CSVUtils.export(path, new StudentConverter(), observableList);
	}
	@Override
	protected void refresh() 
	{
		super.refresh();
		
		codeFilterTextField.setText("");
		nameFilterTextField.setText("");
		phoneFilterTextField.setText("");
		identifyCardFilterTextField.setText("");
		emailFilterTextField.setText("");
		genderFilterComboBox.setValue(null);
		addressFilterComboBox.setValue(null);
		statusFilterComboBox.setValue(null);
		ethnicFilterComboBox.setValue(null);
		religionFilterComboBox.setValue(null);
		nationalityFilterComboBox.setValue(null);
	}
	@Override
	protected void changeDataView(Pageable<Student> pageable) 
	{
		Pageable<Student> totalPageable = new PageRequest<Student>(null, null, pageable.getSorter(), pageable.getSearchKey(), pageable.getFilterModel());
		Long count = studentService.count(totalPageable);
		if(count == null)
			return;
		
		int totalPages = (int)Math.ceil((double) count / pageable.getLimit());
		pagination.setPageCount(totalPages);
	
		List<Student> list = studentService.find(pageable);
		if(list == null)
			return;
		
		observableList.clear();
		observableList.addAll(list);
		
		tableView.setItems(observableList);
		
		listView.setItems(observableList);
	}
	@FXML
	public void evaluateButtonOnAction(ActionEvent event)
	{
		Integer selectedIndex = tableView.getSelectionModel().getSelectedIndex();
		Student selectedItem = observableList.get(selectedIndex);
		
		if(selectedItem == null)
			return;
		
		URL url = getClass().getResource("../../application/views/student/evaluate.fxml");
		FXMLLoader loader = SceneUtils.changeScene(url, null, null, null, null);

		/*com.toanhuuvuong.controller.edit.GenericController<T> controller = loader.getController();
		controller.setItem(selectedIndex, selectedItem);
		controller.showDialog();*/
	}
}
