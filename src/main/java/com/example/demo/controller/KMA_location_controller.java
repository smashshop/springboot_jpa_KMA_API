package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domain.dto.KMA_location_DTO;
import com.example.demo.service.KMA_location_service;

@Controller
@RequestMapping("/KMA")
public class KMA_location_controller {

	private KMA_location_service location_service;
	
	public KMA_location_controller (KMA_location_service location_service) {
		this.location_service = location_service;
	}
	
	 @PostMapping(value = "/short/location", produces = "application/json;charset=UTF-8")
     public ResponseEntity<List<KMA_location_DTO>> get_location(@RequestParam Map<String, String> params){
         return new ResponseEntity<List<KMA_location_DTO>> (location_service.get_location(params), HttpStatus.OK);
     }
	
}
