package com.toanhuuvuong.converter;

import java.util.ArrayList;
import java.util.List;

import com.toanhuuvuong.constant.SystemConstant;
import com.toanhuuvuong.model.SchoolYear;

public class SchoolYearConverter implements IConverter<SchoolYear>
{
	@Override
	public List<String> convert(SchoolYear model)
	{
		List<String> list = new ArrayList<String>();
		
		if(model == null)
		{
			list.add("Mã");
			list.add("Tên năm học");
			list.add("Chủ đề");
			list.add("Trạng thái");
		}
		else
		{
			list.add(model.getCode() != null ? model.getCode() : SystemConstant.UNKNOWN);
			list.add(model.getLowerBound() != null && model.getUpperBound() != null 
					? "Năm học " + model.getLowerBound().toString() + " - " + model.getUpperBound().toString() 
					: SystemConstant.UNKNOWN);
			list.add(model.getTheme() != null ? model.getTheme() : SystemConstant.UNKNOWN);
			list.add(model.getIsDeleted() != null && model.getIsDeleted() ? "Khóa" : "Mở");
		}
		
		return list;
	}
}