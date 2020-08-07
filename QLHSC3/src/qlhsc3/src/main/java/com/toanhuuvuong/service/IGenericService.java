package com.toanhuuvuong.service;

import java.util.List;

public interface IGenericService<T>
{
	public T complete(T model);
	public List<T> complete(List<T> list);
}
