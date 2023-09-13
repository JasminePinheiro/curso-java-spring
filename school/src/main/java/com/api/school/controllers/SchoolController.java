package com.api.school.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.school.dto.SchoolDto;
import com.api.school.models.SchoolModel;
import com.api.school.services.SchoolService;

import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600) // o controlador irá aceitar todas as solicitações
@RequestMapping("/school")
public class SchoolController {

	final SchoolService schoolService;

	public SchoolController(SchoolService schoolService) {
		this.schoolService = schoolService;
	}

	@PostMapping
	public ResponseEntity<Object> saveSchool(@RequestBody @Valid SchoolDto schoolDto) { // montar uma resposta usando o
																						// método http
		var schoolModel = new SchoolModel();
		BeanUtils.copyProperties(schoolDto, schoolModel); // serve para converter os dados de dto pra model
		schoolModel.setDataRegistro(LocalDateTime.now(ZoneId.of("UTC")));
		return ResponseEntity.status(HttpStatus.CREATED).body(schoolService.save(schoolModel));

	}

	@GetMapping
	public ResponseEntity<List<SchoolModel>> getAllSchool() {
		return ResponseEntity.status(HttpStatus.OK).body(schoolService.findAll()); // busca todos os registro do banco
																					// // de dados
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getOneSchool(@PathVariable(value = "id") Long id) {
		Optional<SchoolModel> schoolModelOptional = schoolService.findById(id);
		if (!schoolModelOptional.isPresent()) {
			  Map<String, String> errorResponse = new HashMap<>();
			  
		        errorResponse.put("error", "Aluno não encontrado");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse );
		}
		return ResponseEntity.status(HttpStatus.OK).body(schoolModelOptional.get());
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object>deleteSchool(@PathVariable(value = "id") Long id){
		Optional<SchoolModel> schoolModelOptional = schoolService.findById(id);
		if(!schoolModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não existe");
		}
		schoolService.delete(schoolModelOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Aluno excluido com sucess");
	}
	

}

//A anotação @CrossOrigin(origins = "*", maxAge = 3600) é usada quando você está criando uma API web. Ela define as regras para quem pode acessar sua API e quanto tempo essas regras são lembradas.
//
//origins = "*": Isso significa que qualquer site na internet pode usar sua API. É como deixar a porta aberta para todos.
//
//maxAge = 3600: Isso diz ao navegador que ele pode se lembrar dessas regras por 1 hora. Depois disso, ele verifica novamente.
//
//Basicamente, essa anotação permite que qualquer site acesse sua API, mas o navegador só guarda essas regras por um tempo limitado, para manter a segurança. É útil quando você quer compartilhar dados da sua API com outros sites.