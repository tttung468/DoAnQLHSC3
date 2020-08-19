package com.toanhuuvuong.service;

import com.toanhuuvuong.model.Performance;

public interface IPerformanceService 
{
	public Performance findByCode(String code);
	public Performance findByName(String name);
	public Performance generateFromAvg(Float avg);
}
