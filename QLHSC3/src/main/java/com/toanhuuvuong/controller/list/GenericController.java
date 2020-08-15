package com.toanhuuvuong.controller.list;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import com.toanhuuvuong.constant.SystemConstant;
import com.toanhuuvuong.controller.common.HeaderController;
import com.toanhuuvuong.model.Generic;
import com.toanhuuvuong.pagination.PageRequest;
import com.toanhuuvuong.pagination.Pageable;
import com.toanhuuvuong.utils.SceneUtils;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Pagination;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public abstract class GenericController<T> implements Initializable
{
	// ------------------------------------------- Attributes
	@FXML
	protected HeaderController headerController;
	@FXML
	protected ComboBox<Integer> perPageComboBox;
	@FXML
	protected Button searchButton;
	@FXML
	protected TextField searchTextField;
	@FXML
	protected ComboBox<String> isDeletedFilterComboBox;
	@FXML
	protected Button filterButton;
	@FXML
	protected Button insertButton;
	@FXML
	protected Button updateButton;
	@FXML
	protected Button detailButton;
	@FXML
	protected Button refreshButton;
	@FXML
	protected Button exportCSVButton;
	@FXML
	protected MenuButton showHideColMenuButton;
	@FXML
	protected TableView<T> tableView;
	@FXML
	protected TableColumn<T, String> isDeletedCol;
	@FXML
	protected ListView<T> listView;
	@FXML
	protected Pagination pagination;
	
	protected ObservableList<T> observableList = FXCollections.observableArrayList();;
	protected Pageable<T> pageable;
	// ------------------------------------------- Methods
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{		
		initHeaderController();
		
		initPerPageComboBox();
		
		initIsDeletedFilterComboBox();
		
		initShowHideColMenuButton();
		
		initTableView();
		
		initListView();
		
		initPagination();
	}
	private void initPerPageComboBox()
	{
		perPageComboBox.setItems(FXCollections.observableArrayList(2, 4, 8, 16));
		perPageComboBox.getSelectionModel().select(SystemConstant.DEFAULT_PERPAGE);
		perPageComboBox.setPromptText(SystemConstant.DEFAULT_PERPAGE.toString());
		perPageComboBox.valueProperty().addListener((observable, oldValue, newValue) ->
		{
			String searchKey = (searchTextField.getText() != null && !searchTextField.getText().isEmpty()) 
						? searchTextField.getText() 
						: null;
			pageable = new PageRequest<T>(SystemConstant.DEFAULT_PAGE, newValue.intValue(), null, searchKey, getFilterModel());
			changeDataView(pageable);
		});
	}
	private void initIsDeletedFilterComboBox()
	{
		isDeletedFilterComboBox.setItems(FXCollections.observableArrayList("Mở", "Khóa"));
		isDeletedFilterComboBox.setValue(null);
	}
	private void initShowHideColMenuButton()
	{
		ObservableList<MenuItem> items = showHideColMenuButton.getItems();
		
		for(MenuItem item : items)
		{
			item.setOnAction(e ->
			{
				if(((CheckMenuItem)item).isSelected())
				{
					for(TableColumn<T, ?> col : tableView.getColumns())
					{
						if(col.getText().equals(item.getText()))
							col.setVisible(true);
					}
				}
				else
				{
					for(TableColumn<T, ?> col : tableView.getColumns())
					{
						if(col.getText().equals(item.getText()))
							col.setVisible(false);
					}
				}
			});
		}
	}
	private void initTableView()
	{
		tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> 
		{
		    if (newValue != null)
		    {
		    	if(tableView.getSelectionModel().getSelectedItems().size() == 1)
		    	{
		    		updateButton.setVisible(true);
		    		detailButton.setVisible(true);
		    	}
		    	else
		    	{
		    		updateButton.setVisible(false);
		    		detailButton.setVisible(false);
		    	}
		    }
		    else
		    {
		    	updateButton.setVisible(false);
		    	detailButton.setVisible(false);
		    }
		});
		
		isDeletedCol.setCellValueFactory(cell ->
		{
			ObjectProperty<String> prop = new SimpleObjectProperty<String>();
			Generic value = (Generic)(cell.getValue());
			prop.set(value.getIsDeleted() != null && value.getIsDeleted() ? "Khóa" : "Mở");
			return prop;
		});
	}
	private void initListView()
	{
		listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> 
		{
		    if (newValue != null)
		    {
		    	if(listView.getSelectionModel().getSelectedItems().size() == 1)
		    	{
		    		updateButton.setVisible(true);
		    		detailButton.setVisible(true);
		    	}
		    	else
		    	{
		    		updateButton.setVisible(false);
		    		detailButton.setVisible(false);
		    	}
		    }
		    else
		    {
		    	updateButton.setVisible(false);
		    	detailButton.setVisible(false);
		    }
		});
	}
	private void initPagination()
	{
		pagination.currentPageIndexProperty().addListener((observable, oldValue, newValue) -> 
		{
			String searchKey = (searchTextField.getText() != null && !searchTextField.getText().isEmpty()) 
					? searchTextField.getText() 
					: null;
			pageable = new PageRequest<T>(newValue.intValue() + 1, perPageComboBox.getValue(), null, searchKey, getFilterModel());
			changeDataView(pageable);
		});
	}
	@FXML
	public void searchButtonOnAction(ActionEvent event)
	{
		String searchKey = searchTextField.getText().trim();
		
		if(searchKey != null && !searchKey.isEmpty())
		{
			pageable = new PageRequest<T>(SystemConstant.DEFAULT_PAGE, perPageComboBox.getValue(), null, searchKey, getFilterModel());
			changeDataView(pageable);
		}
	}
	@FXML
	public void filterButtonOnAction(ActionEvent event)
	{
		T filterModel = getFilterModel();
		
		if(filterModel != null)
		{
			String searchKey = searchTextField.getText().trim();
			if(searchKey.isEmpty())
				searchKey = null;
		
			pageable = new PageRequest<T>(SystemConstant.DEFAULT_PAGE, perPageComboBox.getValue(), null, searchKey, filterModel);
			changeDataView(pageable);
		}
	}
	@FXML
	public void insertButtonOnAction(ActionEvent event)
	{
		URL url = getClass().getResource(getEditLoaderPath());
		FXMLLoader loader = SceneUtils.changeScene(url, null, null, null, null); 

		/*com.toanhuuvuong.controller.edit.GenericController<T> controller = loader.getController();
		controller.listDelegate = this;
		controller.setItem(null, null);
		controller.showDialog();*/
	}
	@FXML
	public void updateButtonOnAction(ActionEvent event)
	{
		Integer selectedIndex = tableView.getSelectionModel().getSelectedIndex();
		T selectedItem = observableList.get(selectedIndex);
		
		if(selectedItem == null)
			return;
		
		URL url = getClass().getResource(getEditLoaderPath());
		FXMLLoader loader = SceneUtils.changeScene(url, null, null, null, null);

		/*com.toanhuuvuong.controller.edit.GenericController<T> controller = loader.getController();
		controller.listDelegate = this;
		controller.setItem(selectedIndex, selectedItem);
		controller.showDialog();*/
	}
	@FXML
	public void detailButtonOnAction(ActionEvent event)
	{
		Integer selectedIndex = tableView.getSelectionModel().getSelectedIndex();
		T selectedItem = observableList.get(selectedIndex);
		
		if(selectedItem == null)
			return;
		
		URL url = getClass().getResource(getDetailLoaderPath());
		FXMLLoader loader = SceneUtils.changeScene(url, null, null, null, null);

		/*com.toanhuuvuong.controller.edit.GenericController<T> controller = loader.getController();
		controller.setItem(selectedIndex, selectedItem);
		controller.showDialog();*/
	}
	public void deleteSelectedItem(Integer index, T item)
	{
		observableList.remove(index);
		
		tableView.setItems(observableList);
	}
	public void updateSelectedItem(Integer index, T item)
	{
		if(index == null)
			observableList.add(item);
		else
			observableList.set(index, item);
		
		tableView.setItems(observableList);
	}
	@FXML
	public void refreshButtonOnAction(ActionEvent event)
	{	
		refresh();
		
		pageable = new PageRequest<T>(SystemConstant.DEFAULT_PAGE, SystemConstant.DEFAULT_PERPAGE, null, null, null);
		changeDataView(pageable);
	}
	protected void refresh()
	{
		perPageComboBox.getSelectionModel().select(SystemConstant.DEFAULT_PERPAGE);
		
		searchTextField.setText("");
		
		isDeletedFilterComboBox.setValue(null);
	}
	@FXML
	public void exportCSVButtonOnAction(ActionEvent event)
	{
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Xuất tệp CSV");
		fileChooser.getExtensionFilters().add(new ExtensionFilter("CSV files", "*.csv"));
		File dest = fileChooser.showSaveDialog(((Node)event.getSource()).getScene().getWindow());
		
		if(dest != null)
			exportCSV(dest.getPath());
	}
	protected abstract void initHeaderController();
	protected abstract T getFilterModel();
	protected String getDetailLoaderPath() { return ""; }
	protected abstract String getEditLoaderPath();
	protected abstract void exportCSV(String path);
	protected abstract void changeDataView(Pageable<T> pageable);
}
