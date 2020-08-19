package com.toanhuuvuong.controller.list;

import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import com.toanhuuvuong.constant.SystemConstant;
import com.toanhuuvuong.controller.edit.FormTeacherAssignmentController;
import com.toanhuuvuong.converter.SchoolClassConverter;
import com.toanhuuvuong.model.FormTeacherAssignment;
import com.toanhuuvuong.model.Grade;
import com.toanhuuvuong.model.SchoolClass;
import com.toanhuuvuong.model.SchoolYear;
import com.toanhuuvuong.pagination.PageRequest;
import com.toanhuuvuong.pagination.Pageable;
import com.toanhuuvuong.service.impl.FormTeacherAssignmentService;
import com.toanhuuvuong.service.impl.GradeService;
import com.toanhuuvuong.service.impl.SchoolClassService;
import com.toanhuuvuong.service.impl.SchoolYearService;
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
import javafx.stage.Stage;

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
	private Button assignButton; 
	@FXML
	private TableColumn<SchoolClass, String> codeCol;
	@FXML
	private TableColumn<SchoolClass, String> nameCol;
	@FXML
	private TableColumn<SchoolClass, String> gradeNameCol;
	@FXML
	private TableColumn<SchoolClass, String> formTeacherNameCol;
	
	private SchoolClassService schoolClassService = new SchoolClassService();
	private SchoolYearService schoolYearService = new SchoolYearService();
	private FormTeacherAssignmentService formTeacherAssignmentService = new FormTeacherAssignmentService();
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
		tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> 
		{
		    if (newValue != null)
		    {
		    	if(tableView.getSelectionModel().getSelectedItems().size() == 1)	
		    		assignButton.setVisible(true);
		    	else
		    		assignButton.setVisible(false);
		    }
		    else
		    	assignButton.setVisible(false);
		});
		
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
		formTeacherNameCol.setCellValueFactory(cell ->
		{
			ObjectProperty<String> prop = new SimpleObjectProperty<String>();
			
			SchoolClass schoolClass = cell.getValue();
			SchoolYear schoolYear = schoolYearService.findOne(3L);
			
			FormTeacherAssignment formTeacherAssignment = formTeacherAssignmentService.findBySchoolClass(schoolYear, schoolClass);
			String formTeacherName = (formTeacherAssignment != null && formTeacherAssignment.getClass() != null)
					? formTeacherAssignment.getTeacher().getName() : SystemConstant.UNKNOWN;
			prop.set(formTeacherName);
			
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
	@FXML
	public void assignButtonOnAction(ActionEvent event)
	{
		Integer selectedIndex = tableView.getSelectionModel().getSelectedIndex();
		SchoolClass selectedItem = observableList.get(selectedIndex);
		
		if(selectedItem == null)
			return;
		
		URL url = getClass().getResource("../../application/views/schoolclass/formteacherassignment.fxml");
		Stage stage = new Stage();
		String title = "Phân công giáo viên chủ nhiệm";
		FXMLLoader loader = SceneUtils.changeSceneWithoutLostFocus(url, stage, title, null, null);

		FormTeacherAssignmentController controller = loader.getController();
		controller.editDelegate = this;
		
		SchoolClass schoolClass = selectedItem;
		SchoolYear schoolYear = schoolYearService.findCurrentSchoolYear();
		
		FormTeacherAssignment formTeacherAssignment = formTeacherAssignmentService.findBySchoolClass(schoolYear, schoolClass);
		if(formTeacherAssignment != null)
			controller.setItem(selectedIndex, formTeacherAssignment);
		else
		{
			formTeacherAssignment = new FormTeacherAssignment();
			formTeacherAssignment.setSchoolYear(schoolYear);
			formTeacherAssignment.setSchoolClass(schoolClass);
			formTeacherAssignment.setCapacity(40);
			
			controller.setItem(null, formTeacherAssignment);
		}
	}
}
