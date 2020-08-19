package com.toanhuuvuong.controller.list;

import java.net.URL;
import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import com.toanhuuvuong.constant.SystemConstant;
import com.toanhuuvuong.controller.edit.OfficeStaffAccountController;
import com.toanhuuvuong.converter.OfficeStaffConverter;
import com.toanhuuvuong.model.Account;
import com.toanhuuvuong.model.OfficeStaff;
import com.toanhuuvuong.pagination.PageRequest;
import com.toanhuuvuong.pagination.Pageable;
import com.toanhuuvuong.service.impl.AccountService;
import com.toanhuuvuong.service.impl.OfficeStaffService;
import com.toanhuuvuong.utils.AutoCompleteComboBoxListener;
import com.toanhuuvuong.utils.CSVUtils;
import com.toanhuuvuong.utils.SceneUtils;
import com.toanhuuvuong.utils.SessionUtils;

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
import javafx.stage.Stage;

public class OfficeStaffController extends GenericController<OfficeStaff> implements Initializable
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
	private Button insertAccountButton;
	@FXML
	private Button deleteAccountButton;
	@FXML
	private TableColumn<OfficeStaff, ImageView> avatarPathCol;
	@FXML
	private TableColumn<OfficeStaff, String> codeCol;
	@FXML
	private TableColumn<OfficeStaff, String> nameCol;
	@FXML
	private TableColumn<OfficeStaff, String> phoneCol;
	@FXML
	private TableColumn<OfficeStaff, String> genderCol;
	@FXML
	private TableColumn<OfficeStaff, Date> birthCol;
	@FXML
	private TableColumn<OfficeStaff, String> addressCol;
	@FXML
	private TableColumn<OfficeStaff, Integer> salaryCol;
	@FXML
	private TableColumn<OfficeStaff, String> accountUsernameCol;
	
	private OfficeStaffService officeStaffService = new OfficeStaffService();
	private AccountService accountService = new AccountService();
	
	private Account accountModel = (Account)SessionUtils.getInstance().getValue("accountModel");
	// ------------------------------------------- Methods
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		super.initialize(location, resources);
		
		initGenderFilterComboBox();
		
		initAddressFilterComboBox();
		
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
		Collection<OfficeStaff> models = officeStaffService.findAll();
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
	private void initTableView()
	{
		tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> 
		{
		    if (newValue != null)
		    {
		    	if(tableView.getSelectionModel().getSelectedItems().size() == 1)	
		    	{
		    		insertAccountButton.setVisible(true);
		    		deleteAccountButton.setVisible(true);
		    	}
		    	else
		    	{
		    		insertAccountButton.setVisible(false);
		    		deleteAccountButton.setVisible(false);
		    	}
		    }
		    else
		    {
		    	insertAccountButton.setVisible(false);
		    	deleteAccountButton.setVisible(false);
		    }
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
		codeCol.setCellValueFactory(new PropertyValueFactory<OfficeStaff, String>("code"));
		nameCol.setCellValueFactory(new PropertyValueFactory<OfficeStaff, String>("name"));
		phoneCol.setCellValueFactory(new PropertyValueFactory<OfficeStaff, String>("phone"));
		genderCol.setCellValueFactory(new PropertyValueFactory<OfficeStaff, String>("gender"));
		birthCol.setCellValueFactory(new PropertyValueFactory<OfficeStaff, Date>("birth"));
		addressCol.setCellValueFactory(new PropertyValueFactory<OfficeStaff, String>("address"));
		salaryCol.setCellValueFactory(new PropertyValueFactory<OfficeStaff, Integer>("salary"));
		accountUsernameCol.setCellValueFactory(cell ->
		{
			ObjectProperty<String> prop = new SimpleObjectProperty<String>();
			
			Account account = cell.getValue().getAccount();
			String accountUsername = account != null ? account.getUsername() : SystemConstant.UNKNOWN;
			prop.set(accountUsername);
			
			return prop;
		});
	}
	private void initListView()
	{
		//listView.setCellFactory(listView -> new AccountListViewCell());
	}
	private void initPageable()
	{
		pageable = new PageRequest<OfficeStaff>(SystemConstant.DEFAULT_PAGE, SystemConstant.DEFAULT_PERPAGE, null, null, null);
		
		changeDataView(pageable);
	}
	@Override
	protected void initHeaderController() 
	{
		headerController.setTitleLabelText("QUẢN LÝ GIÁO VỤ");
	}
	@Override
	protected OfficeStaff getFilterModel()
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
		
		OfficeStaff model = new OfficeStaff();
		
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
		return "../../application/views/officestaff/detail.fxml";
	}
	@Override
	public String getEditLoaderPath() 
	{
		return "../../application/views/officestaff/edit.fxml";
	}
	@Override
	protected void exportCSV(String path) 
	{
		CSVUtils.export(path, new OfficeStaffConverter(), observableList);
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
	protected void changeDataView(Pageable<OfficeStaff> pageable) 
	{
		Pageable<OfficeStaff> totalPageable = new PageRequest<OfficeStaff>(null, null, pageable.getSorter(), pageable.getSearchKey(), pageable.getFilterModel());
		Long count = officeStaffService.count(totalPageable);
		if(count == null)
			return;
		
		int totalPages = (int)Math.ceil((double) count / pageable.getLimit());
		pagination.setPageCount(totalPages);
	
		List<OfficeStaff> list = officeStaffService.find(pageable);
		if(list == null)
			return;
		
		observableList.clear();
		observableList.addAll(list);
		
		tableView.setItems(observableList);
		
		listView.setItems(observableList);
	}
	@FXML
	public void insertAccountButtonOnAction(ActionEvent event)
	{
		Integer selectedIndex = tableView.getSelectionModel().getSelectedIndex();
		OfficeStaff selectedItem = observableList.get(selectedIndex);
		
		if(selectedItem == null)
			return;
		
		URL url = getClass().getResource("../../application/views/account/officestaff.fxml");
		Stage stage = new Stage();
		String title = "Tạo tài khoản";
		
		FXMLLoader loader = SceneUtils.changeSceneWithoutLostFocus(url, stage, title, null, null); 
		
		OfficeStaffAccountController controller = loader.getController();
		controller.listDelegate = this;
		controller.setItem(selectedIndex, selectedItem);
	}
	@FXML
	public void deleteAccountButtonOnAction(ActionEvent event)
	{
		Integer selectedIndex = tableView.getSelectionModel().getSelectedIndex();
		OfficeStaff selectedItem = observableList.get(selectedIndex);
		
		if(selectedItem == null)
			return;
		
		if(selectedItem.getAccount() != null)
		{
			Long[] deletedIds = { selectedItem.getAccount().getId() };
			
			selectedItem.setAccount(null);
			officeStaffService.updateOne(selectedItem, accountModel);
			
			accountService.delete(deletedIds);
			
			updateSelectedItem(selectedIndex, selectedItem);
		}
	}
}
