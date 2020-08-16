package com.toanhuuvuong.model;

import java.util.Date;

public class SchoolClass extends Generic implements java.io.Serializable 
{
	private static final long serialVersionUID = 1L;
	
	private Grade grade;
	private String code;
	private String name;

    public SchoolClass() {}
    public SchoolClass(Grade grade, String code, String name,
    		Boolean isDeleted, Date createdDate, String createdBy, Date modifiedDate, String modifiedBy) 
    {
    	super(isDeleted, createdDate, createdBy, modifiedDate, modifiedBy);
    	
    	 this.grade = grade;
         this.code = code;
         this.name = name;
    }
	public Grade getGrade() 
	{
		return grade;
	}
	public void setGrade(Grade grade) 
	{
		this.grade = grade;
	}
	public String getCode() 
	{
		return code;
	}
	public void setCode(String code) 
	{
		this.code = code;
	}
	public String getName() 
	{
		return name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
}
