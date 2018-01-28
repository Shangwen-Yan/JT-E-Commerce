package com.jt.job.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.job.mapper.JobMapper;
import com.jt.job.pojo.Job;
import com.jt.job.service.JobService;

@Controller
public class JobController {
	@Autowired
	JobService jobService;

	@RequestMapping("/testdb")
	@ResponseBody
	public String testDb(){
		Job job=new Job();
		job.setJobId(UUID.randomUUID().toString());
		job.setProv("猎聘");
		job.setIndustry("it");
		job.setPosition("engineering");
		job.setRecruitingNum(10);
		job.setWorkExperience(1);
		job.setWorkAddress("北京");
		job.setEducation(1);
		job.setPay("2000-6000");
		
		//jobService.getClass();
//		jobMapper.insert(job);
		jobService.save(job);
		
		return "index";
		
	}
	
	@RequestMapping("/echarts")
	public String echarts(){

		
		return "echartsDemo";
		
	}

}
