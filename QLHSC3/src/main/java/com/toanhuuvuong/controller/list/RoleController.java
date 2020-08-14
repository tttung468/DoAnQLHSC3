/*package com.toanhuuvuong.controller.list;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.toanhuuvuong.cell.RoleListViewCell;
import com.toanhuuvuong.constant.SystemConstant;
import com.toanhuuvuong.converter.RoleConverter;
import com.toanhuuvuong.model.RoleModel;
import com.toanhuuvuong.pagination.PageRequest;
import com.toanhuuvuong.pagination.Pageable;
import com.toanhuuvuong.service.IRoleService;
import com.toanhuuvuong.service.impl.RoleService;
import com.toanhuuvuong.utils.CSVUtils;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.converter.NumberStringConverter;

public class RoleController extends GenericController<RoleModel> implements Initializable
{
	// ------------------------------------------- Attributes
	@FXML
	private TextField codeFilterTextField;
	@FXML
	private TextField nameFilterTextField;
	@FXML
	private TextField priorityFilterTextField;
	@FXML
	private TableColumn<RoleModel, String> codeCol;
	@FXML
	private TableColumn<RoleModel, String> nameCol;
	@FXML
	private TableColumn<RoleModel, Integer> priorityCol;
	
	private IRoleService roleService = new RoleService();
	// ------------------------------------------- Methods
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		super.initialize(location, resources);
		
		initPriorityFilterTextField();
		
		initTableView();
		
		initListView();
		
		initPageable();
	}
	private void initPriorityFilterTextField()
	{
		priorityFilterTextField.setTextFormatter(new TextFormatter<>(new NumberStringConverter()));
	}
	private void initTableView()
	{
		codeCol.setCellValueFactory(new PropertyValueFactory<RoleModel, String>("code"));
		nameCol.setCellValueFactory(new PropertyValueFactory<RoleModel, String>("name"));
		priorityCol.setCellValueFactory(new PropertyValueFactory<RoleModel, Integer>("priority"));
	}
	private void initListView()
	{
		listView.setCellFactory(listView -> new RoleListViewCell());
	}
	private void initPageable()
	{
		pageable = new PageRequest<RoleModel>(SystemConstant.DEFAULT_PAGE, SystemConstant.DEFAULT_PERPAGE, null, null, null);
		
		changeDataView(pageable);
	}
	@Override
	protected void exportCSV(String path) 
	{
		try
		{
			CSVUtils.export(path, new RoleConverter(), observableList);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	@Override
	protected RoleModel getFilterModel()
	{
		String code = codeFilterTextField.getText().trim();
		String name = nameFilterTextField.getText().trim();
		String priority = priorityFilterTextField.getText().trim();
		String isDeleted = isDeletedFilterComboBox.getValue();
		
		if(code.isEmpty() && 
			name.isEmpty() && 
			priority.isEmpty() && 
			isDeleted == null)
			return null;
		
		RoleModel model = new RoleModel();
		if(!code.isEmpty())
			model.setCode(code);
		if(!name.isEmpty())
			model.setName(name);
		if(!priority.isEmpty())
			model.setPriority(Integer.parseInt(priority));
		if(isDeleted != null)
			model.setIsDeleted(isDeleted.equals("Khóa"));
		
		return model;
	}
	@Override
	public String getEditLoaderPath() 
	{
		return "../../application/views/role/edit.fxml";
	}
	@Override
	protected void refresh() 
	{
		super.refresh();
		
		codeFilterTextField.setText("");
		nameFilterTextField.setText("");
		priorityFilterTextField.setText("");
	}
	@Override
	protected void deleteItem(RoleModel item) 
	{
		Long[] deletedIds = { item.getId() };
		roleService.delete(deletedIds);
	}
	@Override
	protected void changeDataView(Pageable<RoleModel> pageable) 
	{
		int count = roleService.count(new PageRequest<RoleModel>(null, null, pageable.getSorter(), pageable.getSearchKey(), pageable.getFilterModel()));
		int totalPages = (int)Math.ceil((double) count / pageable.getLimit());
		pagination.setPageCount(totalPages);
		
		List<RoleModel> list = roleService.find(pageable);
		
		observableList.clear();
		observableList.addAll(list);
		
		tableView.setItems(observableList);
		
		listView.setItems(observableList);
	}
}*/
