package com.its.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.its.entity.CandidateEntity;

public interface CandidateRepo extends JpaRepository<CandidateEntity, Integer>{

}
