package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.demo.controller.KMA_short_rest_controller;
import com.example.demo.domain.dto.KMA_short_term_DTO;
import com.example.demo.domain.entity.KMA_location;
import com.example.demo.repository.KMA_location_repository;
import com.example.demo.repository.KMA_mid_term_repository;
import com.example.demo.service.KMA_short_term_service;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.bytebuddy.NamingStrategy.Suffixing.BaseNameResolver.ForGivenType;

@AutoConfigureMockMvc
@WebMvcTest(KMA_short_rest_controller.class)
public class KMA_short_term_VilageFcst_tests {

	@MockBean
	private KMA_short_term_service vilageFcst_service;
	@MockBean
	private KMA_location_repository kma_location_repository;
	@MockBean
	private KMA_mid_term_repository kma_mid_term_repository;
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper mapper;
	
	//service 데이터 조회 테스트
//	@Test
//	public void vilageFcst_select_repo() { 
//		KMA_short_term_DTO VilageFcst_DTO = new KMA_short_term_DTO();
//		VilageFcst_DTO.setBaseDate("20230802");
//		VilageFcst_DTO.setBaseTime("0200");
//		VilageFcst_DTO.setNx("97");
//		VilageFcst_DTO.setNy("74");
//		List<KMA_short_term_DTO> list_dto = vilageFcst_service.get_short_term_VilageFcst(VilageFcst_DTO);
//		System.out.println(list_dto.size());
//		for(KMA_short_term_DTO i : list_dto) {
//			System.out.println(i.toString());
//		}
//	}

	//service 데이터 입력 테스트
//	@org.junit.Test
//	public void vilageFcst_insert_repo() { 
//		KMA_short_term_VilageFcst_DTO VilageFcst_DTO ;
//		List<KMA_short_term_VilageFcst_DTO> list_dto = new ArrayList<KMA_short_term_VilageFcst_DTO>();
//		for(int i = 1; i < 5; i++) {
//			VilageFcst_DTO = new KMA_short_term_VilageFcst_DTO();
//			VilageFcst_DTO.setBaseDate("20220731");
//			VilageFcst_DTO.setBaseTime("0600");
//			VilageFcst_DTO.setCategory("PCP" + i);
//			VilageFcst_DTO.setFcstDate("20230861");
//			VilageFcst_DTO.setFcstTime("0700");
//			VilageFcst_DTO.setFcstValue("적설없음");
//			VilageFcst_DTO.setNx("55");
//			VilageFcst_DTO.setNy("127");
//			System.out.println(VilageFcst_DTO.toString());
//			list_dto.add(VilageFcst_DTO);
//		}
//		System.out.println(list_dto.toArray().toString());
//		vilageFcst_service.insert_short_term_VilageFcst(list_dto);
//	}
	
	
	//controller 데이터 입력 테스트
//	@Test
//	public void insert_short_term_VilageFcst() throws Exception{
//		
//		//given
//		String body = mapper.writeValueAsString(
//					KMA_short_term_DTO.builder().baseDate("20230802").baseTime("0200").nx("55").ny("127").build()
//				);
//		
//		
//		mvc.perform(MockMvcRequestBuilders
//					.post("/KMA/short/VilageFcst")
//					.content(body)
//					.contentType(MediaType.APPLICATION_JSON)
//				).andExpect(MockMvcResultMatchers.status().isCreated())
//				.andDo(MockMvcRestDocumentation.document("/KMA/short/VilageFcst"));
//	}
	
	//controller 데이터 조회 테스트
//	@Test
//	public void vilageFcst_get_cont() throws Exception{
//		
//		mvc.perform(MockMvcRequestBuilders
//					.get("/KMA/short/VilageFcst")
//					.param("baseDate", "20230801")
//					.param("baseTime", "0200")
//					.param("nx", "55")
//					.param("ny", "127")
//				).andExpect(MockMvcResultMatchers.status().isOk());
//		
//	}
	
}

