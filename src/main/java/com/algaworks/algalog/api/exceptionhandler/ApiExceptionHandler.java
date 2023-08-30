package com.algaworks.algalog.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.algaworks.algalog.api.exceptionhandler.Problema.Campo;
import com.algaworks.algalog.domain.exception.DomainException;
import com.algaworks.algalog.domain.exception.EntidadeNaoEncontradaException;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{

	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> 
	handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<Campo> campo = new ArrayList<>();
		 
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			String nome = ((FieldError)error).getField();
			String mensagem = messageSource.getMessage(error,
					LocaleContextHolder.getLocale());
			
			campo.add(new Campo(nome, mensagem));
		}
		
		Problema problema = new Problema();
		problema.setStatus(status.value());
		problema.setDataHora(OffsetDateTime.now());
		problema.setTitulo(" Um ou Mais Campos Estão preenchidos Incorretamente,"
				+ " Faça o Preechimento Correto e tente Novamente.");
		problema.setCampo(campo);
		
		return handleExceptionInternal(ex, problema, headers, status, request);
	}
	
	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<Object> handleEntidadeNaoEncontrada(
			EntidadeNaoEncontradaException ex, 
			WebRequest request){
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		Problema problema = new Problema();
		problema.setStatus(status.value());
		problema.setDataHora(OffsetDateTime.now());
		problema.setTitulo(ex.getMessage());
		
		return handleExceptionInternal(ex, problema,
				new HttpHeaders(), status, request);
	}
		
	@ExceptionHandler(DomainException.class)
	public ResponseEntity<Object> handleDomainException(DomainException ex, 
			WebRequest request){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		Problema problema = new Problema();
		problema.setStatus(status.value());
		problema.setDataHora(OffsetDateTime.now());
		problema.setTitulo(ex.getMessage());
		
		return handleExceptionInternal(ex, problema,
				new HttpHeaders(), status, request);
	}
	
	
	
	
	
	
	
	
	

}
