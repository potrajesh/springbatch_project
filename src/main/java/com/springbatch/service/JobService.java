package com.springbatch.service;

import java.util.HashMap;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class JobService {
	@Autowired
	private JobLauncher JobLauncher;

	@Autowired
	@Qualifier("firstJob")
	private Job firstJob;

	@Autowired
	@Qualifier("secondJob")
	private Job secondJob;

	@Async
	public void startJob(String jobName) {
		JobExecution JobExecution = null;
		HashMap<String, JobParameter> jobParams = new HashMap<>();
		jobParams.put("currentTime", new JobParameter(System.currentTimeMillis()));

		JobParameters jobParameters = new JobParameters(jobParams);
		try {
			if (jobName.equals("First Job")) {
				JobExecution = JobLauncher.run(firstJob, jobParameters);
			} else if (jobName.equals("second Job")) {
				JobExecution = JobLauncher.run(secondJob, jobParameters);

			}
			System.out.println("job execution ID" + JobExecution.getId());
		} catch (Exception e) {
			System.out.println("exception while starting job" + e.getMessage());
		}
	}

}
