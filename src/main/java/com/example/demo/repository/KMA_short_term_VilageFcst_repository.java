package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.entity.KMA_short_term_VilageFcst;

@Repository
public interface KMA_short_term_VilageFcst_repository extends JpaRepository<KMA_short_term_VilageFcst, String>{

	public List<KMA_short_term_VilageFcst> findByBaseDateAndBaseTimeAndNxAndNy(String BaseDate, String BaseTime, String Nx, String Ny);
	
}
