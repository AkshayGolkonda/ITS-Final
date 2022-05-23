package com.its.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.its.dto.Candidate;
import com.its.dto.InterviewSchedule;
import com.its.dto.PanelMember;
import com.its.service.AdminService;

@RestController
@RequestMapping("/its-admin")
@CrossOrigin(origins="*")
public class AdminController {
	
	@Autowired
	AdminService adminService;

	//1
	@PostMapping(value="/candidate", consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Candidate> addCandidate(@RequestBody Candidate candidate,@RequestHeader("Authorization") String authToken) {
		return new ResponseEntity<Candidate>(adminService.addCandidate(candidate,authToken),HttpStatus.OK);
	}
	
	//2.1
	@GetMapping(value="/candidate/{id}", produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public Candidate viewCandidateById(@PathVariable("id") int id,@RequestHeader("Authorization") String authToken) {
		return adminService.viewCandidateById(id,authToken);
	}
	
	//2.2
	@GetMapping(value="/candidate", produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public List<Candidate> viewAllCandidates(@RequestHeader("Authorization") String authToken){
		return adminService.viewAllCandidates(authToken);
	}
	
	//3
	@PostMapping(value="/share/{id}", consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public String shareData(@PathVariable("id")int id,@RequestHeader("Authorization") String authToken) {
		return adminService.shareData(id,authToken);
	}
	
	//4
	@PostMapping(value="/schedule", consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public InterviewSchedule scheduleInterview(@RequestBody InterviewSchedule interviewSchedule,@RequestHeader("Authorization") String authToken) {
		return adminService.scheduleInterview(interviewSchedule,authToken);
	}
	
	//5
	@PutMapping(value="/schedule/{id}", consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public InterviewSchedule updateSchedule(@PathVariable("id") int id, @RequestBody InterviewSchedule interviewSchedule,@RequestHeader("Authorization") String authToken) {
		return adminService.updateSchedule(id, interviewSchedule,authToken);
	}
	
	//(additional endpoint)5.1
	@GetMapping(value="/schedule/{id}", produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public InterviewSchedule getInterviewScheduleById(@PathVariable("id")int id,@RequestHeader("Authorization") String authToken) {
		return adminService.getInterviewScheduleById(id,authToken);
	}
	
	//6
	@DeleteMapping(value="/schedule/{id}")
	public boolean deleteSchedule(@PathVariable("id") int id,@RequestHeader("Authorization") String authToken) {
		return adminService.deleteSchedule(id,authToken);
	}
	
	//7
	@PostMapping(value="/panel", consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public PanelMember addPanelMember(@RequestBody PanelMember panelMember,@RequestHeader("Authorization") String authToken) {
		return adminService.addPanelMember(panelMember,authToken);
	}
	
	//8
	@GetMapping(value="/panel/search", produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public List<PanelMember> searchEmployee(@RequestParam(name="id", required = false)Integer id,
    @RequestParam(name ="name", required = false)String name) {
		return adminService.searchEmployee(id, name);
	}
	
	//9.1
	@DeleteMapping(value="/panel/tech/{id}")
	public boolean deleteTechMember(@PathVariable("id")int id) {
		return adminService.deleteTechMember(id);
	}
	
	//9.2
	@DeleteMapping(value="/panel/hr/{id}")
	public boolean deleteHRMember(@PathVariable("id")int id) {
		return adminService.deleteHRMember(id);
	}
	
	//10
	@GetMapping(value="/panel", produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public List<PanelMember> getAllPanelMembers(@RequestHeader("Authorization") String authToken){
		return adminService.getAllPanelMembers(authToken);
	}
}
