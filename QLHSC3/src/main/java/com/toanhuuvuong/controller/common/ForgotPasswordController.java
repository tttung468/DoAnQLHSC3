package com.toanhuuvuong.controller.common;

import java.net.URL;
import java.util.ResourceBundle;

import javax.mail.MessagingException;

import com.toanhuuvuong.constant.SystemConstant;
import com.toanhuuvuong.model.Account;
import com.toanhuuvuong.service.IAccountService;
import com.toanhuuvuong.service.impl.AccountService;
import com.toanhuuvuong.utils.MailUtils;
import com.toanhuuvuong.utils.SceneUtils;
import com.toanhuuvuong.utils.SecurityUtils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class ForgotPasswordController implements Initializable
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
	private Button sendButton;
	
	private IAccountService accountService = new AccountService();
	private ResourceBundle messageBundle = ResourceBundle.getBundle("message");
	private ResourceBundle mailBundle = ResourceBundle.getBundle("mail");
	
	private Account model = new Account();
	// ------------------------------------------- Methods
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		//initLogoCircle();
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
	}
	public boolean validateForm()
	{
		return (!model.getUsername().isEmpty());
	}
	@FXML
	public void usernameTextFieldOnKeyReleased()
	{
		mappingForm();
		
		if(model.getUsername().isEmpty())
		{
			usernameErrorLabel.setText("Tên tài khoản không được trống :(");
			usernameErrorLabel.setVisible(true);
			
			sendButton.setDisable(true);
		}
		else
		{
			usernameErrorLabel.setVisible(false);
			
			if(validateForm())
				sendButton.setDisable(false);
		}
	}
	@FXML
	public void backLoginButtonOnAction(ActionEvent event)
	{
		URL url = getClass().getResource("../../application/views/common/login.fxml");
		Stage stage = (Stage)((Node)(event.getSource())).getScene().getWindow();
		String title = "Đăng nhập";
		
		SceneUtils.changeScene(url, stage, title, SystemConstant.FRAME_WIDTH, SystemConstant.FRAME_HEIGHT);
	}
	@FXML
	public void sendButtonOnAction(ActionEvent event)
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
			try 
			{
				String from = mailBundle.getString("mail.account.username");
				String password = mailBundle.getString("mail.account.password");
				String subject = "RESET PASSWORD";
				String newPassword = SystemConstant.DEFAULT_PASSWORD;
				String content = String.format("Chào %s, mật khẩu mới của bạn sẽ là %s", account.getUsername(), newPassword);
				
				MailUtils.getInstance().sendText(from, password, account.getUsername(), subject, content);
				
				String hashPassword = SecurityUtils.encrypt(newPassword);
				account.setPassword(hashPassword);
			
				accountService.updateOne(account, account);
				
				messageLabel.setText(messageBundle.getString("success_reset_password"));
			    messageLabel.setTextFill(Color.GREEN);
			}
			catch (MessagingException e) 
			{
				e.printStackTrace();
				
				messageLabel.setText(messageBundle.getString("fail_send_email"));
			    messageLabel.setTextFill(Color.RED);
			}
	    }
	}
}
