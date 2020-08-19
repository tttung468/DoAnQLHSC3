package com.toanhuuvuong.controller.edit;

import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

import com.toanhuuvuong.constant.SystemConstant;
import com.toanhuuvuong.model.Account;
import com.toanhuuvuong.model.FormTeacherAssignment;
import com.toanhuuvuong.model.Teacher;
import com.toanhuuvuong.service.impl.FormTeacherAssignmentService;
import com.toanhuuvuong.service.impl.TeacherService;
import com.toanhuuvuong.utils.SessionUtils;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import javafx.util.Callback;

public class FormTeacherAssignmentController extends GenericController<FormTeacherAssignment> implements Initializable
{	
	// ------------------------------------------- Attributes
	@FXML
	private ComboBox<Teacher> teacherComboBox;
	
	private TeacherService teacherService = new TeacherService();
	private FormTeacherAssignmentService formTeacherAssignmentService = new FormTeacherAssignmentService();
	
	private Account accountModel = (Account)SessionUtils.getInstance().getValue("accountModel");
	public com.toanhuuvuong.controller.list.SchoolClassController editDelegate;
	// ------------------------------------------- Methods
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{		
		initSchoolClassComboBox();
	}
	private void initSchoolClassComboBox()
	{
		Collection<Teacher> models = teacherService.findAll();
		
		teacherComboBox.setItems(FXCollections.observableArrayList(models));
		teacherComboBox.setValue(null);
		
		teacherComboBox.setCellFactory(new Callback<ListView<Teacher>, ListCell<Teacher>>() 
		{
            @Override
            public ListCell<Teacher> call(ListView<Teacher> param) 
            {
                final ListCell<Teacher> cell = new ListCell<Teacher>() 
                {
                    @Override
                    protected void updateItem(Teacher item, boolean empty) 
                    {
                        super.updateItem(item, empty);
                        if (item != null && !empty)
                        {
                        	String subjectName = (item.getSubject() != null) ? item.getSubject().getName() : SystemConstant.UNKNOWN;
                        	
                        	setText(item.getName() + " - " + subjectName);
                        }
                        else 
                            setText(null);
                    }
                };
                return cell;
            }
        });
		teacherComboBox.setButtonCell(new ListCell<Teacher>()
		{
            @Override
            protected void updateItem(Teacher item, boolean empty) 
            {
                super.updateItem(item, empty);
                if (item != null && !empty)
                {
                	String subjectName = (item.getSubject() != null) ? item.getSubject().getName() : SystemConstant.UNKNOWN;
                	
                	setText(item.getName() + " - " + subjectName);
                }
                else
                    setText(null);
            }
        });
	}
	@Override
	protected void applyUI() 
	{
		super.applyUI();
	}
	@Override
	public void fillForm() 
	{		
		if(model != null && model.getTeacher() != null)
		{
			for(Teacher teacher : teacherComboBox.getItems())
			{
				if(teacher.getId() == model.getTeacher().getId())
				{
					teacherComboBox.setValue(teacher);
					break;
				}
			}
		}	
	}
	@Override
	public void mappingForm() 
	{	
		if(model == null)
			model = new FormTeacherAssignment();
		
		model.setTeacher(teacherComboBox.getValue());
	}
	@Override
	protected boolean validateForm() 
	{
		return true;
	}
	@Override
	public boolean delete()
	{
		Long[] deletedIds = { model.getId() };
		formTeacherAssignmentService.delete(deletedIds);
		
		messageLabel.setTextFill(Color.GREEN);
		model.setMessageCode("delete_success");
		messageLabel.setText(model.getMessage());
		
		editDelegate.updateSelectedItem(index, model.getSchoolClass());
		
		return true;
	}
	@Override
	public boolean update()
	{
		messageLabel.setTextFill(Color.GREEN);
		model = formTeacherAssignmentService.updateOne(model, accountModel);
		messageLabel.setText("Updated success :)");
		
		editDelegate.updateSelectedItem(index, model.getSchoolClass());
		
		return true;
	}
	@Override
	public boolean insert()
	{	
		messageLabel.setTextFill(Color.GREEN);
		model = formTeacherAssignmentService.insertOne(model, accountModel);
		messageLabel.setText("Inserted success :)");
			
		editDelegate.refreshButtonOnAction(null);
		
		return true;
	}
}
