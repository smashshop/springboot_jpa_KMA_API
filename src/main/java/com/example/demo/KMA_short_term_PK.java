package com.example.demo;

import java.io.Serializable;

import lombok.Data;

@Data
public class KMA_short_term_PK implements Serializable{

	private String fcstDate;
	private String fcstTime;
	private String category;
	
}
