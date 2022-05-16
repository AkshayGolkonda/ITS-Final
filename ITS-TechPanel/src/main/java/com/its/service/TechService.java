package com.its.service;

import java.util.List;

import com.its.dto.Candidate;
import com.its.dto.InterviewSchedule;

public interface TechService {

	//1
	public List<Candidate> viewInterviewCandidates();
	//2
	public InterviewSchedule giveTechRating(int id, InterviewSchedule interviewSchedule);
	//3
	public Candidate getCandidateById(int id);
	//4
	public boolean resignTechPanelMember(int id);
	//additional endpoint 1
	public Candidate addCandidate(Candidate candidate);
	//additional endpoint 2
	public InterviewSchedule scheduleInterview(InterviewSchedule interviewSchedule);
	public List<InterviewSchedule> viewInterviewSchedules();	
}
