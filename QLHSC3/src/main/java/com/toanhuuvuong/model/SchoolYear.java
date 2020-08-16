package com.toanhuuvuong.model;

import java.util.Date;

public class SchoolYear extends Generic implements java.io.Serializable 
{
	private static final long serialVersionUID = 1L;

	private String code;
	private Integer lowerBound;
	private Integer upperBound;
	private String theme;

	public SchoolYear() {}
	public SchoolYear(String code, Integer lowerBound, Integer upperBound, String theme, 
			Boolean isDeleted, Date createdDate, String createdBy, Date modifiedDate, String modifiedBy) 
	{
		super(isDeleted, createdDate, createdBy, modifiedDate, modifiedBy);
		
		this.code = code;
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
		this.theme = theme;
	}
	public String getCode() 
	{
		return this.code;
	}
	public void setCode(String code) 
	{
		this.code = code;
	}
	public Integer getLowerBound() 
	{
		return this.lowerBound;
	}
	public void setLowerBound(Integer lowerBound) 
	{
		this.lowerBound = lowerBound;
	}
	public Integer getUpperBound() 
	{
		return this.upperBound;
	}
	public void setUpperBound(Integer upperBound) 
	{
		this.upperBound = upperBound;
	}
	public String getTheme()
	{
		return this.theme;
	}
	public void setTheme(String theme)
	{
		this.theme = theme;
	}
}
