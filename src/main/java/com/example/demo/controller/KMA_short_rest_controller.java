package com.example.demo.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.dto.KMA_location_DTO;
import com.example.demo.domain.dto.KMA_short_term_DTO;
import com.example.demo.service.KMA_location_service;
import com.example.demo.service.KMA_short_term_service;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/KMA")
public class KMA_short_rest_controller {

	private KMA_short_term_service short_term_VilageFcst_service;
	private KMA_location_service location_service;
	
	@Autowired
	public KMA_short_rest_controller(KMA_short_term_service short_term_VilageFcst_service, KMA_location_service location_service) {
		this.short_term_VilageFcst_service = short_term_VilageFcst_service;
		this.location_service = location_service;
	}
	
	//API 데이터 받아와서 JSONArray로 반환
	public JSONArray api_to_JSon(KMA_location_DTO location_DTO) throws IOException, ParseException{
		
		StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/get" + location_DTO.getService_name()); /*URL*/
		urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=iC9YhAW%2F3B8TgTgKhHUr5hl9HZDjAWEcZdhSXzJKn7%2F2x6eZaFFoBjmZt%2BN4DheeDJwBo0CPHme3LG3R8OmLKQ%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("1000", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /*요청자료형식(XML/JSON) Default: XML*/
        urlBuilder.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode(location_DTO.getBaseDate(), "UTF-8")); /*‘21년 6월 28일 발표*/
        urlBuilder.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode(location_DTO.getBaseTime(), "UTF-8")); /*06시 발표(정시단위) */
        urlBuilder.append("&" + URLEncoder.encode("nx","UTF-8") + "=" + URLEncoder.encode(location_DTO.getNx(), "UTF-8")); /*예보지점의 X 좌표값*/
        urlBuilder.append("&" + URLEncoder.encode("ny","UTF-8") + "=" + URLEncoder.encode(location_DTO.getNy(), "UTF-8")); /*예보지점의 Y 좌표값*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        int response_code = conn.getResponseCode();
        System.out.println("Response code: " + response_code);
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        String result = sb.toString();
        System.out.println(result);
        rd.close();
        conn.disconnect();
        
        JSONParser parser = new JSONParser();
        JSONObject jsonObject;
        
    	jsonObject = (JSONObject)parser.parse(result);
    	JSONObject response = (JSONObject)jsonObject.get("response");
    	JSONObject body = (JSONObject)response.get("body");
    	JSONObject items = (JSONObject)body.get("items");
    	JSONArray jArray = (JSONArray)items.get("item");
    	
    	return jArray;
	}
	
	
	//단기 예보
	@PostMapping(value = "/short/VilageFcst", produces = "application/json;charset=UTF-8")
	public ResponseEntity<KMA_location_DTO> insert_short_term_VilageFcst(KMA_location_DTO location_DTO) throws IOException, ParseException{
		System.out.println(location_DTO.toString());
		ObjectMapper mapper = new ObjectMapper();
		List<KMA_short_term_DTO> list_VilageFcst_DTO = new ArrayList<KMA_short_term_DTO>();
		KMA_location_DTO loca = location_service.get_code(location_DTO.getAreacode());
		loca.setBaseDate(location_DTO.getBaseDate());
		loca.setBaseTime(location_DTO.getBaseTime());
		loca.setService_name(location_DTO.getService_name());
		
		JSONArray jArray = api_to_JSon(loca);
		
		list_VilageFcst_DTO = mapper.readValue(jArray.toString(), new TypeReference<List<KMA_short_term_DTO>>() {});
		
		short_term_VilageFcst_service.insert_short_term_VilageFcst(list_VilageFcst_DTO);
		
		return new ResponseEntity<KMA_location_DTO>(loca, HttpStatus.OK);
	}
	
	@GetMapping(value = "/short/VilageFcst", produces = "application/json;charset=UTF-8")
	public ResponseEntity<List<KMA_short_term_DTO>> get_short_term_VilageFcst(@RequestParam String baseDate,@RequestParam String baseTime,@RequestParam String nx,@RequestParam String ny) {
		KMA_short_term_DTO short_term_VilageFcst_DTO = new KMA_short_term_DTO();
		short_term_VilageFcst_DTO.setBaseDate(baseDate);
		short_term_VilageFcst_DTO.setBaseTime(baseTime);
		short_term_VilageFcst_DTO.setNx(nx);
		short_term_VilageFcst_DTO.setNy(ny);
		
		return new ResponseEntity<List<KMA_short_term_DTO>>(short_term_VilageFcst_service.get_short_term_VilageFcst(short_term_VilageFcst_DTO), HttpStatus.OK);
		
	}
	
	
	//초단기 예보
	@PostMapping(value = "/short/UltraSrtFcst", produces = "application/json;charset=UTF-8")
	public ResponseEntity<KMA_location_DTO> insert_short_term_UltraSrtFcst(KMA_location_DTO location_DTO) throws IOException, ParseException{
		System.out.println(location_DTO.toString());
		ObjectMapper mapper = new ObjectMapper();
		List<KMA_short_term_DTO> list_UltraSrtFcst_DTO = new ArrayList<KMA_short_term_DTO>();
		KMA_location_DTO loca = location_service.get_code(location_DTO.getAreacode());
		loca.setBaseDate(location_DTO.getBaseDate());
		loca.setBaseTime(location_DTO.getBaseTime());
		loca.setService_name(location_DTO.getService_name());
		
		JSONArray jArray = api_to_JSon(loca);
		
		list_UltraSrtFcst_DTO = mapper.readValue(jArray.toString(), new TypeReference<List<KMA_short_term_DTO>>() {});
		
		short_term_VilageFcst_service.insert_short_term_UltraSrtFcst(list_UltraSrtFcst_DTO);
		
		return new ResponseEntity<KMA_location_DTO>(loca, HttpStatus.OK);
	}
	
	@GetMapping(value = "/short/UltraSrtFcst", produces = "application/json;charset=UTF-8")
	public ResponseEntity<List<KMA_short_term_DTO>> get_short_term_UltraSrtFcst(@RequestParam String baseDate,@RequestParam String baseTime,@RequestParam String nx,@RequestParam String ny) {
		KMA_short_term_DTO short_term_UltraSrtFcst_DTO = new KMA_short_term_DTO();
		short_term_UltraSrtFcst_DTO.setBaseDate(baseDate);
		short_term_UltraSrtFcst_DTO.setBaseTime(baseTime);
		short_term_UltraSrtFcst_DTO.setNx(nx);
		short_term_UltraSrtFcst_DTO.setNy(ny);
		
		return new ResponseEntity<List<KMA_short_term_DTO>>(short_term_VilageFcst_service.get_short_term_UltraSrtFcst(short_term_UltraSrtFcst_DTO), HttpStatus.OK);
		
	}
	
}
