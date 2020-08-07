package com.toanhuuvuong.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.ResourceBundle;

public class GenericModel<T>
{
	private ResourceBundle bundle = ResourceBundle.getBundle("message"); 
	private Long id;
	private Boolean isDeleted;
	private Timestamp createdDate;
	private String createdBy;
	private Timestamp modifiedDate;
	private String modifiedBy;
	private Long[] deletedIds; 
	private String type; 
	private List<T> list; 
	private Integer count; 
	private Integer page; 
	private Integer perPage; 
	private Integer totalPages; 
	private String sortBy; 
	private String sortName; 
	private String messageCode;
	private String message;
	private String alert;
	private String searchKey;
	private Boolean exportCSV; 
	
	public Long getId() 
	{
		return id;
	}
	public void setId(Long id) 
	{
		this.id = id;
	}
	public Boolean getIsDeleted() 
	{
		return isDeleted;
	}
	public void setIsDeleted(Boolean isDeleted) 
	{
		this.isDeleted = isDeleted;
	}
	public Timestamp getCreatedDate() 
	{
		return createdDate;
	}
	public void setCreatedDate(Timestamp createdDate) 
	{
		this.createdDate = createdDate;
	}
	public String getCreatedBy()
	{
		return createdBy;
	}
	public void setCreatedBy(String createdBy)
	{
		this.createdBy = createdBy;
	}
	public Timestamp getModifiedDate() 
	{
		return modifiedDate;
	}
	public void setModifiedDate(Timestamp modifiedDate)
	{
		this.modifiedDate = modifiedDate;
	}
	public String getModifiedBy() 
	{
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy)
	{
		this.modifiedBy = modifiedBy;
	}
	public Long[] getDeletedIds() 
	{
		return deletedIds;
	}
	public void setDeletedIds(Long[] deletedIds)
	{
		this.deletedIds = deletedIds;
	}
	public String getType()
	{
		return type;
	}
	public void setType(String type) 
	{
		this.type = type;
	}
	public List<T> getList() 
	{
		return list;
	}
	public void setList(List<T> list)
	{
		this.list = list;
	}
	public Integer getCount() 
	{
		return count;
	}
	public void setCount(Integer count)
	{
		this.count = count;
	}
	public Integer getPage()
	{
		return page;
	}
	public void setPage(Integer page) 
	{
		this.page = page;
	}
	public Integer getPerPage()
	{
		return perPage;
	}
	public void setPerPage(Integer perPage) 
	{
		this.perPage = perPage;
	}
	public Integer getTotalPages() 
	{
		return totalPages;
	}
	public void setTotalPages(Integer totalPages) 
	{
		this.totalPages = totalPages;
	}
	public String getSortBy()
	{
		return sortBy;
	}
	public void setSortBy(String sortBy) 
	{
		this.sortBy = sortBy;
	}
	public String getSortName() 
	{
		return sortName;
	}
	public void setSortName(String sortName) 
	{
		this.sortName = sortName;
	}
	public String getMessageCode() 
	{
		return messageCode;
	}
	public void setMessageCode(String messageCode) 
	{
		this.messageCode = messageCode;
	}
	public String getMessage() 
	{
		message = (messageCode != null ? bundle.getString(messageCode) : null);
		return message;
	}
	public String getAlert() 
	{
		return alert;
	}
	public void setAlert(String alert) 
	{
		this.alert = alert;
	}
	public String getSearchKey() 
	{
		return searchKey;
	}
	public void setSearchKey(String searchKey) 
	{
		this.searchKey = searchKey;
	}
	public Boolean getExportCSV() 
	{
		return exportCSV;
	}
	public void setExportCSV(Boolean exportCSV) 
	{
		this.exportCSV = exportCSV;
	}
}
