package com.mitocode.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "cursos")
public class Curso {
	
	@Id
	private String id;
	
	@NotNull(message = "Es necesario enviar el nombre del curso")
	@Size(min = 3, message = "El nombre debe de tener mínimo tres caracteres")
	@Field(name = "nombre")
	private String nombre;

	@Field(name = "siglas")
	private String siglas;
	
	@NotNull(message = "Debe de indicar el estado del curso")
	@Field(name = "estado")
	private boolean estado;
	
	public Curso() {
		super();
	}

	public Curso(String id, String nombre, String siglas, boolean estado) {
		this.id = id;
		this.nombre = nombre;
		this.siglas = siglas;
		this.estado = estado;

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSiglas() {
		return siglas;
	}

	public void setSiglas(String siglas) {
		this.siglas = siglas;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	

	
	
	
	
	

}
