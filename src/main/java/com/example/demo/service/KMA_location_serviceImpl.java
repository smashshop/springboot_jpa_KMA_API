package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.dto.KMA_location_DTO;
import com.example.demo.repository.KMA_location_repository;

@Service
public class KMA_location_serviceImpl implements KMA_location_service{

	private KMA_location_repository location_repository;
	private ModelMapper mapper;
	
	@Autowired
	public KMA_location_serviceImpl(KMA_location_repository location_repository, ModelMapper mapper) {
		this.location_repository = location_repository;
		this.mapper = mapper;
	}
	
	@Override
	public List<KMA_location_DTO> get_location(Map<String, String> params) {
		
		List<KMA_location_DTO> location_list = new ArrayList<KMA_location_DTO>();
		location_list = location_repository.findAll().stream()
				.map(i -> mapper.map(i, KMA_location_DTO.class))
				.collect(Collectors.toList());
		System.out.println(location_list.size());
		return location_list;
	}
	
	@Override
	public KMA_location_DTO get_code(String areacode) {
		return mapper.map(location_repository.findById(areacode).get(), KMA_location_DTO.class) ;
	}
}
