package com.example.demo.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.demo.domain.dto.KMA_location_DTO;

@Component
public interface KMA_location_service {

	List<KMA_location_DTO> get_location(Map<String, String> params);
	
	KMA_location_DTO get_code(String areacode);
	
}
