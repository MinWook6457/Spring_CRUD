package com.insilicogen.CRUD_PRJ.user.service.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class UserFieldDto {
	private String name;
	private String value;
}
