package com.mitocode.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "estudiantes")
public class Estudiante {
	
	@Id
	private String id;
	
	@NotNull(message = "Es necesario enviar el nombre")
	@Size(min = 2, message = "El nombre debe de tener mínimo dos caracteres")
	@Field(name = "nombres")
	private String nombres;

	@NotNull(message = "Es necesario enviar el apellido")
	@Size(min = 2, message = "El apellido debe de tener mínimo dos caracteres")
	@Field(name = "apellidos")
	private String apellidos;
	
	@NotNull(message = "Es necesario enviar el DNI")
	@Size(min = 8, max = 8, message = "El DNI debe de tener 08 caracteres")
	@Field(name = "dni")
	private String dni;
	
	@NotNull(message = "Es necesario indicar la edad")
	@Min(value = 0, message = "La edad mínima debe de ser cero")
	@Field(name = "edad")
	private Integer edad;

	public Estudiante() {
		super();
	}

	public Estudiante(String id,String nombres, String apellidos, String dni,Integer edad) {
		this.id = id;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.dni = dni;
		this.edad = edad;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}
	
	
	
	

}
