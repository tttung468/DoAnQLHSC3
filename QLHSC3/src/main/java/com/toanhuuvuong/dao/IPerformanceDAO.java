package com.toanhuuvuong.dao;

import com.toanhuuvuong.model.Performance;

public interface IPerformanceDAO
{
	public Performance findByCode(String code);
	public Performance findByName(String name);
}