package com.toanhuuvuong.converter;

import java.util.ArrayList;
import java.util.List;

import com.toanhuuvuong.constant.SystemConstant;
import com.toanhuuvuong.model.AccountModel;

public class AccountConverter  implements IConverter<AccountModel>
{
	@Override
	public List<String> convert(AccountModel model)
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
			list.add(model.getUsername() != null 
					? model.getUsername() 
					: SystemConstant.UNKNOWN);
			list.add(model.getRole() != null && model.getRole().getName() != null 
					? model.getRole().getName() 
					: SystemConstant.UNKNOWN);
			list.add(model.getIsDeleted() 
					? "Khóa" 
					: "Mở");
		}
		
		return list;
	}
}