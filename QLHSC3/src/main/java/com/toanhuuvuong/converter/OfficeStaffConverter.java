package com.toanhuuvuong.converter;

import java.util.ArrayList;
import java.util.List;

import com.toanhuuvuong.constant.SystemConstant;
import com.toanhuuvuong.model.OfficeStaff;

public class OfficeStaffConverter implements IConverter<OfficeStaff>
{
	@Override
	public List<String> convert(OfficeStaff model)
	{
		List<String> list = new ArrayList<String>();
		
		if(model == null)
		{
			list.add("Mã");
			list.add("Họ tên");
			list.add("Số điện thoại");
			list.add("Giới tính");
			list.add("Ngày sinh");
			list.add("Địa chỉ");
			list.add("Lương");
			list.add("Trạng thái");
			list.add("Tài khoản được cấp");
		}
		else
		{
			list.add(model.getCode() != null ? model.getCode() : SystemConstant.UNKNOWN);
			list.add(model.getName() != null ? model.getName() : SystemConstant.UNKNOWN);
			list.add(model.getPhone() != null ? model.getPhone() : SystemConstant.UNKNOWN);
			list.add(model.getGender() != null ? model.getGender() : SystemConstant.UNKNOWN);
			list.add(model.getBirth() != null ? model.getBirth().toString() : SystemConstant.UNKNOWN);
			list.add(model.getAddress() != null ? model.getAddress() : SystemConstant.UNKNOWN);
			list.add(model.getSalary() != null ? model.getSalary().toString() : SystemConstant.UNKNOWN);
			list.add(model.getIsDeleted() != null && model.getIsDeleted() ? "Khóa" : "Mở");
			list.add(model.getAccount() != null && model.getAccount().getUsername() != null 
					? model.getAccount().getUsername()
					: SystemConstant.UNKNOWN);
		}
		
		return list;
	}
}