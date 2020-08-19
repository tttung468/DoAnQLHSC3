package com.toanhuuvuong.model;

import java.util.Date;

public class Subject extends Generic implements java.io.Serializable 
{
	private static final long serialVersionUID = 1L;
	
	private String code;
	private String name;
	private Integer classHours;
	private Integer factor;

	public Subject() {}

	public Subject(String code, String name, Integer classHours, Integer factor, 
			Boolean isDeleted, Date createdDate, String createdBy, Date modifiedDate, String modifiedBy) 
	{
		super(isDeleted, createdDate, createdBy, modifiedDate, modifiedBy);
		
		this.code = code;
		this.name = name;
		this.classHours = classHours;
		this.factor = factor;
	}
	public String getCode() 
	{
		return this.code;
	}
	public void setCode(String code) 
	{
		this.code = code;
	}
	public String getName() 
	{
		return this.name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	public Integer getClassHours() 
	{
		return this.classHours;
	}
	public void setClassHours(Integer classHours)
	{
		this.classHours = classHours;
	}
	public Integer getFactor() 
	{
		return this.factor;
	}
	public void setFactor(Integer factor) 
	{
		this.factor = factor;
	}
}
