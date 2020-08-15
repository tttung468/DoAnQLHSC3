package com.toanhuuvuong.model;

import java.util.Date;

public class Teacher extends Officer implements java.io.Serializable 
{
	private static final long serialVersionUID = 1L;
	
	private Subject subject;
	
	public Teacher() {}
    public Teacher(Account account, Subject subject, String code, String name, String phone, String avatarpath, 
    		String gender, Date birth, String address, Integer salary,
    		Boolean isDeleted, Date createdDate, String createdBy, Date modifiedDate, String modifiedBy) 
    {
       super(account, code, name, phone, avatarpath, 
    		 gender, birth, address, salary,
    		 isDeleted, createdDate, createdBy, modifiedDate, modifiedBy);
       
       this.subject = subject;
    }
	public Subject getSubject() 
	{
		return subject;
	}
	public void setSubject(Subject subject) 
	{
		this.subject = subject;
	}
}
