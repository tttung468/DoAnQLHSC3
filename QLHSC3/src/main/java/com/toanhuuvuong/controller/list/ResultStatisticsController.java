package com.toanhuuvuong.controller.list;

import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import com.toanhuuvuong.constant.SystemConstant;
import com.toanhuuvuong.converter.ScoreConverter;
import com.toanhuuvuong.model.Conduct;
import com.toanhuuvuong.model.Grade;
import com.toanhuuvuong.model.Observation;
import com.toanhuuvuong.model.Performance;
import com.toanhuuvuong.model.SchoolClass;
import com.toanhuuvuong.model.SchoolYear;
import com.toanhuuvuong.model.Score;
import com.toanhuuvuong.model.ScoreType;
import com.toanhuuvuong.model.Semester;
import com.toanhuuvuong.model.Student;
import com.toanhuuvuong.model.StudentOfClass;
import com.toanhuuvuong.model.Subject;
import com.toanhuuvuong.pagination.PageRequest;
import com.toanhuuvuong.pagination.Pageable;
import com.toanhuuvuong.service.impl.ConductService;
import com.toanhuuvuong.service.impl.GradeService;
import com.toanhuuvuong.service.impl.ObservationService;
import com.toanhuuvuong.service.impl.PerformanceService;
import com.toanhuuvuong.service.impl.SchoolClassService;
import com.toanhuuvuong.service.impl.SchoolYearService;
import com.toanhuuvuong.service.impl.ScoreService;
import com.toanhuuvuong.service.impl.ScoreTypeService;
import com.toanhuuvuong.service.impl.SemesterService;
import com.toanhuuvuong.service.impl.StudentOfClassService;
import com.toanhuuvuong.service.impl.StudentService;
import com.toanhuuvuong.service.impl.SubjectService;
import com.toanhuuvuong.utils.CSVUtils;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ResultStatisticsController extends GenericController<Score> implements Initializable
{
	// ------------------------------------------- Attributes
	@FXML
	private ComboBox<String> schoolYearFilterComboBox;
	@FXML
	private ComboBox<String> semesterFilterComboBox;
	@FXML
	private ComboBox<String> gradeFilterComboBox;
	@FXML
	private TableColumn<Score, String> gradeNameCol;
	@FXML
	private TableColumn<Score, String> schoolClassNameCol;
	@FXML
	private TableColumn<Score, Integer> excellentCountCol;
	@FXML
	private TableColumn<Score, Float> excellentPercentCol;
	@FXML
	private TableColumn<Score, Integer> goodCountCol;
	@FXML
	private TableColumn<Score, Float> goodPercentCol;
	@FXML
	private TableColumn<Score, Integer> averageCountCol;
	@FXML
	private TableColumn<Score, Float> averagePercentCol;
	@FXML
	private TableColumn<Score, Integer> belowAverageCountCol;
	@FXML
	private TableColumn<Score, Float> belowAveragePercentCol;
	@FXML
	private TableColumn<Score, Integer> weakCountCol;
	@FXML
	private TableColumn<Score, Float> weakPercentCol;
	
	private ScoreService scoreService = new ScoreService();
	private ScoreTypeService scoreTypeService = new ScoreTypeService();
	private StudentService studentService = new StudentService();
	private StudentOfClassService studentOfClassService = new StudentOfClassService();
	private PerformanceService performanceService = new PerformanceService();
	private ConductService conductService = new ConductService();
	private ObservationService observationService = new ObservationService();
	private SubjectService subjectService = new SubjectService();
	private GradeService gradeService = new GradeService();
	private SemesterService semesterService = new SemesterService();
	private SchoolYearService schoolYearService = new SchoolYearService();
	private SchoolClassService schoolClassService = new SchoolClassService();
	// ------------------------------------------- Methods
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		super.initialize(location, resources);
		
		initSchoolYearFilterComboBox();
		
		initSemesterFilterComboBox();	
		
		initGradeFilterComboBox();
		
		initTableView();
		
		initListView();
		
		initPageable();
	}
	private void initSchoolYearFilterComboBox()
	{
		Collection<SchoolYear> models = schoolYearService.findAll();
		Collection<String> schoolYears = (models == null) ? null
		: models.stream().map(item ->
		{
			return item.getCode();
		}).collect(Collectors.toSet());
		
		if(schoolYears != null)
			schoolYearFilterComboBox.setItems(FXCollections.observableArrayList(schoolYears));
		schoolYearFilterComboBox.setValue(getFilterModel().getSchoolYear().getCode());
	}
	private void initSemesterFilterComboBox()
	{
		Collection<Semester> models = semesterService.findAll();
		Collection<String> semesters = (models == null) ? null
		: models.stream().map(item ->
		{
			return item.getName();
		}).collect(Collectors.toSet());
		
		if(semesters != null)
			semesterFilterComboBox.setItems(FXCollections.observableArrayList(semesters));
		semesterFilterComboBox.setValue(getFilterModel().getSemester().getName());
	}
	private void initGradeFilterComboBox()
	{
		Collection<Grade> models = gradeService.findAll();
		Collection<String> grades = (models == null) ? null
		: models.stream().map(item ->
		{
			return item.getName();
		}).collect(Collectors.toSet());
		
		if(grades != null)
			gradeFilterComboBox.setItems(FXCollections.observableArrayList(grades));
		gradeFilterComboBox.setValue(null);
	}
	private void initTableView()
	{
		gradeNameCol.setCellValueFactory(cell ->
		{
			ObjectProperty<String> prop = new SimpleObjectProperty<String>();
			
			String grade = cell.getValue().getSchoolClass().getGrade().getName();
			prop.set(grade);
			
			return prop;
		});
		schoolClassNameCol.setCellValueFactory(cell ->
		{
			ObjectProperty<String> prop = new SimpleObjectProperty<String>();
			
			String schoolClass = cell.getValue().getSchoolClass().getName();
			prop.set(schoolClass);
			
			return prop;
		});
		excellentCountCol.setCellValueFactory(cell ->
		{
			ObjectProperty<Integer> prop = new SimpleObjectProperty<Integer>();
			
			Semester semester = getFilterModel().getSemester();
			SchoolYear schoolYear = getFilterModel().getSchoolYear();
			SchoolClass schoolClass = cell.getValue().getSchoolClass();
			Performance performance = new Performance();
			performance.setCode("excellent");
			
			Integer count = scoreService.countByPerformanceOfSchoolClass(performance, semester, schoolYear, schoolClass);
			prop.set(count);
			
			return prop;
		});
		excellentPercentCol.setCellValueFactory(cell ->
		{
			ObjectProperty<Float> prop = new SimpleObjectProperty<Float>();
			
			Semester semester = getFilterModel().getSemester();
			SchoolYear schoolYear = getFilterModel().getSchoolYear();
			SchoolClass schoolClass = cell.getValue().getSchoolClass();
			Performance performance = new Performance();
			performance.setCode("excellent");
			
			Integer count = scoreService.countByPerformanceOfSchoolClass(performance, semester, schoolYear, schoolClass);
			Integer sum = studentOfClassService.countByStudentOfSchoolClass(semester, schoolYear, schoolClass);
			Float percent = count.floatValue() / sum.floatValue() * 100;
			prop.set(percent);
			
			return prop;
		});
		goodCountCol.setCellValueFactory(cell ->
		{
			ObjectProperty<Integer> prop = new SimpleObjectProperty<Integer>();
			
			Semester semester = getFilterModel().getSemester();
			SchoolYear schoolYear = getFilterModel().getSchoolYear();
			SchoolClass schoolClass = cell.getValue().getSchoolClass();
			Performance performance = new Performance();
			performance.setCode("good");
			
			Integer count = scoreService.countByPerformanceOfSchoolClass(performance, semester, schoolYear, schoolClass);
			prop.set(count);
			
			return prop;
		});
		goodPercentCol.setCellValueFactory(cell ->
		{
			ObjectProperty<Float> prop = new SimpleObjectProperty<Float>();
			
			Semester semester = getFilterModel().getSemester();
			SchoolYear schoolYear = getFilterModel().getSchoolYear();
			SchoolClass schoolClass = cell.getValue().getSchoolClass();
			Performance performance = new Performance();
			performance.setCode("good");
			
			Integer count = scoreService.countByPerformanceOfSchoolClass(performance, semester, schoolYear, schoolClass);
			Integer sum = studentOfClassService.countByStudentOfSchoolClass(semester, schoolYear, schoolClass);
			Float percent = count.floatValue() / sum.floatValue() * 100;
			prop.set(percent);
			
			return prop;
		});
		averageCountCol.setCellValueFactory(cell ->
		{
			ObjectProperty<Integer> prop = new SimpleObjectProperty<Integer>();
			
			Semester semester = getFilterModel().getSemester();
			SchoolYear schoolYear = getFilterModel().getSchoolYear();
			SchoolClass schoolClass = cell.getValue().getSchoolClass();
			Performance performance = new Performance();
			performance.setCode("average");
			
			Integer count = scoreService.countByPerformanceOfSchoolClass(performance, semester, schoolYear, schoolClass);
			prop.set(count);
			
			return prop;
		});
		averagePercentCol.setCellValueFactory(cell ->
		{
			ObjectProperty<Float> prop = new SimpleObjectProperty<Float>();
			
			Semester semester = getFilterModel().getSemester();
			SchoolYear schoolYear = getFilterModel().getSchoolYear();
			SchoolClass schoolClass = cell.getValue().getSchoolClass();
			Performance performance = new Performance();
			performance.setCode("average");
			
			Integer count = scoreService.countByPerformanceOfSchoolClass(performance, semester, schoolYear, schoolClass);
			Integer sum = studentOfClassService.countByStudentOfSchoolClass(semester, schoolYear, schoolClass);
			Float percent = count.floatValue() / sum.floatValue() * 100;
			prop.set(percent);
			
			return prop;
		});
		belowAverageCountCol.setCellValueFactory(cell ->
		{
			ObjectProperty<Integer> prop = new SimpleObjectProperty<Integer>();
			
			Semester semester = getFilterModel().getSemester();
			SchoolYear schoolYear = getFilterModel().getSchoolYear();
			SchoolClass schoolClass = cell.getValue().getSchoolClass();
			Performance performance = new Performance();
			performance.setCode("below-average");
			
			Integer count = scoreService.countByPerformanceOfSchoolClass(performance, semester, schoolYear, schoolClass);
			prop.set(count);
			
			return prop;
		});
		belowAveragePercentCol.setCellValueFactory(cell ->
		{
			ObjectProperty<Float> prop = new SimpleObjectProperty<Float>();
			
			Semester semester = getFilterModel().getSemester();
			SchoolYear schoolYear = getFilterModel().getSchoolYear();
			SchoolClass schoolClass = cell.getValue().getSchoolClass();
			Performance performance = new Performance();
			performance.setCode("below-average");
			
			Integer count = scoreService.countByPerformanceOfSchoolClass(performance, semester, schoolYear, schoolClass);
			Integer sum = studentOfClassService.countByStudentOfSchoolClass(semester, schoolYear, schoolClass);
			Float percent = count.floatValue() / sum.floatValue() * 100;
			prop.set(percent);
			
			return prop;
		});
		weakCountCol.setCellValueFactory(cell ->
		{
			ObjectProperty<Integer> prop = new SimpleObjectProperty<Integer>();
			
			Semester semester = getFilterModel().getSemester();
			SchoolYear schoolYear = getFilterModel().getSchoolYear();
			SchoolClass schoolClass = cell.getValue().getSchoolClass();
			Performance performance = new Performance();
			performance.setCode("weak");
			
			Integer count = scoreService.countByPerformanceOfSchoolClass(performance, semester, schoolYear, schoolClass);
			prop.set(count);
			
			return prop;
		});
		weakPercentCol.setCellValueFactory(cell ->
		{
			ObjectProperty<Float> prop = new SimpleObjectProperty<Float>();
			
			Semester semester = getFilterModel().getSemester();
			SchoolYear schoolYear = getFilterModel().getSchoolYear();
			SchoolClass schoolClass = cell.getValue().getSchoolClass();
			Performance performance = new Performance();
			performance.setCode("weak");
			
			Integer count = scoreService.countByPerformanceOfSchoolClass(performance, semester, schoolYear, schoolClass);
			Integer sum = studentOfClassService.countByStudentOfSchoolClass(semester, schoolYear, schoolClass);
			Float percent = count.floatValue() / sum.floatValue() * 100;
			prop.set(percent);
			
			return prop;
		});
		isDeletedCol.setVisible(false);
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
		headerController.setTitleLabelText("THỐNG KÊ KẾT QUẢ HỌC TẬP");
	}
	@Override
	protected Score getFilterModel()
	{
		Score model = new Score();
		
		model.setSemester(semesterService.findOne(1L));
		model.setSchoolYear(schoolYearService.findOne(3L));
		
		String schoolYearCode = schoolYearFilterComboBox.getValue();
		String semesterName = semesterFilterComboBox.getValue();
		String gradeName = gradeFilterComboBox.getValue();
		
		if(schoolYearCode == null &&
			semesterName == null &&
			gradeName == null)
			return model;
		
		if(schoolYearCode != null)
		{
			SchoolYear schoolYear = new SchoolYear();
			schoolYear.setCode(schoolYearCode);
			model.setSchoolYear(schoolYear);
		}
		if(semesterName != null)
		{
			Semester semester = new Semester();
			semester.setName(semesterName);
			model.setSemester(semester);
		}
		if(gradeName != null)
		{
			Grade grade = new Grade();
			grade.setName(gradeName);
			SchoolClass schoolClass = new SchoolClass();
			schoolClass.setGrade(grade);
			model.setSchoolClass(schoolClass);
		}
		
		return model;
	}
	@Override
	public String getDetailLoaderPath() 
	{
		return "../../application/views/resultstatistics/detail.fxml";
	}
	@Override
	public String getEditLoaderPath() 
	{
		return "../../application/views/resultstatistics/edit.fxml";
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

		schoolYearFilterComboBox.setValue(null);
		semesterFilterComboBox.setValue(null);
		gradeFilterComboBox.setValue(null);
	}
	@Override
	protected void changeDataView(Pageable<Score> pageable) 
	{
		pageable = new PageRequest<Score>(null, null, null, pageable.getSearchKey(), pageable.getFilterModel());
		
		pagination.setPageCount(SystemConstant.DEFAULT_PAGE);
	
		List<Score> list = scoreService.find(pageable);
		if(list == null)
			return;
		
		list = scoreService.generateScoreGroupBySchoolClassFrom(list, getFilterModel());
		
		observableList.clear();
		observableList.addAll(list);
		
		tableView.setItems(observableList);
		
		listView.setItems(observableList);
	}
}
