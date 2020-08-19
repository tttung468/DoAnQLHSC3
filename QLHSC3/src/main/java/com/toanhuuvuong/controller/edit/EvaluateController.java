package com.toanhuuvuong.controller.edit;

import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

import com.toanhuuvuong.model.Account;
import com.toanhuuvuong.model.Conduct;
import com.toanhuuvuong.model.ConductType;
import com.toanhuuvuong.model.SchoolClass;
import com.toanhuuvuong.model.StudentOfClass;
import com.toanhuuvuong.service.impl.ConductService;
import com.toanhuuvuong.service.impl.ConductTypeService;
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
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.util.Callback;

public class EvaluateController extends GenericController<Conduct> implements Initializable
{	
	// ------------------------------------------- Attributes
	@FXML
	private ComboBox<ConductType> conductTypeComboBox;
	
	private ConductTypeService conductTypeService = new ConductTypeService();
	private ConductService conductService = new ConductService();
	private StudentOfClassService studentOfClassService = new StudentOfClassService();
	private ScoreService scoreService = new ScoreService();
	
	private Account accountModel = (Account)SessionUtils.getInstance().getValue("accountModel");
	public com.toanhuuvuong.controller.list.StudentController editDelegate;
	// ------------------------------------------- Methods
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{		
		initConductTypeComboBox();
	}
	private void initConductTypeComboBox()
	{
		Collection<ConductType> models = conductTypeService.findAll();
		
		conductTypeComboBox.setItems(FXCollections.observableArrayList(models));
		conductTypeComboBox.setValue(null);
		
		conductTypeComboBox.setCellFactory(new Callback<ListView<ConductType>, ListCell<ConductType>>() 
		{
            @Override
            public ListCell<ConductType> call(ListView<ConductType> param) 
            {
                final ListCell<ConductType> cell = new ListCell<ConductType>() 
                {
                    @Override
                    protected void updateItem(ConductType item, boolean empty) 
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
		conductTypeComboBox.setButtonCell(new ListCell<ConductType>()
		{
            @Override
            protected void updateItem(ConductType item, boolean empty) 
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
		if(model != null && model.getConductType() != null)
		{
			for(ConductType type : conductTypeComboBox.getItems())
			{
				if(type.getId() == model.getConductType().getId())
				{
					conductTypeComboBox.setValue(type);
					break;
				}
			}
		}	
	}
	@Override
	public void mappingForm() 
	{	
		if(model == null)
			model = new Conduct();
		
		model.setConductType(conductTypeComboBox.getValue());
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
		conductService.delete(deletedIds);
		
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
		model = conductService.updateOne(model, accountModel);
		messageLabel.setText("Updated success :)");
		
		editDelegate.updateSelectedItem(index, model.getStudent());
		
		return true;
	}
	@Override
	public boolean insert()
	{	
		messageLabel.setTextFill(Color.GREEN);
		model = conductService.insertOne(model, accountModel);
		messageLabel.setText("Inserted success :)");
			
		editDelegate.refreshButtonOnAction(null);
		
		return true;
	}
}
