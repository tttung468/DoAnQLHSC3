package com.toanhuuvuong.controller.edit;

import java.net.URL;
import java.util.ResourceBundle;

import com.toanhuuvuong.model.Account;
import com.toanhuuvuong.model.Score;
import com.toanhuuvuong.service.impl.ScoreService;
import com.toanhuuvuong.utils.SessionUtils;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class ScoreDetailController extends GenericController<Score> implements Initializable
{	
	// ------------------------------------------- Attributes
	@FXML
	private TextField ordinalNumberTextField;
	@FXML
	private TextField valueTextField;
	
	private ScoreService scoreService = new ScoreService();
	
	private Account accountModel = (Account)SessionUtils.getInstance().getValue("accountModel");
	public ScoreController editDelegate;
	// ------------------------------------------- Methods
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{		
		super.initialize(location, resources);
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
			if(model.getOrdinalNumber() != null)
				ordinalNumberTextField.setText(model.getOrdinalNumber().toString());
			if(model.getValue() != null)
				valueTextField.setText(model.getValue().toString());
		}	
	}
	@Override
	public void mappingForm() 
	{	
		if(model == null)
			model = new Score();
		
		model.setOrdinalNumber(Integer.parseInt(ordinalNumberTextField.getText().trim()));
		model.setValue(Float.parseFloat(valueTextField.getText().trim()));
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
		scoreService.delete(deletedIds);
		
		messageLabel.setTextFill(Color.GREEN);
		model.setMessageCode("delete_success");
		messageLabel.setText(model.getMessage());
		
		editDelegate.refresh();
		
		return true;
	}
	@Override
	public boolean update()
	{
		messageLabel.setTextFill(Color.GREEN);
		model = scoreService.updateOne(model, accountModel);
		messageLabel.setText("Updated success :)");
		
		editDelegate.refresh();
		
		return true;
	}
	@Override
	public boolean insert()
	{	
		messageLabel.setTextFill(Color.GREEN);
		model = scoreService.insertOne(model, accountModel);
		messageLabel.setText("Inserted success :)");
			
		editDelegate.refresh();
		
		return true;
	}
}
