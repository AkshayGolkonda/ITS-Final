package com.its.dto;

import java.time.LocalDate;

public class InterviewSchedule {

	private int candidateId;
	private int interviewId;
	private int techRating;
	private int hrRating;
	private String finalStatus;
	private LocalDate interviewDate;
	public InterviewSchedule(int candidateId, int interviewId, int techRating, int hrRating, String finalStatus,
			LocalDate interviewDate) {
		super();
		this.candidateId = candidateId;
		this.interviewId = interviewId;
		this.techRating = techRating;
		this.hrRating = hrRating;
		this.finalStatus = finalStatus;
		this.interviewDate = interviewDate;
	}
	public InterviewSchedule() {
		super();
	}
	public int getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(int candidateId) {
		this.candidateId = candidateId;
	}
	public int getInterviewId() {
		return interviewId;
	}
	public void setInterviewId(int interviewId) {
		this.interviewId = interviewId;
	}
	public int getTechRating() {
		return techRating;
	}
	public void setTechRating(int techRating) {
		this.techRating = techRating;
	}
	public int getHrRating() {
		return hrRating;
	}
	public void setHrRating(int hrRating) {
		this.hrRating = hrRating;
	}
	public String getFinalStatus() {
		return finalStatus;
	}
	public void setFinalStatus(String finalStatus) {
		this.finalStatus = finalStatus;
	}
	public LocalDate getInterviewDate() {
		return interviewDate;
	}
	public void setInterviewDate(LocalDate interviewDate) {
		this.interviewDate = interviewDate;
	}
	@Override
	public String toString() {
		return "InterviewSchedule [candidateId=" + candidateId + ", interviewId=" + interviewId + ", techRating="
				+ techRating + ", hrRating=" + hrRating + ", finalStatus=" + finalStatus + ", interviewDate="
				+ interviewDate + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
	InterviewSchedule interviewSchedule = (InterviewSchedule) obj;
	if (this.finalStatus.equals(interviewSchedule.getFinalStatus())) {
	return true;
	}
	return false;
	}
}
