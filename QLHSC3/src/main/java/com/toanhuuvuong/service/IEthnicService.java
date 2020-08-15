package com.toanhuuvuong.service;

import com.toanhuuvuong.model.Ethnic;

public interface IEthnicService 
{
	public Ethnic findByCode(String code);
	public Ethnic findByName(String name);
}
