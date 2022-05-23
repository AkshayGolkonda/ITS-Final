package com.its.service;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import com.its.dto.Candidate;
import com.its.dto.InterviewSchedule;
import com.its.dto.PanelMember;

public interface AdminService {
	//1
	public Candidate addCandidate(Candidate candidate, String authToken);
	//2.1
	public Candidate viewCandidateById(int id, String authToken);
	//2.2
	public List<Candidate> viewAllCandidates(String authToken);
	//3
	public String shareData(int id, String authToken);
	//4
	public InterviewSchedule scheduleInterview(InterviewSchedule interviewSchedule, String authToken);
	//5
	public InterviewSchedule updateSchedule(int id, InterviewSchedule interviewSchedule, String authToken);
	//additional endpoint
	public InterviewSchedule getInterviewScheduleById(int id, String authToken);
	//6
	public boolean deleteSchedule(int id, String authToken);
	//7
	public PanelMember addPanelMember(PanelMember panelMember, String authToken);
	//8
	public List<PanelMember> searchEmployee(Integer id, String name);
	//9.1
	public boolean deleteTechMember(int id);
	//9.2
	public boolean deleteHRMember(int id);
	//10
	public List<PanelMember> getAllPanelMembers(String authToken);
}
