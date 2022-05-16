package com.its.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AdminServiceDelegateImpl implements AdminServiceDelegate{

	@Autowired
	RestTemplate restTemplate;
	@Override
	public boolean isDeleteSuccessful(int id) {
		//HttpHeaders headers = new HttpHeaders();
		//headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<Integer> entity = new HttpEntity<>(id);
		
		ResponseEntity<Boolean> response=this.restTemplate.exchange("http://API-GATEWAY/its-admin/panel/hr/{id}", HttpMethod.DELETE, entity, Boolean.class,id);
		return response.getBody();
	}

	
}
