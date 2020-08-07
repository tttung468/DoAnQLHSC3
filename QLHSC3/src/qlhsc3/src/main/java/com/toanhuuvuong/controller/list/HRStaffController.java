package com.toanhuuvuong.controller.list;

import java.net.URL;
import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import com.toanhuuvuong.constant.SystemConstant;
import com.toanhuuvuong.converter.HRStaffConverter;
import com.toanhuuvuong.model.HRStaffModel;
import com.toanhuuvuong.pagination.PageRequest;
import com.toanhuuvuong.pagination.Pageable;
import com.toanhuuvuong.service.IHRStaffService;
import com.toanhuuvuong.service.impl.HRStaffService;
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
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.converter.NumberStringConverter;

public class HRStaffController extends GenericController<HRStaffModel> implements Initializable
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
	private TableColumn<HRStaffModel, ImageView> avatarPathCol;
	@FXML
	private TableColumn<HRStaffModel, String> codeCol;
	@FXML
	private TableColumn<HRStaffModel, String> nameCol;
	@FXML
	private TableColumn<HRStaffModel, String> phoneCol;
	@FXML
	private TableColumn<HRStaffModel, String> genderCol;
	@FXML
	private TableColumn<HRStaffModel, Date> birthCol;
	@FXML
	private TableColumn<HRStaffModel, String> addressCol;
	@FXML
	private TableColumn<HRStaffModel, Integer> salaryCol;
	
	private IHRStaffService hrStaffService = new HRStaffService();
	// ------------------------------------------- Methods
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		super.initialize(location, resources);
		
		initGenderFilterComboBox();
		
		initAddressFilterComboBox();
		
		initSalaryFilterTextField();
		
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
		Collection<HRStaffModel> models = hrStaffService.findAll();
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
	private void initSalaryFilterTextField()
	{
		salaryFilterTextField.setTextFormatter(new TextFormatter<>(new NumberStringConverter()));
	}
	private void initTableView()
	{
		avatarPathCol.setCellValueFactory(cell ->
		{
			ObjectProperty<ImageView> prop = new SimpleObjectProperty<ImageView>();
			
			URL url = getClass().getResource(cell.getValue().getAvatarPath());
			String path = url != null ? url.toString() : null;
			Image image = path != null ? new Image(path) : null;
			
			ImageView imageView = new ImageView(image);
			imageView.setFitWidth(180);
			imageView.setFitHeight(150);
			prop.set(imageView);
			
			return prop;
		});
		codeCol.setCellValueFactory(new PropertyValueFactory<HRStaffModel, String>("code"));
		nameCol.setCellValueFactory(new PropertyValueFactory<HRStaffModel, String>("name"));
		phoneCol.setCellValueFactory(new PropertyValueFactory<HRStaffModel, String>("phone"));
		genderCol.setCellValueFactory(new PropertyValueFactory<HRStaffModel, String>("gender"));
		birthCol.setCellValueFactory(new PropertyValueFactory<HRStaffModel, Date>("birth"));
		addressCol.setCellValueFactory(new PropertyValueFactory<HRStaffModel, String>("address"));
		salaryCol.setCellValueFactory(new PropertyValueFactory<HRStaffModel, Integer>("salary"));
	}
	private void initListView()
	{
		//listView.setCellFactory(listView -> new AccountListViewCell());
	}
	private void initPageable()
	{
		pageable = new PageRequest<HRStaffModel>(SystemConstant.DEFAULT_PAGE, SystemConstant.DEFAULT_PERPAGE, null, null, null);
		
		changeDataView(pageable);
	}
	@Override
	protected void initHeaderController() 
	{
		headerController.setTitleLabelText("QUẢN LÝ NHÂN SỰ");
	}
	@Override
	protected HRStaffModel getFilterModel()
	{
		String code = codeFilterTextField.getText().trim();
		String name = nameFilterTextField.getText().trim();
		String phone = phoneFilterTextField.getText().trim();
		String gender = genderFilterComboBox.getValue();
		String address = addressFilterComboBox.getValue();
		String salary = salaryFilterTextField.getText().trim();
		String isDeleted = isDeletedFilterComboBox.getValue();
		
		if(code.isEmpty() && 
			name.isEmpty() &&
			phone.isEmpty() &&
			gender == null &&
			address == null &&
			salary.isEmpty() &&
			isDeleted == null)
			return null;
		
		HRStaffModel model = new HRStaffModel();
		
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
		
		return model;
	}
	@Override
	public String getDetailLoaderPath() 
	{
		return "../../application/views/hrstaff/detail.fxml";
	}
	@Override
	public String getEditLoaderPath() 
	{
		return "../../application/views/hrstaff/edit.fxml";
	}
	@Override
	protected void exportCSV(String path) 
	{
		CSVUtils.export(path, new HRStaffConverter(), observableList);
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
	}
	@Override
	protected void changeDataView(Pageable<HRStaffModel> pageable) 
	{
		Pageable<HRStaffModel> totalPageable = new PageRequest<HRStaffModel>(null, null, pageable.getSorter(), pageable.getSearchKey(), pageable.getFilterModel());
		Integer count = hrStaffService.count(totalPageable);
		if(count == null)
			return;
		
		int totalPages = (int)Math.ceil((double) count / pageable.getLimit());
		pagination.setPageCount(totalPages);
	
		List<HRStaffModel> list = hrStaffService.find(pageable);
		if(list == null)
			return;
		
		observableList.clear();
		observableList.addAll(list);
		
		tableView.setItems(observableList);
		
		listView.setItems(observableList);
	}
}
