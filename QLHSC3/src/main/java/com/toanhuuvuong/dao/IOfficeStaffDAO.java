package com.toanhuuvuong.dao;

import com.toanhuuvuong.model.OfficeStaff;

public interface IOfficeStaffDAO
{
	public OfficeStaff findByCode(String code);
	public OfficeStaff findByAccountUsername(String username);
}