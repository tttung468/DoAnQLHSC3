package com.toanhuuvuong.service;

import com.toanhuuvuong.model.HRStaff;

public interface IOfficeStaffService 
{
	public HRStaff findByCode(String code);
	public HRStaff findByAccountUsername(String username);
}
