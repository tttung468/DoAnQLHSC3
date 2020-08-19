package com.toanhuuvuong.controller.common;

import java.net.URL;
import java.util.ResourceBundle;

import com.toanhuuvuong.constant.SystemConstant;
import com.toanhuuvuong.controller.edit.UpdateProfileController;
import com.toanhuuvuong.model.Account;
import com.toanhuuvuong.model.Officer;
import com.toanhuuvuong.utils.SceneUtils;
import com.toanhuuvuong.utils.SessionUtils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

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
	
	private Account accountModel = (Account)SessionUtils.getInstance().getValue("accountModel");
	private Officer officerModel = (Officer)SessionUtils.getInstance().getValue("officerModel");
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
		if(accountModel == null)
			optionMenuButton.setVisible(false);
		else
		{
			initOptionMenuButton();
			initAvatarCircle();
		}
	}
	private void initOptionMenuButton()
	{
		optionMenuButton.setText(officerModel.getName());
	}
	private void initAvatarCircle()
	{
		String avatarPath = officerModel.getAvatarpath();
		URL url = avatarPath != null ? getClass().getResource(avatarPath) : null;
		String path = url != null ? url.toString() : null;
		Image image = path != null ? new Image(path) : null;
		
		if(image != null)
			avatarCircle.setFill(new ImagePattern(image));
	}
	public void setTitleLabelText(String title)
	{
		titleLabel.setText(title);
	}
	@FXML
	public void updateProfileMenuItemOnAction(ActionEvent event)
	{
		URL url = getClass().getResource("../../application/views/common/updateprofile.fxml");
		Stage stage = new Stage();
		String title = "Cập nhật profile";
		
		FXMLLoader loader = SceneUtils.changeSceneWithoutLostFocus(url, stage, title, null, null);
		
		UpdateProfileController controller = loader.getController();
		controller.setItem(1, accountModel);
	}
	@FXML
	public void logoutMenuItemOnAction(ActionEvent event)
	{
		SessionUtils.getInstance().removeValue("accountModel");
		SessionUtils.getInstance().removeValue("officerModel");
		
		URL url = getClass().getResource("../../application/views/common/login.fxml");
		Stage stage = (Stage)((Node)optionMenuButton).getScene().getWindow();
		String title = "Đăng nhập";
		
		SceneUtils.changeScene(url, stage, title, SystemConstant.FRAME_WIDTH, SystemConstant.FRAME_HEIGHT);
	}
}
