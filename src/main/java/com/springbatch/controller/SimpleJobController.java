package com.springbatch.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobExecutionNotRunningException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.NoSuchJobExecutionException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springbatch.service.JobService;

@RestController
@RequestMapping("/api/job")
public class SimpleJobController {

	@Autowired
	private JobLauncher JobLauncher;
	
	@Autowired
	private JobService jobService;
	
	@Autowired
	@Qualifier("firstJob")
	private Job firstJob;
	
	
	@Autowired
	@Qualifier("secondJob")
	private Job secondJob;
	
	@Autowired
	private JobOperator jobOperator;
	
	@GetMapping("/start/{jobName}")
	public String job(@PathVariable String jobName) throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		jobService.startJob(jobName);
		return "job started...";
	}
	
  	@GetMapping("/stop/{executionId}")
	public String stopJob(@PathVariable long executionId) throws NoSuchJobExecutionException, JobExecutionNotRunningException {
		jobOperator.stop(executionId);
	   return "Job Stopped..";	
	}
	
}
