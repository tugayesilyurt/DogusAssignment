package com.dogus.assisgnment.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.DeleteQuery;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.dogus.assisgnment.entity.Numbers;

public interface NumberRepository extends MongoRepository<Numbers, String> {

	Optional<Numbers> findByNumber(int number);

	@DeleteQuery
	void deleteByNumber(int number);
	
	Optional<Numbers> findTopByOrderByNumberAsc();
	
	Optional<Numbers> findTopByOrderByNumberDesc();

}
