package com.api.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.school.models.SchoolModel;

@Repository
public interface SchoolRepository extends JpaRepository<SchoolModel, Long>{ // realizando tranasações com o banco de dados 
	
	
}
