package com.toanhuuvuong.dao;

import com.toanhuuvuong.model.ConductType;

public interface IConductTypeDAO
{
	public ConductType findByCode(String code);
	public ConductType findByName(String name);
}