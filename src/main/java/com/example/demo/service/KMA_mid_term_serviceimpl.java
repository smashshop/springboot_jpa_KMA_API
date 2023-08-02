package com.example.demo.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.dto.KMA_mid_term_MidLandFcst_DTO;
import com.example.demo.domain.dto.KMA_mid_term_MidSeaFcst_DTO;
import com.example.demo.domain.dto.KMA_mid_term_MidTa_DTO;
import com.example.demo.domain.dto.KMA_mid_term_outlook_DTO;
import com.example.demo.domain.entity.KMA_mid_term;
import com.example.demo.repository.KMA_mid_term_repository;

@Service
public class KMA_mid_term_serviceimpl implements KMA_mid_term_service{

	private KMA_mid_term_repository kma_mid_term_repository;
	private ModelMapper modelMapper;
	
	@Autowired
	public KMA_mid_term_serviceimpl(KMA_mid_term_repository kma_mid_term_repository, ModelMapper modelMapper) {
		this.kma_mid_term_repository = kma_mid_term_repository;
		this.modelMapper = modelMapper;
	}
	
	@Override
	public void insert_KMA_mid_term_outlook(KMA_mid_term_outlook_DTO mid_term_outlook_DTO) {
		System.out.println("중기전망 서비스 데이터 입력");
		kma_mid_term_repository.save(modelMapper.map(mid_term_outlook_DTO, KMA_mid_term.class));
		
	}
	
	@Override
	public KMA_mid_term_outlook_DTO get_KMA_mid_term_outlook(KMA_mid_term_outlook_DTO mid_term_outlook_DTO) {
		System.out.println("중기전망 서비스 데이터 조회 호출");
		System.out.println(mid_term_outlook_DTO.toString());
		return modelMapper.map(kma_mid_term_repository.findByTmFcAndRegId(mid_term_outlook_DTO.getTmFc(), mid_term_outlook_DTO.getRegId()), KMA_mid_term_outlook_DTO.class);
		
	}
	
	@Override
	public void insert_KMA_mid_term_MidLandFcst(KMA_mid_term_MidLandFcst_DTO mid_term_MidLandFcst_DTO) {
		System.out.println("중기육상예보 서비스 데이터 입력");
		kma_mid_term_repository.save(modelMapper.map(mid_term_MidLandFcst_DTO, KMA_mid_term.class));
	}
	
	@Override
	public KMA_mid_term_MidLandFcst_DTO get_KMA_mid_term_MidLandFcst(KMA_mid_term_MidLandFcst_DTO mid_term_MidLandFcst_DTO) {
		System.out.println("중기육상예보 서비스 데이터 조회 호출");
		return modelMapper.map(kma_mid_term_repository.findByTmFcAndRegId(mid_term_MidLandFcst_DTO.getTmFc(), mid_term_MidLandFcst_DTO.getRegId()), KMA_mid_term_MidLandFcst_DTO.class);
		
	}
	
	@Override
	public void insert_KMA_mid_term_MidTa(KMA_mid_term_MidTa_DTO mid_term_MidTa_DTO) {
		System.out.println("중기육상예보 서비스 데이터 입력");
		kma_mid_term_repository.save(modelMapper.map(mid_term_MidTa_DTO, KMA_mid_term.class));
	}
	
	@Override
	public KMA_mid_term_MidTa_DTO get_KMA_mid_term_MidTa(KMA_mid_term_MidTa_DTO mid_term_MidTa_DTO) {
		System.out.println("중기육상예보 서비스 데이터 조회 호출");
		return modelMapper.map(kma_mid_term_repository.findByTmFcAndRegId(mid_term_MidTa_DTO.getTmFc(), mid_term_MidTa_DTO.getRegId()), KMA_mid_term_MidTa_DTO.class);
		
	}
	
	@Override
	public void insert_KMA_mid_term_MidSeaFcst(KMA_mid_term_MidSeaFcst_DTO mid_term_MidSeaFcst_DTO) {
		System.out.println("중기육상예보 서비스 데이터 입력");
		kma_mid_term_repository.save(modelMapper.map(mid_term_MidSeaFcst_DTO, KMA_mid_term.class));
	}
	
	@Override
	public KMA_mid_term_MidSeaFcst_DTO get_KMA_mid_term_MidSeaFcst(KMA_mid_term_MidSeaFcst_DTO mid_term_MidSeaFcst_DTO) {
		System.out.println("중기육상예보 서비스 데이터 조회 호출");
		return modelMapper.map(kma_mid_term_repository.findByTmFcAndRegId(mid_term_MidSeaFcst_DTO.getTmFc(), mid_term_MidSeaFcst_DTO.getRegId()), KMA_mid_term_MidSeaFcst_DTO.class);
		
	}
}
