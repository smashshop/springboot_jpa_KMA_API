package com.example.demo.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.dto.KMA_mid_term_MidLandFcst_DTO;
import com.example.demo.domain.dto.KMA_mid_term_MidSeaFcst_DTO;
import com.example.demo.domain.dto.KMA_mid_term_MidTa_DTO;
import com.example.demo.domain.dto.KMA_mid_term_outlook_DTO;
import com.example.demo.domain.dto.KMA_mid_term_serviceinfo_DTO;
import com.example.demo.service.KMA_mid_term_service;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/KMA")
public class KMA_mid_rest_controller {
	
	private KMA_mid_term_service kma_mid_term_service;
	
	@Autowired
	public KMA_mid_rest_controller(KMA_mid_term_service kma_mid_term_service) {
		this.kma_mid_term_service = kma_mid_term_service;
	}
	
	//API 데이터 받아와서 JSONArray로 반환
	public JSONArray api_to_JSon(KMA_mid_term_serviceinfo_DTO serviceinfo) throws IOException, ParseException{
		
		StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/MidFcstInfoService/" + serviceinfo.getService_name()); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + "iC9YhAW%2F3B8TgTgKhHUr5hl9HZDjAWEcZdhSXzJKn7%2F2x6eZaFFoBjmZt%2BN4DheeDJwBo0CPHme3LG3R8OmLKQ%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /*요청자료형식(XML/JSON)Default: XML*/
        urlBuilder.append("&" + URLEncoder.encode(serviceinfo.getLoca_id(),"UTF-8") + "=" + URLEncoder.encode(serviceinfo.getLoca(), "UTF-8")); /*108 전국, 109 서울, 인천, 경기도 등 (활용가이드 하단 참고자료 참조)*/
        urlBuilder.append("&" + URLEncoder.encode("tmFc","UTF-8") + "=" + URLEncoder.encode(serviceinfo.getTmFc(), "UTF-8")); /*-일 2회(06:00,18:00)회 생성 되며 발표시각을 입력 YYYYMMDD0600 (1800)-최근 24시간 자료만 제공*/
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

	
	//중기 전망예보
	@PostMapping(value = "/mid/getMidFcst")
	public void insert_KMA_mid_term_outlook(KMA_mid_term_serviceinfo_DTO serviceinfo) throws IOException, ParseException{
      
		KMA_mid_term_outlook_DTO kma_mid_term_outlook_DTO = new KMA_mid_term_outlook_DTO();
		ObjectMapper mapper = new ObjectMapper();
		
		JSONArray jArray = api_to_JSon(serviceinfo);
    	for(int i = 0; i < jArray.size(); i++) {
    		
    		JSONObject item = (JSONObject)jArray.get(i);
    		
    		kma_mid_term_outlook_DTO = mapper.readValue(item.toString(), KMA_mid_term_outlook_DTO.class);
    		kma_mid_term_outlook_DTO.setWfSv(kma_mid_term_outlook_DTO.getWfSv().replaceAll("\n", "<br>")); //줄바꿈 표현 위해 br태그로 변경
    		kma_mid_term_outlook_DTO.setRegId(serviceinfo.getLoca());
    		kma_mid_term_outlook_DTO.setTmFc(serviceinfo.getTmFc());
    	}	
    	
    	kma_mid_term_service.insert_KMA_mid_term_outlook(kma_mid_term_outlook_DTO);
    	
	}
	
	@GetMapping(value = "/mid/getMidFcst", produces = "application/json;charset=UTF-8")
	public ResponseEntity<KMA_mid_term_outlook_DTO> get_KMA_mid_term_outlook(String loca, String time){
		KMA_mid_term_outlook_DTO kma_mid_term_outlook_DTO = new KMA_mid_term_outlook_DTO();
		kma_mid_term_outlook_DTO.setRegId(loca);
		kma_mid_term_outlook_DTO.setTmFc(time);
		return new ResponseEntity<KMA_mid_term_outlook_DTO>(kma_mid_term_service.get_KMA_mid_term_outlook(kma_mid_term_outlook_DTO), HttpStatus.OK);
	}
	
	
	//중기 육상예보
	@PostMapping(value = "/mid/getMidLandFcst")
	public void insert_KMA_mid_term_MidLandFcst(KMA_mid_term_serviceinfo_DTO serviceinfo) throws IOException, ParseException{
	
		KMA_mid_term_MidLandFcst_DTO kma_mid_term_MidLandFcst_DTO = new KMA_mid_term_MidLandFcst_DTO();
		ObjectMapper mapper = new ObjectMapper();
		
		JSONArray jArray = api_to_JSon(serviceinfo);
    	for(int i = 0; i < jArray.size(); i++) {
    		
    		JSONObject item = (JSONObject)jArray.get(i);
    		kma_mid_term_MidLandFcst_DTO = mapper.readValue(item.toString(), KMA_mid_term_MidLandFcst_DTO.class);
    		kma_mid_term_MidLandFcst_DTO.setRegId(serviceinfo.getLoca());
    		kma_mid_term_MidLandFcst_DTO.setTmFc(serviceinfo.getTmFc());
    	}
    	
    	kma_mid_term_service.insert_KMA_mid_term_MidLandFcst(kma_mid_term_MidLandFcst_DTO);
	}
	
	@GetMapping(value = "/mid/getMidLandFcst", produces = "application/json;charset=UTF-8")
	public ResponseEntity<KMA_mid_term_MidLandFcst_DTO> get_KMA_mid_term_MidLandFcst(String loca, String time){
		KMA_mid_term_MidLandFcst_DTO kma_mid_term_MidLandFcst_DTO = new KMA_mid_term_MidLandFcst_DTO();
		kma_mid_term_MidLandFcst_DTO.setRegId(loca);
		kma_mid_term_MidLandFcst_DTO.setTmFc(time);
		return new ResponseEntity<KMA_mid_term_MidLandFcst_DTO>(kma_mid_term_service.get_KMA_mid_term_MidLandFcst(kma_mid_term_MidLandFcst_DTO), HttpStatus.OK);
	}
	
	
	//중기 기온
	@PostMapping(value = "/mid/getMidTa")
	public void insert_KMA_mid_term_MidTa(KMA_mid_term_serviceinfo_DTO serviceinfo) throws IOException, ParseException{
		
		KMA_mid_term_MidTa_DTO kma_mid_term_MidTa_DTO = new KMA_mid_term_MidTa_DTO();
		ObjectMapper mapper = new ObjectMapper();
		
		JSONArray jArray = api_to_JSon(serviceinfo);
    	for(int i = 0; i < jArray.size(); i++) {
    		
    		JSONObject item = (JSONObject)jArray.get(i);
    		kma_mid_term_MidTa_DTO = mapper.readValue(item.toString(), KMA_mid_term_MidTa_DTO.class);
    		kma_mid_term_MidTa_DTO.setRegId(serviceinfo.getLoca());
    		kma_mid_term_MidTa_DTO.setTmFc(serviceinfo.getTmFc());
    	}
    	
    	kma_mid_term_service.insert_KMA_mid_term_MidTa(kma_mid_term_MidTa_DTO);
	}
	
	@GetMapping(value = "/mid/getMidTa", produces = "application/json;charset=UTF-8")
	public ResponseEntity<KMA_mid_term_MidTa_DTO> get_KMA_mid_term_MidTa(String loca, String time){
		KMA_mid_term_MidTa_DTO kma_mid_term_MidTa_DTO = new KMA_mid_term_MidTa_DTO();
		kma_mid_term_MidTa_DTO.setRegId(loca);
		kma_mid_term_MidTa_DTO.setTmFc(time);
		return new ResponseEntity<KMA_mid_term_MidTa_DTO>(kma_mid_term_service.get_KMA_mid_term_MidTa(kma_mid_term_MidTa_DTO), HttpStatus.OK);
	}
	
	
	//중기 해상예보
	@PostMapping(value = "/mid/getMidSeaFcst")
	public void insert_kma_mid_term_MidSeaFcsts(KMA_mid_term_serviceinfo_DTO serviceinfo) throws IOException, ParseException{
		
		KMA_mid_term_MidSeaFcst_DTO kma_mid_term_MidSeaFcst_DTO = new KMA_mid_term_MidSeaFcst_DTO();
		ObjectMapper mapper = new ObjectMapper();
		
		JSONArray jArray = api_to_JSon(serviceinfo);
    	for(int i = 0; i < jArray.size(); i++) {
    		
    		JSONObject item = (JSONObject)jArray.get(i);
    		kma_mid_term_MidSeaFcst_DTO = mapper.readValue(item.toString(), KMA_mid_term_MidSeaFcst_DTO.class);
    		kma_mid_term_MidSeaFcst_DTO.setRegId(serviceinfo.getLoca());
    		kma_mid_term_MidSeaFcst_DTO.setTmFc(serviceinfo.getTmFc());
    	}
    	
    	kma_mid_term_service.insert_KMA_mid_term_MidSeaFcst(kma_mid_term_MidSeaFcst_DTO);
	}
	
	@GetMapping(value = "/mid/getMidSeaFcst", produces = "application/json;charset=UTF-8")
	public ResponseEntity<KMA_mid_term_MidSeaFcst_DTO> get_kma_mid_term_MidSeaFcst(String loca, String time){
		KMA_mid_term_MidSeaFcst_DTO kma_mid_term_MidSeaFcst_DTO = new KMA_mid_term_MidSeaFcst_DTO();
		kma_mid_term_MidSeaFcst_DTO.setRegId(loca);
		kma_mid_term_MidSeaFcst_DTO.setTmFc(time);
		return new ResponseEntity<KMA_mid_term_MidSeaFcst_DTO>(kma_mid_term_service.get_KMA_mid_term_MidSeaFcst(kma_mid_term_MidSeaFcst_DTO), HttpStatus.OK);
	}
}
