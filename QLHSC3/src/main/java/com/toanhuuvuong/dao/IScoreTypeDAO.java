package com.toanhuuvuong.dao;

import com.toanhuuvuong.model.ScoreType;

public interface IScoreTypeDAO
{
	public ScoreType findByCode(String code);
	public ScoreType findByName(String name);
}