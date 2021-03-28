package com.mitocode.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "matriculas")
public class Matricula {
	
	@Id
	private String id;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime fecha = LocalDateTime.now();
	
	@NotNull(message = "Debe de enviar el estudiante matriculado")
	@Field(name = "estudiante")
	private Estudiante estudiante;
	
	//@DBRef Not supported
	@NotNull(message = "Debe de enviar los cursos a matricular")
	private List<Curso> cursos;
	
	
	@NotNull(message = "Debe de indicar el estado del curso")
	@Field(name = "estado")
	private boolean estado;
	
	public Matricula() {
		super();
	}

	public Matricula(String id, LocalDateTime fecha, Estudiante estudiante, List<Curso> cursos, boolean estado) {
		this.id = id;
		this.fecha = fecha;
		this.estudiante = estudiante;
		this.cursos = cursos;
		this.estado = estado;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public Estudiante getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	

	
	
	

	
	
	
	
	

}
