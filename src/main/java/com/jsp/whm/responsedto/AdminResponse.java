package com.jsp.whm.responsedto;


import com.jsp.whm.enums.AdminType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminResponse 
{
	private int adminId;
	private String name;
	private String email;
	private AdminType adminType;
}
