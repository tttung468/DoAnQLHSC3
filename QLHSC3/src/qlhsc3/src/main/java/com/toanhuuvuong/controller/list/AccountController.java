/*package com.toanhuuvuong.controller.list;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import com.toanhuuvuong.cell.AccountListViewCell;
import com.toanhuuvuong.constant.SystemConstant;
import com.toanhuuvuong.converter.AccountConverter;
import com.toanhuuvuong.model.AccountModel;
import com.toanhuuvuong.model.RoleModel;
import com.toanhuuvuong.pagination.PageRequest;
import com.toanhuuvuong.pagination.Pageable;
import com.toanhuuvuong.service.IAccountService;
import com.toanhuuvuong.service.IRoleService;
import com.toanhuuvuong.service.impl.AccountService;
import com.toanhuuvuong.service.impl.RoleService;
import com.toanhuuvuong.utils.CSVUtils;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class AccountController extends GenericController<AccountModel> implements Initializable
{
	// ------------------------------------------- Attributes
	@FXML
	private TextField usernameFilterTextField;
	@FXML
	private ComboBox<String> roleFilterComboBox;
	@FXML
	private TableColumn<AccountModel, String> usernameCol;
	@FXML
	private TableColumn<AccountModel, String> roleNameCol;
	
	private IAccountService accountService = new AccountService();
	private IRoleService roleService = new RoleService();
	// ------------------------------------------- Methods
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		super.initialize(location, resources);
		
		initRoleComboBox();
		
		initTableView();
		
		initListView();
		
		initPageable();
	}
	private void initRoleComboBox()
	{		
		Collection<RoleModel> roles = roleService.findAll();
		Collection<String> roleCodes = roles.stream().map(item ->
		{
			return item.getCode();
		}).collect(Collectors.toList());
		
		roleFilterComboBox.setItems(FXCollections.observableArrayList(roleCodes));
		roleFilterComboBox.setValue(null);
		roleFilterComboBox.setCellFactory(value ->
		{
			return new ListCell<String>()
			{
				@Override
	            protected void updateItem(String item, boolean empty)
				{
	                super.updateItem(item, empty);
	 
	                if(!empty && item != null) 
	                	this.setText(roleService.findByCode(item).getName());
					else 
					{	
						this.setText("Loại vai trò");
	                	setGraphic(null);
					}
	            }
			};
		});
	}
	private void initTableView()
	{
		usernameCol.setCellValueFactory(new PropertyValueFactory<AccountModel, String>("username"));
		roleNameCol.setCellValueFactory(cell ->
		{
			ObjectProperty<String> roleNameProp = new SimpleObjectProperty<String>();
			roleNameProp.set(cell.getValue().getRole().getName());
			return roleNameProp;
		});
	}
	private void initListView()
	{
		listView.setCellFactory(listView -> new AccountListViewCell());
	}
	private void initPageable()
	{
		pageable = new PageRequest<AccountModel>(SystemConstant.DEFAULT_PAGE, SystemConstant.DEFAULT_PERPAGE, null, null, null);
		
		changeDataView(pageable);
	}
	@Override
	protected void exportCSV(String path) 
	{
		try
		{
			CSVUtils.export(path, new AccountConverter(), observableList);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	@Override
	protected AccountModel getFilterModel()
	{
		String username = usernameFilterTextField.getText().trim();
		String role = roleFilterComboBox.getValue();
		String isDeleted = isDeletedFilterComboBox.getValue();
		
		if(username.isEmpty() && 
			role == null && 
			isDeleted == null)
			return null;
		
		AccountModel model = new AccountModel();
		if(!username.isEmpty())
			model.setUsername(username);
		model.setRole(new RoleModel());
		if(role != null)
		{
			RoleModel roleModel = roleService.findByCode(role);
			if(roleModel != null)
			{
				model.setRole(roleModel);
				model.setRoleId(roleModel.getId());
			}
		}
		if(isDeleted != null)
			model.setIsDeleted(isDeleted.equals("Khóa"));
		
		return model;
	}
	@Override
	public String getDetailLoaderPath() 
	{
		return "../../application/views/account/detail.fxml";
	}
	@Override
	public String getEditLoaderPath() 
	{
		return "../../application/views/account/edit.fxml";
	}
	@Override
	protected void refresh() 
	{
		super.refresh();
		
		usernameFilterTextField.setText("");
		roleFilterComboBox.setValue(null);
	}
	@Override
	protected void deleteItem(AccountModel item) 
	{
		Long[] deletedIds = { item.getId() };
		accountService.delete(deletedIds);
	}
	@Override
	protected void changeDataView(Pageable<AccountModel> pageable) 
	{
		int count = accountService.count(new PageRequest<AccountModel>(null, null, pageable.getSorter(), pageable.getSearchKey(), pageable.getFilterModel()));
		int totalPages = (int)Math.ceil((double) count / pageable.getLimit());
		pagination.setPageCount(totalPages);
		
		List<AccountModel> list = accountService.find(pageable);
		
		observableList.clear();
		observableList.addAll(list);
		
		tableView.setItems(observableList);
		
		listView.setItems(observableList);
	}
}*/
