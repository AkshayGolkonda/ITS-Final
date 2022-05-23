package com.its.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.its.dto.Candidate;
import com.its.dto.InterviewSchedule;

@Service
public class PanelServiceDelegateImpl implements PanelServiceDelegate{
	
	@Autowired
	RestTemplate restTemplate;

	@Override
	public Candidate shareCandidateWithPanel(Candidate candidate) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<Candidate> entity = new HttpEntity<>(candidate,headers);

		ResponseEntity<Candidate> response=this.restTemplate.postForEntity("http://API-GATEWAY/its-tech/candidate/entry", entity, Candidate.class, candidate);
		return response.getBody();
	}

	@Override
	public InterviewSchedule shareScheduleWithPanel(InterviewSchedule interviewSchedule) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<InterviewSchedule> entity = new HttpEntity<>(interviewSchedule,headers);

		ResponseEntity<InterviewSchedule> response=this.restTemplate.postForEntity("http://API-GATEWAY/its-tech/schedule/entry", entity, InterviewSchedule.class, interviewSchedule);
		return response.getBody();
	}
}
