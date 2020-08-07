package com.toanhuuvuong.converter;

import java.util.List;

public interface IConverter<T>
{
	public List<String> convert(T model);
}
