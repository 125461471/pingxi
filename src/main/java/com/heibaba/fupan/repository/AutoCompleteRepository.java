package com.heibaba.fupan.repository;

import org.springframework.data.repository.CrudRepository;

import com.heibaba.fupan.dto.AutoCompleteDto;

public interface AutoCompleteRepository extends CrudRepository<AutoCompleteDto, String> {
	
}
