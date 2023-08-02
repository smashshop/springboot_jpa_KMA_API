package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.entity.KMA_mid_term;

public interface KMA_mid_term_repository extends JpaRepository<KMA_mid_term, String>{

	public KMA_mid_term findByTmFcAndRegId(String TmFc, String RegId);
	
}
