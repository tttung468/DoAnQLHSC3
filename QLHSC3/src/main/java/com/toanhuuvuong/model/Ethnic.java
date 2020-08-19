package com.toanhuuvuong.model;

import java.util.Date;

public class Ethnic extends Generic implements java.io.Serializable
{
	private static final long serialVersionUID = 1L;
	
	private String code;
    private String name;

    public Ethnic() {}
    public Ethnic(String code, String name,
    		Boolean isDeleted, Date createdDate, String createdBy, Date modifiedDate, String modifiedBy) 
    {
    	super(isDeleted, createdDate, createdBy, modifiedDate, modifiedBy);
    	
        this.code = code;
        this.name = name;
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
}
