package com.algaworks.algalog.api.model.input;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

public class DestinatarioInput {

	@NotBlank
	public String nome;
	
	@NotBlank
	public String logradouro;
	
	@NotBlank
	public String numero;
	
	@NotBlank
	public String complemento;
	
	@NotBlank
	public String bairro;
}
