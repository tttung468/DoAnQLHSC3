package com.toanhuuvuong.controller.list;

import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import com.toanhuuvuong.constant.SystemConstant;
import com.toanhuuvuong.converter.ScoreConverter;
import com.toanhuuvuong.model.Conduct;
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
import com.toanhuuvuong.service.impl.ObservationService;
import com.toanhuuvuong.service.impl.PerformanceService;
import com.toanhuuvuong.service.impl.SchoolClassService;
import com.toanhuuvuong.service.impl.SchoolYearService;
import com.toanhuuvuong.service.impl.ScoreService;
import com.toanhuuvuong.service.impl.ScoreTypeService;
import com.toanhuuvuong.service.impl.SemesterService;
import com.toanhuuvuong.service.impl.StudentOfClassService;
import com.toanhuuvuong.service.impl.StudentService;
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

public class ResultController extends GenericController<Score> implements Initializable
{
	// ------------------------------------------- Attributes
	@FXML
	private TextField studentCodeTextField;
	@FXML
	private TextField studentNameTextField;
	@FXML
	private ComboBox<String> schoolYearFilterComboBox;
	@FXML
	private ComboBox<String> semesterFilterComboBox;
	@FXML
	private TableColumn<Score, String> subjectNameCol;
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
	@FXML
	private TableColumn<Score, String> subjectAvgCol;
	@FXML
	private TextField conductTextField;
	@FXML
	private TextField avgTextField;
	@FXML
	private TextField performanceTextField;
	@FXML
	private TextField absenceTextField;
	@FXML
	private TextField absenceWithoutLeaveTextField;
	@FXML
	private TextArea observationTextArea;
	
	private ScoreService scoreService = new ScoreService();
	private ScoreTypeService scoreTypeService = new ScoreTypeService();
	private StudentService studentService = new StudentService();
	private StudentOfClassService studentOfClassService = new StudentOfClassService();
	private PerformanceService performanceService = new PerformanceService();
	private ConductService conductService = new ConductService();
	private ObservationService observationService = new ObservationService();
	private SemesterService semesterService = new SemesterService();
	private SchoolYearService schoolYearService = new SchoolYearService();
	private SchoolClassService schoolClassService = new SchoolClassService();
	
