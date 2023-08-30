package com.algaworks.algalog.api.model.input;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.algaworks.algalog.domain.ValidationGroups;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteInput {

	@NotNull(groups = ValidationGroups.ClienteId.class)
	private Long id;
	
	@NotBlank
	@Size(max = 60)
	private String nome;
	
	@NotBlank
	@Email
	@Size(max = 255)
	private String email;
	
	private String telefone;
}
