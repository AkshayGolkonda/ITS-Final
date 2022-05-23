package com.its.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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
public class AdminServiceImpl implements AdminService{
	
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
	PanelServiceDelegate panelServiceDelegate;
	
	@Autowired
	UserServiceDelegate userServiceDelegate;

	//1
	@Override
	public Candidate addCandidate(Candidate candidate,String authToken) {
		if(userServiceDelegate.isTokenValid(authToken)) {
			CandidateEntity candidateEntity=convertCandidateDtoIntoEntity(candidate);
			candidateRepo.save(candidateEntity);
			return convertCandidateEntityIntoDto(candidateEntity);
		}
		return null;
	}

	//2.1
	@Override
	public Candidate viewCandidateById(int id,String authToken) {
		if(userServiceDelegate.isTokenValid(authToken)) {
			Optional<CandidateEntity> opCandidateEntity=candidateRepo.findById(id);
			if(opCandidateEntity.isPresent()) {
				return convertCandidateEntityIntoDto(opCandidateEntity.get());
			}
			return null;
		}
		return null;
	}
	
	//2.2
	@Override
	public List<Candidate> viewAllCandidates(String authToken) {
		if(userServiceDelegate.isTokenValid(authToken)) {
			List<CandidateEntity> candidateEntities=candidateRepo.findAll();
			//if(candidateEntities.isEmpty()) { throw new Exception; }
			List<Candidate> candidates=new ArrayList<Candidate>();
			for(CandidateEntity candidateEntity:candidateEntities) {
				candidates.add(convertCandidateEntityIntoDto(candidateEntity));
			}
			return candidates;
		}
		return null;
	}
	
	//3
	@Override
	public String shareData(int id,String authToken) {
		if(userServiceDelegate.isTokenValid(authToken)) {
			InterviewSchedule interviewSchedule=getInterviewScheduleById(id,authToken);
			Candidate candidate=viewCandidateById(interviewSchedule.getCandidateId(),authToken);
			if(interviewSchedule!=null && candidate!=null) {
				panelServiceDelegate.shareCandidateWithPanel(candidate);
				panelServiceDelegate.shareScheduleWithPanel(interviewSchedule);
				return "Data Shared successfully with Panel";
			}
			return "Data Share Failed";
		}
		return null;
	}

	//4
	@Override
	public InterviewSchedule scheduleInterview(InterviewSchedule interviewSchedule,String authToken) {
		if(userServiceDelegate.isTokenValid(authToken)) {
			InterviewScheduleEntity interviewScheduleEntity=convertInterviewScheduleDtoIntoEntity(interviewSchedule);
			interviewScheduleRepo.save(interviewScheduleEntity);
			return convertInterviewScheduleEntityIntoDto(interviewScheduleEntity);
		}
		return null;
	}

	//5
	@Override
	public InterviewSchedule updateSchedule(int id, InterviewSchedule interviewSchedule,String authToken) {
		if(userServiceDelegate.isTokenValid(authToken)) {
			Optional<InterviewScheduleEntity> opInterviewScheduleEntity=interviewScheduleRepo.findById(id);
			if(opInterviewScheduleEntity.isPresent()) {
				InterviewScheduleEntity interviewScheduleEntity=opInterviewScheduleEntity.get();
				interviewScheduleEntity.setCandidateId(interviewSchedule.getCandidateId());
				interviewScheduleEntity.setInterviewDate(interviewSchedule.getInterviewDate());
				interviewScheduleRepo.save(interviewScheduleEntity);
				return convertInterviewScheduleEntityIntoDto(interviewScheduleEntity);
			}
			return null;
		}
		return null;
	}
	
	//additional endpoint
	@Override
	public InterviewSchedule getInterviewScheduleById(int id,String authToken) {
		if(userServiceDelegate.isTokenValid(authToken)) {
			Optional<InterviewScheduleEntity> opInterviewScheduleEntity=interviewScheduleRepo.findById(id);
			if(opInterviewScheduleEntity.isPresent()) {
				return convertInterviewScheduleEntityIntoDto(opInterviewScheduleEntity.get());
			}
			return null;
		}
		return null;
	}

	//6
	@Override
	public boolean deleteSchedule(int id,String authToken) {
		if(userServiceDelegate.isTokenValid(authToken)) {
			if(interviewScheduleRepo.existsById(id)) {
				interviewScheduleRepo.deleteById(id);
				return true;
			}
			return false;
		}
		return false;
	}

	//7
	@Override
	public PanelMember addPanelMember(PanelMember panelMember,String authToken) {
		if(userServiceDelegate.isTokenValid(authToken)) {
			PanelMemberEntity panelMemberEntity=convertPanelMemberDtoIntoEntity(panelMember);
			memberRepo.save(panelMemberEntity);
			return convertPanelMemberEntityIntoDto(panelMemberEntity);
		}
		return null;
	}

	//8
	@Override
	public List<PanelMember> searchEmployee(Integer id, String name) {
		CriteriaBuilder critertiaBuilder=entityManager.getCriteriaBuilder();
		CriteriaQuery<PanelMemberEntity> criteriaQuery=critertiaBuilder.createQuery(PanelMemberEntity.class);
		Root<PanelMemberEntity> rootEntity=criteriaQuery.from(PanelMemberEntity.class);
		
		Predicate predicateId= critertiaBuilder.and();
		Predicate predicateName= critertiaBuilder.and();
		Predicate predicateFinal = critertiaBuilder.and();
		
		if(name!=null && !"".equalsIgnoreCase(name)) {
			predicateName=critertiaBuilder.like(rootEntity.get("name"),"%"+name+"%");
			
		}
		if(id!=null) {
			predicateId=critertiaBuilder.equal(rootEntity.get("employeeId"),id);
		}
		
		predicateFinal=critertiaBuilder.and(predicateId,predicateName);
		criteriaQuery.where(predicateFinal);
		
		TypedQuery<PanelMemberEntity> typedQuery = entityManager.createQuery(criteriaQuery);
		List<PanelMemberEntity> panelEntityList=typedQuery.getResultList();
		//write a convert and return advertise list here
		List<PanelMember> panelList=new ArrayList<>();
		for(PanelMemberEntity p:panelEntityList)
			panelList.add(convertPanelMemberEntityIntoDto(p));
		
		return panelList;
	}

	//9.1
	@Override
	public boolean deleteTechMember(int id) {
		if(memberRepo.existsById(id)) {
			if(memberRepo.getById(id).getType().equalsIgnoreCase("tech")) {
				memberRepo.deleteById(id);
				return true;
			}
			return false;
		}
		return false;
	}

	//9.2
	@Override
	public boolean deleteHRMember(int id) {
		if(memberRepo.existsById(id)) {
			if(memberRepo.getById(id).getType().equalsIgnoreCase("hr")) {
				memberRepo.deleteById(id);
				return true;
			}
			return false;
		}
		return false;
	}

	//10
	@Override
	public List<PanelMember> getAllPanelMembers(String authToken) {
		if(userServiceDelegate.isTokenValid(authToken)) {
			List<PanelMemberEntity> panelMemberEntities=memberRepo.findAll();
			//if(panelMemberEntities.isEmpty()) { throw new Exception;}
			List<PanelMember> panelMembers=new ArrayList<PanelMember>();
			for(PanelMemberEntity panelMemberEntity:panelMemberEntities) {
				panelMembers.add(convertPanelMemberEntityIntoDto(panelMemberEntity));
			}
			return panelMembers;
		}
		return null;
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

}