	private Student studentChoose;
	private Semester semesterChoose;
	private SchoolYear schoolYearChoose;
	private SchoolClass schoolClassChoose;
	// ------------------------------------------- Methods
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		super.initialize(location, resources);
	}
	private void initStudentCodeTextField()
	{
		studentCodeTextField.setText(getFilterModel().getStudent().getCode());
	}
	private void initStudentNameTextField()
	{
		studentNameTextField.setText(getFilterModel().getStudent().getName());
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
	private void initTableView()
	{
		subjectNameCol.setCellValueFactory(cell ->
		{
			ObjectProperty<String> prop = new SimpleObjectProperty<String>();
			
			String subject = cell.getValue().getSubject().getName();
			prop.set(subject);
			
			return prop;
		});
		mouthTestCol.setCellValueFactory(cell ->
		{
			ObjectProperty<String> prop = new SimpleObjectProperty<String>();
			
			Student student = getFilterModel().getStudent();
			ScoreType scoreType = scoreTypeService.findOne(1L);
			Subject subject = cell.getValue().getSubject();
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
			
			Student student = getFilterModel().getStudent();
			ScoreType scoreType = scoreTypeService.findOne(2L);
			Subject subject = cell.getValue().getSubject();
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
			
			Student student = getFilterModel().getStudent();
			ScoreType scoreType = scoreTypeService.findOne(3L);
			Subject subject = cell.getValue().getSubject();
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
			
			Student student = getFilterModel().getStudent();
			ScoreType scoreType = scoreTypeService.findOne(4L);
			Subject subject = cell.getValue().getSubject();
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
			
			Student student = getFilterModel().getStudent();
			ScoreType scoreType = scoreTypeService.findOne(5L);
			Subject subject = cell.getValue().getSubject();
			Semester semester = getFilterModel().getSemester();
			SchoolYear schoolYear = getFilterModel().getSchoolYear();
			SchoolClass schoolClass = getFilterModel().getSchoolClass();
			
			List<Score> scores = scoreService.findByScoreTypeOfStudent(scoreType, student, semester, schoolYear, schoolClass, subject);
			String finalTest = scoreService.generateScoreFrom(scores);
			prop.set(finalTest);
			
			return prop;
		});
		subjectAvgCol.setCellValueFactory(cell ->
		{
			ObjectProperty<String> prop = new SimpleObjectProperty<String>();
			
			Student student = getFilterModel().getStudent();
			Subject subject = cell.getValue().getSubject();
			Semester semester = getFilterModel().getSemester();
			SchoolYear schoolYear = getFilterModel().getSchoolYear();
			SchoolClass schoolClass = getFilterModel().getSchoolClass();
			
			Float subjectAvg = scoreService.calculateSubjectAvg(student, semester, schoolYear, schoolClass, subject);
			prop.set(String.format("%.2f", subjectAvg));
			
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
		headerController.setTitleLabelText("TRA CỨU KẾT QUẢ HỌC TẬP");
	}
	@Override
	protected Score getFilterModel()
	{
		Score model = new Score();
		
		model.setStudent(studentChoose);
		model.setSemester(semesterChoose);
		model.setSchoolYear(schoolYearChoose);
		model.setSchoolClass(schoolClassChoose);
		
		String schoolYearCode = schoolYearFilterComboBox.getValue();
		String semesterName = semesterFilterComboBox.getValue();
		
		if(schoolYearCode == null &&
			semesterName == null)
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
		
		return model;
	}
	private void initConductTextField()
	{
		Student student = getFilterModel().getStudent();
		Semester semester = getFilterModel().getSemester();
		SchoolYear schoolYear = getFilterModel().getSchoolYear();
		SchoolClass schoolClass = getFilterModel().getSchoolClass();
		
		Conduct conduct = conductService.findByStudent(student, semester, schoolYear, schoolClass);
		
		if(conduct != null)
			conductTextField.setText(conduct.getConductType().getName());
		else
			conductTextField.setText(SystemConstant.UNKNOWN);
	}
	private void initAvgAndPerformanceTextField()
	{
		Student student = getFilterModel().getStudent();
		Semester semester = getFilterModel().getSemester();
		SchoolYear schoolYear = getFilterModel().getSchoolYear();
		SchoolClass schoolClass = getFilterModel().getSchoolClass();
		
		Float avg = scoreService.calculateAvg(student, semester, schoolYear, schoolClass);
		
		avgTextField.setText(String.format("%.2f", avg));
		
		Performance performance = performanceService.generateFromAvg(avg);
		if(performance != null)
			performanceTextField.setText(performance.getName());
		else
			performanceTextField.setText(SystemConstant.UNKNOWN);
	}
	private void initAbsenceAndAbsenceWithoutLeaveTextField()
	{
		Student student = getFilterModel().getStudent();
		Semester semester = getFilterModel().getSemester();
		SchoolYear schoolYear = getFilterModel().getSchoolYear();
		SchoolClass schoolClass = getFilterModel().getSchoolClass();
		
		StudentOfClass studentOfClass = studentOfClassService.findByStudent(student, semester, schoolYear, schoolClass);
		
		if(studentOfClass != null)
		{
			absenceTextField.setText(studentOfClass.getAbsence().toString());
			absenceWithoutLeaveTextField.setText(studentOfClass.getAbsenceWithoutLeave().toString());
		}
		else
		{
			absenceTextField.setText(SystemConstant.UNKNOWN);
			absenceWithoutLeaveTextField.setText(SystemConstant.UNKNOWN);
		}
	}
	private void initObservationTextField()
	{
		Student student = getFilterModel().getStudent();
		SchoolYear schoolYear = getFilterModel().getSchoolYear();
		SchoolClass schoolClass = getFilterModel().getSchoolClass();
		
		Observation observation = observationService.findByStudent(student, schoolYear, schoolClass);
		
		if(observation != null)
			observationTextArea.setText(observation.getContent());
		else
			observationTextArea.setText(SystemConstant.UNKNOWN);
	}
	@Override
	public String getDetailLoaderPath() 
	{
		return "../../application/views/result/detail.fxml";
	}
	@Override
	public String getEditLoaderPath() 
	{
		return "../../application/views/result/edit.fxml";
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
	}
	@Override
	protected void changeDataView(Pageable<Score> pageable) 
	{
		initConductTextField();
		
		initAvgAndPerformanceTextField();
		
		initAbsenceAndAbsenceWithoutLeaveTextField();
		
		initObservationTextField();
		
		pageable = new PageRequest<Score>(null, null, null, pageable.getSearchKey(), pageable.getFilterModel());
		
		pagination.setPageCount(SystemConstant.DEFAULT_PAGE);
	
		List<Score> list = scoreService.find(pageable);
		if(list == null)
			return;
		
		list = scoreService.generateScoreGroupBySubjectFrom(list, getFilterModel());
		
		observableList.clear();
		observableList.addAll(list);
		
		tableView.setItems(observableList);
		
		listView.setItems(observableList);
	}
	public void setChoose(Student student, Semester semester, SchoolYear schoolYear)
	{
		this.studentChoose = student;
		this.semesterChoose = semester;
		this.schoolYearChoose = schoolYear;
		
		StudentOfClass studentOfClass = studentOfClassService.findByStudent(student, semester, schoolYear);
		if(studentOfClass != null)
			this.schoolClassChoose = studentOfClass.getSchoolClass();
		
		initStudentCodeTextField();
		
		initStudentNameTextField();
		
		initSchoolYearFilterComboBox();
		
		initSemesterFilterComboBox();	
		
		initTableView();
		
		initListView();
		
		initPageable();
	}
}
