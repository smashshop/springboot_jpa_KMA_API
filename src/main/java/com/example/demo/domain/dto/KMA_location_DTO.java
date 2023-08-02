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
public class KMA_location_DTO {

	private String service_name;
	
    private String areacode;

    private String step1;

    private String step2;

    private String step3;

    private String nx;

    private String ny;
    
    private String baseDate;
	private String baseTime;
	
}
