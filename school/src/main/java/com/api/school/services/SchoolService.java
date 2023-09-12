package com.api.school.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.school.repositories.SchoolRepository;

@Service
public class SchoolService {
	 
	// injeção de dependencias 
	@Autowired
	SchoolRepository schoolRespository;
	

}
