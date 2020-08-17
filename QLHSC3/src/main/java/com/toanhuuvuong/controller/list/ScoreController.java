package com.toanhuuvuong.controller.list;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.toanhuuvuong.constant.SystemConstant;
import com.toanhuuvuong.converter.ScoreConverter;
import com.toanhuuvuong.model.SchoolClass;
import com.toanhuuvuong.model.SchoolYear;
import com.toanhuuvuong.model.Score;
import com.toanhuuvuong.model.ScoreType;
import com.toanhuuvuong.model.Semester;
import com.toanhuuvuong.model.Student;
import com.toanhuuvuong.model.Subject;
import com.toanhuuvuong.pagination.PageRequest;
import com.toanhuuvuong.pagination.Pageable;
import com.toanhuuvuong.service.impl.SchoolClassService;
import com.toanhuuvuong.service.impl.SchoolYearService;
import com.toanhuuvuong.service.impl.ScoreService;
import com.toanhuuvuong.service.impl.ScoreTypeService;
import com.toanhuuvuong.service.impl.SemesterService;
import com.toanhuuvuong.service.impl.SubjectService;
import com.toanhuuvuong.utils.CSVUtils;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ScoreController extends GenericController<Score> implements Initializable
{
	// ------------------------------------------- Attributes
	@FXML
	private TextField codeFilterTextField;
	@FXML
	private TextField nameFilterTextField;
	@FXML
	private ComboBox<String> genderFilterComboBox;
	@FXML
	private ComboBox<String> statusFilterComboBox;
	@FXML
	private TableColumn<Score, ImageView> avatarPathCol;
	@FXML
	private TableColumn<Score, String> codeCol;
	@FXML
	private TableColumn<Score, String> nameCol;
	@FXML
	private TableColumn<Score, String> genderCol;
	@FXML
	private TableColumn<Score, String> statusCol;
	@FXML
	private TableColumn<Score, String> mouthTestCol;
	@FXML
	private TableColumn<Score, String> minuteTestCol;
	@FXML
	private TableColumn<Score, String> hourTestCol;
	@FXML
	private TableColumn<Score, String> semiTestCol;
	@FXML
	private TableColumn<Score, String> finalTestCol;
	
	private ScoreService scoreService = new ScoreService();
	private ScoreTypeService scoreTypeService = new ScoreTypeService();
	private SubjectService subjectService = new SubjectService();
	private SemesterService semesterService = new SemesterService();
	private SchoolYearService schoolYearService = new SchoolYearService();
	private SchoolClassService schoolClassService = new SchoolClassService();
	// ------------------------------------------- Methods
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		super.initialize(location, resources);
		
		initGenderFilterComboBox();
		
		initStatusFilterComboBox();	
		
		initTableView();
		
		initListView();
		
		initPageable();
	}
	private void initGenderFilterComboBox()
	{
		genderFilterComboBox.setItems(FXCollections.observableArrayList("Nam", "Nữ", "Khác"));
		genderFilterComboBox.setValue(null);
	}
	private void initStatusFilterComboBox()
	{
		statusFilterComboBox.setItems(FXCollections.observableArrayList("Chuyển đến", "Chuyển đi", "Thôi học", "Đang học"));
		statusFilterComboBox.setValue(null);
	}
	private void initTableView()
	{
		avatarPathCol.setCellValueFactory(cell ->
		{
			ObjectProperty<ImageView> prop = new SimpleObjectProperty<ImageView>();
			
			String avatarpath = cell.getValue().getStudent().getAvatarpath();
			URL url = avatarpath != null ? getClass().getResource(avatarpath) : null;
			String path = url != null ? url.toString() : null;
			Image image = path != null ? new Image(path) : null;
			
			ImageView imageView = new ImageView(image);
			imageView.setFitWidth(180);
			imageView.setFitHeight(150);
			prop.set(imageView);
			
			return prop;
		});
		codeCol.setCellValueFactory(cell ->
		{
			ObjectProperty<String> prop = new SimpleObjectProperty<String>();
			
			String code = cell.getValue().getStudent().getCode();
			
			prop.set(code);
			
			return prop;
		});
		nameCol.setCellValueFactory(cell ->
		{
			ObjectProperty<String> prop = new SimpleObjectProperty<String>();
			
			String name = cell.getValue().getStudent().getName();
			
			prop.set(name);
			
			return prop;
		});
		genderCol.setCellValueFactory(cell ->
		{
			ObjectProperty<String> prop = new SimpleObjectProperty<String>();
			
			String gender = cell.getValue().getStudent().getGender();
			
			prop.set(gender);
			
			return prop;
		});
		statusCol.setCellValueFactory(cell ->
		{
			ObjectProperty<String> prop = new SimpleObjectProperty<String>();
			
			String status = cell.getValue().getStudent().getStatus();
			
			prop.set(status);
			
			return prop;
		});
		mouthTestCol.setCellValueFactory(cell ->
		{
			ObjectProperty<String> prop = new SimpleObjectProperty<String>();
			
			Student student = cell.getValue().getStudent();
			ScoreType scoreType = scoreTypeService.findOne(1L);
			Subject subject = getFilterModel().getSubject();
			Semester semester = getFilterModel().getSemester();
			SchoolYear schoolYear = getFilterModel().getSchoolYear();
			SchoolClass schoolClass = getFilterModel().getSchoolClass();
			
			List<Score> scores = scoreService.findByScoreTypeOfStudent(scoreType, student, semester, schoolYear, schoolClass, subject);
			String mouthTest = scoreService.generateScoreFrom(scores);
			prop.set(mouthTest);
			
			return prop;
		});
		minuteTestCol.setCellValueFactory(cell ->
		{
			ObjectProperty<String> prop = new SimpleObjectProperty<String>();
			
			Student student = cell.getValue().getStudent();
			ScoreType scoreType = scoreTypeService.findOne(2L);
			Subject subject = getFilterModel().getSubject();
			Semester semester = getFilterModel().getSemester();
			SchoolYear schoolYear = getFilterModel().getSchoolYear();
			SchoolClass schoolClass = getFilterModel().getSchoolClass();
			
			List<Score> scores = scoreService.findByScoreTypeOfStudent(scoreType, student, semester, schoolYear, schoolClass, subject);
			String minuteTest = scoreService.generateScoreFrom(scores);
			prop.set(minuteTest);
			
			return prop;
		});
		hourTestCol.setCellValueFactory(cell ->
		{
			ObjectProperty<String> prop = new SimpleObjectProperty<String>();
			
			Student student = cell.getValue().getStudent();
			ScoreType scoreType = scoreTypeService.findOne(3L);
			Subject subject = getFilterModel().getSubject();
			Semester semester = getFilterModel().getSemester();
			SchoolYear schoolYear = getFilterModel().getSchoolYear();
			SchoolClass schoolClass = getFilterModel().getSchoolClass();
			
			List<Score> scores = scoreService.findByScoreTypeOfStudent(scoreType, student, semester, schoolYear, schoolClass, subject);
			String hourTest = scoreService.generateScoreFrom(scores);
			prop.set(hourTest);
			
			return prop;
		});
		semiTestCol.setCellValueFactory(cell ->
		{
			ObjectProperty<String> prop = new SimpleObjectProperty<String>();
			
			Student student = cell.getValue().getStudent();
			ScoreType scoreType = scoreTypeService.findOne(4L);
			Subject subject = getFilterModel().getSubject();
			Semester semester = getFilterModel().getSemester();
			SchoolYear schoolYear = getFilterModel().getSchoolYear();
			SchoolClass schoolClass = getFilterModel().getSchoolClass();
			
			List<Score> scores = scoreService.findByScoreTypeOfStudent(scoreType, student, semester, schoolYear, schoolClass, subject);
			String semiTest = scoreService.generateScoreFrom(scores);
			prop.set(semiTest);
			
			return prop;
		});
		finalTestCol.setCellValueFactory(cell ->
		{
			ObjectProperty<String> prop = new SimpleObjectProperty<String>();
			
			Student student = cell.getValue().getStudent();
			ScoreType scoreType = scoreTypeService.findOne(5L);
			Subject subject = getFilterModel().getSubject();
			Semester semester = getFilterModel().getSemester();
			SchoolYear schoolYear = getFilterModel().getSchoolYear();
			SchoolClass schoolClass = getFilterModel().getSchoolClass();
			
			List<Score> scores = scoreService.findByScoreTypeOfStudent(scoreType, student, semester, schoolYear, schoolClass, subject);
			String finalTest = scoreService.generateScoreFrom(scores);
			prop.set(finalTest);
			
			return prop;
		});
		
	}
	private void initListView()
	{
		//listView.setCellFactory(listView -> new AccountListViewCell());
	}
	private void initPageable()
	{
		pageable = new PageRequest<Score>(null, null, null, null, getFilterModel());
		
		changeDataView(pageable);
	}
	@Override
	protected void initHeaderController() 
	{
		headerController.setTitleLabelText("BẢNG ĐIỂM");
	}
	@Override
	protected Score getFilterModel()
	{
		Score model = new Score();
		
		model.setSubject(subjectService.findOne(1L));
		model.setSemester(semesterService.findOne(1L));
		model.setSchoolYear(schoolYearService.findOne(3L));
		model.setSchoolClass(schoolClassService.findOne(1L));
		
		String code = codeFilterTextField.getText().trim();
		String name = nameFilterTextField.getText().trim();
		String gender = genderFilterComboBox.getValue();
		String status = statusFilterComboBox.getValue();
		String isDeleted = isDeletedFilterComboBox.getValue();
		
		if(code.isEmpty() && 
			name.isEmpty() &&
			gender == null &&
			status == null &&
			isDeleted == null)
			return model;
		
		Student student = new Student();
		if(!code.isEmpty())
			student.setCode(code);
		if(!name.isEmpty())
			student.setName(name);
		if(gender != null)
			student.setGender(gender);
		if(status != null)
			student.setStatus(status);
		if(isDeleted != null)
			model.setIsDeleted(isDeleted.equals("Khóa"));
		
		model.setStudent(student);
		
		return model;
	}
	@Override
	public String getDetailLoaderPath() 
	{
		return "../../application/views/score/detail.fxml";
	}
	@Override
	public String getEditLoaderPath() 
	{
		return "../../application/views/score/edit.fxml";
	}
	@Override
	protected void exportCSV(String path) 
	{
		CSVUtils.export(path, new ScoreConverter(), observableList);
	}
	@Override
	protected void refresh() 
	{
		super.refresh();
		
		codeFilterTextField.setText("");
		nameFilterTextField.setText("");
		genderFilterComboBox.setValue(null);
		statusFilterComboBox.setValue(null);
	}
	@Override
	protected void changeDataView(Pageable<Score> pageable) 
	{
		pageable = new PageRequest<Score>(null, null, null, pageable.getSearchKey(), pageable.getFilterModel());
		
		pagination.setPageCount(SystemConstant.DEFAULT_PAGE);
	
		List<Score> list = scoreService.find(pageable);
		if(list == null)
			return;
		
		list = scoreService.generateScoreGroupByStudentFrom(list, getFilterModel());
		
		observableList.clear();
		observableList.addAll(list);
		
		tableView.setItems(observableList);
		
		listView.setItems(observableList);
	}
}
