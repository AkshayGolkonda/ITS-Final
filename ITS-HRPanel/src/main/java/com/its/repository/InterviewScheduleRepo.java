package com.its.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.its.entity.InterviewScheduleEntity;

public interface InterviewScheduleRepo extends JpaRepository<InterviewScheduleEntity, Integer>{

}
