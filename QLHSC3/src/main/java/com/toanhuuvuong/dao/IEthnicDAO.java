package com.toanhuuvuong.dao;

import com.toanhuuvuong.model.Ethnic;

public interface IEthnicDAO
{
	public Ethnic findByCode(String code);
	public Ethnic findByName(String name);
}