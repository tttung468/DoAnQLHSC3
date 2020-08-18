package com.toanhuuvuong.service;

import com.toanhuuvuong.model.OfficeStaff;

public interface IOfficeStaffService 
{
	public OfficeStaff findByCode(String code);
	public OfficeStaff findByAccountUsername(String username);
}
