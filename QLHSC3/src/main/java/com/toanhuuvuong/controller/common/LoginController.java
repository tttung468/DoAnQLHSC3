package com.toanhuuvuong.controller.common;

import java.net.URL;
import java.util.ResourceBundle;

import com.toanhuuvuong.constant.SystemConstant;
import com.toanhuuvuong.model.Account;
import com.toanhuuvuong.model.Officer;
import com.toanhuuvuong.service.impl.AccountService;
import com.toanhuuvuong.service.impl.HRStaffService;
import com.toanhuuvuong.service.impl.OfficeStaffService;
import com.toanhuuvuong.service.impl.TeacherService;
import com.toanhuuvuong.utils.SceneUtils;
import com.toanhuuvuong.utils.SecurityUtils;
import com.toanhuuvuong.utils.SessionUtils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class LoginController implements Initializable
{
	// ------------------------------------------- Attributes
	@FXML
	private Circle logoCircle;
	@FXML
	private Label messageLabel;
	@FXML
	private TextField usernameTextField;
	@FXML
	private Label usernameErrorLabel;
	@FXML
	private PasswordField passwordPasswordField;
	@FXML
	private Label passwordErrorLabel;
	@FXML
	private Button loginButton;
	
	private AccountService accountService = new AccountService();
	private TeacherService teacherService = new TeacherService();
	private HRStaffService hrStaffService = new HRStaffService();
	private OfficeStaffService officeStaffService = new OfficeStaffService();
	private ResourceBundle messageBundle = ResourceBundle.getBundle("message");
	
	private Account model = new Account();
	// ------------------------------------------- Methods
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		initLogoCircle();
	}
	private void initLogoCircle()
	{
		URL url = getClass().getResource("../../application/template/image/header/logo-khtn.png");
		String path = url != null ? url.toString() : null;
		Image image = path != null ? new Image(path) : null;
		
		logoCircle.setFill(new ImagePattern(image));
	}
	public void mappingForm() 
	{
		model.setUsername(usernameTextField.getText().trim());
		model.setPassword(passwordPasswordField.getText().trim());
	}
	public boolean validateForm()
	{
		return (!model.getUsername().isEmpty() &&
				!model.getPassword().isEmpty() &&
				model.getPassword().length() >= 6 && model.getPassword().length() <= 32);
	}
	@FXML
	public void usernameTextFieldOnKeyReleased()
	{
		mappingForm();
		
		if(model.getUsername().isEmpty())
		{
			usernameErrorLabel.setText("Tên tài khoản không được trống :(");
			usernameErrorLabel.setVisible(true);
			
			loginButton.setDisable(true);
		}
		else
		{
			usernameErrorLabel.setVisible(false);
			
			if(validateForm())
				loginButton.setDisable(false);
		}
	}
	@FXML
	public void passwordPasswordFieldOnKeyReleased()
	{
		mappingForm();
		
		if(model.getPassword().isEmpty())
		{
			passwordErrorLabel.setText("Mật khẩu không được trống :(");
			passwordErrorLabel.setVisible(true);
			
			loginButton.setDisable(true);
		}
		else if(model.getPassword().length() < 6 || model.getPassword().length() > 32)
		{
			passwordErrorLabel.setText("Mật khẩu phải chứa ít nhất 6 - 32 kí tự :(");
			passwordErrorLabel.setVisible(true);
			
			loginButton.setDisable(true);
		}
		else
		{
			passwordErrorLabel.setVisible(false);
			
			if(validateForm())
				loginButton.setDisable(false);
		}
	}
	@FXML
	public void forgotPasswordHyperlinkOnAction(ActionEvent event)
	{
		URL url = getClass().getResource("../../application/views/common/forgot-password.fxml");
		Stage stage = (Stage)((Node)(event.getSource())).getScene().getWindow();
		String title = "Quên mật khẩu";
		
		SceneUtils.changeScene(url, stage, title, SystemConstant.FRAME_WIDTH, SystemConstant.FRAME_HEIGHT);
	}
	@FXML
	public void loginButtonOnAction(ActionEvent event)
	{
		messageLabel.setVisible(true);
	    
		Account account = accountService.findByUsername(model.getUsername());
	    if(account == null)
	    {
	    	messageLabel.setText(messageBundle.getString("account_not_registered"));
		    messageLabel.setTextFill(Color.RED);
	    }
	    else
	    {
	    	boolean isMatch = SecurityUtils.compare(model.getPassword(), account.getPassword());
			
			if(!isMatch)
			{
				messageLabel.setText(messageBundle.getString("wrong_password"));
			    messageLabel.setTextFill(Color.RED);
			}
			else if(account.getIsDeleted())
			{
				messageLabel.setText(messageBundle.getString("not_permission"));
			    messageLabel.setTextFill(Color.RED);
			}
			else
			{
				String code = account.getRole().getCode();
				Officer officer = null;
				if(code.equals("hr"))
					officer = hrStaffService.findByAccountUsername(account.getUsername());
				else if(code.equals("office"))
					officer = officeStaffService.findByAccountUsername(account.getUsername());
				else if(code.equals("teacher"))
					officer = teacherService.findByAccountUsername(account.getUsername());
				
				if(officer == null)
				{
					messageLabel.setText(messageBundle.getString("not_permission"));
				    messageLabel.setTextFill(Color.RED);
				    return;
				}
				
				SessionUtils.getInstance().putValue("officerModel", officer);
				SessionUtils.getInstance().putValue("accountModel", account);
				
				URL url = getClass().getResource("../../application/views/common/home.fxml");
				Stage stage = (Stage)((Node)(event.getSource())).getScene().getWindow();
				String title = "Trang chủ";
				
				SceneUtils.changeScene(url, stage, title, SystemConstant.FRAME_WIDTH, SystemConstant.FRAME_HEIGHT);
			}
	    }
	}
}
