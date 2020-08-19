package com.toanhuuvuong.controller.edit;

import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

import com.toanhuuvuong.constant.SystemConstant;
import com.toanhuuvuong.model.Account;
import com.toanhuuvuong.model.SchoolYear;
import com.toanhuuvuong.model.Score;
import com.toanhuuvuong.model.Semester;
import com.toanhuuvuong.model.Student;
import com.toanhuuvuong.service.impl.SchoolYearService;
import com.toanhuuvuong.service.impl.SemesterService;
import com.toanhuuvuong.service.impl.StudentService;
import com.toanhuuvuong.utils.SceneUtils;
import com.toanhuuvuong.utils.SessionUtils;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ResultOptionController extends GenericController<Score> implements Initializable
{	
	// ------------------------------------------- Attributes
	@FXML
	private ComboBox<Semester> semesterComboBox;
	@FXML
	private ComboBox<SchoolYear> schoolYearComboBox;
	@FXML
	private TextField codeTextField;
	
	private SemesterService semesterService = new SemesterService();
	private SchoolYearService schoolYearService = new SchoolYearService();
	private StudentService studentService = new StudentService();
	
	private Account accountModel = (Account)SessionUtils.getInstance().getValue("accountModel");
	// ------------------------------------------- Methods
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{		
		initSemesterComboBox();
		
		initSchoolYearComboBox();
	}
	private void initSemesterComboBox()
	{
		Collection<Semester> models = semesterService.findAll();
		
		semesterComboBox.setItems(FXCollections.observableArrayList(models));
		semesterComboBox.setValue(semesterComboBox.getItems().get(0));
		
		semesterComboBox.setCellFactory(new Callback<ListView<Semester>, ListCell<Semester>>() 
		{
            @Override
            public ListCell<Semester> call(ListView<Semester> param) 
            {
                final ListCell<Semester> cell = new ListCell<Semester>() 
                {
                    @Override
                    protected void updateItem(Semester item, boolean empty) 
                    {
                        super.updateItem(item, empty);
                        if (item != null && !empty)
                        	setText(item.getName());
                        else 
                            setText(null);
                    }
                };
                return cell;
            }
        });
		semesterComboBox.setButtonCell(new ListCell<Semester>()
		{
            @Override
            protected void updateItem(Semester item, boolean empty) 
            {
                super.updateItem(item, empty);
                if (item != null && !empty)
                	setText(item.getName());
                else
                    setText(null);
            }
        });
	}
	private void initSchoolYearComboBox()
	{
		Collection<SchoolYear> models = schoolYearService.findAll();
		
		schoolYearComboBox.setItems(FXCollections.observableArrayList(models));
		schoolYearComboBox.setValue(schoolYearComboBox.getItems().get(0));
		
		schoolYearComboBox.setCellFactory(new Callback<ListView<SchoolYear>, ListCell<SchoolYear>>() 
		{
            @Override
            public ListCell<SchoolYear> call(ListView<SchoolYear> param) 
            {
                final ListCell<SchoolYear> cell = new ListCell<SchoolYear>() 
                {
                    @Override
                    protected void updateItem(SchoolYear item, boolean empty) 
                    {
                        super.updateItem(item, empty);
                        if (item != null && !empty)
                        	setText(item.getCode());
                        else 
                            setText(null);
                    }
                };
                return cell;
            }
        });
		schoolYearComboBox.setButtonCell(new ListCell<SchoolYear>()
		{
            @Override
            protected void updateItem(SchoolYear item, boolean empty) 
            {
                super.updateItem(item, empty);
                if (item != null && !empty)
                	setText(item.getCode());
                else
                    setText(null);
            }
        });
	}
	@Override
	protected void applyUI() 
	{
		super.applyUI();
		
		editButton.setText("Xem chi tiết");
		
		editIcon.setIconName("EYE");
	}
	@Override
	public void fillForm() 
	{		
		if(model != null)
		{
			
		}	
	}
	@Override
	public void mappingForm() 
	{	
		if(model == null)
			model = new Score();
		
		model.setSemester(semesterComboBox.getValue());
		model.setSchoolYear(schoolYearComboBox.getValue());
		Student student = studentService.findByCode(codeTextField.getText().trim());
		model.setStudent(student);
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
		URL url = getClass().getResource("../../application/views/result/list.fxml");
		Stage stage = (Stage)(semesterComboBox.getScene().getWindow());
		String title = "Tra cứu kết quả học tập";
		
		FXMLLoader loader = SceneUtils.changeScene(url, stage, title, SystemConstant.FRAME_WIDTH, SystemConstant.FRAME_HEIGHT);
		com.toanhuuvuong.controller.list.ResultController controller = loader.getController();
		
		controller.setChoose(model.getStudent(), model.getSemester(), model.getSchoolYear());
		
		return true;
	}
}
