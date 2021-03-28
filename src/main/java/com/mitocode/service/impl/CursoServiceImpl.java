package com.mitocode.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.model.Curso;
import com.mitocode.repo.ICursoRepo;
import com.mitocode.repo.IGenericRepo;
import com.mitocode.service.ICursoService;

@Service
public class CursoServiceImpl extends CRUDImpl<Curso, String> implements ICursoService {
	
	@Autowired
	private ICursoRepo repo;
	
	@Override
	protected IGenericRepo<Curso, String> getRepo() {		
		return repo;
	}

}
