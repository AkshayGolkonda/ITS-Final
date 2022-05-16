package com.its.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="InterviewScheduleDetails")
public class InterviewScheduleEntity {

	@Id
	private int interviewId;
	private int candidateId;
	private int techRating;
	private int hrRating;
	private String finalStatus;
	private LocalDate interviewDate;
	public InterviewScheduleEntity(int interviewId, int candidateId, int techRating, int hrRating, String finalStatus,
			LocalDate interviewDate) {
		super();
		this.interviewId = interviewId;
		this.candidateId = candidateId;
		this.techRating = techRating;
		this.hrRating = hrRating;
		this.finalStatus = finalStatus;
		this.interviewDate = interviewDate;
	}
	public InterviewScheduleEntity() {
		super();
	}
	public int getInterviewId() {
		return interviewId;
	}
	public void setInterviewId(int interviewId) {
		this.interviewId = interviewId;
	}
	public int getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(int candidateId) {
		this.candidateId = candidateId;
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
		return "InterviewScheduleEntity [interviewId=" + interviewId + ", candidateId=" + candidateId + ", techRating="
				+ techRating + ", hrRating=" + hrRating + ", finalStatus=" + finalStatus + ", interviewDate="
				+ interviewDate + "]";
	}
}
