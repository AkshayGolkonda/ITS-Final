package com.its.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.its.dto.Candidate;
import com.its.dto.InterviewSchedule;
import com.its.dto.PanelMember;
import com.its.service.AdminService;

@WebMvcTest(AdminController.class)
class AdminControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	AdminController adminController;
	
	@MockBean
	AdminService adminService;
	
	@Autowired
	ObjectMapper objectMapper;
	
	
	//Service 1
		@Test
		public void testAddCandidate() throws Exception{
			
			Candidate candidate=new Candidate();
			candidate.setCandidateName("sample");
			HttpHeaders httpHeaders=new HttpHeaders();
			httpHeaders.set("Authorization", "AG66200");
			
			when(this.adminService.addCandidate(candidate, "AG66200")).thenReturn(candidate);
			
			MvcResult mvcResult=this.mockMvc.perform(post("http://localhost:7000/its-admin/candidate")
					.contentType("application/json")
					.content(objectMapper.writeValueAsString(candidate))
					.headers(httpHeaders)
					)
					.andExpect(status().isOk())
					.andExpect(content().string(containsString("sample")))
					.andReturn();
			
			String response=mvcResult.getResponse().getContentAsString();
			assertEquals(response.contains("sample"),true);
		}
		
		
		
		//Service 2.1
		@Test
		public void testViewCandidateById() throws Exception{
			
			Candidate candidate=new Candidate();
			candidate.setCandidateName("sample");
			HttpHeaders httpHeaders=new HttpHeaders();
			httpHeaders.set("Authorization", "AG66200");
			
			when(this.adminService.viewCandidateById(1, "AG66200")).thenReturn(candidate);
			
			MvcResult mvcResult=this.mockMvc.perform(get("http://localhost:7000/its-admin/candidate/"+1)
					.headers(httpHeaders)
					)
					.andExpect(status().isOk())
					.andExpect(content().string(containsString("sample")))
					.andReturn();
			
			String response=mvcResult.getResponse().getContentAsString();
			assertEquals(response.contains("sample"),true);
		}
		
		//Service 2.2
		@Test
		public void testViewAllCandidates() throws Exception {
			List<Candidate> candidateList=new ArrayList<>();
			candidateList.add(new Candidate());
			candidateList.add(new Candidate());
			HttpHeaders httpHeaders=new HttpHeaders();
			httpHeaders.set("Authorization", "AG66200");
			
			when(this.adminService.viewAllCandidates("AG66200")).thenReturn(candidateList);
			
			MvcResult mvcResult=this.mockMvc.perform(get("http://localhost:7000/its-admin/candidate")
					.headers(httpHeaders)
					)
					.andExpect(status().isOk())
					.andReturn();
			
			String  response=mvcResult.getResponse().getContentAsString();
			assertEquals(response.contains("location"),true);
		}
		
		/*
		//Service 3
			@Test
			public void testShareData() throws Exception{
				
				Candidate candidate=new Candidate();
				candidate.setCandidateName("sample");
				HttpHeaders httpHeaders=new HttpHeaders();
				httpHeaders.set("Authorization", "AG66200");
				
				when(this.adminService.shareData(1, "AG66200")).thenReturn("success");
				
				MvcResult mvcResult=this.mockMvc.perform(post("http://localhost:7000/its-admin/candidate/share/"+1)
						.headers(httpHeaders)
						)
						.andExpect(status().isOk())
						.andReturn();
				
				String response=mvcResult.getResponse().getContentAsString();
				assertEquals(response.contains("success"),true);
			}
			
			*/
		
		
		//4
		@Test
		public void testScheduleInterview() throws Exception{
			
			InterviewSchedule interviewSchedule=new InterviewSchedule();
			interviewSchedule.setFinalStatus("PASSED");;
			HttpHeaders httpHeaders=new HttpHeaders();
			httpHeaders.set("Authorization", "AG66200");
			
			when(this.adminService.scheduleInterview(interviewSchedule, "AG66200")).thenReturn(interviewSchedule);
			
			MvcResult mvcResult=this.mockMvc.perform(post("http://localhost:7000/its-admin/schedule")
					.contentType("application/json")
					.content(objectMapper.writeValueAsString(interviewSchedule))
					.headers(httpHeaders)
					)
					.andExpect(status().isOk())
					.andExpect(content().string(containsString("PASSED")))
					.andReturn();
			
			String response=mvcResult.getResponse().getContentAsString();
			assertEquals(response.contains("PASSED"),true);
		}
		
		
		
		
		//5
		@Test
		public void testUpdateSchedule() throws Exception{
			
			InterviewSchedule interviewSchedule=new InterviewSchedule();
			interviewSchedule.setFinalStatus("PASSED");
			HttpHeaders httpHeaders=new HttpHeaders();
			httpHeaders.set("Authorization", "AG66200");
			
			when(this.adminService.updateSchedule(1,interviewSchedule,"AG66200")).thenReturn(interviewSchedule);
			
			MvcResult mvcResult=this.mockMvc.perform(put("http://localhost:7000/its-admin/schedule/"+1)
					.contentType("application/json")
					.content(objectMapper.writeValueAsString(interviewSchedule))
					.headers(httpHeaders)
					)
					.andExpect(status().isOk())
					.andExpect(content().string(containsString("PASSED")))
					.andReturn();
			
			String response=mvcResult.getResponse().getContentAsString();
			assertEquals(response.contains("PASSED"),true);
		}
		
		
		
		//5.1
		@Test
		public void testGetInterviewScheduleById() throws Exception{
			
			InterviewSchedule interviewSchedule=new InterviewSchedule();
			interviewSchedule.setFinalStatus("PASSED");
			HttpHeaders httpHeaders=new HttpHeaders();
			httpHeaders.set("Authorization", "AG66200");
			
			when(this.adminService.getInterviewScheduleById(1, "AG66200")).thenReturn(interviewSchedule);
			
			MvcResult mvcResult=this.mockMvc.perform(get("http://localhost:7000/its-admin/schedule/"+1)
					.headers(httpHeaders)
					)
					.andExpect(status().isOk())
					.andExpect(content().string(containsString("PASSED")))
					.andReturn();
			
			String response=mvcResult.getResponse().getContentAsString();
			assertEquals(response.contains("PASSED"),true);
		}
		
		
		//6
		@Test
		public void testDeleteSchedule() throws Exception{
			
			HttpHeaders httpHeaders=new HttpHeaders();
			httpHeaders.set("Authorization", "AG66200");
			
			when(this.adminService.deleteSchedule(1, "AG66200")).thenReturn(true);
			
			MvcResult mvcResult=this.mockMvc.perform(delete("http://localhost:7000/its-admin/schedule/"+1)
					.headers(httpHeaders)
					)
					.andExpect(status().isOk())
					.andReturn();
			
			String response=mvcResult.getResponse().getContentAsString();
			assertEquals(response.contains("true"),true);
		}
		
		
		
		//7
		@Test
		public void testAddPanelMember() throws Exception{
			
			PanelMember panelMember=new PanelMember();
			panelMember.setName("sample");
			HttpHeaders httpHeaders=new HttpHeaders();
			httpHeaders.set("Authorization", "AG66200");
			
			when(this.adminService.addPanelMember(panelMember, "AG66200")).thenReturn(panelMember);
			
			MvcResult mvcResult=this.mockMvc.perform(post("http://localhost:7000/its-admin/panel")
					.contentType("application/json")
					.content(objectMapper.writeValueAsString(panelMember))
					.headers(httpHeaders)
					)
					.andExpect(status().isOk())
					.andExpect(content().string(containsString("sample")))
					.andReturn();
			
			String response=mvcResult.getResponse().getContentAsString();
			assertEquals(response.contains("sample"),true);
		}
		
		
		
		
		//Service 8
		@Test
		public void testSearchAdvByText() throws Exception {
			List<PanelMember> panelList=new ArrayList<>();
			panelList.add(new PanelMember());
			panelList.add(new PanelMember());
			when(this.adminService.searchEmployee(null, "hr")).thenReturn(panelList);
			
			MvcResult mvcResult=this.mockMvc.perform(get("http://localhost:7000/its-admin/panel/search")
					.param("name", "hr")
					)
					.andExpect(status().isOk())
					.andReturn();
			
			String  response=mvcResult.getResponse().getContentAsString();
			assertEquals(response.contains("name"),true);
		}
		
		
		//9.1
		@Test
		public void testDeleteTechMember() throws Exception{
			
			//HttpHeaders httpHeaders=new HttpHeaders();
			//httpHeaders.set("Authorization", "AG66200");
			
			when(this.adminService.deleteTechMember(1)).thenReturn(true);
			
			MvcResult mvcResult=this.mockMvc.perform(delete("http://localhost:7000/its-admin/panel/tech/"+1)
					)
					.andExpect(status().isOk())
					.andReturn();
			
			String response=mvcResult.getResponse().getContentAsString();
			assertEquals(response.contains("true"),true);
		}
		
		//9.2
		@Test
		public void testDeleteHRMember() throws Exception{
			//HttpHeaders httpHeaders=new HttpHeaders();
			//httpHeaders.set("Authorization", "AG66200");
			
			when(this.adminService.deleteHRMember(1)).thenReturn(true);
			
			MvcResult mvcResult=this.mockMvc.perform(delete("http://localhost:7000/its-admin/panel/hr/"+1)
					)
					.andExpect(status().isOk())
					.andReturn();
			
			String response=mvcResult.getResponse().getContentAsString();
			assertEquals(response.contains("true"),true);
		}
		
		
		//10
		@Test
		public void testGetAllPanelMembers() throws Exception {
			List<PanelMember> panelList=new ArrayList<>();
			panelList.add(new PanelMember());
			panelList.add(new PanelMember());
			HttpHeaders httpHeaders=new HttpHeaders();
			httpHeaders.set("Authorization", "AG66200");
			
			when(this.adminService.getAllPanelMembers("AG66200")).thenReturn(panelList);
			
			MvcResult mvcResult=this.mockMvc.perform(get("http://localhost:7000/its-admin/panel")
					.headers(httpHeaders)
					)
					.andExpect(status().isOk())
					.andReturn();
			
			String  response=mvcResult.getResponse().getContentAsString();
			assertEquals(response.contains("type"),true);
		}
		
		

}
