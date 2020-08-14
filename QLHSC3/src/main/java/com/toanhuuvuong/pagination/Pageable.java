package com.toanhuuvuong.pagination;

import com.toanhuuvuong.sort.Sorter;

public interface Pageable<T>
{
	public Integer getPage();
	public Integer getOffset();
	public Integer getLimit();
	public Sorter getSorter();
	public String getSearchKey();
	public T getFilterModel();
}
