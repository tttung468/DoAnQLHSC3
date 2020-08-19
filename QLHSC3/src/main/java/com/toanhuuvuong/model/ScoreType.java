package com.toanhuuvuong.model;

import java.util.Date;

public class ScoreType extends Generic implements java.io.Serializable 
{
	private static final long serialVersionUID = 1L;
	
	private String code;
	private String name;
	private Integer factor;

	public ScoreType() {}

	public ScoreType(String code, String name, Integer factor,
			Boolean isDeleted, Date createdDate, String createdBy, Date modifiedDate, String modifiedBy) 
	{
		super(isDeleted, createdDate, createdBy, modifiedDate, modifiedBy);
		
		this.code = code;
		this.name = name;
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
	public Integer getFactor() 
	{
		return this.factor;
	}
	public void setFactor(Integer factor) 
	{
		this.factor = factor;
	}
}
