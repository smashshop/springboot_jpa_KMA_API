package com.example.demo.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class KMA_mid_term_serviceinfo_DTO {

	private String loca_id;
	private String service_name;
	private String tmFc;
	private String loca;
	
}
