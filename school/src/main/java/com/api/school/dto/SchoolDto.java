package com.api.school.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
// O dto ajuda na criação de validaçõe nos campos da minha tabela

public class SchoolDto {
	
	@NotBlank
	private String name;
	@NotNull
	private Integer age;

}
