package com.toanhuuvuong.service.impl;

import java.util.List;
import java.util.ListIterator;

public abstract class GenericService<T>
{
	protected abstract T complete(T model);
	protected List<T> complete(List<T> list)
	{
		ListIterator<T> iterator = list.listIterator();
		while(iterator.hasNext()) 
		{
			T next = iterator.next();
			next = complete(next);
		    iterator.set(next);
		 }
		
		return list;
	}
}
