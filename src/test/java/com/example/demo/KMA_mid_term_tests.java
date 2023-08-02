package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.repository.KMA_mid_term_repository;

@SpringBootTest
public class KMA_mid_term_tests {

	@Autowired
	KMA_mid_term_repository kma_mid_term_repository;
	
//	@Test
//	public void select_test() {
//		System.out.println(kma_mid_term_repository.findByTmFcAndRegId("202308010600", "108"));
//	}
	
//	@Test
//	public void insert_test() {
//		formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
//		kma_mid_term_repository.save(KMA_mid_term.builder()
//												.stnId(111)
//												.tmFc(LocalDate.parse("202307311800", formatter))
//												.build()
//									);
//	}
	
}
