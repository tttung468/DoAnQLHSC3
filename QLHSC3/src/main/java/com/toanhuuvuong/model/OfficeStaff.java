package com.toanhuuvuong.model;

import java.util.Date;

public class OfficeStaff extends Officer implements java.io.Serializable 
{
	private static final long serialVersionUID = 1L;
	
	public OfficeStaff() {}
    public OfficeStaff(Account account, String code, String name, String phone, String avatarpath, 
    		String gender, Date birth, String address, Integer salary,
    		Boolean isDeleted, Date createdDate, String createdBy, Date modifiedDate, String modifiedBy) 
    {
       super(account, code, name, phone, avatarpath, 
    		 gender, birth, address, salary,
    		 isDeleted, createdDate, createdBy, modifiedDate, modifiedBy);
    }
}
