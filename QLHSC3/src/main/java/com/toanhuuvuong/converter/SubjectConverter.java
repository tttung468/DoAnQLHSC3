package com.toanhuuvuong.converter;

import java.util.ArrayList;
import java.util.List;

import com.toanhuuvuong.constant.SystemConstant;
import com.toanhuuvuong.model.Subject;

public class SubjectConverter implements IConverter<Subject>
{
	@Override
	public List<String> convert(Subject model)
	{
		List<String> list = new ArrayList<String>();
		
		if(model == null)
		{
			list.add("Mã");
			list.add("Tên môn học");
			list.add("Số tiết dạy");
			list.add("Hệ số");
			list.add("Trạng thái");
		}
		else
		{
			list.add(model.getCode() != null ? model.getCode() : SystemConstant.UNKNOWN);
			list.add(model.getName() != null ? model.getName() : SystemConstant.UNKNOWN);
			list.add(model.getClassHours() != null ? model.getClassHours().toString() : SystemConstant.UNKNOWN);
			list.add(model.getFactor() != null ? model.getFactor().toString() : SystemConstant.UNKNOWN);
			list.add(model.getIsDeleted() != null && model.getIsDeleted() ? "Khóa" : "Mở");
		}
		
		return list;
	}
}