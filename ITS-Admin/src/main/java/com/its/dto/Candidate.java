package com.its.dto;

public class Candidate {

	private int candidateId;
	private String candidateName;
	private String primarySkills;
	private String secondarySkills;
	private int experience;
	private String qualification;
	private String designation;
	private int noticePeriod;
	private String location;
	public Candidate(int candidateId, String candidateName, String primarySkills, String secondarySkills,
			int experience, String qualification, String designation, int noticePeriod, String location) {
		super();
		this.candidateId = candidateId;
		this.candidateName = candidateName;
		this.primarySkills = primarySkills;
		this.secondarySkills = secondarySkills;
		this.experience = experience;
		this.qualification = qualification;
		this.designation = designation;
		this.noticePeriod = noticePeriod;
		this.location = location;
	}
	public Candidate() {
		super();
	}
	public int getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(int candidateId) {
		this.candidateId = candidateId;
	}
	public String getCandidateName() {
		return candidateName;
	}
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	public String getPrimarySkills() {
		return primarySkills;
	}
	public void setPrimarySkills(String primarySkills) {
		this.primarySkills = primarySkills;
	}
	public String getSecondarySkills() {
		return secondarySkills;
	}
	public void setSecondarySkills(String secondarySkills) {
		this.secondarySkills = secondarySkills;
	}
	public int getExperience() {
		return experience;
	}
	public void setExperience(int experience) {
		this.experience = experience;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public int getNoticePeriod() {
		return noticePeriod;
	}
	public void setNoticePeriod(int noticePeriod) {
		this.noticePeriod = noticePeriod;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	@Override
	public String toString() {
		return "Candidate [candidateId=" + candidateId + ", candidateName=" + candidateName + ", primarySkills="
				+ primarySkills + ", secondarySkills=" + secondarySkills + ", experience=" + experience
				+ ", qualification=" + qualification + ", designation=" + designation + ", noticePeriod=" + noticePeriod
				+ ", location=" + location + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
	Candidate candidate = (Candidate) obj;
	if (this.candidateName.equals(candidate.getCandidateName())) {
	return true;
	}
	return false;
	}
	
}
