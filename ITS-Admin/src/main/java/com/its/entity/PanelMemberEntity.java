package com.its.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PanelMemberDetails")
public class PanelMemberEntity {

	@Id
	private Integer employeeId;
	private String name;
	private String type;
	private String location;
	public PanelMemberEntity(Integer employeeId, String name, String type, String location) {
		super();
		this.employeeId = employeeId;
		this.name = name;
		this.type = type;
		this.location = location;
	}
	public PanelMemberEntity() {
		super();
	}
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	@Override
	public String toString() {
		return "PanelMemberEntity [employeeId=" + employeeId + ", name=" + name + ", type=" + type + ", location="
				+ location + "]";
	}
	
}
