package com.toanhuuvuong.converter;

import java.util.ArrayList;
import java.util.List;

import com.toanhuuvuong.constant.SystemConstant;
import com.toanhuuvuong.model.SchoolClass;

public class SchoolClassConverter implements IConverter<SchoolClass>
{
	@Override
	public List<String> convert(SchoolClass model)
	{
		List<String> list = new ArrayList<String>();
		
		if(model == null)
		{
			list.add("Mã");
			list.add("Tên lớp học");
			list.add("Trạng thái");
			list.add("Khối");
		}
		else
		{
			list.add(model.getCode() != null ? model.getCode() : SystemConstant.UNKNOWN);
			list.add(model.getName() != null ? model.getName() : SystemConstant.UNKNOWN);
			list.add(model.getIsDeleted() != null && model.getIsDeleted() ? "Khóa" : "Mở");
			list.add(model.getGrade() != null && model.getGrade().getName() != null 
					? model.getGrade().getName()
					: SystemConstant.UNKNOWN);
		}
		
		return list;
	}
}