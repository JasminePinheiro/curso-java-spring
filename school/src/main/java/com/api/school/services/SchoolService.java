package com.api.school.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.school.models.SchoolModel;
import com.api.school.repositories.SchoolRepository;

import jakarta.transaction.Transactional;

@Service
public class SchoolService {
	 
//	injeção de dependencias 
//	final SchoolRepository schoolRepository;
//
//	public SchoolService(SchoolRepository schoolRepository) {
//		this.schoolRepository = schoolRepository;
//	}
	
	@Autowired
	SchoolRepository schoolRepository;

	@Transactional
	public SchoolModel save(SchoolModel schoolModel) {
		return schoolRepository.save(schoolModel);
	}

	public List <SchoolModel> findAll() {
		return schoolRepository.findAll();
	}

	public Optional<SchoolModel> findById(long id) {
		return schoolRepository.findById(id);
	}

	@Transactional
	public void delete(SchoolModel schoolModel) {
		schoolRepository.delete(schoolModel);
	}

	

}
