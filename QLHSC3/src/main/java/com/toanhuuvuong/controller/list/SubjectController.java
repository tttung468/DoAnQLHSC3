package com.toanhuuvuong.controller.list;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.toanhuuvuong.constant.SystemConstant;
import com.toanhuuvuong.converter.SubjectConverter;
import com.toanhuuvuong.model.Subject;
import com.toanhuuvuong.pagination.PageRequest;
import com.toanhuuvuong.pagination.Pageable;
import com.toanhuuvuong.service.impl.SubjectService;
import com.toanhuuvuong.utils.CSVUtils;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class SubjectController extends GenericController<Subject> implements Initializable
{
	// ------------------------------------------- Attributes
	@FXML
	private TextField codeFilterTextField;
	@FXML
	private TextField nameFilterTextField;
	@FXML
	private TextField classHoursFilterTextField;
	@FXML
	private TextField factorFilterTextField;
	@FXML
	private TableColumn<Subject, String> codeCol;
	@FXML
	private TableColumn<Subject, String> nameCol;
	@FXML
	private TableColumn<Subject, Integer> classHoursCol;
	@FXML
	private TableColumn<Subject, Integer> factorCol;
	
	private SubjectService subjectService = new SubjectService();
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
		codeCol.setCellValueFactory(new PropertyValueFactory<Subject, String>("code"));
		nameCol.setCellValueFactory(new PropertyValueFactory<Subject, String>("name"));
		classHoursCol.setCellValueFactory(new PropertyValueFactory<Subject, Integer>("classHours"));
		factorCol.setCellValueFactory(new PropertyValueFactory<Subject, Integer>("factor"));
	}
	private void initListView()
	{
		//listView.setCellFactory(listView -> new AccountListViewCell());
	}
	private void initPageable()
	{
		pageable = new PageRequest<Subject>(SystemConstant.DEFAULT_PAGE, SystemConstant.DEFAULT_PERPAGE, null, null, null);
		
		changeDataView(pageable);
	}
	@Override
	protected void initHeaderController() 
	{
		headerController.setTitleLabelText("QUẢN LÝ MÔN HỌC");
	}
	@Override
	protected Subject getFilterModel()
	{
		String code = codeFilterTextField.getText().trim();
		String name = nameFilterTextField.getText().trim();
		String classHours = classHoursFilterTextField.getText().trim();;
		String factor = factorFilterTextField.getText().trim();
		String isDeleted = isDeletedFilterComboBox.getValue();
		
		if(code.isEmpty() && 
			name.isEmpty() &&
			classHours.isEmpty() &&
			factor.isEmpty() &&
			isDeleted == null)
			return null;
		
		Subject model = new Subject();
		
		if(!code.isEmpty())
			model.setCode(code);
		if(!name.isEmpty())
			model.setName(name);
		if(!classHours.isEmpty())
			model.setClassHours(Integer.parseInt(classHours));
		if(!factor.isEmpty())
			model.setFactor(Integer.parseInt(factor));
		if(isDeleted != null)
			model.setIsDeleted(isDeleted.equals("Khóa"));
		
		return model;
	}
	@Override
	public String getDetailLoaderPath() 
	{
		return "../../application/views/subject/detail.fxml";
	}
	@Override
	public String getEditLoaderPath() 
	{
		return "../../application/views/subject/edit.fxml";
	}
	@Override
	protected void exportCSV(String path) 
	{
		CSVUtils.export(path, new SubjectConverter(), observableList);
	}
	@Override
	protected void refresh() 
	{
		super.refresh();
		
		codeFilterTextField.setText("");
		nameFilterTextField.setText("");
		classHoursFilterTextField.setText("");
		factorFilterTextField.setText("");
	}
	@Override
	protected void changeDataView(Pageable<Subject> pageable) 
	{
		Pageable<Subject> totalPageable = new PageRequest<Subject>(null, null, pageable.getSorter(), pageable.getSearchKey(), pageable.getFilterModel());
		Long count = subjectService.count(totalPageable);
		if(count == null)
			return;
		
		int totalPages = (int)Math.ceil((double) count / pageable.getLimit());
		pagination.setPageCount(totalPages);
	
		List<Subject> list = subjectService.find(pageable);
		if(list == null)
			return;
		
		observableList.clear();
		observableList.addAll(list);
		
		tableView.setItems(observableList);
		
		listView.setItems(observableList);
	}
}
