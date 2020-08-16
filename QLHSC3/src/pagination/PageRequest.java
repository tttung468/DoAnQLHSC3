/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pagination;

import java.io.Serializable;
import qlhsc3.Generic;
import sort.Sorter;

/**
 *
 * @author ThanhTung
 * @param <T>
 */
public class PageRequest<T extends Generic> implements Pageable<T> 
{
        private Integer page;
	private Integer perPage;
	private Sorter sorter;
	private String searchKey;
	private T filterModel;
	
	public PageRequest(Integer page, Integer perPage, Sorter sorter, String searchKey, T filterModel) 
	{
		this.page = page;
		this.perPage = perPage;
		this.sorter = sorter;
		this.searchKey = searchKey;
		this.filterModel = filterModel;
	}
	@Override
	public Integer getPage() 
	{
		return page;
	}
	@Override
	public Integer getOffset()
	{
		if(page != null && page > 0 && perPage != null && perPage > 0)
			return (page - 1) * perPage;
		return null;
	}
	@Override
	public Integer getLimit() 
	{
		return perPage;
	}
	@Override
	public Sorter getSorter()
	{
		return sorter;
	}
	@Override
	public String getSearchKey() 
	{
		return searchKey;
	}
	@Override
	public T getFilterModel() 
	{
		return filterModel;
	}
}