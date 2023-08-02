package com.example.demo.service;

import com.example.demo.domain.dto.KMA_mid_term_MidLandFcst_DTO;
import com.example.demo.domain.dto.KMA_mid_term_MidSeaFcst_DTO;
import com.example.demo.domain.dto.KMA_mid_term_MidTa_DTO;
import com.example.demo.domain.dto.KMA_mid_term_outlook_DTO;

public interface KMA_mid_term_service {

	//중기전망 조회
	KMA_mid_term_outlook_DTO get_KMA_mid_term_outlook(KMA_mid_term_outlook_DTO mid_term_outlook_DTO);
	void insert_KMA_mid_term_outlook(KMA_mid_term_outlook_DTO mid_term_outlook_DTO);
	
	//중기육상예보 조회
	KMA_mid_term_MidLandFcst_DTO get_KMA_mid_term_MidLandFcst(KMA_mid_term_MidLandFcst_DTO mid_term_MidLandFcst_DTO);
	void insert_KMA_mid_term_MidLandFcst(KMA_mid_term_MidLandFcst_DTO mid_term_MidLandFcst_DTO);
	
	//중기기온예보 조회
	KMA_mid_term_MidTa_DTO get_KMA_mid_term_MidTa(KMA_mid_term_MidTa_DTO mid_term_MidTa_DTO);
	void insert_KMA_mid_term_MidTa(KMA_mid_term_MidTa_DTO mid_term_MidTa_DTO);
	
	//중기해상예보 조회
	KMA_mid_term_MidSeaFcst_DTO get_KMA_mid_term_MidSeaFcst(KMA_mid_term_MidSeaFcst_DTO mid_term_MidSeaFcst_DTO);
	void insert_KMA_mid_term_MidSeaFcst(KMA_mid_term_MidSeaFcst_DTO mid_term_MidSeaFcst_DTO);
	
}
