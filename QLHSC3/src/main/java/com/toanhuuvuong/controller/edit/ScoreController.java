package com.toanhuuvuong.controller.edit;

import java.io.File;
import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import com.toanhuuvuong.model.Account;
import com.toanhuuvuong.model.SchoolClass;
import com.toanhuuvuong.model.SchoolYear;
import com.toanhuuvuong.model.Score;
import com.toanhuuvuong.model.ScoreType;
import com.toanhuuvuong.model.Semester;
import com.toanhuuvuong.model.Student;
import com.toanhuuvuong.model.Subject;
import com.toanhuuvuong.service.impl.NationalityService;
import com.toanhuuvuong.service.impl.ReligionService;
import com.toanhuuvuong.service.impl.SchoolClassService;
import com.toanhuuvuong.service.impl.SchoolYearService;
import com.toanhuuvuong.service.impl.ScoreService;
import com.toanhuuvuong.service.impl.ScoreTypeService;
import com.toanhuuvuong.service.impl.SemesterService;
import com.toanhuuvuong.service.impl.StudentService;
import com.toanhuuvuong.service.impl.SubjectService;
import com.toanhuuvuong.utils.SceneUtils;
import com.toanhuuvuong.utils.SessionUtils;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ScoreController extends GenericController<Score> implements Initializable
{	
	// ------------------------------------------- Attributes
	@FXML
	private Circle avatarCircle;
	@FXML
	private TextField codeTextField;
	@FXML
	private TextField nameTextField;
	@FXML
	private ComboBox<Score> mouthTestComboBox;
	@FXML
	private ComboBox<Float> minuteTestComboBox;
	@FXML
	private ComboBox<Float> hourTestComboBox;
	@FXML
	private ComboBox<Float> semiTestComboBox;
	@FXML
	private ComboBox<Float> finalTestComboBox;
	@FXML
	private TextField avgSubjectTextField;
	
	private StudentService studentService = new StudentService();
	private ScoreService scoreService = new ScoreService();
	private ReligionService religionService = new ReligionService();
	private NationalityService nationalityService = new NationalityService();
	private ScoreTypeService scoreTypeService = new ScoreTypeService();
	private SubjectService subjectService = new SubjectService();
	private SemesterService semesterService = new SemesterService();
	private SchoolYearService schoolYearService = new SchoolYearService();
	private SchoolClassService schoolClassService = new SchoolClassService();
	
	private File avatarFile;
	private Account accountModel = (Account)SessionUtils.getInstance().getValue("accountModel");
	// ------------------------------------------- Methods
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{		
		super.initialize(location, resources);
	}
	private void initCodeAndNameTextField()
	{		
		Student student = model.getStudent();
		
		if(student != null)
		{
			codeTextField.setText(student.getCode());
			nameTextField.setText(student.getName());
		}
	}
	private void initMouthTestComboBox()
	{		
		Student student = model.getStudent();
		ScoreType scoreType = scoreTypeService.findOne(1L);
		Subject subject = model.getSubject();
		Semester semester = model.getSemester();
		SchoolYear schoolYear = model.getSchoolYear();
		SchoolClass schoolClass = model.getSchoolClass();
		
		Collection<Score> models = scoreService.findByScoreTypeOfStudent(scoreType, student, semester, schoolYear, schoolClass, subject);
		
		mouthTestComboBox.setItems(FXCollections.observableArrayList(models));
		mouthTestComboBox.getSelectionModel().select(0);
		
		mouthTestComboBox.valueProperty().addListener((observable, oldValue, newValue) ->
		{
			mouthTestComboBox.setPromptText(newValue.getValue().toString());
		});
		
		mouthTestComboBox.setCellFactory(new Callback<ListView<Score>, ListCell<Score>>() 
		{
            @Override
            public ListCell<Score> call(ListView<Score> param) 
            {
                final ListCell<Score> cell = new ListCell<Score>() 
                {
                    @Override
                    protected void updateItem(Score item, boolean empty) 
                    {
                        super.updateItem(item, empty);
                        if (item != null && !empty)
                            setText(item.getValue().toString());
                        else 
                            setText(null);
                    }
                };
                return cell;
            }
        });
	}
	private void initMinuteTestComboBox()
	{		
		Student student = model.getStudent();
		ScoreType scoreType = scoreTypeService.findOne(2L);
		Subject subject = model.getSubject();
		Semester semester = model.getSemester();
		SchoolYear schoolYear = model.getSchoolYear();
		SchoolClass schoolClass = model.getSchoolClass();
		
		Collection<Score> models = scoreService.findByScoreTypeOfStudent(scoreType, student, semester, schoolYear, schoolClass, subject);
		Collection<Float> values = (models == null) ? null
		: models.stream().map(item ->
		{
			return item.getValue();
		}).collect(Collectors.toList());
		
		minuteTestComboBox.setItems(FXCollections.observableArrayList(values));
		minuteTestComboBox.setValue(minuteTestComboBox.getItems().get(0));
	}
	private void initHourTestComboBox()
	{		
		Student student = model.getStudent();
		ScoreType scoreType = scoreTypeService.findOne(3L);
		Subject subject = model.getSubject();
		Semester semester = model.getSemester();
		SchoolYear schoolYear = model.getSchoolYear();
		SchoolClass schoolClass = model.getSchoolClass();
		
		Collection<Score> models = scoreService.findByScoreTypeOfStudent(scoreType, student, semester, schoolYear, schoolClass, subject);
		Collection<Float> values = (models == null) ? null
		: models.stream().map(item ->
		{
			return item.getValue();
		}).collect(Collectors.toList());
		
		hourTestComboBox.setItems(FXCollections.observableArrayList(values));
		hourTestComboBox.setValue(hourTestComboBox.getItems().get(0));
	}
	private void initSemiTestComboBox()
	{		
		Student student = model.getStudent();
		ScoreType scoreType = scoreTypeService.findOne(4L);
		Subject subject = model.getSubject();
		Semester semester = model.getSemester();
		SchoolYear schoolYear = model.getSchoolYear();
		SchoolClass schoolClass = model.getSchoolClass();
		
		Collection<Score> models = scoreService.findByScoreTypeOfStudent(scoreType, student, semester, schoolYear, schoolClass, subject);
		Collection<Float> values = (models == null) ? null
		: models.stream().map(item ->
		{
			return item.getValue();
		}).collect(Collectors.toList());
		
		semiTestComboBox.setItems(FXCollections.observableArrayList(values));
		semiTestComboBox.setValue(semiTestComboBox.getItems().get(0));
	}
	private void initFinalTestComboBox()
	{		
		Student student = model.getStudent();
		ScoreType scoreType = scoreTypeService.findOne(4L);
		Subject subject = model.getSubject();
		Semester semester = model.getSemester();
		SchoolYear schoolYear = model.getSchoolYear();
		SchoolClass schoolClass = model.getSchoolClass();
		
		Collection<Score> models = scoreService.findByScoreTypeOfStudent(scoreType, student, semester, schoolYear, schoolClass, subject);
		Collection<Float> values = (models == null) ? null
		: models.stream().map(item ->
		{
			return item.getValue();
		}).collect(Collectors.toList());
		
		finalTestComboBox.setItems(FXCollections.observableArrayList(values));
		finalTestComboBox.setValue(finalTestComboBox.getItems().get(0));
	}
	private void initAvgSubjectTextField()
	{		
		Student student = model.getStudent();
		Subject subject = model.getSubject();
		Semester semester = model.getSemester();
		SchoolYear schoolYear = model.getSchoolYear();
		SchoolClass schoolClass = model.getSchoolClass();
		
		Float avgSubject = scoreService.calculateSubjectAvg(student, semester, schoolYear, schoolClass, subject);

		avgSubjectTextField.setText(String.format("%.2f", avgSubject));
	}
	@Override
	protected void applyUI() 
	{
		super.applyUI();
		
		titleLabel.setText("SỬA ĐIỂM");
		
		initCodeAndNameTextField();
		
		initMouthTestComboBox();
		
		initMinuteTestComboBox();
		
		initHourTestComboBox();
		
		initSemiTestComboBox();
		
		initFinalTestComboBox();
		
		initAvgSubjectTextField();
	}
	@Override
	public void fillForm() 
	{		
		
	}
	@Override
	public void mappingForm() 
	{	
		
	}
	@Override
	protected boolean validateForm() 
	{
		return true;
	}
	@Override
	public boolean delete()
	{
		return true;
	}
	@Override
	public boolean update()
	{
		return true;
	}
	@Override
	public boolean insert()
	{	
		return true;
	}
	public void refresh()
	{
		initMouthTestComboBox();
		
		initMinuteTestComboBox();
		
		initHourTestComboBox();
		
		initSemiTestComboBox();
		
		initFinalTestComboBox();
		
		initAvgSubjectTextField();
	}
	@FXML
	public void insertMouthScoreButtonOnAction(ActionEvent event)
	{
		URL url = getClass().getResource("../../application/views/score/editdetail.fxml");
		Stage stage = new Stage();
		String title = "Thêm điểm miệng";
		
		FXMLLoader loader = SceneUtils.changeSceneWithoutLostFocus(url, stage, title, null, null);
		ScoreDetailController controller = loader.getController();
		
		controller.editDelegate = this;
		
		Score score = new Score();
		Student student = model.getStudent();
		ScoreType scoreType = scoreTypeService.findOne(1L);
		Subject subject = model.getSubject();
		Semester semester = model.getSemester();
		SchoolYear schoolYear = model.getSchoolYear();
		SchoolClass schoolClass = model.getSchoolClass();
		score.setStudent(student);
		score.setSubject(subject);
		score.setScoreType(scoreType);
		score.setSemester(semester);
		score.setSchoolYear(schoolYear);
		score.setSchoolClass(schoolClass);
		
		controller.setItem(null, score);
	}
	@FXML
	public void updateMouthScoreButtonOnAction(ActionEvent event)
	{
		Integer selectedIndex = mouthTestComboBox.getSelectionModel().getSelectedIndex();
		Score selectedItem = mouthTestComboBox.getItems().get(selectedIndex);
		
		if(selectedItem == null)
			return;
		
		URL url = getClass().getResource("../../application/views/score/editdetail.fxml");
		Stage stage = new Stage();
		String title = "Sửa/xóa điểm miệng";
		
		FXMLLoader loader = SceneUtils.changeSceneWithoutLostFocus(url, stage, title, null, null);

		ScoreDetailController controller = loader.getController();
		controller.editDelegate = this;
		controller.setItem(selectedIndex, selectedItem);
	}
	@FXML
	public void insertMinuteScoreButtonOnAction(ActionEvent event)
	{
		URL url = getClass().getResource("../../application/views/score/editdetail.fxml");
		Stage stage = new Stage();
		String title = "Thêm điểm miệng";
		
		FXMLLoader loader = SceneUtils.changeSceneWithoutLostFocus(url, stage, title, null, null);
		ScoreDetailController controller = loader.getController();
		
		Score score = new Score();
		Student student = model.getStudent();
		ScoreType scoreType = scoreTypeService.findOne(1L);
		Subject subject = model.getSubject();
		Semester semester = model.getSemester();
		SchoolYear schoolYear = model.getSchoolYear();
		SchoolClass schoolClass = model.getSchoolClass();
		score.setStudent(student);
		score.setSubject(subject);
		score.setScoreType(scoreType);
		score.setSemester(semester);
		score.setSchoolYear(schoolYear);
		score.setSchoolClass(schoolClass);
		
		controller.setItem(null, score);
	}
	@FXML
	public void updateMinuteScoreButtonOnAction(ActionEvent event)
	{
		Integer selectedIndex = mouthTestComboBox.getSelectionModel().getSelectedIndex();
		Score selectedItem = mouthTestComboBox.getItems().get(selectedIndex);
		
		if(selectedItem == null)
			return;
		
		URL url = getClass().getResource("../../application/views/score/editdetail.fxml");
		Stage stage = new Stage();
		String title = "Sửa/xóa điểm miệng";
		
		FXMLLoader loader = SceneUtils.changeSceneWithoutLostFocus(url, stage, title, null, null);

		ScoreDetailController controller = loader.getController();

		controller.setItem(selectedIndex, selectedItem);
	}
	@FXML
	public void insertHourScoreButtonOnAction(ActionEvent event)
	{
		URL url = getClass().getResource("../../application/views/score/editdetail.fxml");
		Stage stage = new Stage();
		String title = "Thêm điểm miệng";
		
		FXMLLoader loader = SceneUtils.changeSceneWithoutLostFocus(url, stage, title, null, null);
		ScoreDetailController controller = loader.getController();
		
		Score score = new Score();
		Student student = model.getStudent();
		ScoreType scoreType = scoreTypeService.findOne(1L);
		Subject subject = model.getSubject();
		Semester semester = model.getSemester();
		SchoolYear schoolYear = model.getSchoolYear();
		SchoolClass schoolClass = model.getSchoolClass();
		score.setStudent(student);
		score.setSubject(subject);
		score.setScoreType(scoreType);
		score.setSemester(semester);
		score.setSchoolYear(schoolYear);
		score.setSchoolClass(schoolClass);
		
		controller.setItem(null, score);
	}
	@FXML
	public void updateHourScoreButtonOnAction(ActionEvent event)
	{
		Integer selectedIndex = mouthTestComboBox.getSelectionModel().getSelectedIndex();
		Score selectedItem = mouthTestComboBox.getItems().get(selectedIndex);
		
		if(selectedItem == null)
			return;
		
		URL url = getClass().getResource("../../application/views/score/editdetail.fxml");
		Stage stage = new Stage();
		String title = "Sửa/xóa điểm miệng";
		
		FXMLLoader loader = SceneUtils.changeSceneWithoutLostFocus(url, stage, title, null, null);

		ScoreDetailController controller = loader.getController();

		controller.setItem(selectedIndex, selectedItem);
	}
	@FXML
	public void insertSemiScoreButtonOnAction(ActionEvent event)
	{
		URL url = getClass().getResource("../../application/views/score/editdetail.fxml");
		Stage stage = new Stage();
		String title = "Thêm điểm miệng";
		
		FXMLLoader loader = SceneUtils.changeSceneWithoutLostFocus(url, stage, title, null, null);
		ScoreDetailController controller = loader.getController();
		
		Score score = new Score();
		Student student = model.getStudent();
		ScoreType scoreType = scoreTypeService.findOne(1L);
		Subject subject = model.getSubject();
		Semester semester = model.getSemester();
		SchoolYear schoolYear = model.getSchoolYear();
		SchoolClass schoolClass = model.getSchoolClass();
		score.setStudent(student);
		score.setSubject(subject);
		score.setScoreType(scoreType);
		score.setSemester(semester);
		score.setSchoolYear(schoolYear);
		score.setSchoolClass(schoolClass);
		
		controller.setItem(null, score);
	}
	@FXML
	public void updateSemiScoreButtonOnAction(ActionEvent event)
	{
		Integer selectedIndex = mouthTestComboBox.getSelectionModel().getSelectedIndex();
		Score selectedItem = mouthTestComboBox.getItems().get(selectedIndex);
		
		if(selectedItem == null)
			return;
		
		URL url = getClass().getResource("../../application/views/score/editdetail.fxml");
		Stage stage = new Stage();
		String title = "Sửa/xóa điểm miệng";
		
		FXMLLoader loader = SceneUtils.changeSceneWithoutLostFocus(url, stage, title, null, null);

		ScoreDetailController controller = loader.getController();

		controller.setItem(selectedIndex, selectedItem);
	}
	@FXML
	public void insertFinalScoreButtonOnAction(ActionEvent event)
	{
		URL url = getClass().getResource("../../application/views/score/editdetail.fxml");
		Stage stage = new Stage();
		String title = "Thêm điểm miệng";
		
		FXMLLoader loader = SceneUtils.changeSceneWithoutLostFocus(url, stage, title, null, null);
		ScoreDetailController controller = loader.getController();
		
		Score score = new Score();
		Student student = model.getStudent();
		ScoreType scoreType = scoreTypeService.findOne(1L);
		Subject subject = model.getSubject();
		Semester semester = model.getSemester();
		SchoolYear schoolYear = model.getSchoolYear();
		SchoolClass schoolClass = model.getSchoolClass();
		score.setStudent(student);
		score.setSubject(subject);
		score.setScoreType(scoreType);
		score.setSemester(semester);
		score.setSchoolYear(schoolYear);
		score.setSchoolClass(schoolClass);
		
		controller.setItem(null, score);
	}
	@FXML
	public void updateFinalScoreButtonOnAction(ActionEvent event)
	{
		Integer selectedIndex = mouthTestComboBox.getSelectionModel().getSelectedIndex();
		Score selectedItem = mouthTestComboBox.getItems().get(selectedIndex);
		
		if(selectedItem == null)
			return;
		
		URL url = getClass().getResource("../../application/views/score/editdetail.fxml");
		Stage stage = new Stage();
		String title = "Sửa/xóa điểm miệng";
		
		FXMLLoader loader = SceneUtils.changeSceneWithoutLostFocus(url, stage, title, null, null);

		ScoreDetailController controller = loader.getController();

		controller.setItem(selectedIndex, selectedItem);
	}
}
