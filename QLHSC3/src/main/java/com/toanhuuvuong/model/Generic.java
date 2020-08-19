package com.toanhuuvuong.model;

import java.util.Date;
import java.util.ResourceBundle;

public class Generic
{
	protected Long id;
	protected Boolean isDeleted;
	protected Date createdDate;
	protected String createdBy;
	protected Date modifiedDate;
	protected String modifiedBy; 
	protected String messageCode;
	protected String message;
	protected String alert;
	
	public Generic() {}
	public Generic(Boolean isDeleted, Date createdDate, String createdBy, Date modifiedDate, String modifiedBy)
	{
		this.isDeleted = isDeleted;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.modifiedDate = modifiedDate;
        this.modifiedBy = modifiedBy;
	}
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
	public Date getCreatedDate() 
	{
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) 
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
	public Date getModifiedDate() 
	{
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate)
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
		ResourceBundle bundle = ResourceBundle.getBundle("message"); 
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
}
