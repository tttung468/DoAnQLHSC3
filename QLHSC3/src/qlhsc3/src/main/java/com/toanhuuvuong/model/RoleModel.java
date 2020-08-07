package com.toanhuuvuong.model;

public class RoleModel extends GenericModel<RoleModel>
{
	private String code;
	private String name;
	private Integer priority;
	
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
	public Integer getPriority() 
	{
		return priority;
	}
	public void setPriority(Integer priority) 
	{
		this.priority = priority;
	}
}
