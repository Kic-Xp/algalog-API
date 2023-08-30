package com.algaworks.algalog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.api.assembler.EntregaAssembler;
import com.algaworks.algalog.api.model.input.ClienteInput;
import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.repository.ClienteRepository;
import com.algaworks.algalog.domain.service.CatalogoClienteService;

import lombok.AllArgsConstructor;//lombok é um literal autowired!
@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	private ClienteRepository clienteRepository;
	private CatalogoClienteService catalogoClienteService;
	private EntregaAssembler entregaAssembler;
	
	@GetMapping
	public List<Cliente> listar() {
		
		return clienteRepository.findByNomeContaining("a");
	}
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long id) {
		return clienteRepository.findById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adicionar(@Valid @RequestBody ClienteInput clienteInput) {
		Cliente clienteSalvo = entregaAssembler
				.toEntity(clienteInput);
		return catalogoClienteService.salvar(clienteSalvo);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Cliente> atualizar(@Valid @PathVariable Long id,
			@RequestBody Cliente cliente){
		if(!clienteRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		cliente.setId(id);
		cliente = catalogoClienteService.salvar(cliente);
		
		return ResponseEntity.ok(cliente);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id){
		if(!clienteRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
			}
		catalogoClienteService.excluir(id);
		
		return ResponseEntity.noContent().build();
	}
}
