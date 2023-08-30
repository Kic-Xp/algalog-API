package com.algaworks.algalog.api.assembler;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.algaworks.algalog.api.model.EntregaModel;
import com.algaworks.algalog.api.model.input.ClienteInput;
import com.algaworks.algalog.api.model.input.EntregaInput;
import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.model.Entrega;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class EntregaAssembler {

	private ModelMapper modelMapper;
	
	public EntregaModel toModel(Entrega entrega){
		return modelMapper.map(entrega, EntregaModel.class);
	}
	public List<EntregaModel> toCollectionModel(List<Entrega> entregas){
		return entregas.stream().map(this::toModel).toList();
	}
	
	public Entrega toEntity(EntregaInput entregaInput){
		return modelMapper.map(entregaInput, Entrega.class);
	}
	public Cliente toEntity(ClienteInput clienteInput){
		return modelMapper.map(clienteInput, Cliente.class);
	}
}
