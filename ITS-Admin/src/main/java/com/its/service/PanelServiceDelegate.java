package com.its.service;

import com.its.dto.Candidate;
import com.its.dto.InterviewSchedule;

public interface PanelServiceDelegate {

	public Candidate shareCandidateWithPanel(Candidate candidate);
	
	public InterviewSchedule shareScheduleWithPanel(InterviewSchedule interviewSchedule);
}
