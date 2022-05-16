package com.its.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.its.dto.Candidate;
import com.its.dto.InterviewSchedule;
import com.its.dto.PanelMember;
import com.its.entity.CandidateEntity;
import com.its.entity.InterviewScheduleEntity;
import com.its.entity.PanelMemberEntity;
import com.its.repository.CandidateRepo;
import com.its.repository.InterviewScheduleRepo;
import com.its.repository.PanelMemberRepo;

@Service
public class TechServiceImpl implements TechService{
	
	@Autowired
	EntityManager entityManager;
	
	@Autowired
	CandidateRepo candidateRepo;
	
	@Autowired
	InterviewScheduleRepo interviewScheduleRepo;
	
	@Autowired
	PanelMemberRepo memberRepo;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	AdminServiceDelegate adminServiceDelegate;

	@Override
	public List<Candidate> viewInterviewCandidates() {
		List<InterviewScheduleEntity> interviewEntities=interviewScheduleRepo.findAll();
		//if(candidateEntities.isEmpty()) { throw new Exception; }
		List<Candidate> candidates=new ArrayList<Candidate>();
		for(InterviewScheduleEntity interviewScheduleEntity:interviewEntities) {
			candidates.add(getCandidateById(interviewScheduleEntity.getCandidateId()));
		}
		return candidates;
	}

	@Override
	public InterviewSchedule giveTechRating(int id, InterviewSchedule interviewSchedule) {
		Optional<InterviewScheduleEntity> opInterviewScheduleEntity=interviewScheduleRepo.findById(id);
		if(opInterviewScheduleEntity.isPresent()) {
			InterviewScheduleEntity interviewScheduleEntity=opInterviewScheduleEntity.get();
			interviewScheduleEntity.setTechRating(interviewSchedule.getTechRating());
			interviewScheduleRepo.save(interviewScheduleEntity);
			return convertInterviewScheduleEntityIntoDto(interviewScheduleEntity);
		}
		return null;
	}

	@Override
	public Candidate getCandidateById(int id) {
		Optional<CandidateEntity> opCandidateEntity=candidateRepo.findById(id);
		if(opCandidateEntity.isPresent()) {
			return convertCandidateEntityIntoDto(opCandidateEntity.get());
		}
		return null;
	}

	@Override
	public boolean resignTechPanelMember(int id) {
		return adminServiceDelegate.isDeleteSuccessful(id);
	}
	
	private CandidateEntity convertCandidateDtoIntoEntity(Candidate candidate) {
		CandidateEntity candidateEntity=modelMapper.map(candidate, CandidateEntity.class);
		return candidateEntity;
	}
	
	private Candidate convertCandidateEntityIntoDto(CandidateEntity candidateEntity) {
		Candidate candidate=modelMapper.map(candidateEntity, Candidate.class);
		return candidate;
	}
	
	private InterviewScheduleEntity convertInterviewScheduleDtoIntoEntity(InterviewSchedule interviewSchedule) {
		InterviewScheduleEntity interviewScheduleEntity=modelMapper.map(interviewSchedule,InterviewScheduleEntity.class);
		return interviewScheduleEntity;
	}
	
	private InterviewSchedule convertInterviewScheduleEntityIntoDto(InterviewScheduleEntity interviewScheduleEntity) {
		InterviewSchedule interviewSchedule=modelMapper.map(interviewScheduleEntity, InterviewSchedule.class);
		return interviewSchedule;
	}
	
	private PanelMemberEntity convertPanelMemberDtoIntoEntity(PanelMember panelMember) {
		PanelMemberEntity panelMemberEntity=modelMapper.map(panelMember, PanelMemberEntity.class);
		return panelMemberEntity;
	}
	
	private PanelMember convertPanelMemberEntityIntoDto(PanelMemberEntity panelMemberEntity) {
		PanelMember panelMember=modelMapper.map(panelMemberEntity, PanelMember.class);
		return panelMember;
	}

	@Override
	public Candidate addCandidate(Candidate candidate) {
		CandidateEntity candidateEntity=convertCandidateDtoIntoEntity(candidate);
		candidateRepo.save(candidateEntity);
		return convertCandidateEntityIntoDto(candidateEntity);
	}

	@Override
	public InterviewSchedule scheduleInterview(InterviewSchedule interviewSchedule) {
		InterviewScheduleEntity interviewScheduleEntity=convertInterviewScheduleDtoIntoEntity(interviewSchedule);
		interviewScheduleRepo.save(interviewScheduleEntity);
		return convertInterviewScheduleEntityIntoDto(interviewScheduleEntity);
	}

	@Override
	public List<InterviewSchedule> viewInterviewSchedules() {
		List<InterviewScheduleEntity> interviewEntities=interviewScheduleRepo.findAll();
		//if(candidateEntities.isEmpty()) { throw new Exception; }
		List<InterviewSchedule> interviewSchedules=new ArrayList<InterviewSchedule>();
		for(InterviewScheduleEntity interviewScheduleEntity:interviewEntities) {
			interviewSchedules.add(convertInterviewScheduleEntityIntoDto(interviewScheduleEntity));
		}
		return interviewSchedules;
	}

}
