package com.mitocode.repo;

import org.springframework.data.mongodb.repository.Query;

import com.mitocode.model.Estudiante;

import reactor.core.publisher.Flux;

public interface IEstudianteRepo extends IGenericRepo<Estudiante, String> {


}
