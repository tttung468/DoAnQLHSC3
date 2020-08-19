package com.toanhuuvuong.controller.edit;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import com.toanhuuvuong.model.Account;
import com.toanhuuvuong.model.OfficeStaff;
import com.toanhuuvuong.service.impl.AccountService;
import com.toanhuuvuong.service.impl.OfficeStaffService;
import com.toanhuuvuong.service.impl.RoleService;
import com.toanhuuvuong.utils.SecurityUtils;
import com.toanhuuvuong.utils.SessionUtils;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class OfficeStaffAccountController extends GenericController<OfficeStaff> implements Initializable
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
	private RoleService roleService = new RoleService();
	private OfficeStaffService officeStaffService = new OfficeStaffService();
	private ResourceBundle messageBundle = ResourceBundle.getBundle("message");
	
	private Account account = new Account();
	private Account accountModel = (Account)SessionUtils.getInstance().getValue("accountModel");
	// ------------------------------------------- Methods
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		
	}
	@Override
	protected void applyUI() 
	{
		super.applyUI();
		
		titleLabel.setText("TẠO TÀI KHOẢN");
		
		isDeletedLabel.setVisible(false);
		isDeletedComboBox.setVisible(false);
		
		deleteButton.setVisible(false);
		
		editButton.setText("Tạo");
		editButton.setDisable(false);
		editIcon.setIconName("PLUS");		
	}
	public void mappingForm() 
	{
		account.setUsername(emailTextField.getText().trim());
		account.setPassword(passwordField.getText().trim());
		account.setPassword2(password2Field.getText().trim());
	}
	public boolean validateForm()
	{
		return (!account.getUsername().isEmpty() &&
				Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE).matcher(account.getUsername()).find() &&
				!model.getName().isEmpty() &&
				!account.getPassword().isEmpty() &&
				account.getPassword().length() >= 6 && account.getPassword().length() <= 32 &&
				!account.getPassword2().isEmpty() &&
				account.getPassword2().length() >= 6 && account.getPassword2().length() <= 32 &&
				account.getPassword().equals(account.getPassword2()));
	}
	@Override
	protected void fillForm() 
	{
		
	}
	@Override
	protected boolean delete() 
	{
		return false;
	}
	@Override
	protected boolean update() 
	{
		Account acc = accountService.findByUsername(account.getUsername());
		if(model.getAccount() != null || acc != null)
		{
			messageLabel.setText(messageBundle.getString("account_existed"));
		    messageLabel.setTextFill(Color.RED);
		    
		    return false;
		}
		messageLabel.setText(messageBundle.getString("register_success"));
	    messageLabel.setTextFill(Color.GREEN);
		
	    account.setPassword(SecurityUtils.encrypt(account.getPassword()));
	    
    	account.setRole(roleService.findByCode("office-staff"));
    	account = accountService.insertOne(account, accountModel);
    	model.setAccount(account);
    	
    	officeStaffService.updateOne(model, accountModel);
    	
    	return true;
	}
	@Override
	protected boolean insert()
	{
		return false;
	}
}
