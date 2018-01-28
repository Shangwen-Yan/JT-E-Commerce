/*import java.util.UUID;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jt.job.mapper.JobMapper;
import com.jt.job.pojo.Job;
import com.jt.job.service.JobService;

public class JobTest {
	@Autowired
	JobService jobService;
	@Test
	public void dbTest(){
		jobService = new JobService();
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
		jobService.save(job);
		

	}

}
*/