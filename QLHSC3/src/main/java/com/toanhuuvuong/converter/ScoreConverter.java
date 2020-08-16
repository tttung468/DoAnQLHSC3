package com.toanhuuvuong.converter;

import java.util.ArrayList;
import java.util.List;

import com.toanhuuvuong.constant.SystemConstant;
import com.toanhuuvuong.model.Score;

public class ScoreConverter implements IConverter<Score>
{
	@Override
	public List<String> convert(Score model)
	{
		List<String> list = new ArrayList<String>();
		
		if(model == null)
		{
			list.add("Mã");
			list.add("Họ tên");
			list.add("Giới tính");
			list.add("Trạng thái học tập");
			list.add("Trạng thái");
		}
		else
		{
			list.add(model.getStudent().getCode() != null ? model.getStudent().getCode() : SystemConstant.UNKNOWN);
			list.add(model.getStudent().getName() != null ? model.getStudent().getName() : SystemConstant.UNKNOWN);
			list.add(model.getStudent().getGender() != null ? model.getStudent().getGender() : SystemConstant.UNKNOWN);
			list.add(model.getStudent().getStatus() != null ? model.getStudent().getStatus() : SystemConstant.UNKNOWN);
			list.add(model.getStudent().getIsDeleted() != null && model.getIsDeleted() ? "Khóa" : "Mở");
		}
		
		return list;
	}
}