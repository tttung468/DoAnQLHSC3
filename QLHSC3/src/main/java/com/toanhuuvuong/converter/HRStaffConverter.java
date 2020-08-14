package com.toanhuuvuong.converter;

import java.util.ArrayList;
import java.util.List;

import com.toanhuuvuong.model.HRStaffModel;

public class HRStaffConverter implements IConverter<HRStaffModel>
{
	@Override
	public List<String> convert(HRStaffModel model)
	{
		List<String> list = new ArrayList<String>();
		
		if(model == null)
		{
			list.add("Tên đăng nhập");
			list.add("Tên vai trò");
			list.add("Trạng thái");
		}
		else
		{
			
		}
		
		return list;
	}
}