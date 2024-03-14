package com.springbatch.service;

import java.util.HashMap;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SecondJobScheduler {
	@Autowired
	private JobLauncher JobLauncher;

	@Autowired
	@Qualifier("firstJob")
	private Job firstJob;

	@Autowired
	@Qualifier("secondJob")
	private Job secondJob;

	
	@Scheduled(cron = "0 0/1 * 1/1 * ?")
	public void secondJobStarter() {
		
		JobExecution JobExecution = null;
		HashMap<String, JobParameter> jobParams = new HashMap<>();
		jobParams.put("currentTime", new JobParameter(System.currentTimeMillis()));

		JobParameters jobParameters = new JobParameters(jobParams);
		try {
				JobExecution = JobLauncher.run(secondJob, jobParameters);

			System.out.println("job execution ID" + JobExecution.getId());
		} catch (Exception e) {
			System.out.println("exception while starting job" + e.getMessage());
		}
		
	}

}
