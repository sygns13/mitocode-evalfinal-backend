package com.mitocode.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mitocode.model.Estudiante;
import com.mitocode.service.IEstudianteService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/estudiantes")
public class EstudianteController {
	
	@Autowired	
	private IEstudianteService service;
	
	@GetMapping
	public Mono<ResponseEntity<Flux<Estudiante>>> listar() {
		//Flux<Estudiante> fxEstudiantes = service.listar();
		Flux<Estudiante> fxEstudiantes = service.ObtenerEstudiantesOrdenados();
		return Mono.just(ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(fxEstudiantes)
			);			
	}
	
	@GetMapping("/{id}")
	public Mono<ResponseEntity<Estudiante>> listarPorId(@PathVariable("id") String id){
		return service.listarPorId(id) 				
					.map(p -> ResponseEntity.ok()
							.contentType(MediaType.APPLICATION_JSON)
							.body(p)
					)
					.defaultIfEmpty(ResponseEntity.notFound().build());		
	}
	
	@PostMapping
	public Mono<ResponseEntity<Estudiante>> registrar(@Valid @RequestBody Estudiante estudiante, final ServerHttpRequest req) {
		return service.registrar(estudiante)
				.map(p -> ResponseEntity.created(URI.create(req.getURI().toString().concat(p.getId())))
						.contentType(MediaType.APPLICATION_JSON)
						.body(p)
				);
	}
	
	@PutMapping("/{id}")
	public Mono<ResponseEntity<Estudiante>> modificar(@Valid @RequestBody Estudiante estudiante, @PathVariable("id") String id) {
			
		Mono<Estudiante> monoEstudiante = Mono.just(estudiante);
		Mono<Estudiante> monoBD = service.listarPorId(id);
		
		return monoBD
				.zipWith(monoEstudiante, (bd, p) -> {
					bd.setId(id);
					bd.setNombres(p.getNombres());
					bd.setApellidos(p.getApellidos());
					bd.setDni(p.getDni());
					bd.setEdad(p.getEdad());
					return bd;
				})
				.flatMap(service::modificar) //x -> service.modificar(x)
				.map(pl -> ResponseEntity.ok()
						.contentType(MediaType.APPLICATION_JSON)
						.body(pl))
				.defaultIfEmpty(new ResponseEntity<Estudiante>(HttpStatus.NOT_FOUND));
	}
	
	@DeleteMapping("/{id}")
	public Mono<ResponseEntity<Void>> eliminar(@PathVariable("id") String id){
		return service.listarPorId(id)
				.flatMap(p -> {
					return service.eliminar(p.getId()) //Mono<Void>
							.then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
				})
				.defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
	}

}
