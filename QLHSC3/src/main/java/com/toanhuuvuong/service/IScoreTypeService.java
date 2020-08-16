package com.toanhuuvuong.service;

import com.toanhuuvuong.model.ScoreType;

public interface IScoreTypeService 
{
	public ScoreType findByCode(String code);
	public ScoreType findByName(String name);
}
