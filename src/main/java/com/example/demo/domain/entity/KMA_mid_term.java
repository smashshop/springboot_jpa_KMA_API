package com.example.demo.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import com.example.demo.KMA_mid_term_PK;

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
@IdClass(KMA_mid_term_PK.class)
public class KMA_mid_term {
	
	@Id
	private String regId;
	@Id
	private String tmFc;
	@Column(length = 1000)
	private String wfSv;
	
	private int rnSt3Am;
	private int rnSt3Pm;
	private int rnSt4Am;
	private int rnSt4Pm;
	private int rnSt5Am;
	private int rnSt5Pm;
	private int rnSt6Am;
	private int rnSt6Pm;
	private int rnSt7Am;
	private int rnSt7Pm;
	private int rnSt8;
	private int rnSt9;
	private int rnSt10;
	
	private int taMin3;
	private int taMin3Low;
	private int taMin3High;
	private int taMax3;
	private int taMax3Low;
	private int taMax3High;
	private int taMin4;
	private int taMin4Low;
	private int taMin4High;
	private int taMax4;
	private int taMax4Low;
	private int taMax4High;
	private int taMin5;
	private int taMin5Low;
	private int taMin5High;
	private int taMax5;
	private int taMax5Low;
	private int taMax5High;
	private int taMin6;
	private int taMin6Low;
	private int taMin6High;
	private int taMax6;
	private int taMax6Low;
	private int taMax6High;
	private int taMin7;
	private int taMin7Low;
	private int taMin7High;
	private int taMax7;
	private int taMax7Low;
	private int taMax7High;
	private int taMin8;
	private int taMin8Low;
	private int taMin8High;
	private int taMax8;
	private int taMax8Low;
	private int taMax8High;
	private int taMin9;
	private int taMin9Low;
	private int taMin9High;
	private int taMax9;
	private int taMax9Low;
	private int taMax9High;
	private int taMin10;
	private int taMin10Low;
	private int taMin10High;
	private int taMax10;
	private int taMax10Low;
	private int taMax10High;
	
	private String wf3Am;
	private String wf3Pm;
	private String wf4Am;
	private String wf4Pm;
	private String wf5Am;
	private String wf5Pm;
	private String wf6Am;
	private String wf6Pm;
	private String wf7Am;
	private String wf7Pm;
	private String wf8;
	private String wf9;
	private String wf10;
	private int wh3AAm;
	private int wh3APm;
	private int wh3BAm;
	private int wh3BPm;
	private int wh4AAm;
	private int wh4APm;
	private int wh4BAm;
	private int wh4BPm;
	private int wh5AAm;
	private int wh5APm;
	private int wh5BAm;
	private int wh5BPm;
	private int wh6AAm;
	private int wh6APm;
	private int wh6BAm;
	private int wh6BPm;
	private int wh7AAm;
	private int wh7APm;
	private int wh7BAm;
	private int wh7BPm;
	private int wh8A;
	private int wh8B;
	private int wh9A;
	private int wh9B;
	private int wh10A;
	private int wh10B;
	
}
