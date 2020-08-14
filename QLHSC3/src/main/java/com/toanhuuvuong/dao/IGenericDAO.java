package com.toanhuuvuong.dao;

import java.util.List;

import com.toanhuuvuong.mapper.IRowMapper;

public interface IGenericDAO<T>
{
	public List<T> find(String sql, IRowMapper<T> rowMapper, Object... parameters); // SELECT 
	public void update(String sql, Object... parameters); // UPDATE/DELETE
	public Long insert(String sql, Object... parameters); // INSERT
}
