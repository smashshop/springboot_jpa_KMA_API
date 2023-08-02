package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.dto.KMA_short_term_DTO;

public interface KMA_short_term_service {

	public void insert_short_term_VilageFcst(List<KMA_short_term_DTO> short_term_DTO);
	public List<KMA_short_term_DTO> get_short_term_VilageFcst(KMA_short_term_DTO short_term_DTO);
	
	public void insert_short_term_UltraSrtFcst(List<KMA_short_term_DTO> short_term_DTO);
	public List<KMA_short_term_DTO> get_short_term_UltraSrtFcst(KMA_short_term_DTO short_term_DTO);
	
}
