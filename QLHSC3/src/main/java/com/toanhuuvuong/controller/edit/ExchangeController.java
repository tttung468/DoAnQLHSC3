package com.toanhuuvuong.controller.edit;

import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

import com.toanhuuvuong.model.Account;
import com.toanhuuvuong.model.SchoolClass;
import com.toanhuuvuong.model.StudentOfClass;
import com.toanhuuvuong.service.impl.SchoolClassService;
import com.toanhuuvuong.service.impl.ScoreService;
import com.toanhuuvuong.service.impl.StudentOfClassService;
import com.toanhuuvuong.utils.SessionUtils;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import javafx.util.Callback;

public class ExchangeController extends GenericController<StudentOfClass> implements Initializable
{	
	// ------------------------------------------- Attributes
	@FXML
	private ComboBox<SchoolClass> schoolClassComboBox;
	
	private SchoolClassService schoolClassService = new SchoolClassService();
	private StudentOfClassService studentOfClassService = new StudentOfClassService();
	private ScoreService scoreService = new ScoreService();
	
	private Account accountModel = (Account)SessionUtils.getInstance().getValue("accountModel");
	public com.toanhuuvuong.controller.list.StudentController editDelegate;
	// ------------------------------------------- Methods
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{		
		initSchoolClassComboBox();
	}
	private void initSchoolClassComboBox()
	{
		Collection<SchoolClass> models = schoolClassService.findAll();
		
		schoolClassComboBox.setItems(FXCollections.observableArrayList(models));
		schoolClassComboBox.setValue(null);
		
		schoolClassComboBox.setCellFactory(new Callback<ListView<SchoolClass>, ListCell<SchoolClass>>() 
		{
            @Override
            public ListCell<SchoolClass> call(ListView<SchoolClass> param) 
            {
                final ListCell<SchoolClass> cell = new ListCell<SchoolClass>() 
                {
                    @Override
                    protected void updateItem(SchoolClass item, boolean empty) 
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
		schoolClassComboBox.setButtonCell(new ListCell<SchoolClass>()
		{
            @Override
            protected void updateItem(SchoolClass item, boolean empty) 
            {
                super.updateItem(item, empty);
                if (item != null && !empty)
                    setText(item.getName());
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
		if(model != null)
		{
			
		}	
	}
	@Override
	public void mappingForm() 
	{	
		if(model == null)
			model = new StudentOfClass();
		
		model.setSchoolClass(schoolClassComboBox.getValue());
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
		studentOfClassService.delete(deletedIds);
		
		messageLabel.setTextFill(Color.GREEN);
		model.setMessageCode("delete_success");
		messageLabel.setText(model.getMessage());
		
		editDelegate.updateSelectedItem(index, model.getStudent());
		
		return true;
	}
	@Override
	public boolean update()
	{
		messageLabel.setTextFill(Color.GREEN);
		model = studentOfClassService.updateOne(model, accountModel);
		messageLabel.setText("Updated success :)");
		
		editDelegate.updateSelectedItem(index, model.getStudent());
		
		scoreService.generateScoreForStudent(model.getStudent(), model.getSemester(), model.getSchoolYear(), model.getSchoolClass());
		
		return true;
	}
	@Override
	public boolean insert()
	{	
		messageLabel.setTextFill(Color.GREEN);
		model = studentOfClassService.insertOne(model, accountModel);
		messageLabel.setText("Inserted success :)");
			
		editDelegate.refreshButtonOnAction(null);
		
		scoreService.generateScoreForStudent(model.getStudent(), model.getSemester(), model.getSchoolYear(), model.getSchoolClass());
		
		return true;
	}
}
