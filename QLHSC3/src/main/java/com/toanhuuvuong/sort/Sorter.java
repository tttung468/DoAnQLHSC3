package com.toanhuuvuong.sort;

public class Sorter 
{
	public static final String ASC = "asc";
	public static final String DESC = "desc";
	private String sortBy;
	private String sortName;
	
	public Sorter(String sortBy, String sortName)
	{
		this.sortBy = sortBy;
		this.sortName = sortName;
	}
	public String getSortBy() 
	{
		return sortBy;
	}
	public String getSortName() 
	{
		return sortName;
	}
}
