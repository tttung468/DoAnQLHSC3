package com.toanhuuvuong.model;

public class AccountModel extends GenericModel<AccountModel>
{
	private String username;
	private String password;
	private String password2;
	private Long roleId;
	private RoleModel role;
	
	public String getUsername() 
	{
		return username;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}
	public String getPassword() 
	{
		return password;
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
	public Long getRoleId() 
	{
		return roleId;
	}
	public void setRoleId(Long roleId) 
	{
		this.roleId = roleId;
	}
	public RoleModel getRole() 
	{
		return role;
	}
	public void setRole(RoleModel role) 
	{
		this.role = role;
	}
}
