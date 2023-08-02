package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.demo.domain.dto.KMA_short_term_DTO;
import com.example.demo.domain.entity.KMA_short_term_UltraSrtFcst;
import com.example.demo.domain.entity.KMA_short_term_VilageFcst;
import com.example.demo.repository.KMA_short_term_UltraSrtFcst_repository;
import com.example.demo.repository.KMA_short_term_VilageFcst_repository;

@Service
public class KMA_short_term_serviceImpl implements KMA_short_term_service{

	private KMA_short_term_VilageFcst_repository short_term_VilageFcst_repository;
	private KMA_short_term_UltraSrtFcst_repository short_term_UltraSrtFcst_repository;
	private ModelMapper modelMapper;
	
	public KMA_short_term_serviceImpl(KMA_short_term_VilageFcst_repository short_term_VilageFcst_repository, ModelMapper modelMapper, KMA_short_term_UltraSrtFcst_repository short_term_UltraSrtFcst_repository) {
		this.short_term_VilageFcst_repository = short_term_VilageFcst_repository;
		this.modelMapper = modelMapper;
		this.short_term_UltraSrtFcst_repository = short_term_UltraSrtFcst_repository;
	}
	
	//단기 예보
	@Override
	public void insert_short_term_VilageFcst(List<KMA_short_term_DTO> short_term_DTO) {
		
		short_term_VilageFcst_repository.saveAll(short_term_DTO
					.stream()
					.map(i -> modelMapper.map(i, KMA_short_term_VilageFcst.class))
					.collect(Collectors.toList())
				);
		
	}
	
	@Override
	public List<KMA_short_term_DTO> get_short_term_VilageFcst(KMA_short_term_DTO short_term_DTO) {
		
		return short_term_VilageFcst_repository.findByBaseDateAndBaseTimeAndNxAndNy(short_term_DTO.getBaseDate(), short_term_DTO.getBaseTime(), short_term_DTO.getNx(), short_term_DTO.getNy())
				.stream()
				.map(i -> modelMapper.map(i, KMA_short_term_DTO.class))
				.collect(Collectors.toList());
	}
	
	
	//초단기 예보
	@Override
	public void insert_short_term_UltraSrtFcst(List<KMA_short_term_DTO> short_term_DTO) {
		
		short_term_UltraSrtFcst_repository.saveAll(short_term_DTO
					.stream()
					.map(i -> modelMapper.map(i, KMA_short_term_UltraSrtFcst.class))
					.collect(Collectors.toList())
				);
		
	}
	
	@Override
	public List<KMA_short_term_DTO> get_short_term_UltraSrtFcst(KMA_short_term_DTO short_term_DTO) {
		return short_term_UltraSrtFcst_repository.findByBaseDateAndBaseTimeAndNxAndNy(short_term_DTO.getBaseDate(), short_term_DTO.getBaseTime(), short_term_DTO.getNx(), short_term_DTO.getNy())
				.stream()
				.map(i -> modelMapper.map(i, KMA_short_term_DTO.class))
				.collect(Collectors.toList());
	}
	
}
