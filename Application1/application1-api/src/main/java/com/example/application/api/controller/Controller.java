package com.example.application.api.controller;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.application.api.model.Log;
import com.example.application.api.model.LogStatus;

@RestController
@RequestMapping("/log")
public class Controller {
	
	@Autowired
	private MessageSource messageSource;
	
	@PostMapping
	public ResponseEntity<?> registrarLog(@Valid @RequestBody Log log, HttpServletResponse response) {
		Log teste = log;
//
//		if(log.getMessage()==null) {
//			String mensagemUsuario = messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new LogStatus(mensagemUsuario,mensagemUsuario,404));
//		}
		try {
			System.out.println("Log registrado com sucesso: "+teste.getMessage());
		} catch(Exception ex) {
			
		}
		
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("")
				.buildAndExpand(0).toUri();
		response.setHeader("Location", uri.toASCIIString());
		String mensagemUsuario = messageSource.getMessage("mensagem.sucesso", null, LocaleContextHolder.getLocale());

		return ResponseEntity.ok(new LogStatus(mensagemUsuario,mensagemUsuario,0));
	}
}


