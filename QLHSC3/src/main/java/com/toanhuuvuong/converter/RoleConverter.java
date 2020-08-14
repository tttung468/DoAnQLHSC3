package com.toanhuuvuong.converter;

import java.util.ArrayList;
import java.util.List;

import com.toanhuuvuong.constant.SystemConstant;
import com.toanhuuvuong.model.Role;

public class RoleConverter implements IConverter<Role>
{
	@Override
	public List<String> convert(Role model)
	{
		List<String> list = new ArrayList<String>();
		
		if(model == null)
		{
			list.add("Mã vai trò");
			list.add("Tên vai trò");
			list.add("Cấp độ ưu tiên");
			list.add("Trạng thái");
		}
		else
		{
			list.add(model.getCode() != null ? model.getCode() : SystemConstant.UNKNOWN);
			list.add(model.getName() != null ? model.getName() : SystemConstant.UNKNOWN);
			list.add(model.getPriority() != null ? model.getPriority().toString() : SystemConstant.UNKNOWN);
			list.add(model.getIsDeleted() ? "Khóa" : "Mở");
		}
		
		return list;
	}
}
