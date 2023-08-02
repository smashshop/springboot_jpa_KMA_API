package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.entity.KMA_location;

@Repository
public interface KMA_location_repository extends JpaRepository<KMA_location, String>{
	
}
