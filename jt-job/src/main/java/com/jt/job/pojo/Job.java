package com.jt.job.pojo;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="job")
public class Job {
	@Id
	  private String jobId; //id
	  private String prov; // 网站来源
	  private String industry; //行业
	  private String position; //职位
	  private Integer recruitingNum; //招聘人数
	  private Integer workExperience; //工作年限
	  private String workAddress; //工作地点
	  private Integer education; //招聘学历 0 大专及以下 1 本科 2 研究生 3 博士
	  private String pay; // 薪资  
		public String getJobId() {
			return jobId;
		}
		public void setJobId(String jobId) {
			this.jobId = jobId;
		}
		public String getProv() {
			return prov;
		}
		public void setProv(String prov) {
			this.prov = prov;
		}
		public String getIndustry() {
			return industry;
		}
		public void setIndustry(String industry) {
			this.industry = industry;
		}
		public String getPosition() {
			return position;
		}
		public void setPosition(String position) {
			this.position = position;
		}
		public Integer getRecruitingNum() {
			return recruitingNum;
		}
		public void setRecruitingNum(Integer recruitingNum) {
			this.recruitingNum = recruitingNum;
		}
		public Integer getWorkExperience() {
			return workExperience;
		}
		public void setWorkExperience(Integer workExperience) {
			this.workExperience = workExperience;
		}
		public String getWorkAddress() {
			return workAddress;
		}
		public void setWorkAddress(String workAddress) {
			this.workAddress = workAddress;
		}
		public Integer getEducation() {
			return education;
		}
		public void setEducation(Integer education) {
			this.education = education;
		}
		public String getPay() {
			return pay;
		}
		public void setPay(String pay) {
			this.pay = pay;
		}

		

}
