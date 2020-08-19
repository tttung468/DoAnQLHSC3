package com.toanhuuvuong.converter;

import java.util.ArrayList;
import java.util.List;

import com.toanhuuvuong.constant.SystemConstant;
import com.toanhuuvuong.model.Student;

public class StudentConverter implements IConverter<Student>
{
	@Override
	public List<String> convert(Student model)
	{
		List<String> list = new ArrayList<String>();
		
		if(model == null)
		{
			list.add("Mã");
			list.add("Họ tên");
			list.add("Số điện thoại");
			list.add("Số CMND");
			list.add("Email");
			list.add("Giới tính");
			list.add("Ngày sinh");
			list.add("Địa chỉ");
			list.add("Trạng thái học tập");
			list.add("Trạng thái");
			list.add("Dân tộc");
			list.add("Tôn giáo");
			list.add("Quốc tịch");
		}
		else
		{
			list.add(model.getCode() != null ? model.getCode() : SystemConstant.UNKNOWN);
			list.add(model.getName() != null ? model.getName() : SystemConstant.UNKNOWN);
			list.add(model.getPhone() != null ? model.getPhone() : SystemConstant.UNKNOWN);
			list.add(model.getIdentifyCard() != null ? model.getIdentifyCard() : SystemConstant.UNKNOWN);
			list.add(model.getEmail() != null ? model.getEmail() : SystemConstant.UNKNOWN);
			list.add(model.getGender() != null ? model.getGender() : SystemConstant.UNKNOWN);
			list.add(model.getBirth() != null ? model.getBirth().toString() : SystemConstant.UNKNOWN);
			list.add(model.getAddress() != null ? model.getAddress() : SystemConstant.UNKNOWN);
			list.add(model.getStatus() != null ? model.getStatus() : SystemConstant.UNKNOWN);
			list.add(model.getIsDeleted() != null && model.getIsDeleted() ? "Khóa" : "Mở");
			list.add(model.getEthnic() != null && model.getEthnic().getName() != null 
					? model.getEthnic().getName()
					: SystemConstant.UNKNOWN);
			list.add(model.getReligion() != null && model.getReligion().getName() != null 
					? model.getReligion().getName()
					: SystemConstant.UNKNOWN);
			list.add(model.getNationality() != null && model.getNationality().getName() != null 
					? model.getNationality().getName()
					: SystemConstant.UNKNOWN);
		}
		
		return list;
	}
}