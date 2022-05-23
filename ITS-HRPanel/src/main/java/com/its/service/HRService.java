package com.its.service;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.its.dto.Candidate;
import com.its.dto.InterviewSchedule;

public interface HRService {
	//1
	public List<Candidate> viewInterviewCandidates(String authToken);
	//2
	public InterviewSchedule giveHRRating(int id,InterviewSchedule interviewSchedule, String authToken);
	//3
	public Candidate getCandidateById(int id, String authToken);
	//4
	public boolean resignHRPanelMember(int id, String authToken);
	//additional endpoint 1
	public Candidate addCandidate(Candidate candidate);
	//additional endpoint 2
	public InterviewSchedule scheduleInterview(InterviewSchedule interviewSchedule);
	//additional endpoint 3
	public List<InterviewSchedule> viewInterviewSchedules();
	
}
