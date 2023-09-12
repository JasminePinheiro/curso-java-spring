package com.api.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.school.models.SchoolModel;

public interface SchoolRepository extends JpaRepository<SchoolModel, Long>{ // realizando tranasações com o banco de dados 

	
	
	
}
