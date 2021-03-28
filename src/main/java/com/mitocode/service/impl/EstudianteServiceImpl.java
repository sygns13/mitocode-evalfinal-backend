package com.mitocode.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mitocode.model.Estudiante;
import com.mitocode.repo.IEstudianteRepo;
import com.mitocode.repo.IGenericRepo;
import com.mitocode.service.IEstudianteService;

import reactor.core.publisher.Flux;

@Service
public class EstudianteServiceImpl extends CRUDImpl<Estudiante, String> implements IEstudianteService {
	
	@Autowired
	private IEstudianteRepo repo;
	
	@Override
	protected IGenericRepo<Estudiante, String> getRepo() {		
		return repo;
	}

	@Override
	public Flux<Estudiante> ObtenerEstudiantesOrdenados() {
		return repo.findAll(Sort.by(Sort.Direction.DESC,"edad"));
	}

}
