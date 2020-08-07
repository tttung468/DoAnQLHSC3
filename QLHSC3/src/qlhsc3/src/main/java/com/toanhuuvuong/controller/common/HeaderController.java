package com.toanhuuvuong.controller.common;

import java.net.URL;
import java.util.ResourceBundle;

import com.toanhuuvuong.model.AccountModel;
import com.toanhuuvuong.utils.SessionUtils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class HeaderController implements Initializable
{
	// ------------------------------------------- Attributes
	@FXML
	private Circle logoCircle;
	@FXML
	private Label titleLabel;
	@FXML
	private Circle avatarCircle;
	@FXML
	private MenuButton optionMenuButton;
	
	private AccountModel accountModel = (AccountModel)SessionUtils.getInstance().getValue("accountModel");
	// ------------------------------------------- Methods
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		initLogoCircle();
		
		initAvatarCircle();
		
		applyUI();
	}
	private void initLogoCircle()
	{
		URL url = getClass().getResource("../../application/template/image/header/logo-khtn.png");
		String path = url != null ? url.toString() : null;
		Image image = path != null ? new Image(path) : null;
		
		logoCircle.setFill(new ImagePattern(image));
	}
	private void applyUI()
	{
		/*if(accountModel == null)
			optionMenuButton.setVisible(false);
		else
			initAvatarCircle();*/
	}
	private void initAvatarCircle()
	{
		URL url = getClass().getResource("../../application/template/image/header/TaylorSwift.jpg");
		String path = url != null ? url.toString() : null;
		Image image = path != null ? new Image(path) : null;
		
		avatarCircle.setFill(new ImagePattern(image));
	}
	public void setTitleLabelText(String title)
	{
		titleLabel.setText(title);
	}
	@FXML
	public void updateProfileMenuItemOnAction(ActionEvent event)
	{
		/*URL url = getClass().getResource("../../application/common/update-profile.fxml");
		FXMLLoader loader = SceneUtils.changeScene(url, null, null, null, null);
		
		UpdateProfileController controller = loader.getController();
		controller.setItem(1, accountModel);
		controller.showDialog();*/
	}
	@FXML
	public void logoutMenuItemOnAction(ActionEvent event)
	{
		SessionUtils.getInstance().removeValue("accountModel");
	}
}
