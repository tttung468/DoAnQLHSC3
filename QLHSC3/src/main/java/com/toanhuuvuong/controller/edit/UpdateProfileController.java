package com.toanhuuvuong.controller.edit;

import java.net.URL;
import java.util.ResourceBundle;

import com.toanhuuvuong.model.Account;
import com.toanhuuvuong.service.impl.AccountService;
import com.toanhuuvuong.utils.SecurityUtils;
import com.toanhuuvuong.utils.SessionUtils;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class UpdateProfileController extends GenericController<Account> implements Initializable
{
	// ------------------------------------------- Attributes
	@FXML
	private Label messageLabel;
	@FXML
	private TextField emailTextField;
	@FXML
	private Label emailErrorLabel;
	@FXML
	private PasswordField passwordField;
	@FXML
	private Label passwordErrorLabel;
	@FXML
	private PasswordField password2Field;
	@FXML
	private Label password2ErrorLabel;
	
	private AccountService accountService = new AccountService();
	private ResourceBundle messageBundle = ResourceBundle.getBundle("message");
	
	private Account accountModel = (Account)SessionUtils.getInstance().getValue("accountModel");
	// ------------------------------------------- Methods
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		emailTextField.setText(accountModel.getUsername());
	}
	@Override
	protected void applyUI() 
	{
		super.applyUI();
		
		titleLabel.setText("CẬP NHẬT PROFILE");
		
		isDeletedLabel.setVisible(false);
		isDeletedComboBox.setVisible(false);
		
		deleteButton.setVisible(false);
		
		editButton.setText("Cập nhật");
		editButton.setDisable(false);
		editIcon.setIconName("EDIT");		
	}
	public void mappingForm() 
	{
		if(model == null)
			model = new Account();
		
		model.setPassword(passwordField.getText().trim());
		model.setPassword2(password2Field.getText().trim());
	}
	public boolean validateForm()
	{
		return (!model.getPassword().isEmpty() &&
				model.getPassword().length() >= 6 && model.getPassword().length() <= 32 &&
				!model.getPassword2().isEmpty() &&
				model.getPassword2().length() >= 6 && model.getPassword2().length() <= 32 &&
				model.getPassword().equals(model.getPassword2()));
	}
	@Override
	protected void fillForm() 
	{
		
	}
	@Override
	protected boolean delete() 
	{
		return true;
	}
	@Override
	protected boolean update() 
	{
		if(!model.getPassword().equals(model.getPassword2()))
		{
			messageLabel.setText(messageBundle.getString("password_not_match"));
		    messageLabel.setTextFill(Color.RED);
		    
		    return false;
		}
		boolean isMatch = SecurityUtils.compare(model.getPassword(), accountModel.getPassword());
		if(!isMatch)
		{
			messageLabel.setText(messageBundle.getString("wrong_password"));
		    messageLabel.setTextFill(Color.RED);
		    
		    return false;
		}
		
		messageLabel.setText(messageBundle.getString("update_success"));
	    messageLabel.setTextFill(Color.GREEN);
		
	    accountModel.setPassword(SecurityUtils.encrypt(model.getPassword2()));
	    
    	accountService.updateOne(accountModel, accountModel);
    	
    	return true;
	}
	@Override
	protected boolean insert()
	{
		return false;
	}
}
