package com.its.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.its.dto.Candidate;
import com.its.dto.InterviewSchedule;
import com.its.service.HRService;

@RestController
@RequestMapping("/its-hr")
@CrossOrigin(origins="*")
public class HRController {
	
	@Autowired
	HRService hrService;

	//1
	@GetMapping(value="/candidate", produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public List<Candidate> viewInterviewCandidates(){
		return hrService.viewInterviewCandidates();
	}
	
	//2
	@PutMapping(value="/interview/{id}", consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public InterviewSchedule giveHRRating(@PathVariable("id") int id, @RequestBody InterviewSchedule interviewSchedule) {
		return hrService.giveHRRating(id, interviewSchedule);
	}
	
	//3
	@GetMapping(value="/candidate/{id}",produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public Candidate getCandidateById(@PathVariable("id")int id) {
		return hrService.getCandidateById(id);
	}
	
	//4
	@DeleteMapping(value="/hr/{id}")
	public boolean resignHRPanelMember(@PathVariable("id")int id) {
		return hrService.resignHRPanelMember(id);
	}
	
	//additional endpoints 1,2 are not required as same implemented in ITS-Tech
	
	//additional endpoint 1
	@PostMapping(value="/candidate/entry", consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public Candidate addCandidate(@RequestBody Candidate candidate) {
		return hrService.addCandidate(candidate);
	}
	
	//additional endpoint 2
	@PostMapping(value="/schedule/entry", consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public InterviewSchedule scheduleInterview(@RequestBody InterviewSchedule interviewSchedule) {
		return hrService.scheduleInterview(interviewSchedule);
	}
	
	//additional endpoint 3
	@GetMapping(value="/interview", produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public List<InterviewSchedule> viewInterviewSchedules(){
		return hrService.viewInterviewSchedules();
	}
}
