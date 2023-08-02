package com.example.demo.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class KMA_short_term_DTO {

	private String baseDate;
	private String baseTime;
	private String category;
	private String fcstDate;
	private String fcstTime;
	private String fcstValue;
	private String nx;
	private String ny;
	
}
