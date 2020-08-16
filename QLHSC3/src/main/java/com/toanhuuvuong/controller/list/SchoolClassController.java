package com.toanhuuvuong.controller.list;

import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import com.toanhuuvuong.constant.SystemConstant;
import com.toanhuuvuong.converter.SchoolClassConverter;
import com.toanhuuvuong.model.Grade;
import com.toanhuuvuong.model.SchoolClass;
import com.toanhuuvuong.pagination.PageRequest;
import com.toanhuuvuong.pagination.Pageable;
import com.toanhuuvuong.service.impl.GradeService;
import com.toanhuuvuong.service.impl.SchoolClassService;
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

public class SchoolClassController extends GenericController<SchoolClass> implements Initializable
{
	// ------------------------------------------- Attributes
	@FXML
	private TextField codeFilterTextField;
	@FXML
	private TextField nameFilterTextField;
	@FXML
	private ComboBox<String> gradeFilterComboBox;
	@FXML
	private TableColumn<SchoolClass, String> codeCol;
	@FXML
	private TableColumn<SchoolClass, String> nameCol;
	@FXML
	private TableColumn<SchoolClass, String> gradeNameCol;
	
	private SchoolClassService schoolClassService = new SchoolClassService();
	private GradeService gradeService = new GradeService();
	// ------------------------------------------- Methods
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		super.initialize(location, resources);
		
		initGradeFilterComboBox();
		
		initTableView();
		
		initListView();
		
		initPageable();
	}
	private void initGradeFilterComboBox()
	{
		Collection<Grade> models = gradeService.findAll();
		Collection<String> gradeNames = (models == null) ? null
		: models.stream().map(item ->
		{
			return item.getName();
		}).collect(Collectors.toSet());
		
		if(gradeNames != null)
			gradeFilterComboBox.setItems(FXCollections.observableArrayList(gradeNames));
		gradeFilterComboBox.setValue(null);
	}
	private void initTableView()
	{
		codeCol.setCellValueFactory(new PropertyValueFactory<SchoolClass, String>("code"));
		nameCol.setCellValueFactory(new PropertyValueFactory<SchoolClass, String>("name"));
		gradeNameCol.setCellValueFactory(cell ->
		{
			ObjectProperty<String> prop = new SimpleObjectProperty<String>();
			
			Grade grade = cell.getValue().getGrade();
			String gradeName = grade != null ? grade.getName() : SystemConstant.UNKNOWN;
			prop.set(gradeName);
			
			return prop;
		});
	}
	private void initListView()
	{
		//listView.setCellFactory(listView -> new AccountListViewCell());
	}
	private void initPageable()
	{
		pageable = new PageRequest<SchoolClass>(SystemConstant.DEFAULT_PAGE, SystemConstant.DEFAULT_PERPAGE, null, null, null);
		
		changeDataView(pageable);
	}
	@Override
	protected void initHeaderController() 
	{
		headerController.setTitleLabelText("QUẢN LÝ LỚP HỌC");
	}
	@Override
	protected SchoolClass getFilterModel()
	{
		String code = codeFilterTextField.getText().trim();
		String name = nameFilterTextField.getText().trim();
		String isDeleted = isDeletedFilterComboBox.getValue();
		String gradeName = gradeFilterComboBox.getValue();
		
		if(code.isEmpty() && 
			name.isEmpty() &&
			gradeName == null &&
			isDeleted == null)
			return null;
		
		SchoolClass model = new SchoolClass();
		
		if(!code.isEmpty())
			model.setCode(code);
		if(!name.isEmpty())
			model.setName(name);
		if(gradeName != null)
		{
			Grade grade = new Grade();
			grade.setName(gradeName);
			model.setGrade(grade);
		}
		if(isDeleted != null)
			model.setIsDeleted(isDeleted.equals("Khóa"));
		
		return model;
	}
	@Override
	public String getDetailLoaderPath() 
	{
		return "../../application/views/schoolclass/detail.fxml";
	}
	@Override
	public String getEditLoaderPath() 
	{
		return "../../application/views/schoolclass/edit.fxml";
	}
	@Override
	protected void exportCSV(String path) 
	{
		CSVUtils.export(path, new SchoolClassConverter(), observableList);
	}
	@Override
	protected void refresh() 
	{
		super.refresh();
		
		codeFilterTextField.setText("");
		nameFilterTextField.setText("");
		gradeFilterComboBox.setValue(null);
	}
	@Override
	protected void changeDataView(Pageable<SchoolClass> pageable) 
	{
		Pageable<SchoolClass> totalPageable = new PageRequest<SchoolClass>(null, null, pageable.getSorter(), pageable.getSearchKey(), pageable.getFilterModel());
		Long count = schoolClassService.count(totalPageable);
		if(count == null)
			return;
		
		int totalPages = (int)Math.ceil((double) count / pageable.getLimit());
		pagination.setPageCount(totalPages);
	
		List<SchoolClass> list = schoolClassService.find(pageable);
		if(list == null)
			return;
		
		observableList.clear();
		observableList.addAll(list);
		
		tableView.setItems(observableList);
		
		listView.setItems(observableList);
	}
}
