package com.algaworks.algalog.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Destinatario {

	@NotBlank
	@Column(name = "destinatario_nome")
	public String nome;
	
	@NotBlank
	@Column(name = "destinatario_logradouro")
	public String logradouro;
	
	@NotBlank
	@Column(name = "destinatario_numero")
	public String numero;
	
	@NotBlank
	@Column(name = "destinatario_complemento")
	public String complemento;
	
	@NotBlank
	@Column(name = "destinatario_bairro")
	public String bairro;
}
