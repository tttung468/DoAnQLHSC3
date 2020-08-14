package com.toanhuuvuong.controller.common;

import java.net.URL;
import java.util.ResourceBundle;

import com.toanhuuvuong.constant.SystemConstant;
import com.toanhuuvuong.model.AccountModel;
import com.toanhuuvuong.service.IAccountService;
import com.toanhuuvuong.service.impl.AccountService;
import com.toanhuuvuong.utils.SceneUtils;
import com.toanhuuvuong.utils.SessionUtils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class MenuController implements Initializable
{
	// ------------------------------------------- Attributes
	@FXML
	private MenuBar menuBar;
	@FXML
	private Menu officerAndStudentMenu;
	@FXML
	private MenuItem teacherListMenuItem;
	@FXML
	private MenuItem officeStaffListMenuItem;
	@FXML
	private MenuItem hrStaffListMenuItem;
	@FXML
	private MenuItem studentListMenuItem;
	@FXML
	private Menu educationMenu;
	@FXML
	private MenuItem subjectListMenuItem;
	@FXML
	private MenuItem schoolYearListMenuItem;
	@FXML
	private MenuItem classListMenuItem;
	@FXML
	private MenuItem exchangeClassMenuItem;
	@FXML
	private Menu lookUpAndStatisticsMenu;
	@FXML
	private MenuItem subjectScoreMenuItem;
	@FXML
	private Menu statisticsMenu;
	@FXML
	private MenuItem officerStatisticsMenuItem;
	@FXML
	private MenuItem resultStatisticsMenuItem;
	@FXML
	private MenuItem lookUpResultMenuItem;
	@FXML
	private Menu assignmentMenu;
	@FXML
	private MenuItem teacherAssignmentMenuItem;
	@FXML
	private Menu regulationMenu;
	@FXML
	private MenuItem regulationMenuItem;
	
	
	private IAccountService accountService = new AccountService();
	
	private AccountModel accountModel = (AccountModel)SessionUtils.getInstance().getValue("accountModel");
	// ------------------------------------------- Methods
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		applyUI();
	}
	private void applyUI()
	{
		if(accountModel == null || accountService.isUserRole(accountModel))
		{
			
		}
	}
	@FXML
	public void teacherListMenuItemOnAction(ActionEvent event)
	{
		URL url = getClass().getResource("../../application/views/teacher/list.fxml");
		Stage stage = (Stage)(menuBar.getScene().getWindow());
		String title = "Danh sách giáo viên";
		
		SceneUtils.changeScene(url, stage, title, SystemConstant.FRAME_WIDTH, SystemConstant.FRAME_HEIGHT);
	}
	@FXML
	public void officeStaffListMenuItemOnAction(ActionEvent event)
	{
		URL url = getClass().getResource("../../application/views/office-staff/list.fxml");
		Stage stage = (Stage)(menuBar.getScene().getWindow());
		String title = "Danh sách giáo vụ";
		
		SceneUtils.changeScene(url, stage, title, SystemConstant.FRAME_WIDTH, SystemConstant.FRAME_HEIGHT);
	}
	@FXML
	public void hrStaffListMenuItemOnAction(ActionEvent event)
	{
		URL url = getClass().getResource("../../application/views/hrstaff/list.fxml");
		Stage stage = (Stage)(menuBar.getScene().getWindow());
		String title = "Danh sách nhân sự";
		
		SceneUtils.changeScene(url, stage, title, SystemConstant.FRAME_WIDTH, SystemConstant.FRAME_HEIGHT);
	}
	@FXML
	public void studentListMenuItemOnAction(ActionEvent event)
	{
		URL url = getClass().getResource("../../application/views/student/list.fxml");
		Stage stage = (Stage)(menuBar.getScene().getWindow());
		String title = "Danh sách học sinh";
		
		SceneUtils.changeScene(url, stage, title, SystemConstant.FRAME_WIDTH, SystemConstant.FRAME_HEIGHT);
	}
	@FXML
	public void subjectListMenuItemOnAction(ActionEvent event)
	{
		URL url = getClass().getResource("../../application/views/subject/list.fxml");
		Stage stage = (Stage)(menuBar.getScene().getWindow());
		String title = "Danh sách môn học";
		
		SceneUtils.changeScene(url, stage, title, SystemConstant.FRAME_WIDTH, SystemConstant.FRAME_HEIGHT);
	}
	@FXML
	public void schoolYearListMenuItemOnAction(ActionEvent event)
	{
		URL url = getClass().getResource("../../application/views/schoolyear/list.fxml");
		Stage stage = (Stage)(menuBar.getScene().getWindow());
		String title = "Danh sách năm học";
		
		SceneUtils.changeScene(url, stage, title, SystemConstant.FRAME_WIDTH, SystemConstant.FRAME_HEIGHT);
	}
	@FXML
	public void classListMenuItemOnAction(ActionEvent event)
	{
		URL url = getClass().getResource("../../application/views/class/list.fxml");
		Stage stage = (Stage)(menuBar.getScene().getWindow());
		String title = "Danh sách lớp học";
		
		SceneUtils.changeScene(url, stage, title, SystemConstant.FRAME_WIDTH, SystemConstant.FRAME_HEIGHT);
	}
	@FXML
	public void exchangeClassMenuItemOnAction(ActionEvent event)
	{
		URL url = getClass().getResource("../../application/views/common/exchangeclass.fxml");
		Stage stage = (Stage)(menuBar.getScene().getWindow());
		String title = "Phân lớp & chuyển lớp";
		
		SceneUtils.changeScene(url, stage, title, SystemConstant.FRAME_WIDTH, SystemConstant.FRAME_HEIGHT);
	}
	@FXML
	public void subjectScoreMenuItemOnAction(ActionEvent event)
	{
		URL url = getClass().getResource("../../application/views/common/subjectscore.fxml");
		Stage stage = (Stage)(menuBar.getScene().getWindow());
		String title = "Bảng điểm";
		
		SceneUtils.changeScene(url, stage, title, SystemConstant.FRAME_WIDTH, SystemConstant.FRAME_HEIGHT);
	}
	@FXML
	public void officerStatisticsMenuItemOnAction(ActionEvent event)
	{
		URL url = getClass().getResource("../../application/views/common/officerstatistics.fxml");
		Stage stage = (Stage)(menuBar.getScene().getWindow());
		String title = "Thống kê CB & NV";
		
		SceneUtils.changeScene(url, stage, title, SystemConstant.FRAME_WIDTH, SystemConstant.FRAME_HEIGHT);
	}
	@FXML
	public void resultStatisticsMenuItemOnAction(ActionEvent event)
	{
		URL url = getClass().getResource("../../application/views/common/resultstatistics.fxml");
		Stage stage = (Stage)(menuBar.getScene().getWindow());
		String title = "Thống kê kết quả học tập";
		
		SceneUtils.changeScene(url, stage, title, SystemConstant.FRAME_WIDTH, SystemConstant.FRAME_HEIGHT);
	}
	@FXML
	public void lookUpResultMenuItemOnAction(ActionEvent event)
	{
		URL url = getClass().getResource("../../application/views/common/lookupresult.fxml");
		Stage stage = (Stage)(menuBar.getScene().getWindow());
		String title = "Tra cứu kết quả học tập";
		
		SceneUtils.changeScene(url, stage, title, SystemConstant.FRAME_WIDTH, SystemConstant.FRAME_HEIGHT);
	}
	@FXML
	public void teacherAssignmentMenuItemOnAction(ActionEvent event)
	{
		URL url = getClass().getResource("../../application/views/common/teacherassignment.fxml");
		Stage stage = (Stage)(menuBar.getScene().getWindow());
		String title = "Phân công giáo viên";
		
		SceneUtils.changeScene(url, stage, title, SystemConstant.FRAME_WIDTH, SystemConstant.FRAME_HEIGHT);
	}
	@FXML
	public void regulationMenuItemOnAction(ActionEvent event)
	{
		URL url = getClass().getResource("../../application/views/common/regulation.fxml");
		Stage stage = (Stage)(menuBar.getScene().getWindow());
		String title = "Thay đổi quy định";
		
		SceneUtils.changeScene(url, stage, title, SystemConstant.FRAME_WIDTH, SystemConstant.FRAME_HEIGHT);
	}
}
