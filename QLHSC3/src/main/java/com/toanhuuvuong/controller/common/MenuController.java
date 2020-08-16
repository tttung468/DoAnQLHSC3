package com.toanhuuvuong.controller.common;

import java.net.URL;
import java.util.ResourceBundle;

import com.toanhuuvuong.model.Account;
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
	
	
	private AccountService accountService = new AccountService();
	
	private Account accountModel = (Account)SessionUtils.getInstance().getValue("accountModel");
	// ------------------------------------------- Methods
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		applyUI();
	}
	private void applyUI()
	{
		if(accountModel == null)
		{
			
		}
	}
	@FXML
	public void teacherListMenuItemOnAction(ActionEvent event)
	{
		URL url = getClass().getResource("../../application/views/teacher/list.fxml");
		Stage stage = (Stage)(menuBar.getScene().getWindow());
		String title = "Danh sách giáo viên";
		Double width = menuBar.getScene().getWidth();
		Double height = menuBar.getScene().getHeight();
		
		SceneUtils.changeScene(url, stage, title, width, height);
	}
	@FXML
	public void officeStaffListMenuItemOnAction(ActionEvent event)
	{
		URL url = getClass().getResource("../../application/views/officestaff/list.fxml");
		Stage stage = (Stage)(menuBar.getScene().getWindow());
		String title = "Danh sách giáo vụ";
		Double width = menuBar.getScene().getWidth();
		Double height = menuBar.getScene().getHeight();
		
		SceneUtils.changeScene(url, stage, title, width, height);
	}
	@FXML
	public void hrStaffListMenuItemOnAction(ActionEvent event)
	{
		URL url = getClass().getResource("../../application/views/hrstaff/list.fxml");
		Stage stage = (Stage)(menuBar.getScene().getWindow());
		String title = "Danh sách nhân sự";
		Double width = menuBar.getScene().getWidth();
		Double height = menuBar.getScene().getHeight();
		
		SceneUtils.changeScene(url, stage, title, width, height);
	}
	@FXML
	public void studentListMenuItemOnAction(ActionEvent event)
	{
		URL url = getClass().getResource("../../application/views/student/list.fxml");
		Stage stage = (Stage)(menuBar.getScene().getWindow());
		String title = "Danh sách học sinh";
		Double width = menuBar.getScene().getWidth();
		Double height = menuBar.getScene().getHeight();
		
		SceneUtils.changeScene(url, stage, title, width, height);
	}
	@FXML
	public void subjectListMenuItemOnAction(ActionEvent event)
	{
		URL url = getClass().getResource("../../application/views/subject/list.fxml");
		Stage stage = (Stage)(menuBar.getScene().getWindow());
		String title = "Danh sách môn học";
		Double width = menuBar.getScene().getWidth();
		Double height = menuBar.getScene().getHeight();
		
		SceneUtils.changeScene(url, stage, title, width, height);
	}
	@FXML
	public void schoolYearListMenuItemOnAction(ActionEvent event)
	{
		URL url = getClass().getResource("../../application/views/schoolyear/list.fxml");
		Stage stage = (Stage)(menuBar.getScene().getWindow());
		String title = "Danh sách năm học";
		Double width = menuBar.getScene().getWidth();
		Double height = menuBar.getScene().getHeight();
		
		SceneUtils.changeScene(url, stage, title, width, height);
	}
	@FXML
	public void classListMenuItemOnAction(ActionEvent event)
	{
		URL url = getClass().getResource("../../application/views/schoolclass/list.fxml");
		Stage stage = (Stage)(menuBar.getScene().getWindow());
		String title = "Danh sách lớp học";
		Double width = menuBar.getScene().getWidth();
		Double height = menuBar.getScene().getHeight();
		
		SceneUtils.changeScene(url, stage, title, width, height);
	}
	@FXML
	public void exchangeClassMenuItemOnAction(ActionEvent event)
	{
		URL url = getClass().getResource("../../application/views/common/exchangeclass.fxml");
		Stage stage = (Stage)(menuBar.getScene().getWindow());
		String title = "Phân lớp & chuyển lớp";
		Double width = menuBar.getScene().getWidth();
		Double height = menuBar.getScene().getHeight();
		
		SceneUtils.changeScene(url, stage, title, width, height);
	}
	@FXML
	public void subjectScoreMenuItemOnAction(ActionEvent event)
	{
		URL url = getClass().getResource("../../application/views/common/subjectscore.fxml");
		Stage stage = (Stage)(menuBar.getScene().getWindow());
		String title = "Bảng điểm";
		Double width = menuBar.getScene().getWidth();
		Double height = menuBar.getScene().getHeight();
		
		SceneUtils.changeScene(url, stage, title, width, height);
	}
	@FXML
	public void officerStatisticsMenuItemOnAction(ActionEvent event)
	{
		URL url = getClass().getResource("../../application/views/common/officerstatistics.fxml");
		Stage stage = (Stage)(menuBar.getScene().getWindow());
		String title = "Thống kê CB & NV";
		Double width = menuBar.getScene().getWidth();
		Double height = menuBar.getScene().getHeight();
		
		SceneUtils.changeScene(url, stage, title, width, height);
	}
	@FXML
	public void resultStatisticsMenuItemOnAction(ActionEvent event)
	{
		URL url = getClass().getResource("../../application/views/common/resultstatistics.fxml");
		Stage stage = (Stage)(menuBar.getScene().getWindow());
		String title = "Thống kê kết quả học tập";
		Double width = menuBar.getScene().getWidth();
		Double height = menuBar.getScene().getHeight();
		
		SceneUtils.changeScene(url, stage, title, width, height);
	}
	@FXML
	public void lookUpResultMenuItemOnAction(ActionEvent event)
	{
		URL url = getClass().getResource("../../application/views/common/lookupresult.fxml");
		Stage stage = (Stage)(menuBar.getScene().getWindow());
		String title = "Tra cứu kết quả học tập";
		Double width = menuBar.getScene().getWidth();
		Double height = menuBar.getScene().getHeight();
		
		SceneUtils.changeScene(url, stage, title, width, height);
	}
	@FXML
	public void teacherAssignmentMenuItemOnAction(ActionEvent event)
	{
		URL url = getClass().getResource("../../application/views/common/teacherassignment.fxml");
		Stage stage = (Stage)(menuBar.getScene().getWindow());
		String title = "Phân công giáo viên";
		Double width = menuBar.getScene().getWidth();
		Double height = menuBar.getScene().getHeight();
		
		SceneUtils.changeScene(url, stage, title, width, height);
	}
	@FXML
	public void regulationMenuItemOnAction(ActionEvent event)
	{
		URL url = getClass().getResource("../../application/views/common/regulation.fxml");
		Stage stage = (Stage)(menuBar.getScene().getWindow());
		String title = "Thay đổi quy định";
		Double width = menuBar.getScene().getWidth();
		Double height = menuBar.getScene().getHeight();
		
		SceneUtils.changeScene(url, stage, title, width, height);
	}
}
