package com.toanhuuvuong.model;

import java.util.Date;

public class Account extends Generic implements java.io.Serializable 
{
	private static final long serialVersionUID = 1L;
	
	private Role role;
    private String username;
    private String password;
    private String password2;

    public Account() {}
    public Account(Role role, String username, String password,
    		Boolean isDeleted, Date createdDate, String createdBy, Date modifiedDate, String modifiedBy) 
    {
    	super(isDeleted, createdDate, createdBy, modifiedDate, modifiedBy);
    	
        this.role = role;
        this.username = username;
        this.password = password;
    }
    public Role getRole() 
    {
        return this.role;
    }
    public void setRole(Role role) 
    {
        this.role = role;
    }
    public String getUsername() 
    {
        return this.username;
    }
    public void setUsername(String username) 
    {
        this.username = username;
    }
    public String getPassword() 
    {
        return this.password;
    }
    public void setPassword(String password) 
    {
        this.password = password;
    }
	public String getPassword2() 
	{
		return password2;
	}
	public void setPassword2(String password2)
	{
		this.password2 = password2;
	}
}
