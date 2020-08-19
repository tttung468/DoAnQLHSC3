package com.toanhuuvuong.controller.list;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.toanhuuvuong.constant.SystemConstant;
import com.toanhuuvuong.converter.SchoolYearConverter;
import com.toanhuuvuong.model.SchoolYear;
import com.toanhuuvuong.pagination.PageRequest;
import com.toanhuuvuong.pagination.Pageable;
import com.toanhuuvuong.service.impl.SchoolYearService;
import com.toanhuuvuong.utils.CSVUtils;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class SchoolYearController extends GenericController<SchoolYear> implements Initializable
{
	// ------------------------------------------- Attributes
	@FXML
	private TextField codeFilterTextField;
	@FXML
	private TextField lowerBoundFilterTextField;
	@FXML
	private TextField upperBoundFilterTextField;
	@FXML
	private TextField themeFilterTextField;
	@FXML
	private TableColumn<SchoolYear, String> codeCol;
	@FXML
	private TableColumn<SchoolYear, String> nameCol;
	@FXML
	private TableColumn<SchoolYear, String> themeCol;
	
	private SchoolYearService schoolYearService = new SchoolYearService();
	// ------------------------------------------- Methods
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		super.initialize(location, resources);
		
		initTableView();
		
		initListView();
		
		initPageable();
	}
	private void initTableView()
	{
		codeCol.setCellValueFactory(new PropertyValueFactory<SchoolYear, String>("code"));
		nameCol.setCellValueFactory(cell ->
		{
			ObjectProperty<String> prop = new SimpleObjectProperty<String>();
			
			String name = "Năm học " + cell.getValue().getLowerBound().toString() + 
					" - " + cell.getValue().getUpperBound().toString();
	
			prop.set(name);
			
			return prop;
		});
		themeCol.setCellValueFactory(new PropertyValueFactory<SchoolYear, String>("theme"));
	}
	private void initListView()
	{
		//listView.setCellFactory(listView -> new AccountListViewCell());
	}
	private void initPageable()
	{
		pageable = new PageRequest<SchoolYear>(SystemConstant.DEFAULT_PAGE, SystemConstant.DEFAULT_PERPAGE, null, null, null);
		
		changeDataView(pageable);
	}
	@Override
	protected void initHeaderController() 
	{
		headerController.setTitleLabelText("QUẢN LÝ NĂM HỌC");
	}
	@Override
	protected SchoolYear getFilterModel()
	{
		String code = codeFilterTextField.getText().trim();
		String lowerBound = lowerBoundFilterTextField.getText().trim();
		String upperBound = upperBoundFilterTextField.getText().trim();
		String theme = themeFilterTextField.getText().trim();;
		String isDeleted = isDeletedFilterComboBox.getValue();
		
		if(code.isEmpty() && 
			lowerBound.isEmpty() &&
			upperBound.isEmpty() &&
			theme.isEmpty() &&
			isDeleted == null)
			return null;
		
		SchoolYear model = new SchoolYear();
		
		if(!code.isEmpty())
			model.setCode(code);
		if(!lowerBound.isEmpty())
			model.setLowerBound(Integer.parseInt(lowerBound));
		if(!upperBound.isEmpty())
			model.setUpperBound(Integer.parseInt(upperBound));
		if(!theme.isEmpty())
			model.setTheme(theme);
		if(isDeleted != null)
			model.setIsDeleted(isDeleted.equals("Khóa"));
		
		return model;
	}
	@Override
	public String getDetailLoaderPath() 
	{
		return "../../application/views/schoolyear/detail.fxml";
	}
	@Override
	public String getEditLoaderPath() 
	{
		return "../../application/views/schoolyear/edit.fxml";
	}
	@Override
	protected void exportCSV(String path) 
	{
		CSVUtils.export(path, new SchoolYearConverter(), observableList);
	}
	@Override
	protected void refresh() 
	{
		super.refresh();
		
		codeFilterTextField.setText("");
		lowerBoundFilterTextField.setText("");
		upperBoundFilterTextField.setText("");
		themeFilterTextField.setText("");
	}
	@Override
	protected void changeDataView(Pageable<SchoolYear> pageable) 
	{
		Pageable<SchoolYear> totalPageable = new PageRequest<SchoolYear>(null, null, pageable.getSorter(), pageable.getSearchKey(), pageable.getFilterModel());
		Long count = schoolYearService.count(totalPageable);
		if(count == null)
			return;
		
		int totalPages = (int)Math.ceil((double) count / pageable.getLimit());
		pagination.setPageCount(totalPages);
	
		List<SchoolYear> list = schoolYearService.find(pageable);
		if(list == null)
			return;
		
		observableList.clear();
		observableList.addAll(list);
		
		tableView.setItems(observableList);
		
		listView.setItems(observableList);
	}
}
