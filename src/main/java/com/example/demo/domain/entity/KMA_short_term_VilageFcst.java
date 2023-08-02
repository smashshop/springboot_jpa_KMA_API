package com.example.demo.domain.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import com.example.demo.KMA_short_term_PK;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@IdClass(KMA_short_term_PK.class)
public class KMA_short_term_VilageFcst {

	private String baseDate;
	private String baseTime;
	@Id
	private String category;
	@Id
	private String fcstDate;
	@Id
	private String fcstTime;
	private String fcstValue;
	private String nx;
	private String ny;
	
}
