package com.its.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.its.entity.PanelMemberEntity;

public interface PanelMemberRepo extends JpaRepository<PanelMemberEntity, Integer>{

}
